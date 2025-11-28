package ru.safoev.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employees_id")
  private Long employees_id;

  @Column(name = "employees_first_name",nullable = false,length = 50)
  private String employees_first_name;

  @Column(name = "employees_last_name",nullable = false,length = 50)
  private String employees_last_name;

  @Column(name = "employees_phone",length = 20, unique = true)
  private String employees_phone;

  @Column(name = "employees_email",length = 100)
  private String employees_email;

  @ManyToOne
  @JoinColumn(name = "gym_id")
  private GymEntity gym;

  @Column(name = "employees_hire_date", nullable = false)
  private LocalDate employees_hire_date;

  @Column(name = "employees_dismissal_date")
  private LocalDate employees_dismissalDate;

  @Column(name = "employees_post", nullable = false, length = 50)
  private String employees_post;

  @Column(name = "employees_salary", nullable = false, precision = 10, scale = 2)
  private BigDecimal employees_salary;

  public EmployeeEntity() {
  }

  public EmployeeEntity(Long employees_id, String employees_first_name, String employees_last_name, String employees_phone, String employees_email, GymEntity gym, LocalDate employees_hire_date, LocalDate employees_dismissalDate, String employees_post, BigDecimal employees_salary) {
    this.employees_id = employees_id;
    this.employees_first_name = employees_first_name;
    this.employees_last_name = employees_last_name;
    this.employees_phone = employees_phone;
    this.employees_email = employees_email;
    this.gym = gym;
    this.employees_hire_date = employees_hire_date;
    this.employees_dismissalDate = employees_dismissalDate;
    this.employees_post = employees_post;
    this.employees_salary = employees_salary;
  }

  public Long getEmployees_id() {
    return employees_id;
  }

  public void setEmployees_id(Long employees_id) {
    this.employees_id = employees_id;
  }

  public String getEmployees_first_name() {
    return employees_first_name;
  }

  public void setEmployees_first_name(String employees_first_name) {
    this.employees_first_name = employees_first_name;
  }

  public String getEmployees_last_name() {
    return employees_last_name;
  }

  public void setEmployees_last_name(String employees_last_name) {
    this.employees_last_name = employees_last_name;
  }

  public String getEmployees_phone() {
    return employees_phone;
  }

  public void setEmployees_phone(String employees_phone) {
    this.employees_phone = employees_phone;
  }

  public String getEmployees_email() {
    return employees_email;
  }

  public void setEmployees_email(String employees_email) {
    this.employees_email = employees_email;
  }

  public GymEntity getGym() {
    return gym;
  }

  public void setGym(GymEntity gym) {
    this.gym = gym;
  }

  public LocalDate getEmployees_hire_date() {
    return employees_hire_date;
  }

  public void setEmployees_hire_date(LocalDate employees_hire_date) {
    this.employees_hire_date = employees_hire_date;
  }

  public LocalDate getEmployees_dismissalDate() {
    return employees_dismissalDate;
  }

  public void setEmployees_dismissalDate(LocalDate employees_dismissalDate) {
    this.employees_dismissalDate = employees_dismissalDate;
  }

  public String getEmployees_post() {
    return employees_post;
  }

  public void setEmployees_post(String employees_post) {
    this.employees_post = employees_post;
  }

  public BigDecimal getEmployees_salary() {
    return employees_salary;
  }

  public void setEmployees_salary(BigDecimal employees_salary) {
    this.employees_salary = employees_salary;
  }
}
