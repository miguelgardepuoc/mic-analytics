package com.antharos.analytics.domain.repository;

import com.antharos.analytics.domain.DepartmentDistribution;
import com.antharos.analytics.domain.DepartmentEmployees;
import com.antharos.analytics.domain.DepartmentSalary;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartmentDistributionRepository {
  List<DepartmentEmployees> getEmployeesByDepartment();

  List<DepartmentSalary> getSalaryByDepartment();

  Optional<DepartmentDistribution> findByDepartmentId(UUID departmentId);

  void save(DepartmentDistribution departmentDistribution);
}
