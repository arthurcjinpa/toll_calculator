package com.toll_calculator.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Dto is the result of the calculation, returned to the user in the controller
 *
 * @author Arthur Babaev
 */
@Data
@Builder
public class CalculationResultDTO {
  private long distance;
  private String cost;
}
