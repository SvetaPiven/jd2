package com.avia.repository;

import com.avia.controller.requests.PassengerCreateRequest;
import com.avia.domain.Passenger;

import java.util.List;

public interface PassengerRepository extends CRUDRepository<Long, Passenger> {
    List<Passenger> searchPassengerCitizenBelarus();
    List<Passenger> findAllWoman();
    List<Passenger> searchPassSurname(String query);
}