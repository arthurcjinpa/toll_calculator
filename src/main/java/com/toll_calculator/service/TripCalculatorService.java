package com.toll_calculator.service;

import com.toll_calculator.dto.CalculationResultDTO;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

@Service
public interface TripCalculatorService {
  CalculationResultDTO calculateTrip(Pair<String, String> locations);
}
