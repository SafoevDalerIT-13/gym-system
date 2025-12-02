package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.VisitDto;
import ru.safoev.entity.VisitEntity;
import ru.safoev.entity.ClientEntity;
import ru.safoev.entity.GymEntity;

/**
 * Маппер для преобразования между сущностью VisitEntity и DTO VisitDto.
 * <p>
 * Этот компонент отвечает за преобразование данных между слоем сущностей
 * и слоем передачи данных для посещений фитнес-центра.
 * Обрабатывает связи с сущностями ClientEntity и GymEntity для получения
 * и установки идентификаторов клиента и зала.
 * </p>
 *
 * @Component указывает, что этот класс является Spring компонентом
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Component
public class VisitMapper {

  /**
   * Преобразует сущность VisitEntity в DTO VisitDto.
   * <p>
   * Используется при получении данных посещения из базы данных
   * для передачи клиенту API. Извлекает идентификаторы клиента и зала
   * из связанных сущностей ClientEntity и GymEntity.
   * </p>
   *
   * @param entity сущность посещения из базы данных
   * @return DTO посещения для передачи данных
   */
  public VisitDto toDto(VisitEntity entity) {
    Long clientId = entity.getClient() != null ? entity.getClient().getClient_id() : null;
    Long gymId = entity.getGym() != null ? entity.getGym().getGym_id() : null;

    return new VisitDto(
            entity.getVisitId(),
            clientId,
            gymId,
            entity.getVisit_checkInTime(),
            entity.getVisit_checkOutTime()
    );
  }

  /**
   * Преобразует DTO VisitDto в сущность VisitEntity.
   * <p>
   * Используется при создании новой записи о посещении или полном обновлении
   * существующей записи. Требует передачу связанных сущностей
   * ClientEntity и GymEntity.
   * </p>
   *
   * @param dto DTO посещения, полученный от клиента API
   * @param client сущность клиента, совершившего посещение
   * @param gym сущность зала, который посетил клиент
   * @return сущность посещения для сохранения в базе данных
   */
  public VisitEntity toEntity(VisitDto dto, ClientEntity client, GymEntity gym) {
    VisitEntity entity = new VisitEntity();
    entity.setVisitId(dto.visitId());
    entity.setClient(client);
    entity.setGym(gym);
    entity.setVisit_checkInTime(dto.checkInTime());
    entity.setVisit_checkOutTime(dto.checkOutTime());
    return entity;
  }

  /**
   * Обновляет существующую сущность VisitEntity данными из DTO VisitDto.
   * <p>
   * Используется для частичного обновления записи о посещении.
   * Обновляются только те поля, которые не являются null в DTO.
   * Принимает необязательные параметры client и gym для обновления связанных сущностей.
   * </p>
   *
   * @param dto DTO с новыми данными о посещении
   * @param entity существующая сущность посещения, которую нужно обновить
   * @param client сущность клиента для обновления связи (может быть null)
   * @param gym сущность зала для обновления связи (может быть null)
   */
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