package com.antharos.analytics.application;

import com.antharos.analytics.domain.EmployeeKpi;
import com.antharos.analytics.domain.MonthKpi;
import com.antharos.analytics.domain.repository.EmployeeKpiRepository;
import com.antharos.analytics.domain.repository.MonthKpiRepository;
import com.antharos.analytics.domain.service.MaterializeKpiForNewMonthUseCase;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MaterializeKpiForNewMonthService implements MaterializeKpiForNewMonthUseCase {

  private final MonthKpiRepository monthKpiRepository;
  private final EmployeeKpiRepository employeeKpiRepository;

  @Override
  public void execute(LocalDate newMonth) {
    LocalDate previousMonth = newMonth.minusMonths(1);

    MonthKpi prevMonthKpi =
        this.monthKpiRepository
            .findByMonth(previousMonth)
            .orElseThrow(() -> new IllegalStateException("No KPI for previous month"));

    EmployeeKpi prevEmployeeKpi =
        this.employeeKpiRepository
            .findByMonth(previousMonth)
            .orElseThrow(() -> new IllegalStateException("No Employee KPI for previous month"));

    this.monthKpiRepository.save(new MonthKpi(newMonth, prevMonthKpi.getTotalSalary()));
    this.employeeKpiRepository.save(new EmployeeKpi(newMonth, prevEmployeeKpi.getTotalEmployees()));
  }
}
