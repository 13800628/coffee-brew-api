// src/main/java/com/example/coffeebrewapi/model/CoffeeBean.java
package com.example.coffe_brew_api.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

@Entity
public class CoffeeBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String origin;
    
    private String flavor;

    @Enumerated(EnumType.STRING)
    @Column(name = "brew_method")
    private BrewMethod brewMethod;
    
    // コンストラクタ
    public CoffeeBean() {}

    public CoffeeBean(String name, String origin, String flavor, BrewMethod brewMethod) {
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
    
    public BrewMethod getBrewMethod() {
        return brewMethod;
    }
    public void setBrewMethod(BrewMethod brewMethod) {
        this.brewMethod = brewMethod;
    }
}