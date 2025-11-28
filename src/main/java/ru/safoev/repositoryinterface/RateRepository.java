package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.safoev.entity.RateEntity;

public interface RateRepository extends JpaRepository<RateEntity,Long> {
}
