package ru.safoev.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "rates")
public class RateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rate_id")
  private Long rate_id;

  @Column(name = "rate_name",length = 100)
  private String rate_name;

  @Column(name = "rate_price", nullable = false, precision = 10, scale = 2)
  private BigDecimal rate_price;

  @Column(name = "rate_price_period", length = 50)
  private String rate_price_period;

  @Column(name = "rate_duration_days", nullable = false)
  private Integer rate_duration_ays;

  @Column(name = "rate_description", columnDefinition = "TEXT")
  private String rate_description;

  public RateEntity() {
  }

  public RateEntity(Long rate_id, String rate_name, BigDecimal rate_price, String rate_price_period, Integer rate_duration_ays, String rate_description) {
    this.rate_id = rate_id;
    this.rate_name = rate_name;
    this.rate_price = rate_price;
    this.rate_price_period = rate_price_period;
    this.rate_duration_ays = rate_duration_ays;
    this.rate_description = rate_description;
  }

  public Long getRate_id() {
    return rate_id;
  }

  public void setRate_id(Long rate_id) {
    this.rate_id = rate_id;
  }

  public String getRate_name() {
    return rate_name;
  }

  public void setRate_name(String rate_name) {
    this.rate_name = rate_name;
  }

  public BigDecimal getRate_price() {
    return rate_price;
  }

  public void setRate_price(BigDecimal rate_price) {
    this.rate_price = rate_price;
  }

  public String getRate_price_period() {
    return rate_price_period;
  }

  public void setRate_price_period(String rate_price_period) {
    this.rate_price_period = rate_price_period;
  }

  public Integer getRate_duration_ays() {
    return rate_duration_ays;
  }

  public void setRate_duration_ays(Integer rate_duration_ays) {
    this.rate_duration_ays = rate_duration_ays;
  }

  public String getRate_description() {
    return rate_description;
  }

  public void setRate_description(String rate_description) {
    this.rate_description = rate_description;
  }
}
