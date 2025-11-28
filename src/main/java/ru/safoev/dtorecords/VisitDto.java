package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
