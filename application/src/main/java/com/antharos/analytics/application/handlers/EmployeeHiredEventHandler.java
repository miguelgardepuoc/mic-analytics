package com.antharos.analytics.application.handlers;

import static com.antharos.analytics.application.EventHandlerUtil.*;

import com.antharos.analytics.domain.EmployeeEvent;
import com.antharos.analytics.domain.EventNames;
import com.antharos.analytics.domain.MonthKpi;
import com.antharos.analytics.domain.repository.DepartmentDistributionRepository;
import com.antharos.analytics.domain.repository.EmployeeKpiRepository;
import com.antharos.analytics.domain.repository.ItDistributionRepository;
import com.antharos.analytics.domain.repository.MonthKpiRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeHiredEventHandler implements EmployeeEventHandler {
  private final MonthKpiRepository monthKpiRepository;
  private final EmployeeKpiRepository employeeKpiRepository;
  private final DepartmentDistributionRepository departmentDistributionRepository;
  private final ItDistributionRepository itDistributionRepository;

  @Override
  public void handle(EmployeeEvent event) {
    log.info("Processing EMPLOYEE_HIRED event for employee {}", event.getId());

    LocalDate currentMonth = LocalDate.now().withDayOfMonth(1);

    MonthKpi monthKpi = this.monthKpiRepository.findByMonth(currentMonth).orElse(new MonthKpi());

    monthKpi.setMonth(currentMonth);
    BigDecimal newTotalSalary =
        monthKpi.getTotalSalary() != null
            ? monthKpi.getTotalSalary().add(event.getSalary())
            : event.getSalary();
    monthKpi.setTotalSalary(newTotalSalary);

    this.monthKpiRepository.save(monthKpi);

    updateEmployeeCount(this.employeeKpiRepository, currentMonth, 1);

    if (event.getDepartmentId() != null) {
      updateDepartmentDistribution(
          this.departmentDistributionRepository,
          UUID.fromString(event.getDepartmentId()),
          1,
          event.getSalary());
    }

    if (belongsToTechnologyDepartment(event)) {
      updateItDistribution(
          this.itDistributionRepository, UUID.fromString(event.getJobTitleId()), 1);
    }
  }

  @Override
  public boolean canHandle(String eventName) {
    return EventNames.EMPLOYEE_HIRED.getDescription().equals(eventName);
  }
}
