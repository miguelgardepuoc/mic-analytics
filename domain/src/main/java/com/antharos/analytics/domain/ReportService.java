package com.antharos.analytics.domain;

import java.util.List;

public interface ReportService {
  List<MonthlyEmployeeCount> getEmployeeCountByMonth();

  List<MonthlySalaryCost> getSalaryCostByMonth();

  List<DepartmentEmployees> getEmployeesByDepartment();

  List<DepartmentSalary> getSalaryByDepartment();

  List<JobTitleEmployees> getEmployeesByJobTitle();
}
