package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.safoev.entity.VisitEntity;

public interface VisitRepository extends JpaRepository<VisitEntity,Long> {
}
