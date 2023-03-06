package com.avia.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(locations = "file:src/main/resources/application-context.xml")
@ExtendWith(SpringExtension.class)
class PassengerRepositoryImplTest {
    @Autowired
    private PassengerRepositoryImpl passengerRepository;

    @Test
    void getFindAllPassTest() {
        System.out.println(passengerRepository.findAll());
    }

    @Test
    void getFindOnePassTest() {
        System.out.println(passengerRepository.findOne(1L));
    }
}
