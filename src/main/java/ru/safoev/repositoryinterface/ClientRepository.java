package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.safoev.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity,Long> {
}
