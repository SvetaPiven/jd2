package com.avia.service;

import com.avia.domain.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    Optional<Passenger> findOne(Long idPass);

    Passenger findById(Long idPass);

    List<Passenger> findAll();

    Passenger create(Passenger object);

    Passenger update(Passenger object);

    Optional<Passenger> deleteById(Long idPass);

    void hardDeleteById(Long idPass);
}