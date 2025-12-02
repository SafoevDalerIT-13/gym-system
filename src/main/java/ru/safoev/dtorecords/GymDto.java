package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * Data Transfer Object (DTO) для передачи данных о зале (фитнес-центре).
 * <p>
 * Используется для передачи данных между слоями приложения при операциях
 * создания, обновления, получения информации о залах фитнес-центра.
 * </p>
 *
 * @param gymId уникальный идентификатор зала (может быть null при создании нового зала)
 * @param gymName название зала (обязательное поле)
 * @param address адрес зала (обязательное поле)
 * @param gymPhone телефон зала (необязательное поле)
 * @param openTime время открытия зала (обязательное поле)
 * @param closeTime время закрытия зала (обязательное поле, должно быть позже времени открытия)
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
public record GymDto(
        Long gymId,

        @NotBlank(message = "Название зала обязательно")
        String gymName,

        @NotBlank(message = "Адрес обязателен")
        String address,

        String gymPhone,

        @NotNull(message = "Время открытия обязательно")
        LocalTime openTime,

        @NotNull(message = "Время закрытия обязательно")
        LocalTime closeTime
) {}