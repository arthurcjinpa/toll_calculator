package com.toll_calculator.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.toll_calculator.dto.LocationDTO;
import com.toll_calculator.service.JsonProcessingService;
import com.toll_calculator.utils.LocationDeserializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * This is an implementation of the JsonProcessingService interface, here we convert to List and
 * save data from our json file. I've decided to create a difference interface and methods for this
 * logic so as not to violate the SOLID principles.
 *
 * @author Arthur Babaev
 */
@Service
public class JsonProcessingServiceImpl implements JsonProcessingService {

  /*
  Good practice would be make this field final, but for
  testing purposes in this assessment I have removed it
  */
  private static String JSON_NAME = "interchanges.json";

  @Override
  public List<LocationDTO> convertAndSaveJsonData() {
    String jsonString = loadInterchangesJsonAsString();
    return deserializeJsonToList(jsonString);
  }

  /*
  Define a method which task is just to deserialize String json to List
  with the help of our custom LocationDeserializer class
  */
  private List<LocationDTO> deserializeJsonToList(String jsonString) {
    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(List.class, new LocationDeserializer());
    objectMapper.registerModule(module);
    List<LocationDTO> locations;
    try {
      locations = objectMapper.readValue(jsonString, new TypeReference<>() {});
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return locations;
  }

  /*
  This method will find your json file in resources and convert it to String
  for future reference
   */
  private String loadInterchangesJsonAsString() {
    // Load the JSON file from the resources folder
    ClassPathResource resource = new ClassPathResource(JSON_NAME);
    byte[] bytes;
    try {
      bytes = StreamUtils.copyToByteArray(resource.getInputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // Convert the byte array to a string using the UTF-8 encoding
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
