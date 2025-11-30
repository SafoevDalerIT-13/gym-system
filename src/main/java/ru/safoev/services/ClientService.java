package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.ClientDto;
import ru.safoev.entity.ClientEntity;
import ru.safoev.mappers.ClientMapper;
import ru.safoev.repositoryinterface.ClientRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClientService {
  private final ClientRepository clientRepository;

  private final ClientMapper clientMapper;

  @Autowired
  public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
    this.clientRepository = clientRepository;
    this.clientMapper = clientMapper;
  }

  public ClientDto getClientById(Long id) {
    ClientEntity client = clientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));
    return  clientMapper.toDto(client);
  }

  public List<ClientDto> getAllClients() {
    return clientRepository.findAll().stream()
            .map(clientMapper::toDto)
            .collect(Collectors.toList());
  }

  public ClientDto createClient(ClientDto clientDto) {
    ClientEntity entityToSave = clientMapper.toEntity(clientDto);
    ClientEntity savedEntity = clientRepository.save(entityToSave);
    return clientMapper.toDto(savedEntity);
  }

  public ClientDto updateClient(Long id, ClientDto clientDto) {
    ClientEntity existingClient = clientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));

    ClientEntity entityToUpdate = clientMapper.toEntity(clientDto);
    entityToUpdate.setClient_id(existingClient.getClient_id());
    entityToUpdate.setClient_registration_date(existingClient.getClient_registration_date());

    ClientEntity updatedEntity = clientRepository.save(entityToUpdate);
    return clientMapper.toDto(updatedEntity);
  }

  public void deleteClient(Long id) {
    if (!clientRepository.existsById(id)) {
      throw new NoSuchElementException("Client not found with id: " + id);
    }
    clientRepository.deleteById(id);
  }


}
