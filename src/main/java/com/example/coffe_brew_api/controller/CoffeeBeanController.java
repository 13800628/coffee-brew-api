// src/main/java/com/example/coffeebrewapi/controller/CoffeeBeanController.java
package com.example.coffe_brew_api.controller;

import com.example.coffe_brew_api.model.CoffeeBean;
import com.example.coffe_brew_api.repository.CoffeeBeanRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/beans")
@CrossOrigin(origins = "http://localhost:3000")
public class CoffeeBeanController {

    private final CoffeeBeanRepository repository;

    public CoffeeBeanController(CoffeeBeanRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping
    public List<CoffeeBean> getBeans(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String flavor,
            @RequestParam(required = false) String brewMethod
    ) {
        if (name != null && !name.isEmpty()) {
            return repository.findByNameContainingIgnoreCase(name);
        }
        if (origin != null && !origin.isEmpty()) {
            return repository.findByOriginContainingIgnoreCase(origin);
        }
        if (flavor != null && !flavor.isEmpty()) {
            return repository.findByFlavorContainingIgnoreCase(flavor);
        }
        if (brewMethod != null && !brewMethod.isEmpty()) {
            return repository.findByBrewMethodContainingIgnoreCase(brewMethod);
        }
        return repository.findAll();
    }

    @GetMapping("/search")
    public List<CoffeeBean> searchBeans(
        @RequestParam(required = false) String keyword
    ) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return repository.findByNameContainingIgnoreCaseOrOriginContainingIgnoreCaseOrFlavorContainingIgnoreCaseOrBrewMethodContainingIgnoreCase(
                keyword, keyword, keyword, keyword
            );
        }
        return repository.findAll();
    }
    
    @PostMapping
    public CoffeeBean addBean(@Valid @RequestBody CoffeeBean bean) {
        return repository.save(bean);
    }
}