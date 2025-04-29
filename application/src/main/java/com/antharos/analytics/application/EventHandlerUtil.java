package com.antharos.analytics.application;

import com.antharos.analytics.domain.DepartmentDistribution;
import com.antharos.analytics.domain.EmployeeEvent;
import com.antharos.analytics.domain.EmployeeKpi;
import com.antharos.analytics.domain.ItDistribution;
import com.antharos.analytics.domain.repository.DepartmentDistributionRepository;
import com.antharos.analytics.domain.repository.EmployeeKpiRepository;
import com.antharos.analytics.domain.repository.ItDistributionRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventHandlerUtil {

  private static final String TECHNOLOGY = "a4a77bc5-e352-4ac0-8ec3-d3af8271f61f";

  public static void updateEmployeeCount(
      EmployeeKpiRepository repository, LocalDate month, int delta) {
    EmployeeKpi employeeKpi = repository.findByMonth(month).orElse(new EmployeeKpi());

    employeeKpi.setMonth(month);
    long currentCount =
        employeeKpi.getTotalEmployees() != null ? employeeKpi.getTotalEmployees() : 0L;
    employeeKpi.setTotalEmployees(currentCount + delta);

    repository.save(employeeKpi);
  }

  public static void updateDepartmentDistribution(
      DepartmentDistributionRepository repository,
      UUID departmentId,
      int employeeDelta,
      BigDecimal salaryDelta) {

    DepartmentDistribution departmentDistribution =
        repository.findByDepartmentId(departmentId).orElse(new DepartmentDistribution());

    departmentDistribution.setDepartmentId(departmentId);

    long currentEmployees =
        departmentDistribution.getTotalEmployees() != null
            ? departmentDistribution.getTotalEmployees()
            : 0L;
    departmentDistribution.setTotalEmployees(currentEmployees + employeeDelta);

    BigDecimal currentSalary =
        departmentDistribution.getTotalSalary() != null
            ? departmentDistribution.getTotalSalary()
            : BigDecimal.ZERO;
    departmentDistribution.setTotalSalary(currentSalary.add(salaryDelta));

    repository.save(departmentDistribution);
  }

  public static void updateItDistribution(
      ItDistributionRepository repository, UUID jobTitleId, int delta) {

    ItDistribution itDistribution =
        repository.findByJobTitleId(jobTitleId).orElse(new ItDistribution());

    itDistribution.setJobTitleId(jobTitleId);
    long currentCount =
        itDistribution.getTotalEmployees() != null ? itDistribution.getTotalEmployees() : 0L;
    itDistribution.setTotalEmployees(currentCount + delta);

    repository.save(itDistribution);
  }

  public static boolean belongsToTechnologyDepartment(EmployeeEvent event) {
    return event != null && TECHNOLOGY.equals(event.getDepartmentId());
  }
}
