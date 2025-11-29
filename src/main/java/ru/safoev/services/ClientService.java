package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.ClientDto;
import ru.safoev.entity.ClientEntity;
import ru.safoev.repositoryinterface.ClientRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClientService {

  private final ClientRepository clientRepository;

  @Autowired
  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public ClientDto getClientById(Long id) {
    ClientEntity client = clientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));
    return  toDtoClient(client);
  }

  public List<ClientDto> getAllClients() {
    return clientRepository.findAll().stream()
            .map(this::toDtoClient)
            .collect(Collectors.toList());
  }

  public ClientDto createClient(ClientDto clientDto) {
    ClientEntity client = new ClientEntity();
    client.setClient_first_name(clientDto.firstName());
    client.setClient_last_name(clientDto.lastName());
    client.setClient_phone(clientDto.phone());
    client.setClient_email(clientDto.email());
    client.setClient_date_of_birth(clientDto.dateOfBirth());
    client.setClient_registration_date(clientDto.registrationDate() != null ?
            clientDto.registrationDate() : java.time.LocalDateTime.now());

    ClientEntity saved = clientRepository.save(client);
    return toDtoClient(saved);
  }

  public ClientDto updateClient(Long id, ClientDto clientDto) {
    ClientEntity client = clientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));

    if (clientDto.firstName() != null) {
      client.setClient_first_name(clientDto.firstName());
    }
    if (clientDto.lastName() != null) {
      client.setClient_last_name(clientDto.lastName());
    }
    if (clientDto.phone() != null) {
      client.setClient_phone(clientDto.phone());
    }
    if (clientDto.email() != null) {
      client.setClient_email(clientDto.email());
    }
    if (clientDto.dateOfBirth() != null) {
      client.setClient_date_of_birth(clientDto.dateOfBirth());
    }

    ClientEntity updated = clientRepository.save(client);
    return toDtoClient(updated);
  }

  public void deleteClient(Long id) {
    if (!clientRepository.existsById(id)) {
      throw new NoSuchElementException("Client not found with id: " + id);
    }
    clientRepository.deleteById(id);
  }

  private ClientDto toDtoClient(ClientEntity client) {
    return new ClientDto(
            client.getClient_id(),
            client.getClient_first_name(),
            client.getClient_last_name(),
            client.getClient_phone(),
            client.getClient_email(),
            client.getClient_date_of_birth(),
            client.getClient_registration_date()
    );
  }
}
