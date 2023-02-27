package com.noirix.controller;

import com.noirix.domain.Passenger;
import com.noirix.repository.PassengerRepositoryImpl;
import com.noirix.service.PassengerService;
import com.noirix.service.PassengerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FrontController extends HttpServlet {
    private final PassengerService passengerService = new PassengerServiceImpl(new PassengerRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/hello");
        if (dispatcher != null) {
            System.out.println("Forward will be done!");
            System.out.println("We are processing user request");

            List<Passenger> passengers = passengerService.findAll();

            String collect = passengers.stream().map(Passenger::getFullName).collect(Collectors.joining(","));

            req.setAttribute("passengerFullName", collect);
            req.setAttribute("passengers", passengers);

            dispatcher.forward(req, resp);
        }
    }
}
