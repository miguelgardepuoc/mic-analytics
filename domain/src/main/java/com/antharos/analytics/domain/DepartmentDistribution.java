package com.antharos.analytics.domain;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDistribution {
  private UUID departmentId;
  private Long totalEmployees;
  private BigDecimal totalSalary;
}
