package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.EquipmentDto;
import ru.safoev.entity.EquipmentEntity;
import ru.safoev.entity.GymEntity;
import ru.safoev.repositoryinterface.EquipmentRepository;
import ru.safoev.repositoryinterface.GymRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EquipmentService {
  private final EquipmentRepository equipmentRepository;

  private final GymRepository gymRepository;

  @Autowired
  public EquipmentService(EquipmentRepository equipmentRepository, GymRepository gymRepository) {
    this.equipmentRepository = equipmentRepository;
    this.gymRepository = gymRepository;
  }

  public EquipmentDto getEquipmentById(Long id) {
    EquipmentEntity equipmentEntity = equipmentRepository.findById(id).
            orElseThrow(() -> new IllegalArgumentException("Equipment not found with id: " + id));

    return toDtoEquipment(equipmentEntity);
  }

  public List<EquipmentDto> getAllEquipment() {
    return equipmentRepository.findAll().stream()
            .map(this::toDtoEquipment)
            .collect(Collectors.toList());
  }

  public EquipmentDto createEquipment(EquipmentDto equipmentDto) {
    GymEntity gym = gymRepository.findById(equipmentDto.gymId())
            .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + equipmentDto.gymId()));
    EquipmentEntity equipmentEntity = new EquipmentEntity();
    equipmentEntity.setEquipmentName(equipmentDto.equipmentName());
    equipmentEntity.setEquipment_buyDate(equipmentDto.buyDate());
    equipmentEntity.setEquipmentStatus(equipmentDto.equipmentStatus());
    equipmentEntity.setGym(gym);

    EquipmentEntity saved = equipmentRepository.save(equipmentEntity);
    return toDtoEquipment(saved);
  }

  public EquipmentDto updateEquipment(Long id, EquipmentDto equipmentDto) {
    EquipmentEntity equipment = equipmentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Equipment not found with id: " + id));

    if (equipmentDto.equipmentName() != null) {
      equipment.setEquipmentName(equipmentDto.equipmentName());
    }
    if (equipmentDto.buyDate() != null) {
      equipment.setEquipment_buyDate(equipmentDto.buyDate());
    }
    if (equipmentDto.equipmentStatus() != null) {
      equipment.setEquipmentStatus(equipmentDto.equipmentStatus());
    }

    if (equipmentDto.gymId() != null) {
      GymEntity gym = gymRepository.findById(equipmentDto.gymId())
              .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + equipmentDto.gymId()));
      equipment.setGym(gym);
    }

    EquipmentEntity updated = equipmentRepository.save(equipment);
    return toDtoEquipment(updated);
  }


  public void deleteEquipment(Long id) {
    if (!equipmentRepository.existsById(id)) {
      throw new NoSuchElementException("Equipment not found with id: " + id);
    }
    equipmentRepository.deleteById(id);
  }

  private EquipmentDto toDtoEquipment(EquipmentEntity equipment) {
    Long gymId = equipment.getGym() != null ? equipment.getGym().getGum_id() : null;

    return new EquipmentDto(
            equipment.getEquipmentId(),
            equipment.getEquipmentName(),
            equipment.getEquipment_buyDate(),
            equipment.getEquipmentStatus(),
            gymId
    );
  }
}