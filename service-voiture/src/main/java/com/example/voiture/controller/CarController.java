package com.example.voiture.controller;

import com.example.voiture.model.Car;
import com.example.voiture.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/byClient/{clientId}")
    public Car getCarByClientId(@PathVariable Long clientId) {
        return carService.getCarByClientId(clientId);
    }
}

