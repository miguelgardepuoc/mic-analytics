package com.antharos.analytics.domain.repository;

import com.antharos.analytics.domain.DepartmentEmployees;
import com.antharos.analytics.domain.DepartmentSalary;
import java.util.List;

public interface DepartmentDistributionRepository {
  List<DepartmentEmployees> getEmployeesByDepartment();

  List<DepartmentSalary> getSalaryByDepartment();
}
