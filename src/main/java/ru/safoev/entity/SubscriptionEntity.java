package ru.safoev.entity;

import jakarta.persistence.*;
import ru.safoev.enumlists.SubscriptionStatus;
import java.time.LocalDate;

/**
 * Сущность абонемента клиента фитнес-центра.
 * <p>
 * Представляет таблицу "subscriptions" в базе данных и содержит информацию об абонементах клиентов.
 * Используется для хранения и управления данными абонементов в системе.
 * </p>
 *
 * @Entity указывает, что этот класс является JPA сущностью
 * @Table(name = "subscriptions") задает имя таблицы в базе данных
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity {

  /**
   * Уникальный идентификатор абонемента.
   * <p>
   * Генерируется автоматически с использованием стратегии IDENTITY.
   * Соответствует столбцу "subscription_id" в таблице базы данных.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subscription_id")
  private Long subscription_id;

  /**
   * Клиент, которому принадлежит абонемент.
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
   * Тариф абонемента.
   * <p>
   * Связь ManyToOne с сущностью RateEntity.
   * Обязательное поле (nullable = false).
   * Соответствует внешнему ключу "rate_id" в таблице базы данных.
   * </p>
   */
  @ManyToOne
  @JoinColumn(name = "rate_id", nullable = false)
  private RateEntity rate;

  /**
   * Дата начала действия абонемента.
   * <p>
   * Обязательное поле (nullable = false).
   * Соответствует столбцу "subscription_start_date" в таблице базы данных.
   * </p>
   */
  @Column(name = "subscription_start_date", nullable = false)
  private LocalDate subscription_startDate;

  /**
   * Дата окончания действия абонемента.
   * <p>
   * Обязательное поле (nullable = false).
   * Соответствует столбцу "subscription_end_date" в таблице базы данных.
   * </p>
   */
  @Column(name = "subscription_end_date", nullable = false)
  private LocalDate subscription_endDate;

  /**
   * Период заморозки абонемента.
   * <p>
   * Необязательное поле.
   * Максимальная длина - 50 символов.
   * Соответствует столбцу "subscription_freeze_period" в таблице базы данных.
   * </p>
   */
  @Column(name = "subscription_freeze_period", length = 50)
  private String subscription_freezePeriod;

  /**
   * Статус абонемента.
   * <p>
   * Использует перечисление SubscriptionStatus.
   * Хранится как строка в базе данных (EnumType.STRING).
   * Максимальная длина - 20 символов.
   * Значение по умолчанию - SubscriptionStatus.ACTIVE.
   * Соответствует столбцу "subscription_status" в таблице базы данных.
   * </p>
   */
  @Column(name = "subscription_status", length = 20)
  @Enumerated(EnumType.STRING)
  private SubscriptionStatus subscription_status = SubscriptionStatus.ACTIVE;

  /**
   * Конструктор без параметров (требуется JPA).
   */
  public SubscriptionEntity() {
  }

  /**
   * Конструктор со всеми параметрами.
   *
   * @param subscription_id уникальный идентификатор абонемента
   * @param client клиент, которому принадлежит абонемент
   * @param rate тариф абонемента
   * @param subscription_startDate дата начала действия
   * @param subscription_endDate дата окончания действия
   * @param subscription_freezePeriod период заморозки
   * @param subscription_status статус абонемента
   */
  public SubscriptionEntity(Long subscription_id, ClientEntity client, RateEntity rate,
                            LocalDate subscription_startDate, LocalDate subscription_endDate,
                            String subscription_freezePeriod, SubscriptionStatus subscription_status) {
    this.subscription_id = subscription_id;
    this.client = client;
    this.rate = rate;
    this.subscription_startDate = subscription_startDate;
    this.subscription_endDate = subscription_endDate;
    this.subscription_freezePeriod = subscription_freezePeriod;
    this.subscription_status = subscription_status;
  }

  // Геттеры и сеттеры

  /**
   * Возвращает идентификатор абонемента.
   *
   * @return идентификатор абонемента
   */
  public Long getSubscription_id() {
    return subscription_id;
  }

  /**
   * Устанавливает идентификатор абонемента.
   *
   * @param subscription_id идентификатор абонемента
   */
  public void setSubscription_id(Long subscription_id) {
    this.subscription_id = subscription_id;
  }

  /**
   * Возвращает клиента, которому принадлежит абонемент.
   *
   * @return клиент
   */
  public ClientEntity getClient() {
    return client;
  }

  /**
   * Устанавливает клиента для абонемента.
   *
   * @param client клиент
   */
  public void setClient(ClientEntity client) {
    this.client = client;
  }

  /**
   * Возвращает тариф абонемента.
   *
   * @return тариф
   */
  public RateEntity getRate() {
    return rate;
  }

  /**
   * Устанавливает тариф для абонемента.
   *
   * @param rate тариф
   */
  public void setRate(RateEntity rate) {
    this.rate = rate;
  }

  /**
   * Возвращает дату начала действия абонемента.
   *
   * @return дата начала действия
   */
  public LocalDate getSubscription_startDate() {
    return subscription_startDate;
  }

  /**
   * Устанавливает дату начала действия абонемента.
   *
   * @param subscription_startDate дата начала действия
   */
  public void setSubscription_startDate(LocalDate subscription_startDate) {
    this.subscription_startDate = subscription_startDate;
  }

  /**
   * Возвращает дату окончания действия абонемента.
   *
   * @return дата окончания действия
   */
  public LocalDate getSubscription_endDate() {
    return subscription_endDate;
  }

  /**
   * Устанавливает дату окончания действия абонемента.
   *
   * @param subscription_endDate дата окончания действия
   */
  public void setSubscription_endDate(LocalDate subscription_endDate) {
    this.subscription_endDate = subscription_endDate;
  }

  /**
   * Возвращает период заморозки абонемента.
   *
   * @return период заморозки
   */
  public String getSubscription_freezePeriod() {
    return subscription_freezePeriod;
  }

  /**
   * Устанавливает период заморозки абонемента.
   *
   * @param subscription_freezePeriod период заморозки
   */
  public void setSubscription_freezePeriod(String subscription_freezePeriod) {
    this.subscription_freezePeriod = subscription_freezePeriod;
  }

  /**
   * Возвращает статус абонемента.
   *
   * @return статус абонемента
   */
  public SubscriptionStatus getSubscription_status() {
    return subscription_status;
  }

  /**
   * Устанавливает статус абонемента.
   *
   * @param subscription_status статус абонемента
   */
  public void setSubscription_status(SubscriptionStatus subscription_status) {
    this.subscription_status = subscription_status;
  }
}