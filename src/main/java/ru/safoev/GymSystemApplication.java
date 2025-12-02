package ru.safoev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Главный класс приложения системы управления фитнес-центром.
 * <p>
 * Этот класс является точкой входа в Spring Boot приложение.
 * Содержит конфигурационные аннотации для настройки компонентов
 * Spring Boot, JPA репозиториев и сканирования сущностей.
 * </p>
 *
 * @SpringBootApplication объединяет три аннотации:
 * - @Configuration: помечает класс как источник конфигурационных бинов
 * - @EnableAutoConfiguration: включает автоматическую конфигурацию Spring Boot
 * - @ComponentScan: включает сканирование компонентов в пакете и подпакетах
 *
 * @EnableJpaRepositories включает сканирование и регистрацию JPA репозиториев
 * @EntityScan включает сканирование JPA сущностей в указанных пакетах
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@SpringBootApplication
@EnableJpaRepositories("ru.safoev.repositoryinterface")
@EntityScan("ru.safoev.entity")
public class GymSystemApplication {

  /**
   * Главный метод приложения, запускающий Spring Boot приложение.
   * <p>
   * Этот метод инициализирует Spring Application Context,
   * запускает встроенный сервер приложений и настраивает все бины.
   * </p>
   *
   * @param args аргументы командной строки, переданные при запуске приложения
   */
	public static void main(String[] args) {
    SpringApplication.run(GymSystemApplication.class, args);
	}

}
