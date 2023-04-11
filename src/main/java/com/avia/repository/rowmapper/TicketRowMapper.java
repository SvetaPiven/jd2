package com.avia.repository.rowmapper;

import com.avia.domain.Ticket;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.avia.repository.columns.TicketColumns.CHANGED;
import static com.avia.repository.columns.TicketColumns.CREATED;
import static com.avia.repository.columns.TicketColumns.ID_AIRLINE;
import static com.avia.repository.columns.TicketColumns.ID_FLIGHT;
import static com.avia.repository.columns.PassengerColumns.ID_PASS;
import static com.avia.repository.columns.TicketColumns.ID_PLACE;
import static com.avia.repository.columns.TicketColumns.ID_TICKET;
import static com.avia.repository.columns.TicketColumns.ID_TICKET_CLASS;
import static com.avia.repository.columns.TicketColumns.ID_TICKET_STATUS;
import static com.avia.repository.columns.TicketColumns.IS_DELETED;
import static com.avia.repository.columns.TicketColumns.PRICE;

@Component
public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket;
        try {
            ticket = Ticket.builder()
                    .idTicket(rs.getLong(ID_TICKET.name()))
                    .idPass(rs.getLong(ID_PASS.name()))
                    .idTicketStatus(rs.getLong(ID_TICKET_STATUS.name()))
                    .price(rs.getBigDecimal(PRICE.name()))
                    .idFlight(rs.getLong(ID_FLIGHT.name()))
                    .idPlace(rs.getString(ID_PLACE.name()))
                    .idTicketClass(rs.getLong(ID_TICKET_CLASS.name()))
                    .idAirline(rs.getLong(ID_AIRLINE.name()))
                    .created(rs.getTimestamp(CREATED.name()))
                    .changed(rs.getTimestamp(CHANGED.name()))
                    .isDeleted(rs.getBoolean(IS_DELETED.name()))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ticket;
    }
}
