package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.EmployeeDto;
import ru.safoev.entity.EmployeeEntity;
import ru.safoev.entity.GymEntity;
import ru.safoev.mappers.EmployeeMapper;
import ru.safoev.repositoryinterface.EmployeeRepository;
import ru.safoev.repositoryinterface.GymRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final GymRepository gymRepository;
  private final EmployeeMapper employeeMapper;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository,GymRepository gymRepository, EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.gymRepository = gymRepository;
    this.employeeMapper = employeeMapper;
  }

  public EmployeeDto getEmployeeById(Long id) {
    EmployeeEntity employeeEntity = employeeRepository.findById(id).
            orElseThrow(()-> new IllegalArgumentException("Employee not found with id: " + id));
    return employeeMapper.toDto(employeeEntity);
  }

  public List<EmployeeDto> getAllEmployees() {
    return employeeRepository.findAll().stream()
            .map(employeeMapper::toDto)
            .collect(Collectors.toList());
  }

  public EmployeeDto createEmployees(EmployeeDto employeeDto) {
    EmployeeEntity entityToSave = employeeMapper.toEntity(employeeDto);
    if (employeeDto.gymId() != null) {
      GymEntity gym = gymRepository.findById(employeeDto.gymId())
              .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + employeeDto.gymId()));
      entityToSave.setGym(gym);
    }
    EmployeeEntity saved = employeeRepository.save(entityToSave);
    return employeeMapper.toDto(saved);
  }

  public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
    EmployeeEntity employee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

    employeeMapper.updateEntityFromDto(employeeDto,employee);
    if (employeeDto.gymId() != null) {
      GymEntity gym = gymRepository.findById(employeeDto.gymId())
              .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + employeeDto.gymId()));
      employee.setGym(gym);
    }

    EmployeeEntity updated = employeeRepository.save(employee);
    return employeeMapper.toDto(updated);
  }

  public void deleteEmployee(Long id) {
    if (!employeeRepository.existsById(id)) {
      throw new NoSuchElementException("Employee not found with id: " + id);
    }
    employeeRepository.deleteById(id);
  }

}
