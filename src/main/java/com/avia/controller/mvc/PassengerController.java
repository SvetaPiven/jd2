package com.avia.controller.mvc;

import com.avia.controller.requests.PassengerCreateRequest;
import com.avia.domain.Passenger;
import com.avia.repository.impl.PassengerRepositoryJdbcTemplateImpl;
import com.avia.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/passengers")
public class PassengerController {

    private static final Logger log = Logger.getLogger(PassengerController.class);

    private final PassengerService passengerService;

    @GetMapping()
    public String findAllPassenger() {
        List<Passenger> passengers = passengerService.findAll();

        String collect = passengers.stream().map(Passenger::getFullName).collect(Collectors.joining(","));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passengerFullName", collect);
        modelAndView.addObject("passengers", passengers);

        return "index";
    }

    @GetMapping("/{id}")
    public String findByIdPassenger(@PathVariable String id) {

        Long parsedPassengerId;

        try {
            parsedPassengerId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("Passenger id: " + id + " cannot be parsed to Long", e);
            parsedPassengerId = 1L;
        }

        Optional<Passenger> passenger = passengerService.findOne(parsedPassengerId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passengerFullName", passenger.getClass());
        modelAndView.addObject("passengers", Collections.singletonList(passenger));

        return "index";
    }

    @PutMapping
    public String updatePassenger(@ModelAttribute PassengerCreateRequest request) {

        Passenger passenger = passengerService.update(Passenger.builder()
                .idPass(request.getIdPass())
                .fullName(request.getFullName())
                .personalId(request.getPersonalId())
                .build());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passenger", passenger);

        return "update";
    }

    @PostMapping
    public String createPassenger(@ModelAttribute PassengerCreateRequest request) {
        Passenger passenger = Passenger.builder()
                .fullName(request.getFullName())
                .personalId(request.getPersonalId())
                .build();
        passengerService.create(passenger);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passenger", passenger);

        return "newpassenger";
    }

    @DeleteMapping
    public String deletePassenger(@ModelAttribute PassengerCreateRequest request) {

        passengerService.deleteById(request.getIdPass());

        return "redirect:/passengers";
    }
}
