package com.avia;

import com.avia.configuration.PropertiesDB;
import com.avia.domain.Passenger;
import com.avia.repository.PassengerRepositoryImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        PassengerRepositoryImpl passengerRepository = new PassengerRepositoryImpl(new PropertiesDB());
        List<Passenger> all = passengerRepository.findAll();

        for (Passenger passenger : all) {
            System.out.println(passenger);
        }
    }
}