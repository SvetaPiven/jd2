package com.avia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Passenger {

    private Long idPass;
    private String fullName;
    private String personalId;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;
}
