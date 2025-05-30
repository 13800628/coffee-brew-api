package com.example.coffe_brew_api.dto;

import java.util.List;

public class BrewMethodResponse {
  private String name;
  private String description;
  private List<String> steps;
  private Integer timerSeconds;

  // 4引数のコンストラクタ（メイン）
  public BrewMethodResponse(String name, String description, List<String> steps, Integer timerSeconds) {
    this.name = name;
    this.description = description;
    this.steps = steps;
    this.timerSeconds = timerSeconds;
  }

  // timerSeconds 省略時のコンストラクタ（thisで上を呼ぶ）
  public BrewMethodResponse(String name, String description, List<String> steps) {
    this(name, description, steps, null);
  }

  public Integer getTimerSeconds() {
    return timerSeconds;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getSteps() {
    return steps;
  }
}