package com.avia.controller.mvc;

import com.avia.controller.requests.PassengerCreateRequest;
import com.avia.domain.Passenger;
import com.avia.repository.impl.PassengerRepositoryJdbcTemplateImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/passengers")
@RequiredArgsConstructor
public class PassengerRestController {

    private final PassengerRepositoryJdbcTemplateImpl passengerRepositoryJdbcTemplate;

    @GetMapping
    public ResponseEntity<Object> getAllPassengers() {
        List<Passenger> passengers = passengerRepositoryJdbcTemplate.findAll();
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody PassengerCreateRequest request) {

        Passenger build = Passenger.builder()
                .fullName(request.getFullName())
                .personalId(request.getPersonalId())
                .build();

        Passenger createdPassenger = passengerRepositoryJdbcTemplate.create(build);

        return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> findPassengerById(@PathVariable Long id) {

        Passenger passenger = passengerRepositoryJdbcTemplate.findById(id);

        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Passenger> updatePassenger(@RequestBody PassengerCreateRequest request) {

        Passenger passenger = passengerRepositoryJdbcTemplate.update(Passenger.builder()
                .idPass(request.getIdPass())
                .fullName(request.getFullName())
                .personalId(request.getPersonalId())
                .build());

        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Passenger> partialUpdatePassenger(@RequestBody PassengerCreateRequest request) {

        Passenger passenger = passengerRepositoryJdbcTemplate.update(Passenger.builder()
                .idPass(request.getIdPass())
                .fullName(request.getFullName())
                .personalId(request.getPersonalId())
                .build());

        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Optional<Passenger>> deletePassenger(@RequestBody PassengerCreateRequest request) {

        Optional<Passenger> passenger = passengerRepositoryJdbcTemplate.deleteById(request.getIdPass());

        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchPassenger(@RequestParam(value = "query") String query) {

        List<Passenger> passengers = passengerRepositoryJdbcTemplate.searchPassSurname(query);

        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @GetMapping("/search/woman")
    public ResponseEntity<Object> searchAllWoman() {

        List<Passenger> passengers = passengerRepositoryJdbcTemplate.findAllWoman();

        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @GetMapping("/search/belarus")
    public ResponseEntity<Object> searchPassCitizenBelarus() {

        List<Passenger> passengers = passengerRepositoryJdbcTemplate.searchPassengerCitizenBelarus();

        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }
}

//Domain - Car
//GET + /rest/cars - findAll
//GET + /rest/cars/{id} - findOne
//POST + /rest/cars - create object
//PUT + /rest/cars - update object
//DELETE + /rest/cars - update object

//PATCH + /rest/cars  - partial update of object
//GET + /rest/cars/search/woman
//GET + /rest/cars/search
//PUT + /rest/cars/calculate
//query - поисковой запрос
//limit/offset = page = ограничение на число выводимых объектов


