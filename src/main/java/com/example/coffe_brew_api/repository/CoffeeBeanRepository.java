package com.example.coffe_brew_api.repository;

import com.example.coffe_brew_api.model.CoffeeBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeBeanRepository extends JpaRepository<CoffeeBean, Long> {
   List<CoffeeBean> findByNameContainingIgnoreCase(String name);
   List<CoffeeBean> findByOriginContainingIgnoreCase(String origin);
   List<CoffeeBean> findByFlavorContainingIgnoreCase(String flavor);
   List<CoffeeBean> findByBrewMethodContainingIgnoreCase(String brewMethod);
   List<CoffeeBean>
   findByNameContainingIgnoreCaseOrOriginContainingIgnoreCaseOrFlavorContainingIgnoreCaseOrBrewMethodContainingIgnoreCase(
         String name, String origin, String flavor, String brewMethod
   );
}
