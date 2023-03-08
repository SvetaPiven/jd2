package com.avia.repository;

import com.avia.domain.Passenger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Primary
public class PassengerSecondRepositoryImpl implements PassengerRepository {

    @Override
    public Passenger findById(Long id) {
        return null;
    }

    @Override
    public Optional<Passenger> findOne(Long idPass) {
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
    public Optional<Passenger> deleteById(Long idPass) {
        return null;
    }

    @Override
    public List<Passenger> searchPassengerCitizenBelarus() {
        return null;
    }

    @Override
    public List<Passenger> findAllWoman() {
        return null;
    }

    @Override
    public List<Passenger> findFirstLetterSurname() {
        return null;
    }

    @Override
    public List<Passenger> findMinskRegionPass() {
        return null;
    }
}