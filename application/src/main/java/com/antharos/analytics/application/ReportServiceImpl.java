package com.antharos.analytics.application;

import com.antharos.analytics.domain.*;
import com.antharos.analytics.domain.repository.DepartmentDistributionRepository;
import com.antharos.analytics.domain.repository.EmployeeKpiRepository;
import com.antharos.analytics.domain.repository.ItDistributionRepository;
import com.antharos.analytics.domain.repository.MonthKpiRepository;
import com.antharos.analytics.domain.service.ReportService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

  private final EmployeeKpiRepository employeeKpiRepo;
  private final MonthKpiRepository monthKpiRepo;
  private final DepartmentDistributionRepository deptRepo;
  private final ItDistributionRepository itRepo;

  @Override
  public List<MonthlyEmployeeCount> getEmployeeCountByMonth() {
    return this.employeeKpiRepo.getEmployeeCountByMonth();
  }

  @Override
  public List<MonthlySalaryCost> getSalaryCostByMonth() {
    return this.monthKpiRepo.getSalaryCostByMonth();
  }

  @Override
  public List<DepartmentEmployees> getEmployeesByDepartment() {
    return this.deptRepo.getEmployeesByDepartment();
  }

  @Override
  public List<DepartmentSalary> getSalaryByDepartment() {
    return this.deptRepo.getSalaryByDepartment();
  }

  @Override
  public List<JobTitleEmployees> getEmployeesByJobTitle() {
    return this.itRepo.getEmployeesByJobTitle();
  }
}
