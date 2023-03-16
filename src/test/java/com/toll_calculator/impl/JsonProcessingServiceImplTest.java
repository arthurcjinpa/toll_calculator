package com.toll_calculator.impl;

import com.toll_calculator.BaseTest;
import com.toll_calculator.dto.LocationDTO;
import com.toll_calculator.service.impl.JsonProcessingServiceImpl;
import org.junit.AfterClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonProcessingServiceImplTest extends BaseTest {

    private static String originalJsonName;

    // After tests initialization, we will return the original json name
    @AfterClass
    public static void tearDownAfterClass() throws NoSuchFieldException, IllegalAccessException {
        Field field = JsonProcessingServiceImpl.class.getDeclaredField("JSON_NAME");
        field.setAccessible(true);

        // Set the value of the private field back to its original value
        field.set(null, originalJsonName);
    }

    @Test
    public void convertAndSaveOriginalJsonDataTest() {
        // when
        List<LocationDTO> locationDTOS = jsonProcessingService.convertAndSaveJsonData();

        // then
        assertNotNull(locationDTOS);
        assertEquals(locationDTOS.size(), 44);
    }

    /*
     In this scenario by using reflection to access the private field
     we will change the original json name to the one we will use in the test.

     Also, despite the fact we added new field and make routes array empty,
     json is still readable and deserializable
     */
    @Test
    public void convertAndSaveNewJsonDataTest() throws NoSuchFieldException, IllegalAccessException {
        // given
        Field field = JsonProcessingServiceImpl.class.getDeclaredField("JSON_NAME");
        field.setAccessible(true);
        // Save the original name of json so in tearDownAfterClass() we could return it back
        originalJsonName = (String) field.get(null);
        field.set(null, "test_interchanges.json");

        // when
        List<LocationDTO> locationDTOS = jsonProcessingService.convertAndSaveJsonData();

        // then
        assertNotNull(locationDTOS);
        assertEquals(locationDTOS.size(), 2);
    }

    @Test(expected = RuntimeException.class)
    public void convertAndSaveNewJsonDataExceptionTest() throws NoSuchFieldException, IllegalAccessException {
        // given
        Field field = JsonProcessingServiceImpl.class.getDeclaredField("JSON_NAME");
        field.setAccessible(true);
        field.set(null, "test_exception_interchanges.json");

        // when
        jsonProcessingService.convertAndSaveJsonData();
    }
}
