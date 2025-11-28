package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safoev.entity.RateEntity;

@Repository
public interface RateRepository extends JpaRepository<RateEntity,Long> {
}
