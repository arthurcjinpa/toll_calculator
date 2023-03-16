package com.toll_calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteDTO {
  private Long toId;
  private double distance;
  private String startDate;
  private boolean enter;
  private boolean exit;
}
