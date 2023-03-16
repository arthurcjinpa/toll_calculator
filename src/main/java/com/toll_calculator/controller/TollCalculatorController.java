package com.toll_calculator.controller;

import com.toll_calculator.dto.CalculationResultDTO;
import com.toll_calculator.service.TripCalculatorService;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * In this REST controller with only one method "calculateTripDetails"
 * we get a CalculationResultDTO with the fields we need
 *
 * @author Arthur Babaev
 */
@RestController
@RequiredArgsConstructor
public class TollCalculatorController {

    private final TripCalculatorService tripCalculatorService;

    /**
     * This is GET request with:
     * @param firstLocation name of the first location from json in String
     * @param secondLocation name of the second location from json in String
     * @return CalculationResultDTO with two fields: "distance" and "cost"
     */
    @GetMapping("/calculate")
    public CalculationResultDTO calculateTripDetails(@RequestParam String firstLocation,
                                                     @RequestParam String secondLocation) {
        return tripCalculatorService.calculateTrip(new Pair<>(firstLocation, secondLocation));
    }
}
