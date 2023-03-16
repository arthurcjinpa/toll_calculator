package com.toll_calculator.service;

import com.toll_calculator.dto.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JsonProcessingService {
    List<LocationDTO> convertAndSaveJsonData();
}
