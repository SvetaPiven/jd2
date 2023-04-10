package com.avia.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TicketCreateRequest {
    private Long idTicket;
    private Long idPass;
    private Long idTicketStatus;
    private BigDecimal price;
    private Long idFlight;
    private String idPlace;
    private Long idTicketClass;
    private Long idAirline;
}
