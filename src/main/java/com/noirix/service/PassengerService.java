package com.noirix.service;

import com.noirix.domain.Passenger;

import java.util.List;

public interface PassengerService {
    Passenger findOne(Long idPass);

    List<Passenger> findAll();

    Passenger create(Passenger object);

    Passenger update(Passenger object);

    void delete(Long idPass);
}
