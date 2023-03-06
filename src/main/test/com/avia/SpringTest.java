package com.avia;

import com.avia.domain.Passenger;
import com.avia.repository.PassengerRepository;
import com.avia.service.PassengerAggregationService;
import com.avia.service.PassengerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SpringTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.avia");
        PassengerRepository passengerRepository = applicationContext.getBean("passengerRepositoryImpl", PassengerRepository.class);
        PassengerService passengerService = applicationContext.getBean("passengerServiceImpl", PassengerService.class);
        PassengerAggregationService passengerAggregationService = applicationContext.getBean("passengerAggServiceImpl", PassengerAggregationService.class);

        System.out.println(passengerService.findAll());

        Map<Long, Object> allBel = passengerAggregationService.searchPassengerCitizenBelarus();
        for(Map.Entry<Long, Object> entry: allBel.entrySet())
            System.out.println(entry.getKey() + " - " + entry.getValue());

        List<Passenger> all = passengerRepository.findAll();
        for (Passenger passenger : all) {
            System.out.println(passenger);
        }

        System.out.println(passengerRepository.findOne(1L));

        System.out.println(passengerRepository.create(Passenger.builder()
                .fullName("Marta Marta")
                .personalId("4130577B636PB7")
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(null).build()));

        System.out.println(passengerRepository.update(Passenger.builder()
                .idPass(110L)
                .fullName("Marti Marta")
                .personalId("4130578B636PB7")
                .created(Timestamp.valueOf(LocalDateTime.now())).build()));
        passengerRepository.delete(103L);

        List<Passenger> letter = passengerRepository.findFirstLetterSurname();
        for (Passenger passenger : letter) {
            System.out.println(passenger);
        }

        List<Passenger> woman = passengerRepository.findAllWoman();
        for (Passenger passenger : woman) {
            System.out.println(passenger);
        }
    }
}