package com.antharos.analytics.infrastructure.out.repository.itdistribution;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "it_distribution")
public class ItDistributionEntity {

  @Id private UUID jobTitleId;

  @Column(name = "total_employees", nullable = false)
  private Long totalEmployees;
}
