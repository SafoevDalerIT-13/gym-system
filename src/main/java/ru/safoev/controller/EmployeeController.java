package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.EmployeeDto;
import ru.safoev.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/gym/employee")
public class EmployeeController {
  private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) {
    log.info("Called getEmployeeById with id: {}",id);
    return ResponseEntity.ok(employeeService.getEmployeeById(id));
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    log.info("Called getAllEmployees");
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  @PostMapping("/create")
  public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
    log.info("Called create Employee");
    return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployees(employeeDto));
  }

  @PutMapping("/update")
  public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id, EmployeeDto employeeDto) {
    log.info("Called updateEmployee with id: {}", id);
    return ResponseEntity.ok(employeeService.updateEmployee(id,employeeDto));
  }

  @DeleteMapping("/delete")
  public void deleteEmployee(@PathVariable("id") Long id) {
    log.info("Called deleteEmployee with id: {}",id);
    employeeService.deleteEmployee(id);
  }

}
