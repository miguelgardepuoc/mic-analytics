package com.antharos.analytics.infrastructure.out.repository.departmentdistribution;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "department_distribution")
public class DepartmentDistributionEntity {

  @Id private UUID departmentId;

  @Column(name = "total_employees", nullable = false)
  private Long totalEmployees;

  @Column(name = "total_salary")
  private BigDecimal totalSalary;
}
