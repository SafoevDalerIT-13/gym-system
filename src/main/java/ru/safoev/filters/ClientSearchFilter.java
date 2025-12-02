package ru.safoev.filters;

/**
 * Фильтр для поиска клиентов фитнес-центра.
 * <p>
 * Используется для передачи параметров фильтрации и пагинации
 * при поиске клиентов в системе.
 * Все поля являются необязательными и могут использоваться
 * в различных комбинациях.
 * </p>
 *
 * @param client_id уникальный идентификатор клиента (необязательное поле)
 * @param client_email электронная почта клиента (необязательное поле)
 * @param pageSize размер страницы для пагинации (необязательное поле)
 * @param pageNumber номер страницы для пагинации (необязательное поле)
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
public record ClientSearchFilter(
        Long client_id,
        String client_email,
        Integer pageSize,
        Integer pageNumber
) {}