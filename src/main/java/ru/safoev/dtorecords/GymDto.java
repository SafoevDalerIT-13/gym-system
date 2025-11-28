package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

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