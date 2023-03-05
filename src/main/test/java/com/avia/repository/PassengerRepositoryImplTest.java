package com.avia.repository;

import com.avia.domain.Passenger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(locations = "file:src/main/resources/application-context.xml")
@ExtendWith(SpringExtension.class)
class PassengerRepositoryImplTest {
    @Autowired
    private PassengerRepositoryImpl passengerRepository;

    @Test
    void getFindAllPassTest() {
        List<Passenger> pass = new ArrayList<>();
        pass.add(new Passenger());

        List<Passenger> expected = passengerRepository.findAll();

        Assertions.assertEquals(expected, pass);
     //   System.out.println(passengerRepository.findAll());
    }

    @Test
    void getFindOnePassTest() {
        System.out.println(passengerRepository.findOne(1L));
    }

//    @Test
//    void getCreatePassTest() {
//        System.out.println(passengerRepository.create());
//    }
}
