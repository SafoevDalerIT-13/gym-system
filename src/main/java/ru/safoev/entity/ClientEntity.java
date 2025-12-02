package ru.safoev.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Сущность клиента фитнес-центра.
 * <p>
 * Представляет таблицу "clients" в базе данных и содержит информацию о клиентах.
 * Используется для хранения и управления данными клиентов в системе.
 * </p>
 *
 * @Entity указывает, что этот класс является JPA сущностью
 * @Table(name = "clients") задает имя таблицы в базе данных
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "clients")
public class ClientEntity {

  /**
   * Уникальный идентификатор клиента.
   * <p>
   * Генерируется автоматически с использованием стратегии IDENTITY.
   * Соответствует столбцу "client_id" в таблице базы данных.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "client_id")
  private Long client_id;

  /**
   * Имя клиента.
   * <p>
   * Обязательное поле (nullable = false).
   * Максимальная длина - 50 символов.
   * Соответствует столбцу "client_first_name" в таблице базы данных.
   * </p>
   */
  @Column(name = "client_first_name", nullable = false, length = 50)
  private String client_first_name;

  /**
   * Фамилия клиента.
   * <p>
   * Обязательное поле (nullable = false).
   * Максимальная длина - 50 символов.
   * Соответствует столбцу "client_last_name" в таблице базы данных.
   * </p>
   */
  @Column(name = "client_last_name", nullable = false, length = 50)
  private String client_last_name;

  /**
   * Телефонный номер клиента.
   * <p>
   * Должен быть уникальным (unique = true).
   * Максимальная длина - 20 символов.
   * Соответствует столбцу "client_phone" в таблице базы данных.
   * </p>
   */
  @Column(name = "client_phone", length = 20, unique = true)
  private String client_phone;

  /**
   * Электронная почта клиента.
   * <p>
   * Максимальная длина - 100 символов.
   * Соответствует столбцу "client_email" в таблице базы данных.
   * </p>
   */
  @Column(name = "client_email", length = 100)
  private String client_email;

  /**
   * Дата рождения клиента.
   * <p>
   * Соответствует столбцу "client_date_of_birth" в таблице базы данных.
   * </p>
   */
  @Column(name = "client_date_of_birth")
  private LocalDate client_date_of_birth;

  /**
   * Дата и время регистрации клиента в системе.
   * <p>
   * Автоматически устанавливается при создании записи через метод @PrePersist.
   * Соответствует столбцу "client_registration_date" в таблице базы данных.
   * </p>
   */
  @Column(name = "client_registration_date")
  private LocalDateTime client_registration_date;

  /**
   * Конструктор без параметров (требуется JPA).
   */
  public ClientEntity() {
  }

  /**
   * Callback-метод, выполняемый перед сохранением сущности.
   * <p>
   * Автоматически устанавливает дату регистрации на текущее время,
   * если она не была установлена ранее.
   * </p>
   */
  @PrePersist
  protected void onCreate() {
    if (client_registration_date == null) {
      client_registration_date = LocalDateTime.now();
    }
  }

  /**
   * Конструктор со всеми параметрами.
   *
   * @param client_id уникальный идентификатор клиента
   * @param client_first_name имя клиента
   * @param client_last_name фамилия клиента
   * @param client_phone телефонный номер клиента
   * @param client_email электронная почта клиента
   * @param client_date_of_birth дата рождения клиента
   * @param client_registration_date дата регистрации клиента
   */
  public ClientEntity(Long client_id, String client_first_name, String client_last_name,
                      String client_phone, String client_email, LocalDate client_date_of_birth,
                      LocalDateTime client_registration_date) {
    this.client_id = client_id;
    this.client_first_name = client_first_name;
    this.client_last_name = client_last_name;
    this.client_phone = client_phone;
    this.client_email = client_email;
    this.client_date_of_birth = client_date_of_birth;
    this.client_registration_date = client_registration_date;
  }

  // Геттеры и сеттеры

  /**
   * Возвращает идентификатор клиента.
   *
   * @return идентификатор клиента
   */
  public Long getClient_id() {
    return client_id;
  }

  /**
   * Устанавливает идентификатор клиента.
   *
   * @param client_id идентификатор клиента
   */
  public void setClient_id(Long client_id) {
    this.client_id = client_id;
  }

  /**
   * Возвращает имя клиента.
   *
   * @return имя клиента
   */
  public String getClient_first_name() {
    return client_first_name;
  }

  /**
   * Устанавливает имя клиента.
   *
   * @param client_first_name имя клиента
   */
  public void setClient_first_name(String client_first_name) {
    this.client_first_name = client_first_name;
  }

  /**
   * Возвращает фамилию клиента.
   *
   * @return фамилия клиента
   */
  public String getClient_last_name() {
    return client_last_name;
  }

  /**
   * Устанавливает фамилию клиента.
   *
   * @param client_last_name фамилия клиента
   */
  public void setClient_last_name(String client_last_name) {
    this.client_last_name = client_last_name;
  }

  /**
   * Возвращает телефонный номер клиента.
   *
   * @return телефонный номер клиента
   */
  public String getClient_phone() {
    return client_phone;
  }

  /**
   * Устанавливает телефонный номер клиента.
   *
   * @param client_phone телефонный номер клиента
   */
  public void setClient_phone(String client_phone) {
    this.client_phone = client_phone;
  }

  /**
   * Возвращает электронную почту клиента.
   *
   * @return электронная почта клиента
   */
  public String getClient_email() {
    return client_email;
  }

  /**
   * Устанавливает электронную почту клиента.
   *
   * @param client_email электронная почта клиента
   */
  public void setClient_email(String client_email) {
    this.client_email = client_email;
  }

  /**
   * Возвращает дату рождения клиента.
   *
   * @return дата рождения клиента
   */
  public LocalDate getClient_date_of_birth() {
    return client_date_of_birth;
  }

  /**
   * Устанавливает дату рождения клиента.
   *
   * @param client_date_of_birth дата рождения клиента
   */
  public void setClient_date_of_birth(LocalDate client_date_of_birth) {
    this.client_date_of_birth = client_date_of_birth;
  }

  /**
   * Возвращает дату регистрации клиента.
   *
   * @return дата регистрации клиента
   */
  public LocalDateTime getClient_registration_date() {
    return client_registration_date;
  }

  /**
   * Устанавливает дату регистрации клиента.
   *
   * @param client_registration_date дата регистрации клиента
   */
  public void setClient_registration_date(LocalDateTime client_registration_date) {
    this.client_registration_date = client_registration_date;
  }
}