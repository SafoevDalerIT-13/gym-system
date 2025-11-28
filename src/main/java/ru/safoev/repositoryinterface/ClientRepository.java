package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safoev.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long> {
}
