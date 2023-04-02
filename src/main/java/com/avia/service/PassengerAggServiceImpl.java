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
    public Map<Long, Object> searchPassengerCitizenBelarus() {
        List<Passenger> passengers = passengerRepository.searchPassengerCitizenBelarus();
        Map<Long, Object> resultMap = new HashMap<>();
        for (Passenger passenger : passengers) {
            Long idPass = passenger.getIdPass();
            String fullName = passenger.getFullName();
            resultMap.put(idPass, fullName);
        }
        return resultMap;
    }
}