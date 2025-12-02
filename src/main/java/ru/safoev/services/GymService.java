package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.GymDto;
import ru.safoev.entity.GymEntity;
import ru.safoev.mappers.GymMapper;
import ru.safoev.repositoryinterface.GymRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymService {
  private final GymRepository gymRepository;
  private final GymMapper gymMapper;

  @Autowired
  public GymService(GymRepository gymRepository, GymMapper gymMapper) {
    this.gymRepository = gymRepository;
    this.gymMapper = gymMapper;
  }

  public GymDto getGymById(Long id) {
    GymEntity gym = gymRepository.findById(id).
            orElseThrow(()-> new IllegalArgumentException("Gym not found with id: " + id));
    return  gymMapper.toDto(gym);
  }

  public List<GymDto> getAllGym() {
    return gymRepository.findAll().stream()
            .map(gymMapper::toDto)
            .collect(Collectors.toList());
  }

  public GymDto createGym(GymDto gymDto) {
    if (!gymDto.closeTime().isAfter(gymDto.openTime())) {
      throw new IllegalArgumentException("Close time must be after open time");
    }

    GymEntity entityToSave = gymMapper.toEntity(gymDto);
    GymEntity saved = gymRepository.save(entityToSave);
    return gymMapper.toDto(saved);
  }

  public GymDto updateGym(Long id, GymDto gymDto) {
    GymEntity existingGym = gymRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + id));

    gymMapper.updateEntityFromDto(gymDto, existingGym);

    if (gymDto.openTime() != null || gymDto.closeTime() != null) {
      LocalTime openTime = gymDto.openTime() != null ? gymDto.openTime() : existingGym.getGym_open_time();
      LocalTime closeTime = gymDto.closeTime() != null ? gymDto.closeTime() : existingGym.getGym_end_time();

      if (!closeTime.isAfter(openTime)) {
        throw new IllegalArgumentException("Close time must be after open time");
      }
    }

    GymEntity updated = gymRepository.save(existingGym);
    return gymMapper.toDto(updated);
  }

  public void deleteGym(Long id) {
    if(!gymRepository.existsById(id)) {
      throw new IllegalArgumentException("Gym not found with id: " + id);
    }
    gymRepository.deleteById(id);
  }

}
