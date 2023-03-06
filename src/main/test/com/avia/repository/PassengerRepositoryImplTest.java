package com.avia.repository;

import com.avia.domain.Passenger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@ContextConfiguration(locations = "file:src/main/resources/application-context.xml")
@ExtendWith(SpringExtension.class)
class PassengerRepositoryImplTest {
    @Autowired
    private PassengerRepositoryImpl passengerRepository;

    @Test
    void getFindAllPassTest() {
        List<Passenger> all = passengerRepository.findAll();
        for (Passenger passenger : all) {
            System.out.println(passenger);
        }
    }

    @Test
    void getFindOnePassTest() {
        System.out.println(passengerRepository.findOne(1L));
    }

    @Test
    void getCreateTest() {
        System.out.println(passengerRepository.create(Passenger.builder()
                .fullName("Marta Marta")
                .personalId("4130577B636PB7")
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(null).build()));

    }

    @Test
    void getUpdateTest() {
        System.out.println(passengerRepository.update(Passenger.builder()
                .idPass(10L)
                .fullName("Martins Iden")
                .personalId("5130578B636PB4")
                .created(Timestamp.valueOf(LocalDateTime.now())).build()));
        passengerRepository.deleteById(76L);
    }

    @Test
    void getFirstLetterFullnameTest() {
        List<Passenger> letter = passengerRepository.findFirstLetterSurname();
        for (Passenger passenger : letter) {
            System.out.println(passenger);
        }
    }

    @Test
    void getAllWomanTest() {
        List<Passenger> woman = passengerRepository.findAllWoman();
        for (Passenger passenger : woman) {
            System.out.println(passenger);
        }
    }
}
