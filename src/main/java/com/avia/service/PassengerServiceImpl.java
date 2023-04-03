package com.avia.service;

import com.avia.controller.requests.PassengerCreateRequest;
import com.avia.domain.Passenger;
import com.avia.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Optional<Passenger> findOne(Long idPass) {
        if (idPass.toString().isEmpty()) {
            throw new RuntimeException("Id does not exist!");
        }
        return passengerRepository.findOne(idPass);
    }

    @Override
    public Passenger findById(Long idPass) {
        return null;
    }

    @Override
    public List<Passenger> findAll() {
        /*Validation layer*/
        return passengerRepository.findAll();
    }

    @Override
    public Passenger create(Passenger passenger) {
        /*Validation layer*/
        if (passenger.getPersonalId().length() <= 14) {
            throw new RuntimeException("Something wrong!");
        }
        return passengerRepository.create(passenger);
    }

    @Override
    public Passenger update(Passenger object) {
        return null;
    }

    @Override
    public Optional<Passenger> deleteById(Long idPass) {
        return Optional.empty();
    }

    @Override
    public void save(PassengerCreateRequest request) {
            passengerRepository.create(Passenger.builder()
                    .idPass(request.getIdPass())
                    .fullName(request.getFullName())
                    .personalId(request.getPersonalId())
                    .build());
    }
}