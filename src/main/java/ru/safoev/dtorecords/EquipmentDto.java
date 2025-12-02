package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) для передачи данных об оборудовании фитнес-центра.
 * <p>
 * Используется для передачи данных между слоями приложения при операциях
 * создания, обновления, получения информации об оборудовании.
 * </p>
 *
 * @param equipmentId уникальный идентификатор оборудования (может быть null при создании нового оборудования)
 * @param equipmentName название оборудования (обязательное поле)
 * @param buyDate дата покупки оборудования (необязательное поле)
 * @param equipmentStatus статус оборудования из перечисления {@link ru.safoev.enumlists.EquipmentStatus}
 * @param gymId идентификатор зала, к которому принадлежит оборудование (обязательное поле)
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 * @see ru.safoev.enumlists.EquipmentStatus
 */
public record EquipmentDto(
        Long equipmentId,

        @NotBlank(message = "Название оборудования обязательно")
        String equipmentName,

        LocalDate buyDate,

        ru.safoev.enumlists.EquipmentStatus equipmentStatus,

        @NotNull(message = "ID зала обязательно")
        Long gymId
) {}