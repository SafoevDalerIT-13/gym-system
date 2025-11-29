package ru.safoev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/gym")
  public String home() {
    return "Фитнес-система запущена! Доступные endpoints: " +
            "GET /clients, POST /clients, GET /clients/{id}";
  }
}