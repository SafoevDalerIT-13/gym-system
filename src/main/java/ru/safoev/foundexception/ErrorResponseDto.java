package ru.safoev.foundexception;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) для передачи информации об ошибке.
 * <p>
 * Используется для стандартизированного представления информации об ошибках
 * в ответах API. Содержит основное сообщение, детализированное описание
 * и временную метку возникновения ошибки.
 * </p>
 *
 * @param message основное сообщение об ошибке
 * @param detailedMessage детализированное описание ошибки (может содержать stack trace или дополнительные детали)
 * @param errorTime дата и время возникновения ошибки
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
public record ErrorResponseDto(
        String message,
        String detailedMessage,
        LocalDateTime errorTime
) {
}