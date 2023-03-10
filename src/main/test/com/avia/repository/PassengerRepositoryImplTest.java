package com.avia.repository;

import com.avia.domain.PassDoc;
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
    @Autowired
    private DocumentPassRepositoryImpl documentPassRepository;

    @Test
    void findAllPassTest() {
        List<Passenger> all = passengerRepository.findAll();
        for (Passenger passenger : all) {
            System.out.println(passenger);
        }
    }

    @Test
    void findOnePassTest() {
        System.out.println(passengerRepository.findOne(1000L));
    }

    @Test
    void createTest() {
        System.out.println(passengerRepository.create(Passenger.builder()
                .fullName("Martin Martin")
                .personalId("4130577B635PB1")
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(null).build()));
    }

    @Test
    void updateTest() {
        System.out.println(passengerRepository.update(Passenger.builder()
                .idPass(19L)
                .fullName("Marti Idenn")
                .personalId("5230578B636PB4")
                .created(Timestamp.valueOf(LocalDateTime.now())).build()));
    }

    @Test
    void firstLetterFullnameTest() {
        List<Passenger> letter = passengerRepository.findFirstLetterSurname();
        for (Passenger passenger : letter) {
            System.out.println(passenger);
        }
    }

    @Test
    void allWomanTest() {
        List<Passenger> woman = passengerRepository.findAllWoman();
        for (Passenger passenger : woman) {
            System.out.println(passenger);
        }
    }

    @Test
    void deleteTest() {
        System.out.println(passengerRepository.deleteById(12L));
    }

    @Test
    void findOnePassByIdTest() {
        System.out.println(passengerRepository.findById(1L));
    }

    @Test
    void findPassMinskRegionTest() {
        List<PassDoc> pass = documentPassRepository.findMinskRegionPass();
        for (PassDoc passenger : pass) {
            System.out.println(passenger);
        }
    }
}
