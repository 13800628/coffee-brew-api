package com.example.coffe_brew_api.service;

import com.example.coffe_brew_api.model.CoffeeBean;
import com.example.coffe_brew_api.repository.CoffeeBeanRepository;
import com.example.coffe_brew_api.dto.CoffeeBeanRequestDto;
import com.example.coffe_brew_api.dto.CoffeeBeanResponseDto;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.example.coffe_brew_api.model.BrewMethod;

@Service
public class CoffeeBeanService {
  
  private final CoffeeBeanRepository repository;

  public CoffeeBeanService(CoffeeBeanRepository repository) {
    this.repository = repository;
  }

  public CoffeeBeanResponseDto createCoffeeBean(CoffeeBeanRequestDto dto) {
    Objects.requireNonNull(dto, "request must not be null");
    CoffeeBean saved = addBean(dto);
    return convertToDto(saved);
  }
  
  public List<CoffeeBeanResponseDto> getBeans(String name, String origin, String flavor, BrewMethod brewMethod) {
  CoffeeBean probe = new CoffeeBean();
  probe.setName(name);
  probe.setOrigin(origin);
  probe.setFlavor(flavor);
  probe.setBrewMethod(brewMethod);

  ExampleMatcher matcher = ExampleMatcher.matchingAll()
      .withIgnoreCase()
      .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
      .withIgnoreNullValues();

  Example<CoffeeBean> example = Example.of(probe, matcher);
  List<CoffeeBean> beans = repository.findAll(example);

    return beans.stream()
        .map(this::convertToDto)
        .toList();
  }
  
  public List<CoffeeBeanResponseDto> searchByKeyword(String keyword) {
    Objects.requireNonNull(keyword, "keyword must not be null");

    List<CoffeeBean> beans;
    if (!keyword.trim().isEmpty()){
      beans = repository.searchByKeyword(keyword);
    } else {
      beans = repository.findAll();
    }

    return beans.stream()
        .map(this::convertToDto)
        .toList();
  }
  
  public CoffeeBean addBean(CoffeeBeanRequestDto dto) {
    if (dto.getName() == null) {
      throw new IllegalArgumentException("name must not be null");
    }
    CoffeeBean bean = new CoffeeBean(
      dto.getName(),
      dto.getOrigin(),
      dto.getFlavor(),
      dto.getBrewMethod()
    );

    return repository.save(bean);
  }

  public CoffeeBeanResponseDto convertToDto(CoffeeBean bean) {
    Objects.requireNonNull(bean, "bean must not be null");
    return new CoffeeBeanResponseDto(
        bean.getId(), 
        bean.getName(), 
        bean.getOrigin(), 
        bean.getFlavor(), 
        bean.getBrewMethod().toValue()
      );
  }
}
