// src/main/java/com/example/coffeebrewapi/controller/CoffeeBeanController.java
package com.example.coffe_brew_api.controller;

import com.example.coffe_brew_api.model.CoffeeBean;
import com.example.coffe_brew_api.dto.CoffeeBeanRequestDto;
import com.example.coffe_brew_api.model.BrewMethod;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.coffe_brew_api.dto.CoffeeBeanResponseDto;
import com.example.coffe_brew_api.service.CoffeeBeanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/beans")
@CrossOrigin(origins = "http://localhost:3000")
public class CoffeeBeanController {

    private final CoffeeBeanService service;

    public CoffeeBeanController(CoffeeBeanService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<CoffeeBeanResponseDto> getBeans(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String flavor,
            @RequestParam(required = false) BrewMethod brewMethod
    ) {
        return service.getBeans(name, origin, flavor, brewMethod);
    }
    
    @GetMapping("/search")
    public List<CoffeeBeanResponseDto> searchBeans(@RequestParam(required = false) String keyword) {
        return service.searchByKeyword(keyword);
    }
    
    @PostMapping
    public CoffeeBean addBean(@Valid @RequestBody CoffeeBeanRequestDto dto) {
        return service.addBean(dto);
    }
}