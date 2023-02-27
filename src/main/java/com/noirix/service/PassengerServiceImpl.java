package com.noirix.service;

import com.noirix.domain.Passenger;
import com.noirix.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//id = userServiceImpl
public class PassengerServiceImpl implements PassengerService {

//    @Autowired
//    @Qualifier("userSecondRepositoryImpl")

//    @Inject //JSR-330
//    @Named("userRepositoryImpl")
//    @Named("userRepositoryImpl")

    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Passenger findOne(Long idPass) {
        return null;
    }

    @Override
    public List<Passenger> findAll() {
        /*Validation layer*/
        return passengerRepository.findAll();
    }

    @Override
    public Passenger create(Passenger object) {
        /*Validation layer*/
        if (object.getIdPass() > 23) {
            throw new RuntimeException("Something wrong!");
        }

        return passengerRepository.create(object);
    }

    @Override
    public Passenger update(Passenger object) {
        return null;
    }

    @Override
    public void delete(Long idPass) {

    }
}
