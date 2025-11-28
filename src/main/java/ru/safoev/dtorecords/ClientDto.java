package ru.safoev.dtorecords;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

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