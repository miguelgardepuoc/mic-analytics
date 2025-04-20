package com.antharos.analytics.infrastructure.out.repository.employeekpi;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;

@Getter
@Entity
@Table(name = "employee_kpi")
@IdClass(EmployeeKpiId.class)
public class EmployeeKpiEntity {

  @Id private LocalDate month;

  @Id private UUID status;

  @Column(name = "total_employees", nullable = false)
  private Long totalEmployees;
}
