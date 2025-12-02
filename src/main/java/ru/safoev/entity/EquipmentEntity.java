package ru.safoev.entity;

import jakarta.persistence.*;
import ru.safoev.enumlists.EquipmentStatus;
import java.time.LocalDate;

/**
 * Сущность оборудования фитнес-центра.
 * <p>
 * Представляет таблицу "equipment" в базе данных и содержит информацию об оборудовании.
 * Используется для хранения и управления данными оборудования в системе.
 * </p>
 *
 * @Entity указывает, что этот класс является JPA сущностью
 * @Table(name = "equipment") задает имя таблицы в базе данных
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "equipment")
public class EquipmentEntity {

  /**
   * Уникальный идентификатор оборудования.
   * <p>
   * Генерируется автоматически с использованием стратегии IDENTITY.
   * Соответствует столбцу "equipment_id" в таблице базы данных.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "equipment_id")
  private Long equipmentId;

  /**
   * Название оборудования.
   * <p>
   * Обязательное поле (nullable = false).
   * Максимальная длина - 100 символов.
   * Соответствует столбцу "equipment_name" в таблице базы данных.
   * </p>
   */
  @Column(name = "equipment_name", nullable = false, length = 100)
  private String equipmentName;

  /**
   * Дата покупки оборудования.
   * <p>
   * Необязательное поле (может быть null).
   * Соответствует столбцу "equipment_buy_date" в таблице базы данных.
   * </p>
   */
  @Column(name = "equipment_buy_date")
  private LocalDate equipment_buyDate;

  /**
   * Статус оборудования.
   * <p>
   * Использует перечисление EquipmentStatus.
   * Максимальная длина - 20 символов.
   * Значение по умолчанию - EquipmentStatus.ACTIVE.
   * Соответствует столбцу "equipment_status" в таблице базы данных.
   * </p>
   */
  @Column(name = "equipment_status", length = 20)
  private EquipmentStatus equipmentStatus = EquipmentStatus.ACTIVE;

  /**
   * Фитнес-зал, к которому принадлежит оборудование.
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
   * Конструктор без параметров (требуется JPA).
   */
  public EquipmentEntity() {}

  /**
   * Конструктор со всеми параметрами.
   *
   * @param equipmentId уникальный идентификатор оборудования
   * @param equipmentName название оборудования
   * @param equipment_buyDate дата покупки оборудования
   * @param equipmentStatus статус оборудования
   * @param gym фитнес-зал, к которому принадлежит оборудование
   */
  public EquipmentEntity(Long equipmentId, String equipmentName, LocalDate equipment_buyDate,
                         EquipmentStatus equipmentStatus, GymEntity gym) {
    this.equipmentId = equipmentId;
    this.equipmentName = equipmentName;
    this.equipment_buyDate = equipment_buyDate;
    this.equipmentStatus = equipmentStatus;
    this.gym = gym;
  }

  // Геттеры и сеттеры

  /**
   * Возвращает идентификатор оборудования.
   *
   * @return идентификатор оборудования
   */
  public Long getEquipmentId() {
    return equipmentId;
  }

  /**
   * Устанавливает идентификатор оборудования.
   *
   * @param equipmentId идентификатор оборудования
   */
  public void setEquipmentId(Long equipmentId) {
    this.equipmentId = equipmentId;
  }

  /**
   * Возвращает название оборудования.
   *
   * @return название оборудования
   */
  public String getEquipmentName() {
    return equipmentName;
  }

  /**
   * Устанавливает название оборудования.
   *
   * @param equipmentName название оборудования
   */
  public void setEquipmentName(String equipmentName) {
    this.equipmentName = equipmentName;
  }

  /**
   * Возвращает дату покупки оборудования.
   *
   * @return дата покупки оборудования
   */
  public LocalDate getEquipment_buyDate() {
    return equipment_buyDate;
  }

  /**
   * Устанавливает дату покупки оборудования.
   *
   * @param equipment_buyDate дата покупки оборудования
   */
  public void setEquipment_buyDate(LocalDate equipment_buyDate) {
    this.equipment_buyDate = equipment_buyDate;
  }

  /**
   * Возвращает статус оборудования.
   *
   * @return статус оборудования
   */
  public EquipmentStatus getEquipmentStatus() {
    return equipmentStatus;
  }

  /**
   * Устанавливает статус оборудования.
   *
   * @param equipmentStatus статус оборудования
   */
  public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
    this.equipmentStatus = equipmentStatus;
  }

  /**
   * Возвращает фитнес-зал, к которому принадлежит оборудование.
   *
   * @return фитнес-зал
   */
  public GymEntity getGym() {
    return gym;
  }

  /**
   * Устанавливает фитнес-зал для оборудования.
   *
   * @param gym фитнес-зал
   */
  public void setGym(GymEntity gym) {
    this.gym = gym;
  }
}