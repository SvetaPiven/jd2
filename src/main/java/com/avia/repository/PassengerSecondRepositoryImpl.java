package com.avia.repository;

import com.avia.domain.Passenger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class PassengerSecondRepositoryImpl implements PassengerRepository {

    @Override
    public Passenger findOne(Long idPass) {
        return null;
    }

    @Override
    public List<Passenger> findAll() {
        return null;
    }

    @Override
    public Passenger create(Passenger object) {
        return null;
    }

    @Override
    public Passenger update(Passenger object) {
        return null;
    }

    @Override
    public void delete(Long idPass) {

    }

    @Override
    public void searchPassenger() {

    }
}
