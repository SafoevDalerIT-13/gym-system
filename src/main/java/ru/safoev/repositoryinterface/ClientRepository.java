package ru.safoev.repositoryinterface;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.safoev.entity.ClientEntity;
import ru.safoev.filters.ClientSearchFilter;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long> {

  @Query("""
          SELECT r.id from ClientEntity r
          WHERE r.client_id =:client_id
          AND r.client_email =:client_email
          """)
  List<ClientEntity> searchAllByFilter(
          @Param("client_id") Long client_id,
          @Param("client_email") String client_email,
          Pageable pageable
  );
}
