package ru.safoev.repositoryinterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safoev.entity.EmployeeEntity;

/**
 * Репозиторий для работы с сущностью EmployeeEntity.
 * <p>
 * Предоставляет методы для доступа к данным сотрудников в базе данных.
 * Расширяет JpaRepository для получения стандартных CRUD операций.
 * Наследует все базовые методы работы с сущностями, включая:
 * сохранение, обновление, удаление, поиск по ID и получение всех записей.
 * </p>
 *
 * @Repository указывает, что этот интерфейс является Spring репозиторием
 * @JpaRepository<EmployeeEntity, Long> базовый интерфейс для работы с JPA,
 * где EmployeeEntity - тип сущности, Long - тип идентификатора
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}