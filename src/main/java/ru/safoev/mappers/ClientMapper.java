package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.ClientDto;
import ru.safoev.entity.ClientEntity;

@Component
public class ClientMapper {

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
}