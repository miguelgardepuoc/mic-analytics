package com.antharos.analytics.infrastructure.out.repository.monthkpi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;

@Getter
@Entity
@Table(name = "month_kpi")
public class MonthKpiEntity {
  @Id private LocalDate month;

  @Column(name = "total_salary", nullable = false)
  private Long totalSalary;
}
