package com.noirix.repository;

import com.noirix.domain.Passenger;

public interface PassengerRepository extends CRUDRepository <Long, Passenger> {

    void searchPassenger();
}
