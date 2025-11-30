package ru.safoev.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "gums", uniqueConstraints = {
        @UniqueConstraint(columnNames = "gum_address")
})
public class GymEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gum_id")
  private Long gum_id;

  @Column(name = "gum_name",nullable = false,length = 100)
  private String gum_name;

  @Column(name = "gum_address",nullable = false,length = 100)
  private String gum_address;

  @Column(name = "gum_phone",length = 20)
  private String gum_phone;

  @Column(name = "gum_open_time", nullable = false)
  private LocalTime gum_open_time;

  @Column(name = "gum_end_time",nullable = false)
  private LocalTime gum_end_time;

  public GymEntity() {
  }

  public GymEntity(Long gum_id, String gum_name, String gum_address, String gum_phone, LocalTime gum_open_time, LocalTime gum_end_time) {
    this.gum_id = gum_id;
    this.gum_name = gum_name;
    this.gum_address = gum_address;
    this.gum_phone = gum_phone;
    this.gum_open_time = gum_open_time;
    this.gum_end_time = gum_end_time;
  }

  public Long getGum_id() {
    return gum_id;
  }

  public void setGum_id(Long gum_id) {
    this.gum_id = gum_id;
  }

  public String getGum_name() {
    return gum_name;
  }

  public void setGum_name(String gum_name) {
    this.gum_name = gum_name;
  }

  public String getGum_address() {
    return gum_address;
  }

  public void setGum_address(String gum_address) {
    this.gum_address = gum_address;
  }

  public String getGum_phone() {
    return gum_phone;
  }

  public void setGum_phone(String gum_phone) {
    this.gum_phone = gum_phone;
  }

  public LocalTime getGum_open_time() {
    return gum_open_time;
  }

  public void setGum_open_time(LocalTime gum_open_time) {
    this.gum_open_time = gum_open_time;
  }

  public LocalTime getGum_end_time() {
    return gum_end_time;
  }

  public void setGum_end_time(LocalTime gum_end_time) {
    this.gum_end_time = gum_end_time;
  }
}
