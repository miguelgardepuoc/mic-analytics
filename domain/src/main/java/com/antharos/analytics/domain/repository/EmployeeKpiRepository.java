package com.antharos.analytics.domain.repository;

import com.antharos.analytics.domain.MonthlyEmployeeCount;
import java.util.List;

public interface EmployeeKpiRepository {
  List<MonthlyEmployeeCount> getEmployeeCountByMonth();
}
