package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.ClientDto;
import ru.safoev.entity.ClientEntity;

/**
 * Маппер для преобразования между сущностью ClientEntity и DTO ClientDto.
 * <p>
 * Этот компонент отвечает за преобразование данных между слоем сущностей
 * и слоем передачи данных. Используется для изоляции логики преобразования
 * данных и обеспечения чистоты архитектуры.
 * </p>
 *
 * @Component указывает, что этот класс является Spring компонентом
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Component
public class ClientMapper {

  /**
   * Преобразует сущность ClientEntity в DTO ClientDto.
   * <p>
   * Используется при получении данных клиента из базы данных
   * для передачи клиенту API.
   * </p>
   *
   * @param entity сущность клиента из базы данных
   * @return DTO клиента для передачи данных
   */
  public ClientDto toDto(ClientEntity entity) {
    return new ClientDto(
            entity.getClient_id(),
            entity.getClient_first_name(),
            entity.getClient_last_name(),
            entity.getClient_phone(),
            entity.getClient_email(),
            entity.getClient_date_of_birth(),
            entity.getClient_registration_date()
    );
  }

  /**
   * Преобразует DTO ClientDto в сущность ClientEntity.
   * <p>
   * Используется при создании нового клиента или полном обновлении
   * существующего клиента.
   * </p>
   *
   * @param dto DTO клиента, полученный от клиента API
   * @return сущность клиента для сохранения в базе данных
   */
  public ClientEntity toEntity(ClientDto dto) {
    return new ClientEntity(
            dto.clientId(),
            dto.firstName(),
            dto.lastName(),
            dto.phone(),
            dto.email(),
            dto.dateOfBirth(),
            dto.registrationDate()
    );
  }

  /**
   * Обновляет существующую сущность ClientEntity данными из DTO ClientDto.
   * <p>
   * Используется для частичного обновления клиента.
   * Обновляются только те поля, которые не являются null в DTO.
   * Поле registrationDate не обновляется, так как это системное поле.
   * </p>
   *
   * @param dto DTO с новыми данными клиента
   * @param entity существующая сущность клиента, которую нужно обновить
   */
  public void updateEntityFromDto(ClientDto dto, ClientEntity entity) {
    if (dto.firstName() != null) {
      entity.setClient_first_name(dto.firstName());
    }
    if (dto.lastName() != null) {
      entity.setClient_last_name(dto.lastName());
    }
    if (dto.phone() != null) {
      entity.setClient_phone(dto.phone());
    }
    if (dto.email() != null) {
      entity.setClient_email(dto.email());
    }
    if (dto.dateOfBirth() != null) {
      entity.setClient_date_of_birth(dto.dateOfBirth());
    }
  }
}