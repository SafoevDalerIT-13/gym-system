package ru.safoev.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Сущность сотрудника фитнес-центра.
 * <p>
 * Представляет таблицу "employees" в базе данных и содержит информацию о сотрудниках.
 * Используется для хранения и управления данными сотрудников в системе.
 * </p>
 *
 * @Entity указывает, что этот класс является JPA сущностью
 * @Table(name = "employees") задает имя таблицы в базе данных
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "employees")
public class EmployeeEntity {

  /**
   * Уникальный идентификатор сотрудника.
   * <p>
   * Генерируется автоматически с использованием стратегии IDENTITY.
   * Соответствует столбцу "employees_id" в таблице базы данных.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employees_id")
  private Long employees_id;

  /**
   * Имя сотрудника.
   * <p>
   * Обязательное поле (nullable = false).
   * Максимальная длина - 50 символов.
   * Соответствует столбцу "employees_first_name" в таблице базы данных.
   * </p>
   */
  @Column(name = "employees_first_name", nullable = false, length = 50)
  private String employees_first_name;

  /**
   * Фамилия сотрудника.
   * <p>
   * Обязательное поле (nullable = false).
   * Максимальная длина - 50 символов.
   * Соответствует столбцу "employees_last_name" в таблице базы данных.
   * </p>
   */
  @Column(name = "employees_last_name", nullable = false, length = 50)
  private String employees_last_name;

  /**
   * Телефонный номер сотрудника.
   * <p>
   * Должен быть уникальным (unique = true).
   * Максимальная длина - 20 символов.
   * Соответствует столбцу "employees_phone" в таблице базы данных.
   * </p>
   */
  @Column(name = "employees_phone", length = 20, unique = true)
  private String employees_phone;

  /**
   * Электронная почта сотрудника.
   * <p>
   * Максимальная длина - 100 символов.
   * Соответствует столбцу "employees_email" в таблице базы данных.
   * </p>
   */
  @Column(name = "employees_email", length = 100)
  private String employees_email;

  /**
   * Фитнес-зал, в котором работает сотрудник.
   * <p>
   * Связь ManyToOne с сущностью GymEntity.
   * Соответствует внешнему ключу "gym_id" в таблице базы данных.
   * </p>
   */
  @ManyToOne
  @JoinColumn(name = "gym_id")
  private GymEntity gym;

  /**
   * Дата приема на работу сотрудника.
   * <p>
   * Обязательное поле (nullable = false).
   * Соответствует столбцу "employees_hire_date" в таблице базы данных.
   * </p>
   */
  @Column(name = "employees_hire_date", nullable = false)
  private LocalDate employees_hire_date;

  /**
   * Дата увольнения сотрудника.
   * <p>
   * Необязательное поле (может быть null для работающих сотрудников).
   * Соответствует столбцу "employees_dismissal_date" в таблице базы данных.
   * </p>
   */
  @Column(name = "employees_dismissal_date")
  private LocalDate employees_dismissalDate;

  /**
   * Должность сотрудника.
   * <p>
   * Обязательное поле (nullable = false).
   * Максимальная длина - 50 символов.
   * Соответствует столбцу "employees_post" в таблице базы данных.
   * </p>
   */
  @Column(name = "employees_post", nullable = false, length = 50)
  private String employees_post;

  /**
   * Зарплата сотрудника.
   * <p>
   * Обязательное поле (nullable = false).
   * Используется BigDecimal для точных финансовых расчетов.
   * Precision = 10, Scale = 2 (всего 10 цифр, 2 из которых после запятой).
   * Соответствует столбцу "employees_salary" в таблице базы данных.
   * </p>
   */
  @Column(name = "employees_salary", nullable = false, precision = 10, scale = 2)
  private BigDecimal employees_salary;

  /**
   * Конструктор без параметров (требуется JPA).
   */
  public EmployeeEntity() {
  }

