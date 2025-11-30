package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.GymDto;
import ru.safoev.entity.GymEntity;
import ru.safoev.repositoryinterface.GymRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymService {
  private final GymRepository gymRepository;

  @Autowired
  public GymService(GymRepository gymRepository) {
    this.gymRepository = gymRepository;
  }

  public GymDto getGymById(Long id) {
    GymEntity gym = gymRepository.findById(id).
            orElseThrow(()-> new IllegalArgumentException("Gym not found with id: " + id));
    return  toDtoGym(gym);
  }

  public List<GymDto> getAllGym() {
    return gymRepository.findAll().stream()
            .map(this::toDtoGym)
            .collect(Collectors.toList());
  }

  public GymDto createGym(GymDto gymDto) {
    GymEntity gym = new GymEntity();
    gym.setGum_name(gymDto.gymName());
    gym.setGum_phone(gymDto.gymPhone());
    gym.setGum_address(gymDto.address());
    gym.setGum_open_time(gymDto.openTime());
    gym.setGum_end_time(gymDto.closeTime());

    GymEntity save = gymRepository.save(gym);
    return toDtoGym(save);
  }

  public GymDto updateGym(Long id, GymDto gymDto) {
    GymEntity gym = gymRepository.findById(id).
            orElseThrow(()-> new IllegalArgumentException("Gym not found with id: " + id));

    if(gymDto.gymName() != null) {
      gym.setGum_name(gymDto.gymName());
    }
    if(gymDto.gymPhone() != null) {
      gym.setGum_phone(gymDto.gymPhone());
    }
    if(gymDto.address() != null) {
      gym.setGum_address(gymDto.address());
    }
    if(gymDto.openTime() != null) {
      gym.setGum_open_time(gymDto.openTime());
    }
    if(gymDto.closeTime() != null) {
      gym.setGum_end_time(gymDto.closeTime());
    }

    GymEntity update = gymRepository.save(gym);
    return toDtoGym(update);

  }

  public void deleteGym(Long id) {
    if(!gymRepository.existsById(id)) {
      throw new IllegalArgumentException("Gym not found with id: " + id);
    }
    gymRepository.deleteById(id);
  }

  private GymDto toDtoGym(GymEntity gym) {
    return new GymDto(
            gym.getGum_id(),
            gym.getGum_name(),
            gym.getGum_address(),
            gym.getGum_phone(),
            gym.getGum_open_time(),
            gym.getGum_end_time()
    );
  }
}
