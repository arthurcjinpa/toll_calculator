package com.toll_calculator;

import com.toll_calculator.service.JsonProcessingService;
import com.toll_calculator.service.TripCalculatorService;
import com.toll_calculator.utils.LocationDeserializer;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * For our convenience we have put the necessary annotations and dependencies for tests in a
 * separate class
 *
 * @author Arthur Babaev
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Ignore
public class BaseTest {
  @Autowired protected LocationDeserializer locationDeserializer;
  @Autowired protected TripCalculatorService tripCalculatorService;
  @Autowired protected JsonProcessingService jsonProcessingService;
}
