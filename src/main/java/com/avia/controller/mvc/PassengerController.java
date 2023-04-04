package com.avia.controller.mvc;

import com.avia.controller.requests.PassengerCreateRequest;
import com.avia.domain.Passenger;
import com.avia.repository.impl.PassengerRepositoryJdbcTemplateImpl;
import com.avia.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/{id}")
    public String findByIdPassenger(@PathVariable(value = "id") Long id, Model model) {

        model.addAttribute("passengers", passengerService.findById(id));

        return "index";
    }

    @GetMapping("/update")
    public String addPass(Model model) {

        model.addAttribute("passenger", new PassengerCreateRequest());
        return "update";
    }

    @PatchMapping("/update")
    public String updatePassenger(@ModelAttribute PassengerCreateRequest request) {
        passengerService.update(
                Passenger.builder().
                fullName(request.getFullName()).
                personalId(request.getPersonalId()).
                build());
        return "update";
    }

    @GetMapping("/create")
    public String addPassenger(Model model) {
        model.addAttribute("passenger", new Passenger());

        return "newpassenger";
    }

    @PostMapping("/save")
    public String savePassenger(@ModelAttribute("passenger") Passenger passenger) {

        passengerRepositoryJdbcTemplate.create(passenger);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePassenger(@PathVariable(value = "id") Long id) {

        passengerRepositoryJdbcTemplate.hardDeleteById(id);

        return "redirect:/";
    }
}
