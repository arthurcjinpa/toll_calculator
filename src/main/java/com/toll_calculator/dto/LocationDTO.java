package com.toll_calculator.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.toll_calculator.utils.LocationDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonDeserialize(using = LocationDeserializer.class)
public class LocationDTO {
  private Long id;
  private String name;
  private double lat;
  private double lng;
  private List<RouteDTO> routes;
}
