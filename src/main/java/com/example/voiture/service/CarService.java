package com.example.voiture.service;

import com.example.voiture.model.Car;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarService {

    private final Map<Long, Car> cars = new HashMap<>();

    public CarService() {
        // Initialisation avec quelques données en mémoire
        cars.put(1L, new Car(10L, "Toyota", "Yaris", 1L));
        cars.put(2L, new Car(11L, "Renault", "Clio", 2L));
        cars.put(3L, new Car(12L, "Peugeot", "208", 3L));
        cars.put(4L, new Car(13L, "Volkswagen", "Golf", 4L));
        cars.put(5L, new Car(14L, "Ford", "Fiesta", 5L));
    }

    public Car getCarByClientId(Long clientId) {
        // Simuler un temps de traitement (20ms pour rendre les différences visibles)
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return cars.getOrDefault(clientId, 
            new Car(0L, "Inconnue", "Inconnue", clientId));
    }
}

