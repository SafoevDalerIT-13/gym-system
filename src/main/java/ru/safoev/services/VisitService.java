package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.VisitDto;
import ru.safoev.entity.VisitEntity;
import ru.safoev.entity.ClientEntity;
import ru.safoev.entity.GymEntity;
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

  @Autowired
  public VisitService(VisitRepository visitRepository,
                      ClientRepository clientRepository,
                      GymRepository gymRepository) {
    this.visitRepository = visitRepository;
    this.clientRepository = clientRepository;
    this.gymRepository = gymRepository;
  }

  public VisitDto getVisitById(Long id) {
    VisitEntity visit = visitRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + id));
    return toDtoVisit(visit);
  }

  public List<VisitDto> getAllVisits() {
    return visitRepository.findAll().stream()
            .map(this::toDtoVisit)
            .collect(Collectors.toList());
  }

  public VisitDto createVisit(VisitDto visitDto) {
    if (visitDto.clientId() == null) {
      throw new IllegalArgumentException("Client ID is required");
    }
    if (visitDto.gymId() == null) {
      throw new IllegalArgumentException("Gym ID is required");
    }
    if (visitDto.checkInTime() == null) {
      throw new IllegalArgumentException("Check-in time is required");
    }

    ClientEntity client = clientRepository.findById(visitDto.clientId())
            .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + visitDto.clientId()));

    GymEntity gym = gymRepository.findById(visitDto.gymId())
            .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + visitDto.gymId()));

    VisitEntity visit = new VisitEntity();
    visit.setClient(client);
    visit.setGym(gym);
    visit.setVisit_checkInTime(visitDto.checkInTime());
    visit.setVisit_checkOutTime(visitDto.checkOutTime());

    VisitEntity saved = visitRepository.save(visit);
    return toDtoVisit(saved);
  }

  public VisitDto updateVisit(Long id, VisitDto visitDto) {
    VisitEntity visit = visitRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + id));

    if (visitDto.clientId() != null) {
      ClientEntity client = clientRepository.findById(visitDto.clientId())
              .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + visitDto.clientId()));
      visit.setClient(client);
    }

    if (visitDto.gymId() != null) {
      GymEntity gym = gymRepository.findById(visitDto.gymId())
              .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + visitDto.gymId()));
      visit.setGym(gym);
    }

    if (visitDto.checkInTime() != null) {
      visit.setVisit_checkInTime(visitDto.checkInTime());
    }

    if (visitDto.checkOutTime() != null) {
      visit.setVisit_checkOutTime(visitDto.checkOutTime());
    }

    VisitEntity updated = visitRepository.save(visit);
    return toDtoVisit(updated);
  }

  public void deleteVisit(Long id) {
    if (!visitRepository.existsById(id)) {
      throw new NoSuchElementException("Visit not found with id: " + id);
    }
    visitRepository.deleteById(id);
  }

  private VisitDto toDtoVisit(VisitEntity visit) {
    Long clientId = visit.getClient() != null ? visit.getClient().getClient_id() : null;
    Long gymId = visit.getGym() != null ? visit.getGym().getGum_id() : null;

    return new VisitDto(
            visit.getVisitId(),
            clientId,
            gymId,
            visit.getVisit_checkInTime(),
            visit.getVisit_checkOutTime()
    );
  }
}