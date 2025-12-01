package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.VisitDto;
import ru.safoev.entity.VisitEntity;
import ru.safoev.entity.ClientEntity;
import ru.safoev.entity.GymEntity;
import ru.safoev.mappers.VisitMapper;
import ru.safoev.repositoryinterface.VisitRepository;
import ru.safoev.repositoryinterface.ClientRepository;
import ru.safoev.repositoryinterface.GymRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VisitService {

  private final VisitRepository visitRepository;
  private final ClientRepository clientRepository;
  private final GymRepository gymRepository;
  private final VisitMapper visitMapper;

  @Autowired
  public VisitService(VisitRepository visitRepository,
                      ClientRepository clientRepository,
                      GymRepository gymRepository, VisitMapper visitMapper) {
    this.visitRepository = visitRepository;
    this.clientRepository = clientRepository;
    this.gymRepository = gymRepository;
    this.visitMapper = visitMapper;
  }

  public VisitDto getVisitById(Long id) {
    VisitEntity visit = visitRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + id));
    return visitMapper.toDto(visit);
  }

  public List<VisitDto> getAllVisits() {
    return visitRepository.findAll().stream()
            .map(visitMapper::toDto)
            .collect(Collectors.toList());
  }

  public VisitDto createVisit(VisitDto visitDto) {
    ClientEntity client = clientRepository.findById(visitDto.clientId())
            .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + visitDto.clientId()));

    GymEntity gym = gymRepository.findById(visitDto.gymId())
            .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + visitDto.gymId()));

    VisitEntity visit = visitMapper.toEntity(visitDto, client, gym);

    VisitEntity saved = visitRepository.save(visit);
    return visitMapper.toDto(saved);
  }

  public VisitDto updateVisit(Long id, VisitDto visitDto) {
    VisitEntity existingVisit = visitRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + id));

    ClientEntity client = null;
    if (visitDto.clientId() != null) {
      client = clientRepository.findById(visitDto.clientId())
              .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + visitDto.clientId()));
    }

    GymEntity gym = null;
    if (visitDto.gymId() != null) {
      gym = gymRepository.findById(visitDto.gymId())
              .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + visitDto.gymId()));
    }

    visitMapper.updateEntityFromDto(visitDto, existingVisit, client, gym);

    VisitEntity updated = visitRepository.save(existingVisit);
    return visitMapper.toDto(updated);
  }

  public void deleteVisit(Long id) {
    if (!visitRepository.existsById(id)) {
      throw new NoSuchElementException("Visit not found with id: " + id);
    }
    visitRepository.deleteById(id);
  }
}