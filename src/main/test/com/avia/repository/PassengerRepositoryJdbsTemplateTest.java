package com.avia.repository;

import com.avia.domain.PassDoc;
import com.avia.domain.Passenger;
import com.avia.repository.impl.PassDocRepositoryImpl;
import com.avia.repository.impl.PassengerRepositoryJdbcTemplateImpl;
import org.apache.log4j.Logger;
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
class PassengerRepositoryJdbsTemplateTest {
    private static final Logger logger = Logger.getLogger(PassengerRepositoryJdbsTemplateTest.class);
    @Autowired
    private PassDocRepositoryImpl documentPassRepository;
    @Autowired
    private PassengerRepositoryJdbcTemplateImpl passengerRepositoryJdbcTemplate;

    @Test
    void findAllPassWithJdbsTemplateTest() {
        List<Passenger> all = passengerRepositoryJdbcTemplate.findAll();
        for (Passenger passenger : all) {
            logger.info(passenger);
        }
    }

    @Test
    void findOnePassWithJdbsTemplateTest() {
        logger.info(passengerRepositoryJdbcTemplate.findOne(100L));
    }

    @Test
    void createWithJdbsTemplateTest() {
        logger.info(passengerRepositoryJdbcTemplate.create(Passenger.builder()
                .fullName("Lolo Katia")
                .personalId("4130129B117PB3")
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .build()));
    }

    @Test
    void updateWithJdbsTemplateTest() {
        logger.info(passengerRepositoryJdbcTemplate.update(Passenger.builder()
                .idPass(19L)
                .fullName("Martin Ideya")
                .personalId("5230578B786PB4")
                .created(Timestamp.valueOf(LocalDateTime.now())).build()));
    }

    @Test
    void firstLetterFullnameWithJdbsTemplateTest() {
        List<Passenger> surname = passengerRepositoryJdbcTemplate.searchPassSurname("Barkova");
        for (Passenger passenger : surname) {
            logger.info(passenger);
        }
    }

    @Test
    void allWomanWithJdbsTemplateTest() {
        List<Passenger> woman = passengerRepositoryJdbcTemplate.findAllWoman();
        for (Passenger passenger : woman) {
            logger.info(passenger);
        }
    }

    @Test
    void deleteWithJdbsTemplateTest() {
        logger.info(passengerRepositoryJdbcTemplate.deleteById(22L));
    }

    @Test
    void findOnePassByIdWithJdbsTemplateTest() {
        System.out.println(passengerRepositoryJdbcTemplate.findById(1L));
    }

    @Test
    void findPassMinskRegionWithJdbsTemplateTest() {
        List<PassDoc> pass = documentPassRepository.findMinskRegionPass();
        for (PassDoc passenger : pass) {
            logger.info(passenger);
        }
    }
}