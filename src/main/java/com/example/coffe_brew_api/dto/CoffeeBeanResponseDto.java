package com.example.coffe_brew_api.dto;

import com.example.coffe_brew_api.model.BrewMethod;

public class CoffeeBeanResponseDto {
  private Long id;
  private String name;
  private String origin;
  private String flavor;
  private String brewMethod;

  public CoffeeBeanResponseDto(Long id, String name, String origin, String flavor, String brewMethod) {
    this.id = id;
    this.name = name;
    this.origin = origin;
    this.flavor = flavor;
    this.brewMethod = brewMethod;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getOrigin(){
    return origin;
  }

  public String getFlavor() {
    return flavor;
  }
  
  public String getBrewMethod() {
    return brewMethod;
  }
}
