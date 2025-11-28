package ru.safoev.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class ClientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "client_id")
  private Long client_id;

  @Column(name = "client_first_name",nullable = false,length = 50)
  private String client_first_name;

  @Column(name = "client_last_name",nullable = false,length = 50)
  private String client_last_name;

  @Column(name = "client_phone",length = 20, unique = true)
  private String client_phone;

  @Column(name = "client_email",length = 100)
  private String client_email;

  @Column(name = "client_date_of_birth")
  private LocalDate client_date_of_birth;

  @Column(name = "client_registration_date")
  private LocalDateTime client_registration_date;

  public ClientEntity() {
  }

  public ClientEntity(Long client_id, String client_first_name, String client_last_name, String client_phone, String client_email, LocalDate client_date_of_birth, LocalDateTime client_registration_date) {
    this.client_id = client_id;
    this.client_first_name = client_first_name;
    this.client_last_name = client_last_name;
    this.client_phone = client_phone;
    this.client_email = client_email;
    this.client_date_of_birth = client_date_of_birth;
    this.client_registration_date = client_registration_date;
  }

  public Long getClient_id() {
    return client_id;
  }

  public void setClient_id(Long client_id) {
    this.client_id = client_id;
  }

  public String getClient_first_name() {
    return client_first_name;
  }

  public void setClient_first_name(String client_first_name) {
    this.client_first_name = client_first_name;
  }

  public String getClient_last_name() {
    return client_last_name;
  }

  public void setClient_last_name(String client_last_name) {
    this.client_last_name = client_last_name;
  }

  public String getClient_phone() {
    return client_phone;
  }

  public void setClient_phone(String client_phone) {
    this.client_phone = client_phone;
  }

  public String getClient_email() {
    return client_email;
  }

  public void setClient_email(String client_email) {
    this.client_email = client_email;
  }

  public LocalDate getClient_date_of_birth() {
    return client_date_of_birth;
  }

  public void setClient_date_of_birth(LocalDate client_date_of_birth) {
    this.client_date_of_birth = client_date_of_birth;
  }

  public LocalDateTime getClient_registration_date() {
    return client_registration_date;
  }

  public void setClient_registration_date(LocalDateTime client_registration_date) {
    this.client_registration_date = client_registration_date;
  }
}
