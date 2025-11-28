package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.safoev.entity.EquipmentEntity;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity,Long> {
}
