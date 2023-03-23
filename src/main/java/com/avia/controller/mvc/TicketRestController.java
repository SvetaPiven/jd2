package com.avia.controller.mvc;

import com.avia.controller.requests.TicketCreateRequest;
import com.avia.domain.Ticket;
import com.avia.repository.impl.TicketRepositoryJdbcTemplateImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/tickets")
@RequiredArgsConstructor
public class TicketRestController {

    private final TicketRepositoryJdbcTemplateImpl ticketRepositoryJdbcTemplate;

    @GetMapping
    public ResponseEntity<Object> getAllTickets() {
        List<Ticket> tickets = ticketRepositoryJdbcTemplate.findAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketCreateRequest request) {

        Ticket build = Ticket.builder()
                .idTicket(request.getIdTicket())
                .idPass(request.getIdPass())
                .idTicketClass(request.getIdTicketClass())
                .price(request.getPrice())
                .idFlight(request.getIdFlight())
                .idPlace(request.getIdPlace())
                .idTicketClass(request.getIdTicketClass())
                .idAirline(request.getIdAirline())
                .build();

        Ticket createdTicket = ticketRepositoryJdbcTemplate.create(build);

        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findTicketById(@PathVariable Long id) {

        Ticket ticket = ticketRepositoryJdbcTemplate.findById(id);

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Ticket> partialUpdateTicket(@RequestBody TicketCreateRequest request) {

        Ticket ticket = ticketRepositoryJdbcTemplate.update(Ticket.builder()
                .idTicket(request.getIdTicket())
                .idPass(request.getIdPass())
                .idTicketClass(request.getIdTicketClass())
                .price(request.getPrice())
                .idFlight(request.getIdFlight())
                .idPlace(request.getIdPlace())
                .idTicketClass(request.getIdTicketClass())
                .idAirline(request.getIdAirline())
                .build());

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Optional<Ticket>> deleteTicket(@RequestBody TicketCreateRequest request) {

        Optional<Ticket> ticket = ticketRepositoryJdbcTemplate.deleteById(request.getIdTicket());

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchMostExpensiveTicket(@RequestBody TicketCreateRequest request) {

        BigDecimal price = ticketRepositoryJdbcTemplate.findMostExpensiveTicket(request.getIdPass());

        return new ResponseEntity<>(price, HttpStatus.OK);
    }
}