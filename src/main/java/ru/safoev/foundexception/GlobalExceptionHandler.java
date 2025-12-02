package ru.safoev.foundexception;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

/**
 * Глобальный обработчик исключений для приложения.
 * <p>
 * Этот класс обрабатывает исключения, возникающие во всех контроллерах приложения.
 * Использует аннотацию @ControllerAdvice для централизованной обработки исключений
 * и возврата стандартизированных ответов об ошибках.
 * </p>
 *
 * @ControllerAdvice указывает, что этот класс обрабатывает исключения для всех контроллеров
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Логгер для записи информации об исключениях.
   */
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * Обрабатывает все неперехваченные исключения.
   * <p>
   * Этот метод перехватывает любые исключения, которые не были обработаны
   * другими методами этого класса.
   * </p>
   *
   * @param e исключение, которое было выброшено
   * @return ResponseEntity с информацией об ошибке и статусом 500 (Internal Server Error)
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> HandleGenericException(Exception e) {
    log.info("Handle exception: ", e);

    var errorDto = new ErrorResponseDto(
            "Internal server error",
            e.getMessage(),
            LocalDateTime.now()
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
  }

  /**
   * Обрабатывает исключения EntityNotFoundException.
   * <p>
   * Этот метод перехватывает исключения, возникающие при поиске несуществующих сущностей.
   * </p>
   *
   * @param e исключение EntityNotFoundException
   * @return ResponseEntity с информацией об ошибке и статусом 404 (Not Found)
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> HandleEntityNotFound(EntityNotFoundException e) {
    log.info("Handle entityNotFoundException: ", e);

    var errorDto = new ErrorResponseDto(
            "Entity not found",
            e.getMessage(),
            LocalDateTime.now()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
  }

  /**
   * Обрабатывает исключения, связанные с неверными запросами.
   * <p>
   * Этот метод перехватывает следующие типы исключений:
   * <ul>
   *   <li>IllegalArgumentException - неверные аргументы методов</li>
   *   <li>IllegalStateException - неверное состояние объекта</li>
   *   <li>MethodArgumentNotValidException - ошибки валидации аргументов методов контроллера</li>
   * </ul>
   * </p>
   *
   * @param e одно из перечисленных исключений
   * @return ResponseEntity с информацией об ошибке и статусом 400 (Bad Request)
   */
  @ExceptionHandler(exception = {
          IllegalArgumentException.class,
          IllegalStateException.class,
          MethodArgumentNotValidException.class
  })
  public ResponseEntity<ErrorResponseDto> HandleBadRequest(Exception e) {
    log.info("Handle handleBadRequest: ", e);
    var errorDto = new ErrorResponseDto(
            "Bad request",
            e.getMessage(),
            LocalDateTime.now()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
  }
}