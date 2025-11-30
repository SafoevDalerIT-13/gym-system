package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.EmployeeDto;
import ru.safoev.dtorecords.EquipmentDto;
import ru.safoev.services.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/gym/equipment")
public class EquipmentController {
  private static final Logger log = LoggerFactory.getLogger(EquipmentController.class);

  private final EquipmentService equipmentService;

  @Autowired
  public EquipmentController(EquipmentService equipmentService) {
    this.equipmentService = equipmentService;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<EquipmentDto> getEquipmentById(@PathVariable("id") Long id) {
    log.info("Called getEquipmentById with id: {}", id);
    return ResponseEntity.ok(equipmentService.getEquipmentById(id));
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<EquipmentDto>> getAllEquipment() {
    log.info("Called getAllEquipment");
    return ResponseEntity.ok(equipmentService.getAllEquipment());
  }

  @PostMapping("/create")
  public ResponseEntity<EquipmentDto> createEquipment(@Valid @RequestBody EquipmentDto equipmentDto) {
    log.info("Called createEquipment");
    return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.createEquipment(equipmentDto));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<EquipmentDto> updateEquipment(@PathVariable("id") Long id, EquipmentDto equipmentDto) {
    log.info("Called updateEmployee with id: {}", id);
    return ResponseEntity.ok(equipmentService.updateEquipment(id,equipmentDto));
  }

  @DeleteMapping("/delete/{id}")
  public void deleteEquipment(@PathVariable("id") Long id) {
    log.info("Called deleteEmployee with id: {}",id);
    equipmentService.deleteEquipment(id);
  }
}
