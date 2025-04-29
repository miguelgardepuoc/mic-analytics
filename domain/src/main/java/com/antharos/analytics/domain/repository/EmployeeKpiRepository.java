package com.antharos.analytics.domain.repository;

import com.antharos.analytics.domain.EmployeeKpi;
import com.antharos.analytics.domain.MonthlyEmployeeCount;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeKpiRepository {
  List<MonthlyEmployeeCount> getEmployeeCountByMonth();

  Optional<EmployeeKpi> findByMonth(LocalDate month);

  void save(EmployeeKpi employeeKpi);
}
