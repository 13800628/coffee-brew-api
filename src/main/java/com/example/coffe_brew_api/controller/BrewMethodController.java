package com.example.coffe_brew_api.controller;

import com.example.coffe_brew_api.dto.BrewMethodResponse;
import com.example.coffe_brew_api.service.BrewMethodService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/brew-methods")
public class BrewMethodController {
  
  private final BrewMethodService brewMethodService;

  public BrewMethodController(BrewMethodService brewMethodService) {
    this.brewMethodService = brewMethodService;
  }

  @GetMapping
  public List<BrewMethodResponse> getBrewMethod() {
    return brewMethodService.getAllMethods();
  }
}
