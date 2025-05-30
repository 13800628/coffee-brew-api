package com.example.coffe_brew_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.coffe_brew_api.model.CoffeeBean;
import com.example.coffe_brew_api.repository.CoffeeBeanRepository;

@SpringBootApplication
public class CoffeBrewApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeBrewApiApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(CoffeeBeanRepository repository) {
		return args -> {
			repository.save(new CoffeeBean("エチオピアモカ", "エチオピア", "フルーティーで酸味がある"));
			repository.save(new CoffeeBean("コロンビア スプレモ", "コロンビア", "バランスが良くコクがある"));
			repository.save(new CoffeeBean("ブルーマウンテン", "ジャマイカ", "酸味・苦味・コク全てがマイルド"));
			repository.save(new CoffeeBean("グアテマラ アンティグア", "グアテマラ", "チョコレートのような甘みと酸味のバランスがいい"));
			repository.save(new CoffeeBean("ブラジル サントス", "ブラジル", "ナッツやチョコ系の香りと甘味が特徴"));
		};
	}
}
