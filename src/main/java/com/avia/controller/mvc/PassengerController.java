package com.avia.controller.mvc;

import com.avia.domain.Passenger;
import com.avia.repository.impl.PassengerRepositoryJdbcTemplateImpl;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final PassengerRepositoryJdbcTemplateImpl passengerRepositoryJdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findAll() {
        List<Passenger> passengers = passengerRepositoryJdbcTemplate.findAll();

        String collect = passengers.stream().map(Passenger::getFullName).collect(Collectors.joining(","));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passengerFullName", collect);
        modelAndView.addObject("passengers", passengers);

        modelAndView.setViewName("hello");

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView findById(@PathVariable String id) {

        Long parsedPassengerId;

        try {
            parsedPassengerId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("Passenger id: " + id + " cannot be parsed to Long", e);
            parsedPassengerId = 1L;
        }

        Optional<Passenger> passenger = passengerRepositoryJdbcTemplate.findOne(parsedPassengerId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("passengerFullName", passenger.getClass());
        modelAndView.addObject("passengers", Collections.singletonList(passenger));

        modelAndView.setViewName("hello");

        return modelAndView;
    }
}
