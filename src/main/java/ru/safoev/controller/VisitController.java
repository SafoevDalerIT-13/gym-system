package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.VisitDto;
import ru.safoev.services.VisitService;

import java.util.List;

@RestController
@RequestMapping("/gym/visit")
public class VisitController {
  private static final Logger log = LoggerFactory.getLogger(VisitController.class);

  private final VisitService visitService;

  @Autowired
  public VisitController(VisitService visitService) {
    this.visitService = visitService;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<VisitDto> getVisitById(@PathVariable("id") Long id) {
    log.info("Called getVisitById with id: {}", id);
    return ResponseEntity.ok(visitService.getVisitById(id));
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<VisitDto>> getAllVisits() {
    log.info("Called getAllVisits");
    return ResponseEntity.ok(visitService.getAllVisits());
  }

  @PostMapping("/create")
  public ResponseEntity<VisitDto> createVisit(@Valid @RequestBody VisitDto visitDto) {
    log.info("Called createVisit with data: {}", visitDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(visitService.createVisit(visitDto));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<VisitDto> updateVisit(@PathVariable("id") Long id, @Valid @RequestBody VisitDto visitDto) {
    log.info("Called updateVisit with id: {} and data: {}", id, visitDto);
    return ResponseEntity.ok(visitService.updateVisit(id, visitDto));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteVisit(@PathVariable("id") Long id) {
    log.info("Called deleteVisit with id: {}", id);
    visitService.deleteVisit(id);
    return ResponseEntity.noContent().build();
  }
}