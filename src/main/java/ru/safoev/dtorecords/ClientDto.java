package ru.safoev.dtorecords;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) для передачи данных о клиенте.
 * <p>
 * Используется для передачи данных между слоями приложения при операциях
 * создания, обновления, получения информации о клиентах фитнес-центра.
 * </p>
 *
 * @param clientId уникальный идентификатор клиента (может быть null при создании нового клиента)
 * @param firstName имя клиента (обязательное поле)
 * @param lastName фамилия клиента (обязательное поле)
 * @param phone телефон клиента (необязательное поле)
 * @param email email клиента (валидируется на корректность формата)
 * @param dateOfBirth дата рождения клиента (должна быть в прошлом)
 * @param registrationDate дата и время регистрации клиента в системе
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
public record ClientDto(
        Long clientId,

        @NotBlank(message = "Имя обязательно")
        String firstName,

        @NotBlank(message = "Фамилия обязательна")
        String lastName,

        String phone,

        @Email(message = "Некорректный email")
        String email,

        @Past(message = "Дата рождения должна быть в прошлом")
        LocalDate dateOfBirth,

        LocalDateTime registrationDate
) { }