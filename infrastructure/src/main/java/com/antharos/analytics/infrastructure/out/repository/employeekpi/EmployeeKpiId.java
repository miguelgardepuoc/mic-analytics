package com.antharos.analytics.infrastructure.out.repository.employeekpi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class EmployeeKpiId implements Serializable {

  private LocalDate month;
  private UUID status;

  public EmployeeKpiId() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EmployeeKpiId)) return false;
    EmployeeKpiId that = (EmployeeKpiId) o;
    return Objects.equals(month, that.month) && Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(month, status);
  }
}
