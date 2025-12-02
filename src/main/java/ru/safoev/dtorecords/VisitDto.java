package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) для передачи данных о посещениях.
 * <p>
 * Используется для передачи данных между слоями приложения при операциях
 * создания, обновления, получения информации о посещениях клиентов.
 * </p>
 *
 * @param visitId уникальный идентификатор посещения (может быть null при создании новой записи о посещении)
 * @param clientId идентификатор клиента (обязательное поле)
 * @param gymId идентификатор зала (обязательное поле)
 * @param checkInTime время входа клиента (обязательное поле)
 * @param checkOutTime время выхода клиента (необязательное поле)
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
public record VisitDto(
        Long visitId,

        @NotNull(message = "ID клиента обязательно")
        Long clientId,

        @NotNull(message = "ID зала обязательно")
        Long gymId,

        @NotNull(message = "Время check-in обязательно")
        LocalDateTime checkInTime,

        LocalDateTime checkOutTime
) {}