package com.avia.service;

import com.avia.domain.Passenger;
import com.avia.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PassengerAggServiceImpl implements PassengerAggregationService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Map<String, Object> searchPassengerCitizenBelarus() {
        List<Passenger> passengers = passengerRepository.findAll();
        Map<String, Object> resultMap = new HashMap<>();
        for (Passenger passenger : passengers) {
            String personalId = passenger.getPersonalId();
            List<Passenger> fullName = passengerRepository.searchPassengerCitizenBelarus();
            resultMap.put(personalId, fullName);
        }
        return resultMap;
    }
}