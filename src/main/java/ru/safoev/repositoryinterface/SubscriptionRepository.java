package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safoev.entity.SubscriptionEntity;

/**
 * Репозиторий для работы с сущностью SubscriptionEntity.
 * <p>
 * Предоставляет методы для доступа к данным абонементов в базе данных.
 * Расширяет JpaRepository для получения стандартных CRUD операций.
 * Наследует все базовые методы работы с сущностями, включая:
 * сохранение, обновление, удаление, поиск по ID и получение всех записей.
 * </p>
 *
 * @Repository указывает, что этот интерфейс является Spring репозиторием
 * @JpaRepository<SubscriptionEntity, Long> базовый интерфейс для работы с JPA,
 * где SubscriptionEntity - тип сущности, Long - тип идентификатора
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
}