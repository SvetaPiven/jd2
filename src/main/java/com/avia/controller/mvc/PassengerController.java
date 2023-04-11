package com.avia.controller.mvc;

import com.avia.domain.Passenger;
import com.avia.repository.impl.PassengerRepositoryJdbcTemplateImpl;
import com.avia.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PassengerController {

    private static final Logger log = Logger.getLogger(PassengerController.class);

    private final PassengerService passengerService;
    private final PassengerRepositoryJdbcTemplateImpl passengerRepositoryJdbcTemplate;

    @GetMapping("/")
    public String findAllPassenger(Model model) {

        model.addAttribute("passengers", passengerService.findAll());

        return "index";
    }

    @GetMapping("/findbysurname")
    public String showBySurnamePassenger(Model model) {

        model.addAttribute("passenger", new Passenger());

        return "findbysurname";
    }

    @PostMapping("/findbysurname")
    public String findPassengerBySurname(@ModelAttribute Passenger passenger, Model model) {

        model.addAttribute("passengers", passengerRepositoryJdbcTemplate.searchPassSurname(passenger.getFullName()));

        return "findbysurname";
    }

    @GetMapping("/findbyid")
    public String showByIdPassenger(Model model) {

        model.addAttribute("passenger", new Passenger());

        return "findbyid";
    }

    @PostMapping("/find")
    public String findPassengerByID(@ModelAttribute("passenger") Passenger passenger, Model model) {

        model.addAttribute("passenger", passengerService.findById(passenger.getIdPass()));

        return "findbyid";
    }

    @PostMapping("/create")
    public String savePassenger(@ModelAttribute("passenger") Passenger passenger) {

        passengerService.create(passenger);

        return "redirect:/index";
    }

    @PostMapping("/save")
    public String updatePassenger(@ModelAttribute("passenger") Passenger passenger) {

        passengerRepositoryJdbcTemplate.update(passenger);

        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String updatePassenger(@PathVariable(value = "id") long id, Model model) {

        Passenger passenger = passengerService.findById(id);

        model.addAttribute("passenger", passenger);
        return "update";
    }

    @GetMapping("/create")
    public String addPassenger(Model model) {

        model.addAttribute("passenger", new Passenger());

        return "newpassenger";
    }

    @GetMapping("/delete/{id}")
    public String deletePassenger(@PathVariable(value = "id") Long id) {

        passengerRepositoryJdbcTemplate.hardDeleteById(id);

        return "redirect:/";
    }
}
