package com.avia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Ticket {
    private Long idTicket;
    private Long idPass;
    private Long idTicketStatus;
    private BigDecimal price;
    private Long idFlight;
    private String idPlace;
    private Long idTicketClass;
    private Long idAirline;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
