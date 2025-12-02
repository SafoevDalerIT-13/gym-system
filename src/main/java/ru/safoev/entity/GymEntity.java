package ru.safoev.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "gyms", uniqueConstraints = {
        @UniqueConstraint(columnNames = "gym_address")
})
public class GymEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gym_id")
  private Long gym_id;

  @Column(name = "gym_name",nullable = false,length = 100)
  private String gym_name;

  @Column(name = "gym_address",nullable = false,length = 100)
  private String gym_address;

  @Column(name = "gym_phone",length = 20)
  private String gym_phone;

  @Column(name = "gym_open_time", nullable = false)
  private LocalTime gym_open_time;

  @Column(name = "gym_end_time",nullable = false)
  private LocalTime gym_end_time;

  public GymEntity() {
  }

  public GymEntity(Long gym_id, String gym_name, String gym_address, String gym_phone, LocalTime gym_open_time, LocalTime gym_end_time) {
    this.gym_id = gym_id;
    this.gym_name = gym_name;
    this.gym_address = gym_address;
    this.gym_phone = gym_phone;
    this.gym_open_time = gym_open_time;
    this.gym_end_time = gym_end_time;
  }

  public Long getGym_id() {
    return gym_id;
  }

  public void setGym_id(Long gym_id) {
    this.gym_id = gym_id;
  }

  public String getGym_name() {
    return gym_name;
  }

  public void setGym_name(String gym_name) {
    this.gym_name = gym_name;
  }

  public String getGym_address() {
    return gym_address;
  }

  public void setGym_address(String gym_address) {
    this.gym_address = gym_address;
  }

  public String getGym_phone() {
    return gym_phone;
  }

  public void setGym_phone(String gym_phone) {
    this.gym_phone = gym_phone;
  }

  public LocalTime getGym_open_time() {
    return gym_open_time;
  }

  public void setGym_open_time(LocalTime gym_open_time) {
    this.gym_open_time = gym_open_time;
  }

  public LocalTime getGym_end_time() {
    return gym_end_time;
  }

  public void setGym_end_time(LocalTime gym_end_time) {
    this.gym_end_time = gym_end_time;
  }
}
