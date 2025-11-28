package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.safoev.entity.GymEntity;

public interface GymRepository extends JpaRepository<GymEntity,Long> {}
