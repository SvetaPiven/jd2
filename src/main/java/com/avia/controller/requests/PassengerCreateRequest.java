package com.avia.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class PassengerCreateRequest {
    private String fullName;
    private String personalId;
    private Long idPass;
}
