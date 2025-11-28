package ru.safoev.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
public class VisitEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "visit_id")
  private Long visitId;

  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false)
  private ClientEntity client;

  @ManyToOne
  @JoinColumn(name = "gym_id", nullable = false)
  private GymEntity gym;

  @Column(name = "visit_check_in_time", nullable = false)
  private LocalDateTime visit_checkInTime;

  @Column(name = "visit_check_out_time")
  private LocalDateTime visit_checkOutTime;

  public VisitEntity() {}

  public VisitEntity(Long visitId, ClientEntity client, GymEntity gym, LocalDateTime visit_checkInTime, LocalDateTime visit_checkOutTime) {
    this.visitId = visitId;
    this.client = client;
    this.gym = gym;
    this.visit_checkInTime = visit_checkInTime;
    this.visit_checkOutTime = visit_checkOutTime;
  }

  public Long getVisitId() {
    return visitId;
  }

  public void setVisitId(Long visitId) {
    this.visitId = visitId;
  }

  public ClientEntity getClient() {
    return client;
  }

  public void setClient(ClientEntity client) {
    this.client = client;
  }

  public GymEntity getGym() {
    return gym;
  }

  public void setGym(GymEntity gym) {
    this.gym = gym;
  }

  public LocalDateTime getVisit_checkInTime() {
    return visit_checkInTime;
  }

  public void setVisit_checkInTime(LocalDateTime visit_checkInTime) {
    this.visit_checkInTime = visit_checkInTime;
  }

  public LocalDateTime getVisit_checkOutTime() {
    return visit_checkOutTime;
  }

  public void setVisit_checkOutTime(LocalDateTime visit_checkOutTime) {
    this.visit_checkOutTime = visit_checkOutTime;
  }
}
