package com.avia.service;

import com.avia.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Optional<Ticket> findOne(Long idTicket);

    Ticket findById(Long idTicket);

    List<Ticket> findAll();

    Ticket create(Ticket object);

    Ticket update(Ticket object);

    Optional<Ticket> deleteById(Long idTicket);
}
