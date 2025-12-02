package ru.safoev.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность посещения фитнес-центра.
 * <p>
 * Представляет таблицу "visits" в базе данных и содержит информацию о посещениях клиентов.
 * Используется для хранения и управления данными о посещениях в системе.
 * </p>
 *
 * @Entity указывает, что этот класс является JPA сущностью
 * @Table(name = "visits") задает имя таблицы в базе данных
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "visits")
public class VisitEntity {

  /**
   * Уникальный идентификатор посещения.
   * <p>
   * Генерируется автоматически с использованием стратегии IDENTITY.
   * Соответствует столбцу "visit_id" в таблице базы данных.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "visit_id")
  private Long visitId;

  /**
   * Клиент, совершивший посещение.
   * <p>
   * Связь ManyToOne с сущностью ClientEntity.
   * Обязательное поле (nullable = false).
   * Соответствует внешнему ключу "client_id" в таблице базы данных.
   * </p>
   */
  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private ClientEntity client;

  /**
   * Фитнес-зал, который посетил клиент.
   * <p>
   * Связь ManyToOne с сущностью GymEntity.
   * Обязательное поле (nullable = false).
   * Соответствует внешнему ключу "gym_id" в таблице базы данных.
   * </p>
   */
  @ManyToOne
  @JoinColumn(name = "gym_id", nullable = false)
  private GymEntity gym;

  /**
   * Время входа клиента в фитнес-зал.
   * <p>
   * Обязательное поле (nullable = false).
   * Соответствует столбцу "visit_check_in_time" в таблице базы данных.
   * </p>
   */
  @Column(name = "visit_check_in_time", nullable = false)
  private LocalDateTime visit_checkInTime;

  /**
   * Время выхода клиента из фитнес-зала.
   * <p>
   * Необязательное поле (может быть null, если клиент еще не вышел).
   * Соответствует столбцу "visit_check_out_time" в таблице базы данных.
   * </p>
   */
  @Column(name = "visit_check_out_time")
  private LocalDateTime visit_checkOutTime;

  /**
   * Конструктор без параметров (требуется JPA).
   */
  public VisitEntity() {}

  /**
   * Конструктор со всеми параметрами.
   *
   * @param visitId уникальный идентификатор посещения
   * @param client клиент, совершивший посещение
   * @param gym фитнес-зал, который посетил клиент
   * @param visit_checkInTime время входа клиента
   * @param visit_checkOutTime время выхода клиента
   */
  public VisitEntity(Long visitId, ClientEntity client, GymEntity gym,
                     LocalDateTime visit_checkInTime, LocalDateTime visit_checkOutTime) {
    this.visitId = visitId;
    this.client = client;
    this.gym = gym;
    this.visit_checkInTime = visit_checkInTime;
    this.visit_checkOutTime = visit_checkOutTime;
  }

  // Геттеры и сеттеры

  /**
   * Возвращает идентификатор посещения.
   *
   * @return идентификатор посещения
   */
  public Long getVisitId() {
    return visitId;
  }

  /**
   * Устанавливает идентификатор посещения.
   *
   * @param visitId идентификатор посещения
   */
  public void setVisitId(Long visitId) {
    this.visitId = visitId;
  }

  /**
   * Возвращает клиента, совершившего посещение.
   *
   * @return клиент
   */
  public ClientEntity getClient() {
    return client;
  }

  /**
   * Устанавливает клиента для посещения.
   *
   * @param client клиент
   */
  public void setClient(ClientEntity client) {
    this.client = client;
  }

  /**
   * Возвращает фитнес-зал, который посетил клиент.
   *
   * @return фитнес-зал
   */
  public GymEntity getGym() {
    return gym;
  }

  /**
   * Устанавливает фитнес-зал для посещения.
   *
   * @param gym фитнес-зал
   */
  public void setGym(GymEntity gym) {
    this.gym = gym;
  }

  /**
   * Возвращает время входа клиента.
   *
   * @return время входа
   */
  public LocalDateTime getVisit_checkInTime() {
    return visit_checkInTime;
  }

  /**
   * Устанавливает время входа клиента.
   *
   * @param visit_checkInTime время входа
   */
  public void setVisit_checkInTime(LocalDateTime visit_checkInTime) {
    this.visit_checkInTime = visit_checkInTime;
  }

  /**
   * Возвращает время выхода клиента.
   *
   * @return время выхода (может быть null)
   */
  public LocalDateTime getVisit_checkOutTime() {
    return visit_checkOutTime;
  }

  /**
   * Устанавливает время выхода клиента.
   *
   * @param visit_checkOutTime время выхода
   */
  public void setVisit_checkOutTime(LocalDateTime visit_checkOutTime) {
    this.visit_checkOutTime = visit_checkOutTime;
  }
}