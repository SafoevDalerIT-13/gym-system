package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safoev.entity.VisitEntity;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity,Long> {
}
