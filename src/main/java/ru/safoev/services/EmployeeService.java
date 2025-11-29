package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.safoev.dtorecords.EmployeeDto;
import ru.safoev.entity.EmployeeEntity;
import ru.safoev.entity.GymEntity;
import ru.safoev.repositoryinterface.EmployeeRepository;
import ru.safoev.repositoryinterface.GymRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final GymRepository gymRepository;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository,GymRepository gymRepository) {
    this.employeeRepository = employeeRepository;
    this.gymRepository = gymRepository;
  }

  public EmployeeDto getEmployeeById(Long id) {
    EmployeeEntity employeeEntity = employeeRepository.findById(id).
            orElseThrow(()-> new IllegalArgumentException("Employee not found with id: " + id));
    return toDtoEmployee(employeeEntity);
  }

  public List<EmployeeDto> getAllEmployees() {
    return employeeRepository.findAll().stream()
            .map(this::toDtoEmployee)
            .collect(Collectors.toList());
  }

  public EmployeeDto createEmployees(EmployeeDto employeeDto) {
    EmployeeEntity employee = new EmployeeEntity();
    employee.setEmployees_first_name(employeeDto.firstName());
    employee.setEmployees_last_name(employeeDto.lastName());
    employee.setEmployees_phone(employeeDto.empPhone());
    employee.setEmployees_email(employeeDto.empEmail());
    employee.setEmployees_post(employeeDto.post());
    employee.setEmployees_salary(employeeDto.salary());
    employee.setEmployees_hire_date(employeeDto.hireDate());
    employee.setEmployees_dismissalDate(employeeDto.dismissalDate());

    EmployeeEntity saved = employeeRepository.save(employee);
    return toDtoEmployee(saved);
  }

  public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
    EmployeeEntity employee = employeeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

    if (employeeDto.firstName() != null) {
      employee.setEmployees_first_name(employeeDto.firstName());
    }
    if (employeeDto.lastName() != null) {
      employee.setEmployees_last_name(employeeDto.lastName());
    }
    if (employeeDto.empPhone() != null) {
      employee.setEmployees_phone(employeeDto.empPhone());
    }
    if (employeeDto.empEmail() != null) {
      employee.setEmployees_email(employeeDto.empEmail());
    }
    if (employeeDto.post() != null) {
      employee.setEmployees_post(employeeDto.post());
    }
    if (employeeDto.salary() != null && employeeDto.salary().compareTo(BigDecimal.ZERO) > 0) {
      employee.setEmployees_salary(employeeDto.salary());
    }
    if (employeeDto.hireDate() != null) {
      employee.setEmployees_hire_date(employeeDto.hireDate());
    }
    if (employeeDto.dismissalDate() != null) {
      employee.setEmployees_dismissalDate(employeeDto.dismissalDate());
    }

    if (employeeDto.gymId() != null) {
      GymEntity gym = gymRepository.findById(employeeDto.gymId())
              .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + employeeDto.gymId()));
      employee.setGym(gym);
    }

    EmployeeEntity updated = employeeRepository.save(employee);
    return toDtoEmployee(updated);
  }

  public void deleteEmployee(Long id) {
    if (!employeeRepository.existsById(id)) {
      throw new NoSuchElementException("Employee not found with id: " + id);
    }
    employeeRepository.deleteById(id);
  }

  private EmployeeDto toDtoEmployee(EmployeeEntity employeeEntity) {
    Long gymId = employeeEntity.getGym() != null ? employeeEntity.getGym().getGum_id() : null;
    return new EmployeeDto(
            employeeEntity.getEmployees_id(),
            employeeEntity.getEmployees_first_name(),
            employeeEntity.getEmployees_last_name(),
            employeeEntity.getEmployees_phone(),
            employeeEntity.getEmployees_email(),
            gymId,
            employeeEntity.getEmployees_hire_date(),
            employeeEntity.getEmployees_dismissalDate(),
            employeeEntity.getEmployees_post(),
            employeeEntity.getEmployees_salary()
    );
  }

}
