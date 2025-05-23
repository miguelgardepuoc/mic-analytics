package com.antharos.analytics.application.handlers;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import com.antharos.analytics.domain.EmployeeEvent;
import com.antharos.analytics.domain.MonthKpi;
import com.antharos.analytics.domain.repository.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTerminatedEventHandlerUnitTest {

  private MonthKpiRepository monthKpiRepository;
  private EmployeeKpiRepository employeeKpiRepository;
  private DepartmentDistributionRepository departmentDistributionRepository;
  private ItDistributionRepository itDistributionRepository;

  private EmployeeTerminatedEventHandler handler;

  @BeforeEach
  void setup() {
    monthKpiRepository = mock(MonthKpiRepository.class);
    employeeKpiRepository = mock(EmployeeKpiRepository.class);
    departmentDistributionRepository = mock(DepartmentDistributionRepository.class);
    itDistributionRepository = mock(ItDistributionRepository.class);

    handler =
        new EmployeeTerminatedEventHandler(
            monthKpiRepository,
            employeeKpiRepository,
            departmentDistributionRepository,
            itDistributionRepository);
  }

  @Test
  void handle_shouldUpdateMonthKpiAndRepositories() {
    LocalDate currentMonth = LocalDate.now().withDayOfMonth(1);
    BigDecimal salary = BigDecimal.valueOf(5000);
    String departmentId = "a4a77bc5-e352-4ac0-8ec3-d3af8271f61f"; // tech dept id
    String jobTitleId = UUID.randomUUID().toString();

    EmployeeEvent event = new EmployeeEvent();
    event.setId(UUID.randomUUID());
    event.setSalary(salary);
    event.setDepartmentId(departmentId);
    event.setJobTitleId(jobTitleId);

    MonthKpi existingMonthKpi = new MonthKpi(currentMonth, BigDecimal.valueOf(15000));
    when(monthKpiRepository.findByMonth(currentMonth)).thenReturn(Optional.of(existingMonthKpi));

    handler.handle(event);

    verify(monthKpiRepository)
        .save(
            argThat(
                kpi ->
                    kpi.getMonth().equals(currentMonth)
                        && kpi.getTotalSalary().equals(BigDecimal.valueOf(10000))));

    verify(employeeKpiRepository).save(argThat(kpi -> kpi.getMonth().equals(currentMonth)));

    verify(departmentDistributionRepository)
        .save(
            argThat(deptDist -> deptDist.getDepartmentId().equals(UUID.fromString(departmentId))));

    verify(itDistributionRepository)
        .save(argThat(itDist -> itDist.getJobTitleId().equals(UUID.fromString(jobTitleId))));
  }

  @Test
  void handle_shouldNotSaveMonthKpiWhenSalaryIsNull() {
    LocalDate currentMonth = LocalDate.now().withDayOfMonth(1);

    EmployeeEvent event = new EmployeeEvent();
    event.setId(UUID.randomUUID());
    event.setSalary(null);

    when(monthKpiRepository.findByMonth(currentMonth))
        .thenReturn(Optional.of(new MonthKpi(currentMonth, null)));

    handler.handle(event);

    verify(monthKpiRepository, never()).save(any(MonthKpi.class));
  }

  @Test
  void canHandle_shouldReturnTrueForEmployeeTerminated() {
    boolean result = handler.canHandle("EmployeeTerminated");
    assert result;
  }

  @Test
  void canHandle_shouldReturnFalseForOtherEvent() {
    boolean result = handler.canHandle("EmployeeHired");
    assert !result;
  }
}
