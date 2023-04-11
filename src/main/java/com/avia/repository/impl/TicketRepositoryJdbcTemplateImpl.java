package com.avia.repository.impl;

import com.avia.domain.Ticket;
import com.avia.exceptions.EntityNotFoundException;
import com.avia.repository.TicketRepository;
import com.avia.repository.rowmapper.TicketRowMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class TicketRepositoryJdbcTemplateImpl implements TicketRepository {
    private static final Logger logger = Logger.getLogger(TicketRepositoryJdbcTemplateImpl.class);
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final TicketRowMapper ticketRowMapper;

    @Override
    public Ticket findById(Long idTicket) {
        try {
            return jdbcTemplate.queryForObject("select * from tickets where id_ticket = " + idTicket, ticketRowMapper);
        } catch (RuntimeException e) {
            logger.error("Ticket not found with id " + idTicket);
            throw new EntityNotFoundException("Error!");
        }
    }

    @Override
    public Optional<Ticket> findOne(Long idTicket) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from tickets where id_ticket = " + idTicket,
                    ticketRowMapper));
        } catch (RuntimeException e) {
            logger.error("Ticket not found with id " + idTicket);
            throw new EntityNotFoundException("Error!");
        }
    }

    @Override
    public List<Ticket> findAll() {
        return jdbcTemplate.query("select * from tickets order by id_ticket asc", ticketRowMapper);
    }

    @Override
    public Ticket create(Ticket ticket) {
        final String sql = "insert into tickets (id_pass, id_ticket_status, price, id_flight, id_place, id_ticket_class," +
                " id_airline, created) values (?, ?, ?, ?, ?, ?, ?, ?) ";
        if (ticket.getIdTicketStatus() == 1) {
            jdbcTemplate.update(sql, ticket.getIdPass(),
                    1L,
                    ticket.getPrice(),
                    ticket.getIdFlight(),
                    ticket.getIdPlace(),
                    ticket.getIdTicketClass(),
                    ticket.getIdAirline(),
                    ticket.getCreated());
        } else {
            jdbcTemplate.update(sql, ticket.getIdPass(),
                    ticket.getIdTicketStatus(),
                    ticket.getPrice(),
                    ticket.getIdFlight(),
                    ticket.getIdPlace(),
                    ticket.getIdTicketClass(),
                    ticket.getIdAirline(),
                    ticket.getCreated());
        }
        return ticket;
    }

    @Override
    public Ticket update(Ticket ticket) {
        final String sql = "update tickets set id_pass = ?, id_ticket_status = ?, price = ?, id_flight = ?, id_place = ?," +
                "id_ticket_class = ?, id_airline = ?, changed = ? WHERE id_ticket = ?";
        jdbcTemplate.update(sql, ticket.getIdPass(),
                ticket.getIdTicketStatus(),
                ticket.getPrice(),
                ticket.getIdFlight(),
                ticket.getIdPlace(),
                ticket.getIdTicketClass(),
                ticket.getIdAirline(),
                ticket.getChanged(),
                ticket.getIdTicket());
        return findById(ticket.getIdTicket());
    }

    @Override
    public Optional<Ticket> deleteById(Long idTicket) {
        final String sql = "update tickets set is_deleted = true, changed = ?  WHERE id_ticket = ?";
        jdbcTemplate.update(sql, Timestamp.valueOf(LocalDateTime.now()), idTicket);
        return findOne(idTicket);
    }

    public BigDecimal findMostExpensiveTicket(Long idPass) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall simpleJdbcCallFunction = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("find_most_expensive_ticket");
        SqlParameterSource in = new MapSqlParameterSource().addValue("idP", idPass);
        return simpleJdbcCallFunction.executeFunction(BigDecimal.class, in);
    }

    public void findSaleTicket(Long idTicket, Float discount) {
        jdbcTemplate.update("call sale(?, ?)", idTicket, discount);
    }

    public BigDecimal profitAirline(Long query) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall simpleJdbcCallFunction = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("calculate_profit_airline");
        SqlParameterSource in = new MapSqlParameterSource().addValue("query", query);
        return simpleJdbcCallFunction.executeFunction(BigDecimal.class, in);
    }
}
