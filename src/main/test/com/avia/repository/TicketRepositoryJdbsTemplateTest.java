package com.avia.repository;

import com.avia.domain.Ticket;
import com.avia.repository.impl.TicketRepositoryJdbcTemplateImpl;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@ContextConfiguration(locations = "file:src/main/resources/application-context.xml")
@ExtendWith(SpringExtension.class)
class TicketRepositoryJdbsTemplateTest {
    private static final Logger logger = Logger.getLogger(PassengerRepositoryJdbsTemplateTest.class);
    @Autowired
    private TicketRepositoryJdbcTemplateImpl ticketRepositoryJdbcTemplate;
    @Test
    void profitAirlineTest() {
        logger.info("Creating Store Function...");
        logger.info(ticketRepositoryJdbcTemplate.profitAirline(1L));
    }

    @Test
    void startFunctionTest() {
        logger.info("Creating Store Function...");
        logger.info(ticketRepositoryJdbcTemplate.findMostExpensiveTicket(7L));
    }

    @Test
    void startProcTest() {
        logger.info("Creating Store Procedure...");
        ticketRepositoryJdbcTemplate.findSaleTicket(5L, 0.05F);
    }

    @Test
    void findAllTicketWithJdbsTemplateTest() {
        List<Ticket> all = ticketRepositoryJdbcTemplate.findAll();
        for (Ticket ticket : all) {
            logger.info(ticket);
        }
    }

    @Test
    void findOneTicketWithJdbsTemplateTest() {
        logger.info(ticketRepositoryJdbcTemplate.findOne(100L));
    }

    @Test
    void createTicketWithJdbsTemplateTest() {
        logger.info(ticketRepositoryJdbcTemplate.create(Ticket.builder()
                .idPass(100L)
                .idTicketStatus(2L)
                .price(BigDecimal.valueOf(200))
                .idFlight(1L)
                .idPlace(String.valueOf(1))
                .idTicketClass(1L)
                .idAirline(1L)
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .build()));
    }

    @Test
    void updateWithJdbsTemplateTest() {
        logger.info(ticketRepositoryJdbcTemplate.update(Ticket.builder()
                .idTicket(5L)
                .idTicketStatus(2L)
                .idPass(6L)
                .idTicketClass(1L)
                .idAirline(5L)
                .price(BigDecimal.valueOf(400.33))
                .idFlight(6L)
                .idPlace(String.valueOf(4D))
                .changed(Timestamp.valueOf(LocalDateTime.now()))
                .build()));
    }

    @Test
    void deleteWithJdbsTemplateTest() {
        logger.info(ticketRepositoryJdbcTemplate.deleteById(22L));
    }

    @Test
    void findOneTicketByIdWithJdbsTemplateTest() {
        logger.info(ticketRepositoryJdbcTemplate.findById(1L));
    }
}