package com.example.coffe_brew_api.repository;

import com.example.coffe_brew_api.model.CoffeeBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CoffeeBeanRepository extends JpaRepository<CoffeeBean, Long> {
   List<CoffeeBean> findByNameContainingIgnoreCase(String name);
   List<CoffeeBean> findByOriginContainingIgnoreCase(String origin);
   List<CoffeeBean> findByFlavorContainingIgnoreCase(String flavor);
   List<CoffeeBean> findByBrewMethod(com.example.coffe_brew_api.model.BrewMethod brewMethod);
   
   @Query("SELECT c FROM CoffeeBean c WHERE " +
          "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
          "LOWER(c.origin) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
          "LOWER(c.flavor) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
          "LOWER(str(c.brewMethod)) LIKE LOWER(CONCAT('%', :keyword, '%'))")
      List<CoffeeBean> searchByKeyword(@Param("keyword") String keyword);
}
