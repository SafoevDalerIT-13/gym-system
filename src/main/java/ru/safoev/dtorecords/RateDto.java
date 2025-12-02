package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) для передачи данных о тарифах.
 * <p>
 * Используется для передачи данных между слоями приложения при операциях
 * создания, обновления, получения информации о тарифах.
 * </p>
 *
 * @param rateId уникальный идентификатор тарифа (может быть null при создании нового тарифа)
 * @param rateName название тарифа (обязательное поле)
 * @param price цена тарифа (обязательное поле, должна быть положительной)
 * @param pricePeriod период оплаты тарифа (например: "месяц", "год")
 * @param durationDays длительность действия тарифа в днях (обязательное поле, должна быть положительной)
 * @param description описание тарифа (необязательное поле)
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
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