package com.avia.repository.impl;

import com.avia.domain.Ticket;
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

import javax.annotation.PostConstruct;
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
    private SimpleJdbcCall simpleJdbcCallFunction;
    private SimpleJdbcCall simpleJdbcCallProc;

    @Override
    public Ticket findById(Long idTicket) {
        return jdbcTemplate.queryForObject("select * from tickets where id_ticket = " + idTicket, ticketRowMapper);
    }

    @Override
    public Optional<Ticket> findOne(Long idTicket) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from tickets where id_ticket = " + idTicket,
                ticketRowMapper));
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
        return ticket;
    }

    @Override
    public Optional<Ticket> deleteById(Long idTicket) {
        final String sql = "update tickets set is_deleted = true, changed = ?  WHERE id_ticket = ?";
        jdbcTemplate.update(sql, Timestamp.valueOf(LocalDateTime.now()), idTicket);
        return findOne(idTicket);
    }

    @Override
    public List<Ticket> findAllWoman() {
        return null;
    }

    @Override
    public List<Ticket> searchPassSurname() {
        return null;
    }

    @Override
    public List<Ticket> findMinskRegionPass() {
        return null;
    }

//    @PostConstruct
//    public void init() {
//        jdbcTemplate.setResultsMapCaseInsensitive(true);
//        simpleJdbcCallFunction = new SimpleJdbcCall(jdbcTemplate)
//                .withFunctionName("findMostExpensiveTicket");
//        simpleJdbcCallProc = new SimpleJdbcCall(jdbcTemplate)
//                .withProcedureName("findSaleTicket");
//    }

    public void findMostExpensiveTicket(Long idP) {
//        final String SQL_FUNCTION = " CREATE OR REPLACE FUNCTION findMostExpensiveTicket(id_p int) "
//                + " RETURNS numeric(10, 2) "
//                + " LANGUAGE plpgsql "
//                + " AS "
//                + " $$ "
//                + " DECLARE "
//                + " itemPrice numeric(10, 2);"
//                + " begin "
//                + " SELECT MAX(price) "
//                + " INTO itemPrice "
//                + " FROM tickets "
//                + " WHERE id_pass = id_p; "
//                + " RETURN itemPrice; "
//                + " end; "
//                + " $$; ";
//        jdbcTemplate.execute(SQL_FUNCTION);
        final String sql = "SELECT findMostExpensiveTicket(1) as mostExpensive;";
        jdbcTemplate.execute(sql);
        SqlParameterSource in = new MapSqlParameterSource().addValue("idP", idP);
        BigDecimal price = simpleJdbcCallFunction.executeFunction(BigDecimal.class, in);
        logger.info(price);
    }

    public void findSaleTicket(Long idTicket, Float discount) {
         jdbcTemplate.update("call findSaleTicket(?, ?)", idTicket, discount);
    }
}
