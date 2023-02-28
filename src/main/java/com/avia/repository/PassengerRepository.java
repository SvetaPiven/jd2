package com.avia.repository;

import com.avia.domain.Passenger;

public interface PassengerRepository extends CRUDRepository<Long, Passenger> {
    void searchPassenger();
}
