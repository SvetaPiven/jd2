package com.avia;

import com.avia.domain.Passenger;
import com.avia.repository.PassengerRepository;
import com.avia.repository.PassengerRepositoryImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PassengerRepository passengerRepository = new PassengerRepositoryImpl();
        System.out.println("method findAll");
        List<Passenger> all = passengerRepository.findAll();
        for (Passenger passenger : all) {
            System.out.println(passenger);
        }
        System.out.println("method create");
        Passenger pass = new Passenger("Lavign Avril", "6131001B467PB5", Timestamp.valueOf(LocalDateTime.now()), null);
        System.out.println(passengerRepository.create(pass));
        System.out.println("method findAllWoman");
        List<Passenger> woman = passengerRepository.findAllWoman();
        for (Passenger passenger : woman) {
            System.out.println(passenger);
        }
        System.out.println("method findFirstLetterSurname");
        List<Passenger> letter = passengerRepository.findFirstLetterSurname();
        for (Passenger passenger : letter) {
            System.out.println(passenger);
        }
        System.out.println("method update");
        System.out.println(passengerRepository.update(new Passenger(101L, "Gucci Kurt", "5200208B200PB4", Timestamp.valueOf(LocalDateTime.now()))));
        System.out.println("method delete");
        passengerRepository.delete(3L);
        System.out.println("method findOne");
        System.out.println(passengerRepository.findOne(99L));
    }
}