package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.VisitDto;
import ru.safoev.entity.VisitEntity;
import ru.safoev.entity.ClientEntity;
import ru.safoev.entity.GymEntity;

@Component
public class VisitMapper {

  public VisitDto toDto(VisitEntity entity) {
    Long clientId = entity.getClient() != null ? entity.getClient().getClient_id() : null;
    Long gymId = entity.getGym() != null ? entity.getGym().getGum_id() : null;

    return new VisitDto(
            entity.getVisitId(),
            clientId,
            gymId,
            entity.getVisit_checkInTime(),
            entity.getVisit_checkOutTime()
    );
  }

  public VisitEntity toEntity(VisitDto dto, ClientEntity client, GymEntity gym) {
    VisitEntity entity = new VisitEntity();
    entity.setVisitId(dto.visitId());
    entity.setClient(client);
    entity.setGym(gym);
    entity.setVisit_checkInTime(dto.checkInTime());
    entity.setVisit_checkOutTime(dto.checkOutTime());
    return entity;
  }

  public void updateEntityFromDto(VisitDto dto, VisitEntity entity, ClientEntity client, GymEntity gym) {
    if (client != null) {
      entity.setClient(client);
    }
    if (gym != null) {
      entity.setGym(gym);
    }
    if (dto.checkInTime() != null) {
      entity.setVisit_checkInTime(dto.checkInTime());
    }
    if (dto.checkOutTime() != null) {
      entity.setVisit_checkOutTime(dto.checkOutTime());
    }
  }
}