package com.avia.service;

import com.avia.domain.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    Optional<Passenger> findOne(Long idPass);

    List<Passenger> findAll();

    Passenger create(Passenger object);

    boolean update(Passenger object);

    boolean delete(Long idPass);
}