package com.antharos.analytics.application;

import static org.mockito.Mockito.*;

import com.antharos.analytics.domain.EmployeeKpi;
import com.antharos.analytics.domain.MonthKpi;
import com.antharos.analytics.domain.repository.EmployeeKpiRepository;
import com.antharos.analytics.domain.repository.MonthKpiRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaterializeKpiForNewMonthServiceUnitTest {

  private MonthKpiRepository monthKpiRepository;
  private EmployeeKpiRepository employeeKpiRepository;
  private MaterializeKpiForNewMonthService service;

  @BeforeEach
  void setUp() {
    monthKpiRepository = mock(MonthKpiRepository.class);
    employeeKpiRepository = mock(EmployeeKpiRepository.class);
    service = new MaterializeKpiForNewMonthService(monthKpiRepository, employeeKpiRepository);
  }

  @Test
  void whenExecute_thenCopiesPreviousMonthKpiToNewMonth() {
    LocalDate newMonth = LocalDate.of(2024, 5, 1);
    LocalDate previousMonth = newMonth.minusMonths(1);

    MonthKpi previousMonthKpi = new MonthKpi(previousMonth, new BigDecimal("10000"));
    EmployeeKpi previousEmployeeKpi = new EmployeeKpi(previousMonth, 42L);

    when(monthKpiRepository.findByMonth(previousMonth)).thenReturn(Optional.of(previousMonthKpi));
    when(employeeKpiRepository.findByMonth(previousMonth))
        .thenReturn(Optional.of(previousEmployeeKpi));

    service.execute(newMonth);

    verify(monthKpiRepository).save(any(MonthKpi.class));
    verify(employeeKpiRepository).save(any(EmployeeKpi.class));
  }

  @Test
  void whenPreviousMonthKpiIsMissing_thenThrowsException() {
    LocalDate newMonth = LocalDate.of(2024, 5, 1);
    LocalDate previousMonth = newMonth.minusMonths(1);

    when(monthKpiRepository.findByMonth(previousMonth)).thenReturn(Optional.empty());

    org.junit.jupiter.api.Assertions.assertThrows(
        IllegalStateException.class, () -> service.execute(newMonth), "No KPI for previous month");
  }

  @Test
  void whenPreviousEmployeeKpiIsMissing_thenThrowsException() {
    LocalDate newMonth = LocalDate.of(2024, 5, 1);
    LocalDate previousMonth = newMonth.minusMonths(1);

    MonthKpi previousMonthKpi = new MonthKpi(previousMonth, new BigDecimal("10000"));
    when(monthKpiRepository.findByMonth(previousMonth)).thenReturn(Optional.of(previousMonthKpi));
    when(employeeKpiRepository.findByMonth(previousMonth)).thenReturn(Optional.empty());

    org.junit.jupiter.api.Assertions.assertThrows(
        IllegalStateException.class,
        () -> service.execute(newMonth),
        "No Employee KPI for previous month");
  }
}
