package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record RateDto(
        Long rateId,

        @NotBlank(message = "Название тарифа обязательно")
        String rateName,

        @NotNull(message = "Цена обязательна")
        @Positive(message = "Цена должна быть положительной")
        BigDecimal price,

        String pricePeriod,

        @NotNull(message = "Длительность обязательна")
        @Positive(message = "Длительность должна быть положительной")
        Integer durationDays,

        String description
) {}