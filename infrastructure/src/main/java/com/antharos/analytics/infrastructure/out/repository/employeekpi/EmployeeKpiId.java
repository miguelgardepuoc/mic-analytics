package com.antharos.analytics.infrastructure.out.repository.employeekpi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeKpiId implements Serializable {

  private LocalDate month;

  public EmployeeKpiId(LocalDate month) {
    this.month = month;
  }

  public EmployeeKpiId() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EmployeeKpiId that)) return false;
    return Objects.equals(month, that.month);
  }

  @Override
  public int hashCode() {
    return Objects.hash(month);
  }
}
