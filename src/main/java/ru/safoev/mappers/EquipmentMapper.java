package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.EquipmentDto;
import ru.safoev.entity.EquipmentEntity;

@Component
public class EquipmentMapper {

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
  public EquipmentEntity toEntity(EquipmentDto dto) {
    return new EquipmentEntity(
            dto.equipmentId(),
            dto.equipmentName(),
            dto.buyDate(),
            dto.equipmentStatus(),
            null
    );
  }

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