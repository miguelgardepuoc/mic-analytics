package com.antharos.analytics.infrastructure.in.controller;

import com.antharos.analytics.domain.*;
import com.antharos.analytics.domain.service.ReportService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/kpi")
public class KpiController {

  private final ReportService reportService;

  @GetMapping("/employees/monthly")
  public List<MonthlyEmployeeCount> getEmployeesByMonth() {
    return reportService.getEmployeeCountByMonth();
  }

  @GetMapping("/salary/monthly")
  public List<MonthlySalaryCost> getSalaryByMonth() {
    return reportService.getSalaryCostByMonth();
  }

  @GetMapping("/employees/department")
  public List<DepartmentEmployees> getEmployeesByDepartment() {
    return reportService.getEmployeesByDepartment();
  }

  @GetMapping("/salary/department")
  public List<DepartmentSalary> getSalaryByDepartment() {
    return reportService.getSalaryByDepartment();
  }

  @GetMapping("/employees/job-title")
  public List<JobTitleEmployees> getEmployeesByJobTitle() {
    return reportService.getEmployeesByJobTitle();
  }
}
