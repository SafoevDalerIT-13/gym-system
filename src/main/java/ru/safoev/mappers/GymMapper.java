package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.GymDto;
import ru.safoev.entity.GymEntity;

/**
 * Маппер для преобразования между сущностью GymEntity и DTO GymDto.
 * <p>
 * Этот компонент отвечает за преобразование данных между слоем сущностей
 * и слоем передачи данных для фитнес-залов.
 * Используется для изоляции логики преобразования данных фитнес-залов.
 * </p>
 *
 * @Component указывает, что этот класс является Spring компонентом
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Component
public class GymMapper {

  /**
   * Преобразует сущность GymEntity в DTO GymDto.
   * <p>
   * Используется при получении данных фитнес-зала из базы данных
   * для передачи клиенту API.
   * </p>
   *
   * @param entity сущность фитнес-зала из базы данных
   * @return DTO фитнес-зала для передачи данных
   */
  public GymDto toDto(GymEntity entity) {
    return new GymDto(
            entity.getGym_id(),
            entity.getGym_name(),
            entity.getGym_address(),
            entity.getGym_phone(),
            entity.getGym_open_time(),
            entity.getGym_end_time()
    );
  }

  /**
   * Преобразует DTO GymDto в сущность GymEntity.
   * <p>
   * Используется при создании нового фитнес-зала или полном обновлении
   * существующего фитнес-зала.
   * </p>
   *
   * @param dto DTO фитнес-зала, полученный от клиента API
   * @return сущность фитнес-зала для сохранения в базе данных
   */
  public GymEntity toEntity(GymDto dto) {
    return new GymEntity(
            dto.gymId(),
            dto.gymName(),
            dto.address(),
            dto.gymPhone(),
            dto.openTime(),
            dto.closeTime()
    );
  }

  /**
   * Обновляет существующую сущность GymEntity данными из DTO GymDto.
   * <p>
   * Используется для частичного обновления фитнес-зала.
   * Обновляются только те поля, которые не являются null в DTO.
   * </p>
   *
   * @param dto DTO с новыми данными фитнес-зала
   * @param entity существующая сущность фитнес-зала, которую нужно обновить
   */
  public void updateEntityFromDto(GymDto dto, GymEntity entity) {
    if (dto.gymName() != null) {
      entity.setGym_name(dto.gymName());
    }
    if (dto.address() != null) {
      entity.setGym_address(dto.address());
    }
    if (dto.gymPhone() != null) {
      entity.setGym_phone(dto.gymPhone());
    }
    if (dto.openTime() != null) {
      entity.setGym_open_time(dto.openTime());
    }
    if (dto.closeTime() != null) {
      entity.setGym_end_time(dto.closeTime());
    }
  }
}