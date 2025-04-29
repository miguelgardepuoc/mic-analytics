package com.antharos.analytics.infrastructure.out.repository.employeekpi;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "employee_kpi")
@IdClass(EmployeeKpiId.class)
public class EmployeeKpiEntity {

  @Id private LocalDate month;

  @Column(name = "total_employees", nullable = false)
  private Long totalEmployees;
}
