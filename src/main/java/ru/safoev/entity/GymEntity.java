package ru.safoev.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

/**
 * Сущность фитнес-зала.
 * <p>
 * Представляет таблицу "gyms" в базе данных и содержит информацию о фитнес-залах.
 * Используется для хранения и управления данными фитнес-залов в системе.
 * </p>
 *
 * @Entity указывает, что этот класс является JPA сущностью
 * @Table(name = "gyms") задает имя таблицы в базе данных
 * @UniqueConstraint обеспечивает уникальность адреса зала
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "gyms", uniqueConstraints = {
        @UniqueConstraint(columnNames = "gym_address")
})
public class GymEntity {

  /**
   * Уникальный идентификатор фитнес-зала.
   * <p>
   * Генерируется автоматически с использованием стратегии IDENTITY.
   * Соответствует столбцу "gym_id" в таблице базы данных.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gym_id")
  private Long gym_id;

  /**
   * Название фитнес-зала.
   * <p>
   * Обязательное поле (nullable = false).
   * Максимальная длина - 100 символов.
   * Соответствует столбцу "gym_name" в таблице базы данных.
   * </p>
   */
  @Column(name = "gym_name", nullable = false, length = 100)
  private String gym_name;

  /**
   * Адрес фитнес-зала.
   * <p>
   * Обязательное поле (nullable = false).
   * Максимальная длина - 100 символов.
   * Уникальное значение (уникальный индекс на столбце "gym_address").
   * Соответствует столбцу "gym_address" в таблице базы данных.
   * </p>
   */
  @Column(name = "gym_address", nullable = false, length = 100)
  private String gym_address;

  /**
   * Телефон фитнес-зала.
   * <p>
   * Необязательное поле.
   * Максимальная длина - 20 символов.
   * Соответствует столбцу "gym_phone" в таблице базы данных.
   * </p>
   */
  @Column(name = "gym_phone", length = 20)
  private String gym_phone;

  /**
   * Время открытия фитнес-зала.
   * <p>
   * Обязательное поле (nullable = false).
   * Соответствует столбцу "gym_open_time" в таблице базы данных.
   * </p>
   */
  @Column(name = "gym_open_time", nullable = false)
  private LocalTime gym_open_time;

  /**
   * Время закрытия фитнес-зала.
   * <p>
   * Обязательное поле (nullable = false).
   * Соответствует столбцу "gym_end_time" в таблице базы данных.
   * </p>
   */
  @Column(name = "gym_end_time", nullable = false)
  private LocalTime gym_end_time;

  /**
   * Конструктор без параметров (требуется JPA).
   */
  public GymEntity() {
  }

  /**
   * Конструктор со всеми параметрами.
   *
   * @param gym_id уникальный идентификатор фитнес-зала
   * @param gym_name название фитнес-зала
   * @param gym_address адрес фитнес-зала
   * @param gym_phone телефон фитнес-зала
   * @param gym_open_time время открытия
   * @param gym_end_time время закрытия
   */
  public GymEntity(Long gym_id, String gym_name, String gym_address,
                   String gym_phone, LocalTime gym_open_time, LocalTime gym_end_time) {
    this.gym_id = gym_id;
    this.gym_name = gym_name;
    this.gym_address = gym_address;
    this.gym_phone = gym_phone;
    this.gym_open_time = gym_open_time;
    this.gym_end_time = gym_end_time;
  }

  // Геттеры и сеттеры

  /**
   * Возвращает идентификатор фитнес-зала.
   *
   * @return идентификатор фитнес-зала
   */
  public Long getGym_id() {
    return gym_id;
  }

  /**
   * Устанавливает идентификатор фитнес-зала.
   *
   * @param gym_id идентификатор фитнес-зала
   */
  public void setGym_id(Long gym_id) {
    this.gym_id = gym_id;
  }

  /**
   * Возвращает название фитнес-зала.
   *
   * @return название фитнес-зала
   */
  public String getGym_name() {
    return gym_name;
  }

  /**
   * Устанавливает название фитнес-зала.
   *
   * @param gym_name название фитнес-зала
   */
  public void setGym_name(String gym_name) {
    this.gym_name = gym_name;
  }

  /**
   * Возвращает адрес фитнес-зала.
   *
   * @return адрес фитнес-зала
   */
  public String getGym_address() {
    return gym_address;
  }

  /**
   * Устанавливает адрес фитнес-зала.
   *
   * @param gym_address адрес фитнес-зала
   */
  public void setGym_address(String gym_address) {
    this.gym_address = gym_address;
  }

  /**
   * Возвращает телефон фитнес-зала.
   *
   * @return телефон фитнес-зала
   */
  public String getGym_phone() {
    return gym_phone;
  }

  /**
   * Устанавливает телефон фитнес-зала.
   *
   * @param gym_phone телефон фитнес-зала
   */
  public void setGym_phone(String gym_phone) {
    this.gym_phone = gym_phone;
  }

  /**
   * Возвращает время открытия фитнес-зала.
   *
   * @return время открытия
   */
  public LocalTime getGym_open_time() {
    return gym_open_time;
  }

  /**
   * Устанавливает время открытия фитнес-зала.
   *
   * @param gym_open_time время открытия
   */
  public void setGym_open_time(LocalTime gym_open_time) {
    this.gym_open_time = gym_open_time;
  }

  /**
   * Возвращает время закрытия фитнес-зала.
   *
   * @return время закрытия
   */
  public LocalTime getGym_end_time() {
    return gym_end_time;
  }

  /**
   * Устанавливает время закрытия фитнес-зала.
   *
   * @param gym_end_time время закрытия
   */
  public void setGym_end_time(LocalTime gym_end_time) {
    this.gym_end_time = gym_end_time;
  }
}