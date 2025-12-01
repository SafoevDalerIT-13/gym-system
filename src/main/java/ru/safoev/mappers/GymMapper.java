package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.GymDto;
import ru.safoev.entity.GymEntity;

@Component
public class GymMapper {
  public GymDto toDto(GymEntity entity) {
    return new GymDto(
            entity.getGum_id(),
            entity.getGum_name(),
            entity.getGum_address(),
            entity.getGum_phone(),
            entity.getGum_open_time(),
            entity.getGum_end_time()
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
      entity.setGum_name(dto.gymName());
    }
    if (dto.address() != null) {
      entity.setGum_address(dto.address());
    }
    if (dto.gymPhone() != null) {
      entity.setGum_phone(dto.gymPhone());
    }
    if (dto.openTime() != null) {
      entity.setGum_open_time(dto.openTime());
    }
    if (dto.closeTime() != null) {
      entity.setGum_end_time(dto.closeTime());
    }
  }
}