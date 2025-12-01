package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.EquipmentDto;
import ru.safoev.entity.EquipmentEntity;
import ru.safoev.entity.GymEntity;
import ru.safoev.mappers.EquipmentMapper;
import ru.safoev.repositoryinterface.EquipmentRepository;
import ru.safoev.repositoryinterface.GymRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EquipmentService {
  private final EquipmentRepository equipmentRepository;
  private final GymRepository gymRepository;
  private final EquipmentMapper equipmentMapper;

  @Autowired
  public EquipmentService(EquipmentRepository equipmentRepository, GymRepository gymRepository, EquipmentMapper equipmentMapper) {
    this.equipmentRepository = equipmentRepository;
    this.gymRepository = gymRepository;
    this.equipmentMapper = equipmentMapper;
  }

  public EquipmentDto getEquipmentById(Long id) {
    EquipmentEntity equipmentEntity = equipmentRepository.findById(id).
            orElseThrow(() -> new IllegalArgumentException("Equipment not found with id: " + id));

    return equipmentMapper.toDto(equipmentEntity);
  }

  public List<EquipmentDto> getAllEquipment() {
    return equipmentRepository.findAll().stream()
            .map(equipmentMapper::toDto)
            .collect(Collectors.toList());
  }

  public EquipmentDto createEquipment(EquipmentDto equipmentDto) {
    EquipmentEntity entityToSave = equipmentMapper.toEntity(equipmentDto);
    GymEntity gym = gymRepository.findById(equipmentDto.gymId())
            .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + equipmentDto.gymId()));
    entityToSave.setGym(gym);

    EquipmentEntity saved = equipmentRepository.save(entityToSave);
    return equipmentMapper.toDto(saved);
  }

  public EquipmentDto updateEquipment(Long id, EquipmentDto equipmentDto) {
    EquipmentEntity existingEquipment = equipmentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Equipment not found with id: " + id));
    equipmentMapper.updateEntityFromDto(equipmentDto, existingEquipment);

    GymEntity gym = gymRepository.findById(equipmentDto.gymId())
            .orElseThrow(() -> new IllegalArgumentException("Gym not found with id: " + equipmentDto.gymId()));
    existingEquipment.setGym(gym);

    EquipmentEntity updated = equipmentRepository.save(existingEquipment);
    return equipmentMapper.toDto(updated);
  }


  public void deleteEquipment(Long id) {
    if (!equipmentRepository.existsById(id)) {
      throw new NoSuchElementException("Equipment not found with id: " + id);
    }
    equipmentRepository.deleteById(id);
  }
}