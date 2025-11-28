package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeDto(
        Long employeeId,

        @NotBlank(message = "Имя обязательно")
        String firstName,

        @NotBlank(message = "Фамилия обязательна")
        String lastName,

        String empPhone,

        String empEmail,

        Long gymId,

        @NotNull(message = "Дата приема на работу обязательна")
        LocalDate hireDate,

        LocalDate dismissalDate,

        @NotBlank(message = "Должность обязательна")
        String post,

        @NotNull(message = "Зарплата обязательна")
        @Positive(message = "Зарплата должна быть положительной")
        BigDecimal salary
) {}