package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.GymDto;
import ru.safoev.entity.GymEntity;

@Component
public class GymMapper {
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