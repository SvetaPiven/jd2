package com.avia.service;

import com.avia.domain.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{
    @Override
    public Optional<Ticket> findOne(Long idTicket) {
        return Optional.empty();
    }

    @Override
    public Ticket findById(Long idTicket) {
        return null;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Ticket create(Ticket object) {
        return null;
    }

    @Override
    public Ticket update(Ticket object) {
        return null;
    }

    @Override
    public Optional<Ticket> deleteById(Long idTicket) {
        return Optional.empty();
    }
}
