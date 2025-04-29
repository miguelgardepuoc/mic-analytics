package com.antharos.analytics.infrastructure.out.repository.monthkpi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name = "month_kpi")
public class MonthKpiEntity {
  @Id private LocalDate month;

  @Column(name = "total_salary", precision = 19, scale = 2, nullable = false)
  private BigDecimal totalSalary;
}
