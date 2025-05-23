package com.antharos.analytics.application.handlers;

import static org.mockito.Mockito.*;

import com.antharos.analytics.domain.*;
import com.antharos.analytics.domain.repository.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeHiredEventHandlerUnitTest {

  private MonthKpiRepository monthKpiRepo;
  private EmployeeKpiRepository employeeKpiRepo;
  private DepartmentDistributionRepository deptRepo;
  private ItDistributionRepository itRepo;
  private EmployeeHiredEventHandler handler;

  @BeforeEach
  void setUp() {
    monthKpiRepo = mock(MonthKpiRepository.class);
    employeeKpiRepo = mock(EmployeeKpiRepository.class);
    deptRepo = mock(DepartmentDistributionRepository.class);
    itRepo = mock(ItDistributionRepository.class);
    handler = new EmployeeHiredEventHandler(monthKpiRepo, employeeKpiRepo, deptRepo, itRepo);
  }

  @Test
  void handle_shouldUpdateMonthKpiAndRepositoriesCorrectly() {
    LocalDate currentMonth = LocalDate.now().withDayOfMonth(1);
    BigDecimal salary = BigDecimal.valueOf(5000);
    String departmentId = "a4a77bc5-e352-4ac0-8ec3-d3af8271f61f";
    String jobTitleId = UUID.randomUUID().toString();

    EmployeeEvent event = new EmployeeEvent();
    event.setId(UUID.randomUUID());
    event.setSalary(salary);
    event.setDepartmentId(departmentId);
    event.setJobTitleId(jobTitleId);

    MonthKpi existingKpi = new MonthKpi(currentMonth, BigDecimal.valueOf(10000));
    when(monthKpiRepo.findByMonth(currentMonth)).thenReturn(Optional.of(existingKpi));
    when(employeeKpiRepo.findByMonth(currentMonth))
        .thenReturn(Optional.of(new EmployeeKpi(currentMonth, 10L)));

    handler.handle(event);

    verify(monthKpiRepo)
        .save(
            argThat(
                kpi ->
                    kpi.getMonth().equals(currentMonth)
                        && kpi.getTotalSalary().equals(BigDecimal.valueOf(15000))));

    verify(employeeKpiRepo)
        .save(
            argThat(
                kpi -> kpi.getMonth().equals(currentMonth) && kpi.getTotalEmployees().equals(11L)));

    verify(deptRepo)
        .save(
            argThat(
                dist ->
                    dist.getDepartmentId().equals(UUID.fromString(departmentId))
                        && dist.getTotalEmployees() == 1
                        && dist.getTotalSalary().compareTo(salary) == 0));

    verify(itRepo)
        .save(
            argThat(
                itDist ->
                    itDist.getJobTitleId().equals(UUID.fromString(jobTitleId))
                        && itDist.getTotalEmployees() == 1));
  }

  @Test
  void canHandle_shouldReturnTrueForEmployeeHired() {
    boolean result = handler.canHandle("EmployeeHired");
    assert result;
  }

  @Test
  void canHandle_shouldReturnFalseForOtherEvent() {
    boolean result = handler.canHandle("EmployeeOnLeave");
    assert !result;
  }
}
