package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.EmployeeDto;
import ru.safoev.entity.EmployeeEntity;

@Component
public class EmployeeMapper {
  public EmployeeDto toDto(EmployeeEntity employeeEntity) {
    Long gymId = employeeEntity.getGym() != null ? employeeEntity.getGym().getGym_id() : null;
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

  public EmployeeEntity toEntity(EmployeeDto dto) {
    return new EmployeeEntity(
            dto.employeeId(),
            dto.firstName(),
            dto.lastName(),
            dto.empPhone(),
            dto.empEmail(),
            null,
            dto.hireDate(),
            dto.dismissalDate(),
            dto.post(),
            dto.salary()
    );
  }

  public void updateEntityFromDto(EmployeeDto dto, EmployeeEntity entity) {
    if (dto.firstName() != null) {
      entity.setEmployees_first_name(dto.firstName());
    }
    if (dto.lastName() != null) {
      entity.setEmployees_last_name(dto.lastName());
    }
    if (dto.empPhone() != null) {
      entity.setEmployees_phone(dto.empPhone());
    }
    if (dto.empEmail()!= null) {
      entity.setEmployees_email(dto.empEmail());
    }
    if (dto.hireDate() != null) {
      entity.setEmployees_hire_date(dto.hireDate());
    }
    if (dto.dismissalDate() != null) {
      entity.setEmployees_dismissalDate(dto.dismissalDate());
    }
    if (dto.post() != null) {
      entity.setEmployees_post(dto.post());
    }
    if (dto.salary() != null) {
      entity.setEmployees_salary(dto.salary());
    }

  }

}
