// src/main/java/com/example/coffe_brew_api/model/BrewMethod.java
package com.example.coffe_brew_api.model;

public enum BrewMethod {
    POUR_OVER("Pour Over"),
    FRENCH_PRESS("French Press"),
    ESPRESSO("Espresso"),
    SIPHON("Siphon"),
    PAPER_DRIP("Paper Drip"),
    HAND_DRIP("Hand Drip");

    
    private final String value;

    BrewMethod(String value) {
        this.value = value;
    }

    public String toValue() {
        return value;
    }

    public static BrewMethod fromValue(String value) {
        for (BrewMethod method : BrewMethod.values()) {
            if (method.value.equalsIgnoreCase(value)) {
                return method;
            }
        }
        throw new IllegalArgumentException("Unknown brew method: " + value);
    }
}