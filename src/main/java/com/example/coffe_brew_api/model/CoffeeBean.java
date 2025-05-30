// src/main/java/com/example/coffeebrewapi/model/CoffeeBean.java
package com.example.coffe_brew_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;

@Entity
public class CoffeeBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "名前は必須です")
    private String name;

    @NotBlank(message ="原産国は必須です")
    private String origin;

    @NotBlank(message = "フレーバーは必須です")
    private String flavor;

    
    private String brewMethod;
    
    // コンストラクタ
    public CoffeeBean() {}

    public CoffeeBean(String name, String origin, String flavor) {
        this.name = name;
        this.origin = origin;
        this.flavor = flavor;
        this.brewMethod = brewMethod;
    }

    // ゲッター・セッター
    public Long getId() {
        return id;
    }

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

    public String getBrewMethod() {
        return brewMethod;
    }

    public void setBrewMethod(String brewMethod) {
        this.brewMethod = brewMethod;
    }
}