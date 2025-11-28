package ru.safoev.entity;


import jakarta.persistence.*;
import ru.safoev.enumlists.SubscriptionStatus;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subscription_id")
  private Long subscription_id;

  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private ClientEntity client;

  @ManyToOne
  @JoinColumn(name = "rate_id", nullable = false)
  private RateEntity rate;

  @Column(name = "subscription_start_date", nullable = false)
  private LocalDate subscription_startDate;

  @Column(name = "subscription_end_date", nullable = false)
  private LocalDate subscription_endDate;

  @Column(name = "subscription_freeze_period", length = 50)
  private String subscription_freezePeriod;

  @Column(name = "subscription_status", length = 20)
  private SubscriptionStatus subscription_status = SubscriptionStatus.ACTIVE;

  public SubscriptionEntity() {
  }

  public SubscriptionEntity(Long subscription_id, ClientEntity client, RateEntity rate, LocalDate subscription_startDate, LocalDate subscription_endDate, String subscription_freezePeriod, SubscriptionStatus subscription_status) {
    this.subscription_id = subscription_id;
    this.client = client;
    this.rate = rate;
    this.subscription_startDate = subscription_startDate;
    this.subscription_endDate = subscription_endDate;
    this.subscription_freezePeriod = subscription_freezePeriod;
    this.subscription_status = subscription_status;
  }

  public Long getSubscription_id() {
    return subscription_id;
  }

  public void setSubscription_id(Long subscription_id) {
    this.subscription_id = subscription_id;
  }

  public ClientEntity getClient() {
    return client;
  }

  public void setClient(ClientEntity client) {
    this.client = client;
  }

  public RateEntity getRate() {
    return rate;
  }

  public void setRate(RateEntity rate) {
    this.rate = rate;
  }

  public LocalDate getSubscription_startDate() {
    return subscription_startDate;
  }

  public void setSubscription_startDate(LocalDate subscription_startDate) {
    this.subscription_startDate = subscription_startDate;
  }

  public LocalDate getSubscription_endDate() {
    return subscription_endDate;
  }

  public void setSubscription_endDate(LocalDate subscription_endDate) {
    this.subscription_endDate = subscription_endDate;
  }

  public String getSubscription_freezePeriod() {
    return subscription_freezePeriod;
  }

  public void setSubscription_freezePeriod(String subscription_freezePeriod) {
    this.subscription_freezePeriod = subscription_freezePeriod;
  }

  public SubscriptionStatus getSubscription_status() {
    return subscription_status;
  }

  public void setSubscription_status(SubscriptionStatus subscription_status) {
    this.subscription_status = subscription_status;
  }
}
