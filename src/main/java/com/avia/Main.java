package com.avia;

import com.avia.domain.Passenger;
import com.avia.repository.PassengerRepository;
import com.avia.repository.PassengerRepositoryImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        PassengerRepository passengerRepository = new PassengerRepositoryImpl();

        List<Passenger> all = passengerRepository.findAll();

        for (Passenger passenger : all) {
            System.out.println(passenger);
        }

    }
}