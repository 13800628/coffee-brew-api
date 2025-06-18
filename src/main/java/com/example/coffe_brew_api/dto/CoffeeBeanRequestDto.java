package com.example.coffe_brew_api.dto;

import com.example.coffe_brew_api.model.BrewMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CoffeeBeanRequestDto {
    @NotBlank(message = "{coffeeBean.name.notBlank}")
    @Size(min = 2, max = 20, message = "{coffeeBean.name.size}")
    private String name;
    
    @NotBlank(message = "{coffeeBean.origin.notBlank}")
    @Size(min = 2, max = 20, message = "{coffeeBean.origin.size}")
    private String origin;
    
    @NotBlank(message = "{coffeeBean.flavor.notBlank}")
    @Size(min = 2, max = 50, message = "{coffeeBean.flavor.size}")
    private String flavor;
    
    @NotNull(message = "{coffeeBean.brewMethod.notNull}")
    private BrewMethod brewMethod;

    public CoffeeBeanRequestDto () {
    };

    public CoffeeBeanRequestDto(String name, String origin, String flavor, BrewMethod brewMethod){
        this.name = name;
        this.origin = origin;
        this.flavor = flavor;
        this.brewMethod = brewMethod;
    }

    // ゲッター・セッター
  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  public String getOrigin() {
      return origin;
  }

  public void setOrigin(String origin) {
      this.origin = origin;
  }

  public String getFlavor() {
      return flavor;
  }

  public void setFlavor(String flavor) {
      this.flavor = flavor;
  }

  public BrewMethod getBrewMethod() {
      return brewMethod;
  }

  public void setBrewMethod(BrewMethod brewMethod) {
        this.brewMethod = brewMethod;
  }
}
