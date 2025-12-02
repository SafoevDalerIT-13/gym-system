package ru.safoev.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Сущность тарифа фитнес-центра.
 * <p>
 * Представляет таблицу "rates" в базе данных и содержит информацию о тарифах.
 * Используется для хранения и управления данными тарифов в системе.
 * </p>
 *
 * @Entity указывает, что этот класс является JPA сущностью
 * @Table(name = "rates") задает имя таблицы в базе данных
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "rates")
public class RateEntity {

  /**
   * Уникальный идентификатор тарифа.
   * <p>
   * Генерируется автоматически с использованием стратегии IDENTITY.
   * Соответствует столбцу "rate_id" в таблице базы данных.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rate_id")
  private Long rate_id;

  /**
   * Название тарифа.
   * <p>
   * Максимальная длина - 100 символов.
   * Соответствует столбцу "rate_name" в таблице базы данных.
   * </p>
   */
  @Column(name = "rate_name", length = 100)
  private String rate_name;

  /**
   * Цена тарифа.
   * <p>
   * Обязательное поле (nullable = false).
   * Используется BigDecimal для точных финансовых расчетов.
   * Precision = 10, Scale = 2 (всего 10 цифр, 2 из которых после запятой).
   * Соответствует столбцу "rate_price" в таблице базы данных.
   * </p>
   */
  @Column(name = "rate_price", nullable = false, precision = 10, scale = 2)
  private BigDecimal rate_price;

  /**
   * Период оплаты тарифа.
   * <p>
   * Например: "месяц", "год", "квартал".
   * Максимальная длина - 50 символов.
   * Соответствует столбцу "rate_price_period" в таблице базы данных.
   * </p>
   */
  @Column(name = "rate_price_period", length = 50)
  private String rate_price_period;

  /**
   * Длительность действия тарифа в днях.
   * <p>
   * Обязательное поле (nullable = false).
   * Соответствует столбцу "rate_duration_days" в таблице базы данных.
   * </p>
   */
  @Column(name = "rate_duration_days", nullable = false)
  private Integer rate_duration_days;

  /**
   * Описание тарифа.
   * <p>
   * Использует тип TEXT для хранения длинных текстов.
   * Соответствует столбцу "rate_description" в таблице базы данных.
   * </p>
   */
  @Column(name = "rate_description", columnDefinition = "TEXT")
  private String rate_description;

  /**
   * Конструктор без параметров (требуется JPA).
   */
  public RateEntity() {
  }

  /**
   * Конструктор со всеми параметрами.
   *
   * @param rate_id уникальный идентификатор тарифа
   * @param rate_name название тарифа
   * @param rate_price цена тарифа
   * @param rate_price_period период оплаты тарифа
   * @param rate_duration_days длительность действия в днях
   * @param rate_description описание тарифа
   */
  public RateEntity(Long rate_id, String rate_name, BigDecimal rate_price,
                    String rate_price_period, Integer rate_duration_days,
                    String rate_description) {
    this.rate_id = rate_id;
    this.rate_name = rate_name;
    this.rate_price = rate_price;
    this.rate_price_period = rate_price_period;
    this.rate_duration_days = rate_duration_days;
    this.rate_description = rate_description;
  }

  // Геттеры и сеттеры

  /**
   * Возвращает идентификатор тарифа.
   *
   * @return идентификатор тарифа
   */
  public Long getRate_id() {
    return rate_id;
  }

  /**
   * Устанавливает идентификатор тарифа.
   *
   * @param rate_id идентификатор тарифа
   */
  public void setRate_id(Long rate_id) {
    this.rate_id = rate_id;
  }

  /**
   * Возвращает название тарифа.
   *
   * @return название тарифа
   */
  public String getRate_name() {
    return rate_name;
  }

  /**
   * Устанавливает название тарифа.
   *
   * @param rate_name название тарифа
   */
  public void setRate_name(String rate_name) {
    this.rate_name = rate_name;
  }

  /**
   * Возвращает цену тарифа.
   *
   * @return цена тарифа
   */
  public BigDecimal getRate_price() {
    return rate_price;
  }

  /**
   * Устанавливает цену тарифа.
   *
   * @param rate_price цена тарифа
   */
  public void setRate_price(BigDecimal rate_price) {
    this.rate_price = rate_price;
  }

  /**
   * Возвращает период оплаты тарифа.
   *
   * @return период оплаты тарифа
   */
  public String getRate_price_period() {
    return rate_price_period;
  }

  /**
   * Устанавливает период оплаты тарифа.
   *
   * @param rate_price_period период оплаты тарифа
   */
  public void setRate_price_period(String rate_price_period) {
    this.rate_price_period = rate_price_period;
  }

  /**
   * Возвращает длительность действия тарифа в днях.
   *
   * @return длительность действия в днях
   */
  public Integer getRate_duration_days() {
    return rate_duration_days;
  }

  /**
   * Устанавливает длительность действия тарифа в днях.
   *
   * @param rate_duration_days длительность действия в днях
   */
  public void setRate_duration_days(Integer rate_duration_days) {
    this.rate_duration_days = rate_duration_days;
  }

  /**
   * Возвращает описание тарифа.
   *
   * @return описание тарифа
   */
  public String getRate_description() {
    return rate_description;
  }

  /**
   * Устанавливает описание тарифа.
   *
   * @param rate_description описание тарифа
   */
  public void setRate_description(String rate_description) {
    this.rate_description = rate_description;
  }
}