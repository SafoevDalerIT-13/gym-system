package ru.safoev.entity;

import jakarta.persistence.*;
import ru.safoev.enumlists.EquipmentStatus;

import java.time.LocalDate;

@Entity
@Table(name = "equipment")
public class EquipmentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "equipment_id")
  private Long equipmentId;

  @Column(name = "equipment_name", nullable = false, length = 100)
  private String equipmentName;

  @Column(name = "equipment_buy_date")
  private LocalDate equipment_buyDate;

  @Column(name = "equipment_status", length = 20)
  private EquipmentStatus equipmentStatus = EquipmentStatus.ACTIVE;

  @ManyToOne
  @JoinColumn(name = "gym_id", nullable = false)
  private GymEntity gym;

  public EquipmentEntity() {}

  public EquipmentEntity(Long equipmentId, String equipmentName, LocalDate equipment_buyDate, EquipmentStatus equipmentStatus, GymEntity gym) {
    this.equipmentId = equipmentId;
    this.equipmentName = equipmentName;
    this.equipment_buyDate = equipment_buyDate;
    this.equipmentStatus = equipmentStatus;
    this.gym = gym;
  }

  public Long getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(Long equipmentId) {
    this.equipmentId = equipmentId;
  }

  public String getEquipmentName() {
    return equipmentName;
  }

  public void setEquipmentName(String equipmentName) {
    this.equipmentName = equipmentName;
  }

  public LocalDate getEquipment_buyDate() {
    return equipment_buyDate;
  }

  public void setEquipment_buyDate(LocalDate equipment_buyDate) {
    this.equipment_buyDate = equipment_buyDate;
  }

  public EquipmentStatus getEquipmentStatus() {
    return equipmentStatus;
  }

  public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
    this.equipmentStatus = equipmentStatus;
  }

  public GymEntity getGym() {
    return gym;
  }

  public void setGym(GymEntity gym) {
    this.gym = gym;
  }
}