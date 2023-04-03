package com.avia.controller.mvc;

import com.avia.controller.requests.PassengerCreateRequest;
import com.avia.domain.Passenger;
import com.avia.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PassengerController {

    private static final Logger log = Logger.getLogger(PassengerController.class);

    private final PassengerService passengerService;

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

    @PutMapping("/show/{id}")
    public String updatePassenger(@PathVariable(value = "id") long id, Model model) {

        Passenger passenger = passengerService.findById(id);

        model.addAttribute("passenger", passenger);
        return "update";
    }

    @GetMapping("/addnew")
    public String addPassenger(Model model) {
        model.addAttribute("passenger", new PassengerCreateRequest());

        return "newpassenger";
    }

    @PostMapping("/save")
    public String savePassenger(@ModelAttribute("passenger") PassengerCreateRequest request) {

        passengerService.save(request);

        return "redirect:/";
    }

    @DeleteMapping
    public String deletePassenger(@ModelAttribute PassengerCreateRequest request) {

        passengerService.deleteById(request.getIdPass());

        return "redirect:/passengers";
    }
}
