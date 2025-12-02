package ru.safoev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурация CORS (Cross-Origin Resource Sharing) для приложения.
 * <p>
 * Этот класс настраивает политику CORS, разрешая кросс-доменные запросы
 * к API приложения. CORS необходим для обеспечения безопасного взаимодействия
 * между клиентскими приложениями и сервером, когда они находятся на разных доменах.
 * </p>
 *
 * @Configuration указывает, что этот класс содержит конфигурационные методы Spring
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Configuration
public class CorsConfig {

  /**
   * Создает и настраивает конфигуратор CORS для Spring MVC.
   * <p>
   * Этот бин определяет настройки CORS для всех эндпоинтов приложения.
   * Конфигурация разрешает запросы с любых источников со стандартными
   * HTTP методами и заголовками.
   * </p>
   *
   * @return конфигуратор WebMvcConfigurer с настройками CORS
   */
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        /**
         * Настраивает CORS для всех путей в приложении.
         *
         * @param registry реестр для настройки CORS
         *
         * Конфигурация включает:
         * - Разрешение запросов со всех источников (allowedOrigins("*"))
         * - Разрешение основных HTTP методов (GET, POST, PUT, DELETE, OPTIONS)
         * - Разрешение всех заголовков (allowedHeaders("*"))
         * - Отключение передачи учетных данных (allowCredentials(false))
         * - Установка максимального времени кэширования предзапросов (maxAge(3600))
         */
        registry.addMapping("/**")
                .allowedOrigins("*") // Разрешить все источники
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
      }
    };
  }
}