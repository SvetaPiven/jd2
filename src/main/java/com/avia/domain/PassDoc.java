package com.avia.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PassDoc {
    private Long idPass;
    private String fullName;
    private String personalId;
    private Long idDocumentPass;
    private Long idDocumentType;
    private String documentNum;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;
}

