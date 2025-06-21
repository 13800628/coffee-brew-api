package com.example.coffe_brew_api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.coffe_brew_api.dto.CoffeeBeanRequestDto;
import com.example.coffe_brew_api.dto.CoffeeBeanResponseDto;
import com.example.coffe_brew_api.model.BrewMethod;
import com.example.coffe_brew_api.service.CoffeeBeanService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CoffeeBeanController.class)
public class CoffeeBeanControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CoffeeBeanService coffeeBeanService;

  @Test
  @DisplayName("POST /api/coffee-beans - 正常系")
  void createCoffeeBean_success() throws Exception {
    CoffeeBeanRequestDto request = new CoffeeBeanRequestDto();
    request.setName("Ethiopia");
    request.setOrigin("Africa");
    request.setFlavor("Fruity and floral");
    request.setBrewMethod(BrewMethod.HAND_DRIP);

    CoffeeBeanResponseDto responseDto = new CoffeeBeanResponseDto(
      1L,
    "Ethiopia",
    "Africa",
    "Fruity and floral",
    "HAND_DRIP"
    );

    when(coffeeBeanService.createCoffeeBean(any(CoffeeBeanRequestDto.class))).thenReturn(responseDto);

    mockMvc.perform(post("/beans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.name").value("Ethiopia"));
  }

  @Test
  @DisplayName("POST /api/coffee-beans - 異常系(nameが空)")
  void createCoffeeBean_nameIsBlank() throws Exception {
    CoffeeBeanRequestDto request = new CoffeeBeanRequestDto();
    request.setName("");
    request.setOrigin("Colombia");
    request.setFlavor("Rich flavor");
    request.setBrewMethod(BrewMethod.HAND_DRIP);



    mockMvc.perform(post("/beans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.fieldErrors.name").exists());
  }

  @Test
  @DisplayName("POST /beans - 異常系(nameが1文字で短すぎる)")
  void createCoffeeBean_nameTooShort() throws Exception {
    CoffeeBeanRequestDto request = new CoffeeBeanRequestDto();
    request.setName("A");
    request.setOrigin("Colombia");
    request.setFlavor("Rich flavor");
    request.setBrewMethod(BrewMethod.HAND_DRIP);

    mockMvc.perform(post("/beans")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.fieldErrors.name").exists());
  }

  @Test
  @DisplayName("POST /beans - 異常系(nameが長すぎる)")
  void createCoffeeBean_nameTooLong() throws Exception {
    CoffeeBeanRequestDto request = new CoffeeBeanRequestDto();
    request.setName("ThisNameIsWayTooLongToBeValid");
    request.setOrigin("Colombia");
    request.setFlavor("Rich flavor");
    request.setBrewMethod(BrewMethod.HAND_DRIP);

    mockMvc.perform(post("/beans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.fieldErrors.name").exists());
  }

  @Test
  @DisplayName("POST /beans - 異常系(originが1文字で短すぎる)")
  void createCoffeeBean_originTooShort() throws Exception {
    CoffeeBeanRequestDto request = new CoffeeBeanRequestDto();
    request.setName("Colombia");
    request.setOrigin("A");
    request.setFlavor("Citrusy and rich");
    request.setBrewMethod(BrewMethod.HAND_DRIP);

    mockMvc.perform(post("/beans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.fieldErrors.origin").exists());
  }

  @Test
  @DisplayName("POST /api/coffee-beans - 異常系(brewMethodが不正な値)")
  void createCoffeeBean_invalidBrewMethod() throws Exception {
    CoffeeBeanRequestDto request = new CoffeeBeanRequestDto();
    request.setName("Brazilian");
    request.setOrigin("Brazil");
    request.setFlavor("Nutty");
    request.setBrewMethod(null);


    mockMvc.perform(post("/beans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.message").exists());
  }

  @Test
  @DisplayName("POST /beans - 異常系(originが空)")
  void createCoffeeBean_originIsBlank() throws Exception {
    CoffeeBeanRequestDto request = new CoffeeBeanRequestDto();
    request.setName("Kenya");
    request.setOrigin("");
    request.setFlavor("Citrus");
    request.setBrewMethod(BrewMethod.HAND_DRIP);

    mockMvc.perform(post("/beans")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsBytes(request)))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.fieldErrors.origin").exists());
  }
}