  /**
   * Конструктор со всеми параметрами.
   *
   * @param employees_id уникальный идентификатор сотрудника
   * @param employees_first_name имя сотрудника
   * @param employees_last_name фамилия сотрудника
   * @param employees_phone телефонный номер сотрудника
   * @param employees_email электронная почта сотрудника
   * @param gym фитнес-зал сотрудника
   * @param employees_hire_date дата приема на работу
   * @param employees_dismissalDate дата увольнения
   * @param employees_post должность сотрудника
   * @param employees_salary зарплата сотрудника
   */
  public EmployeeEntity(Long employees_id, String employees_first_name, String employees_last_name,
                        String employees_phone, String employees_email, GymEntity gym,
                        LocalDate employees_hire_date, LocalDate employees_dismissalDate,
                        String employees_post, BigDecimal employees_salary) {
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

  // Геттеры и сеттеры

  /**
   * Возвращает идентификатор сотрудника.
   *
   * @return идентификатор сотрудника
   */
  public Long getEmployees_id() {
    return employees_id;
  }

  /**
   * Устанавливает идентификатор сотрудника.
   *
   * @param employees_id идентификатор сотрудника
   */
  public void setEmployees_id(Long employees_id) {
    this.employees_id = employees_id;
  }

  /**
   * Возвращает имя сотрудника.
   *
   * @return имя сотрудника
   */
  public String getEmployees_first_name() {
    return employees_first_name;
  }

  /**
   * Устанавливает имя сотрудника.
   *
   * @param employees_first_name имя сотрудника
   */
  public void setEmployees_first_name(String employees_first_name) {
    this.employees_first_name = employees_first_name;
  }

  /**
   * Возвращает фамилию сотрудника.
   *
   * @return фамилия сотрудника
   */
  public String getEmployees_last_name() {
    return employees_last_name;
  }

  /**
   * Устанавливает фамилию сотрудника.
   *
   * @param employees_last_name фамилия сотрудника
   */
  public void setEmployees_last_name(String employees_last_name) {
    this.employees_last_name = employees_last_name;
  }

  /**
   * Возвращает телефонный номер сотрудника.
   *
   * @return телефонный номер сотрудника
   */
  public String getEmployees_phone() {
    return employees_phone;
  }

  /**
   * Устанавливает телефонный номер сотрудника.
   *
   * @param employees_phone телефонный номер сотрудника
   */
  public void setEmployees_phone(String employees_phone) {
    this.employees_phone = employees_phone;
  }

  /**
   * Возвращает электронную почту сотрудника.
   *
   * @return электронная почта сотрудника
   */
  public String getEmployees_email() {
    return employees_email;
  }

  /**
   * Устанавливает электронную почту сотрудника.
   *
   * @param employees_email электронная почта сотрудника
   */
  public void setEmployees_email(String employees_email) {
    this.employees_email = employees_email;
  }

  /**
   * Возвращает фитнес-зал сотрудника.
   *
   * @return фитнес-зал сотрудника
   */
  public GymEntity getGym() {
    return gym;
  }

  /**
   * Устанавливает фитнес-зал сотрудника.
   *
   * @param gym фитнес-зал сотрудника
   */
  public void setGym(GymEntity gym) {
    this.gym = gym;
  }

  /**
   * Возвращает дату приема на работу сотрудника.
   *
   * @return дата приема на работу
   */
  public LocalDate getEmployees_hire_date() {
    return employees_hire_date;
  }

  /**
   * Устанавливает дату приема на работу сотрудника.
   *
   * @param employees_hire_date дата приема на работу
   */
  public void setEmployees_hire_date(LocalDate employees_hire_date) {
    this.employees_hire_date = employees_hire_date;
  }

  /**
   * Возвращает дату увольнения сотрудника.
   *
   * @return дата увольнения (может быть null)
   */
  public LocalDate getEmployees_dismissalDate() {
    return employees_dismissalDate;
  }

  /**
   * Устанавливает дату увольнения сотрудника.
   *
   * @param employees_dismissalDate дата увольнения
   */
  public void setEmployees_dismissalDate(LocalDate employees_dismissalDate) {
    this.employees_dismissalDate = employees_dismissalDate;
  }

  /**
   * Возвращает должность сотрудника.
   *
   * @return должность сотрудника
   */
  public String getEmployees_post() {
    return employees_post;
  }

  /**
   * Устанавливает должность сотрудника.
   *
   * @param employees_post должность сотрудника
   */
  public void setEmployees_post(String employees_post) {
    this.employees_post = employees_post;
  }

  /**
   * Возвращает зарплату сотрудника.
   *
   * @return зарплата сотрудника
   */
  public BigDecimal getEmployees_salary() {
    return employees_salary;
  }

  /**
   * Устанавливает зарплату сотрудника.
   *
   * @param employees_salary зарплата сотрудника
   */
  public void setEmployees_salary(BigDecimal employees_salary) {
    this.employees_salary = employees_salary;
  }
}