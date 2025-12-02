package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) для передачи данных об абонементах.
 * <p>
 * Используется для передачи данных между слоями приложения при операциях
 * создания, обновления, получения информации об абонементах клиентов.
 * </p>
 *
 * @param subscriptionId уникальный идентификатор абонемента (может быть null при создании нового абонемента)
 * @param clientId идентификатор клиента (обязательное поле)
 * @param rateId идентификатор тарифа (обязательное поле)
 * @param startDate дата начала действия абонемента (обязательное поле, должна быть сегодня или в будущем)
 * @param endDate дата окончания действия абонемента (обязательное поле, должна быть сегодня или в будущем)
 * @param freezePeriod период заморозки абонемента (необязательное поле)
 * @param subscriptionStatus статус абонемента (необязательное поле)
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
public record SubscriptionDto(
        Long subscriptionId,

        @NotNull(message = "ID клиента обязательно")
        Long clientId,

        @NotNull(message = "ID тарифа обязательно")
        Long rateId,

        @NotNull(message = "Дата начала обязательна")
        @FutureOrPresent(message = "Дата начала должна быть сегодня или в будущем")
        LocalDate startDate,

        @NotNull(message = "Дата окончания обязательна")
        @FutureOrPresent(message = "Дата окончания должна быть сегодня или в будущем")
        LocalDate endDate,

        String freezePeriod,

        String subscriptionStatus
) {}