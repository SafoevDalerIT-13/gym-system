package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.EmployeeDto;
import ru.safoev.entity.EmployeeEntity;

/**
 * Маппер для преобразования между сущностью EmployeeEntity и DTO EmployeeDto.
 * <p>
 * Этот компонент отвечает за преобразование данных между слоем сущностей
 * и слоем передачи данных для сотрудников фитнес-центра.
 * Обрабатывает связь с сущностью GymEntity для получения/установки идентификатора зала.
 * </p>
 *
 * @Component указывает, что этот класс является Spring компонентом
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Component
public class EmployeeMapper {

  /**
   * Преобразует сущность EmployeeEntity в DTO EmployeeDto.
   * <p>
   * Используется при получении данных сотрудника из базы данных
   * для передачи клиенту API. Извлекает идентификатор зала из связанной
   * сущности GymEntity.
   * </p>
   *
   * @param employeeEntity сущность сотрудника из базы данных
   * @return DTO сотрудника для передачи данных
   */
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

  /**
   * Преобразует DTO EmployeeDto в сущность EmployeeEntity.
   * <p>
   * Используется при создании нового сотрудника или полном обновлении
   * существующего сотрудника.
   * Поле gym устанавливается в null и должно быть установлено отдельно.
   * </p>
   *
   * @param dto DTO сотрудника, полученный от клиента API
   * @return сущность сотрудника для сохранения в базе данных
   */
  public EmployeeEntity toEntity(EmployeeDto dto) {
    return new EmployeeEntity(
            dto.employeeId(),
            dto.firstName(),
            dto.lastName(),
            dto.empPhone(),
            dto.empEmail(),
            null, // gym устанавливается отдельно
            dto.hireDate(),
            dto.dismissalDate(),
            dto.post(),
            dto.salary()
    );
  }

  /**
   * Обновляет существующую сущность EmployeeEntity данными из DTO EmployeeDto.
   * <p>
   * Используется для частичного обновления сотрудника.
   * Обновляются только те поля, которые не являются null в DTO.
   * Поле gym не обновляется в этом методе и должно обновляться отдельно.
   * </p>
   *
   * @param dto DTO с новыми данными сотрудника
   * @param entity существующая сущность сотрудника, которую нужно обновить
   */
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
    if (dto.empEmail() != null) {
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