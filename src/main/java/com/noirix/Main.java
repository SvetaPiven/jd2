package com.noirix;

import com.noirix.domain.Passenger;
import com.noirix.repository.PassengerRepository;
import com.noirix.repository.PassengerRepositoryImpl;

import java.util.List;

public class Main {

    //Ctrl+Alt+O - import optimizing
    //Ctrl+Alt+L - formatting

    public static void main(String[] args) {

        PassengerRepository passengerRepository = new PassengerRepositoryImpl();

        List<Passenger> all = passengerRepository.findAll();

        for (Passenger passenger : all) {
            System.out.println(passenger);
        }

    }
}