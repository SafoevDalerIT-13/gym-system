package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.EquipmentDto;
import ru.safoev.entity.EquipmentEntity;

/**
 * Маппер для преобразования между сущностью EquipmentEntity и DTO EquipmentDto.
 * <p>
 * Этот компонент отвечает за преобразование данных между слоем сущностей
 * и слоем передачи данных для оборудования фитнес-центра.
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
public class EquipmentMapper {

  /**
   * Преобразует сущность EquipmentEntity в DTO EquipmentDto.
   * <p>
   * Используется при получении данных оборудования из базы данных
   * для передачи клиенту API. Извлекает идентификатор зала из связанной
   * сущности GymEntity.
   * </p>
   *
   * @param entity сущность оборудования из базы данных
   * @return DTO оборудования для передачи данных
   */
  public EquipmentDto toDto(EquipmentEntity entity) {
    Long gymId = entity.getGym() != null ? entity.getGym().getGym_id() : null;

    return new EquipmentDto(
            entity.getEquipmentId(),
            entity.getEquipmentName(),
            entity.getEquipment_buyDate(),
            entity.getEquipmentStatus(),
            gymId
    );
  }

  /**
   * Преобразует DTO EquipmentDto в сущность EquipmentEntity.
   * <p>
   * Используется при создании нового оборудования или полном обновлении
   * существующего оборудования.
   * Поле gym устанавливается в null и должно быть установлено отдельно.
   * </p>
   *
   * @param dto DTO оборудования, полученный от клиента API
   * @return сущность оборудования для сохранения в базе данных
   */
  public EquipmentEntity toEntity(EquipmentDto dto) {
    return new EquipmentEntity(
            dto.equipmentId(),
            dto.equipmentName(),
            dto.buyDate(),
            dto.equipmentStatus(),
            null // gym устанавливается отдельно
    );
  }

  /**
   * Обновляет существующую сущность EquipmentEntity данными из DTO EquipmentDto.
   * <p>
   * Используется для частичного обновления оборудования.
   * Обновляются только те поля, которые не являются null в DTO.
   * Поле gym не обновляется в этом методе и должно обновляться отдельно.
   * </p>
   *
   * @param dto DTO с новыми данными оборудования
   * @param entity существующая сущность оборудования, которую нужно обновлить
   */
  public void updateEntityFromDto(EquipmentDto dto, EquipmentEntity entity) {
    if (dto.equipmentName() != null) {
      entity.setEquipmentName(dto.equipmentName());
    }
    if (dto.buyDate() != null) {
      entity.setEquipment_buyDate(dto.buyDate());
    }
    if (dto.equipmentStatus() != null) {
      entity.setEquipmentStatus(dto.equipmentStatus());
    }
  }
}