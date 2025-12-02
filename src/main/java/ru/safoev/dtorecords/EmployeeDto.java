package ru.safoev.dtorecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) для передачи данных о сотруднике фитнес-центра.
 * <p>
 * Используется для передачи данных между слоями приложения при операциях
 * создания, обновления, получения информации о сотрудниках.
 * </p>
 *
 * @param employeeId уникальный идентификатор сотрудника (может быть null при создании нового сотрудника)
 * @param firstName имя сотрудника (обязательное поле)
 * @param lastName фамилия сотрудника (обязательное поле)
 * @param empPhone телефон сотрудника (необязательное поле)
 * @param empEmail email сотрудника (необязательное поле)
 * @param gymId идентификатор зала, к которому привязан сотрудник (необязательное поле)
 * @param hireDate дата приема на работу (обязательное поле)
 * @param dismissalDate дата увольнения сотрудника (необязательное поле, null если сотрудник работает)
 * @param post должность сотрудника (обязательное поле)
 * @param salary заработная плата сотрудника (обязательное поле, должна быть положительной)
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
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