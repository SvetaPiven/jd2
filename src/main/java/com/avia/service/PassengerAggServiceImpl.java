package com.avia.service;

import com.avia.domain.Passenger;
import com.avia.repository.PassengerRepository;
import com.avia.repository.PassengerRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassengerAggServiceImpl implements PassengerAggregationService {

    private final PassengerRepository passengerRepository = new PassengerRepositoryImpl();

    @Override
    public Map<String, Object> getStats() {

        List<Passenger> passengers = passengerRepository.findAll();
        Passenger one = passengerRepository.findOne(2L);
        passengerRepository.searchPassenger();

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("allPassengers", passengers);
        resultMap.put("onePassenger", one);

        return resultMap;
    }
}
