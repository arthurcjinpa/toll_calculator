package com.toll_calculator.exception;

/**
 * Here I created a custom exception which will give me the flexibility to add attribute that are
 * not a part of a standard Java exception.
 *
 * @author Arthur Babaev
 */
public class LocationCalculationException extends RuntimeException {
  public LocationCalculationException(String message) {
    super(message);
  }
}
