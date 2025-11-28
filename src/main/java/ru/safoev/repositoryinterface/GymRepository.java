package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safoev.entity.GymEntity;

@Repository
public interface GymRepository extends JpaRepository<GymEntity,Long> {}
