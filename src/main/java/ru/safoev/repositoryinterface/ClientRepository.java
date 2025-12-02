package ru.safoev.repositoryinterface;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.safoev.entity.ClientEntity;

import java.util.List;

/**
 * Репозиторий для работы с сущностью ClientEntity.
 * <p>
 * Предоставляет методы для доступа к данным клиентов в базе данных.
 * Расширяет JpaRepository для получения стандартных CRUD операций.
 * Содержит пользовательские запросы для поиска клиентов по фильтрам.
 * </p>
 *
 * @Repository указывает, что этот интерфейс является Spring репозиторием
 * @JpaRepository<ClientEntity, Long> базовый интерфейс для работы с JPA
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

  /**
   * Поиск клиентов по фильтрам с поддержкой пагинации.
   * <p>
   * Выполняет поиск клиентов по идентификатору и/или электронной почте.
   * Оба параметра являются необязательными - если параметр null,
   * он не учитывается в условии поиска.
   * Поддерживает пагинацию через параметр Pageable.
   * </p>
   *
   * @param client_id уникальный идентификатор клиента (необязательный параметр)
   * @param client_email электронная почта клиента (необязательный параметр)
   * @param pageable объект пагинации для ограничения результатов
   * @return список сущностей клиентов, соответствующих критериям поиска
   *
   * @Query определяет пользовательский JPQL запрос для поиска клиентов
   */
  @Query("""
          SELECT r FROM ClientEntity r
          WHERE (:client_id IS NULL OR r.client_id = :client_id)
          AND (:client_email IS NULL OR r.client_email = :client_email)
          """)
  List<ClientEntity> searchAllByFilter(
          @Param("client_id") Long client_id,
          @Param("client_email") String client_email,
          Pageable pageable
  );
}