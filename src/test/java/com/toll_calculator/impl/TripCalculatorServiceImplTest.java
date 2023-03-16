package com.toll_calculator.impl;

import com.toll_calculator.BaseTest;
import com.toll_calculator.dto.CalculationResultDTO;
import com.toll_calculator.exception.LocationCalculationException;
import javafx.util.Pair;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TripCalculatorServiceImplTest extends BaseTest {

  @Test
  public void calculateTripTest() {
    // given
    Pair<String, String> locations = new Pair<>("Appleby Line", "Dundas Street");

    // when
    CalculationResultDTO result = tripCalculatorService.calculateTrip(locations);

    // then
    assertNotNull(result.getCost());
    assertTrue(result.getDistance() != 0);
    assertEquals(result.getDistance(), 3847);
    assertEquals(result.getCost(), "$961.75");
  }

  /*
  Here we pass two locations which are not connected
  and waiting for out custom exception to throw
  */
  @Test
  public void calculateTripExceptionTest() {
    // given
    Pair<String, String> locations = new Pair<>("Appleby Line", "QEW");

    // when
    Exception exception =
        assertThrows(
            LocationCalculationException.class,
            () -> {
              tripCalculatorService.calculateTrip(locations);
            });
    String expectedMessage =
        "Locations - "
            + locations.getKey()
            + " and "
            + locations.getValue()
            + " are not connected.";
    String actualMessage = exception.getMessage();

    // then
    assertEquals(expectedMessage, actualMessage);
  }
}
