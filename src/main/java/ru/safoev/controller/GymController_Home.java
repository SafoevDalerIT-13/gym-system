package ru.safoev.controller;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.GymDto;
import ru.safoev.services.GymService;

import java.util.List;

@RestController
@RequestMapping("/gym")
public class GymController_Home {
  private static final Logger log = LoggerFactory.getLogger(GymController_Home.class);

  private final GymService gymService;

  @Autowired
  public GymController_Home(GymService gymService) {
    this.gymService = gymService;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<GymDto> getGymById(@PathVariable("id") Long id) {
    log.info("Called getGymById with id: {}", id);
    return ResponseEntity.ok(gymService.getGymById(id));
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<GymDto>> getAllGyms() {
    log.info("Called getAllGyms");
    return ResponseEntity.ok(gymService.getAllGym());
  }

  @PostMapping("/create")
  public ResponseEntity<GymDto> createGym(@Valid @RequestBody GymDto gymDto) {
    log.info("Called createGym");
    return ResponseEntity.status(HttpStatus.CREATED).body(gymService.createGym(gymDto));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<GymDto> updateGym(@PathVariable("id") Long id, @Valid GymDto gymDto) {
    log.info("Called updateGym with id: {}",id);
    return ResponseEntity.ok(gymService.updateGym(id,gymDto));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteGym(@PathVariable("id") Long id) {
    log.info("Called updateGym with id: {}",id);
    gymService.deleteGym(id);
    return ResponseEntity.noContent().build();
  }

}
