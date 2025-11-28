package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

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