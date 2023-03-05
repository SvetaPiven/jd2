package com.avia;

import com.avia.configuration.PropertiesDB;
import com.avia.domain.Passenger;
import com.avia.repository.PassengerRepositoryImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        PassengerRepositoryImpl passengerRepository = new PassengerRepositoryImpl(new PropertiesDB());
//        Passenger pass = new Passenger();
//        Passenger passNew = Passenger.builder()
//                .fullName("jjj")
//                .build();

        List<Passenger> all = passengerRepository.findAll();

        for (Passenger passenger : all) {
            System.out.println(passenger);
        }
//        Passenger pass = new Passenger("Mona Liza", "6131108B868PB5", Timestamp.valueOf(LocalDateTime.now()), null);
//        System.out.println(passengerRepository.create(pass));
//        List<Passenger> woman = passengerRepository.findAllWoman();
//
//        for (Passenger passenger : woman) {
//            System.out.println(passenger);
//        }
//        List<Passenger> letter = passengerRepository.findFirstLetterSurname();
//
//        for (Passenger passenger : letter) {
//            System.out.println(passenger);
//        }
//    }
        // System.out.println(passengerRepository.update(new Passenger(100l, "Russel Kurt", "3200288B190PB4", Timestamp.valueOf(LocalDateTime.now()))));
      //    passengerRepository.delete(103L);
      //  System.out.println(passengerRepository.findOne(99l));
    }
}