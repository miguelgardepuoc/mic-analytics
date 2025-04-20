package com.antharos.analytics.infrastructure.out.repository.employeekpi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "employee_kpi")
@IdClass(EmployeeKpiId.class)
public class EmployeeKpiEntity {

  @Id private LocalDate month;

  @Id private UUID status;

  @Column(name = "total_employees", nullable = false)
  private Long totalEmployees;
}
