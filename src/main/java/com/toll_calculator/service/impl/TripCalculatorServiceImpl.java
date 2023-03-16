package com.toll_calculator.service.impl;

import com.toll_calculator.dto.CalculationResultDTO;
import com.toll_calculator.dto.LocationDTO;
import com.toll_calculator.exception.LocationCalculationException;
import com.toll_calculator.service.JsonProcessingService;
import com.toll_calculator.service.TripCalculatorService;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * This is an implementation of the TripCalculatorService interface, the basic logic of calculating
 * the cost of the trip is described here
 *
 * @author Arthur Babaev
 */
@Service
@RequiredArgsConstructor
public class TripCalculatorServiceImpl implements TripCalculatorService {

  private final JsonProcessingService jsonProcessingService;
  private static final double TOLL_RATE = 0.25;

  /*
  First save in the List all the locations from interchanges.json.
  There are multiple options how to do this, for example with a help of REST,
  we could create a different request and method which will read json file once again
  and load new location data, but we will keep it simple for now :)
   */
  @Override
  public CalculationResultDTO calculateTrip(Pair<String, String> tripLocations) {
    List<LocationDTO> locations = jsonProcessingService.convertAndSaveJsonData();

    LocationDTO firstLocationDTO = getLocationByName(locations, tripLocations.getKey());
    LocationDTO secondLocationDTO = getLocationByName(locations, tripLocations.getValue());

    if (isLocationsConnected(firstLocationDTO, secondLocationDTO)) {
      long distance = getDistanceBetweenLocations(firstLocationDTO, secondLocationDTO);
      String costInDollars = getCostOfTripByDistance(distance);

      return CalculationResultDTO.builder().distance(distance).cost(costInDollars).build();
    } else {
      throw new LocationCalculationException(
          "Locations - "
              + tripLocations.getKey()
              + " and "
              + tripLocations.getValue()
              + " are not connected.");
    }
  }

  // Define a method to get a location by its name
  public LocationDTO getLocationByName(List<LocationDTO> locations, String locationName) {
    return locations.stream()
        .filter(location -> location.getName().equals(locationName))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("No matching location found"));
  }

  // Define a method to check if two locations are connected by a route
  public boolean isLocationsConnected(LocationDTO firstLocation, LocationDTO secondLocation) {
    return firstLocation.getRoutes().stream()
            .anyMatch(route -> route.getToId().equals(secondLocation.getId()))
        && secondLocation.getRoutes().stream()
            .anyMatch(route -> route.getToId().equals(firstLocation.getId()));
  }

  // Define a method to check the distance between two locations
  public long getDistanceBetweenLocations(LocationDTO firstLocation, LocationDTO secondLocation) {
    double distance =
        firstLocation.getRoutes().stream()
            .filter(route -> route.getToId().equals(secondLocation.getId()))
            .findFirst()
            .orElseThrow(
                () -> {
                  throw new LocationCalculationException(
                      "Could not find connected routes between "
                          + firstLocation.getName()
                          + " and "
                          + secondLocation.getName());
                })
            .getDistance();

    String stringValue = Double.toString(distance);
    return Long.parseLong(stringValue.replace(".", ""));
  }

  // Define a helper method to calculate cost of trip
  public String getCostOfTripByDistance(long distance) {
    double amount = distance * TOLL_RATE;

    // Create a NumberFormat object with US locale and currency style
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

    // Format the amount as a currency string
    return formatter.format(amount);
  }
}
