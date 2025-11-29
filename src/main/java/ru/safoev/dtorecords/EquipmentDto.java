package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EquipmentDto(
        Long equipmentId,

        @NotBlank(message = "Название оборудования обязательно")
        String equipmentName,

        LocalDate buyDate,

        ru.safoev.enumlists.EquipmentStatus equipmentStatus,

        @NotNull(message = "ID зала обязательно")
        Long gymId
) {}
