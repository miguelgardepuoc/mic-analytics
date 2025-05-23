package com.antharos.analytics.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.antharos.analytics.domain.*;
import com.antharos.analytics.domain.repository.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportServiceImplUnitTest {

  private EmployeeKpiRepository employeeKpiRepo;
  private MonthKpiRepository monthKpiRepo;
  private DepartmentDistributionRepository deptRepo;
  private ItDistributionRepository itRepo;
  private ReportServiceImpl service;

  @BeforeEach
  void setUp() {
    employeeKpiRepo = mock(EmployeeKpiRepository.class);
    monthKpiRepo = mock(MonthKpiRepository.class);
    deptRepo = mock(DepartmentDistributionRepository.class);
    itRepo = mock(ItDistributionRepository.class);
    service = new ReportServiceImpl(employeeKpiRepo, monthKpiRepo, deptRepo, itRepo);
  }

  @Test
  void getEmployeeCountByMonth_shouldReturnExpectedResult() {
    List<MonthlyEmployeeCount> expected = List.of(mock(MonthlyEmployeeCount.class));
    when(employeeKpiRepo.getEmployeeCountByMonth()).thenReturn(expected);

    List<MonthlyEmployeeCount> result = service.getEmployeeCountByMonth();

    assertThat(result).isEqualTo(expected);
    verify(employeeKpiRepo).getEmployeeCountByMonth();
  }

  @Test
  void getSalaryCostByMonth_shouldReturnExpectedResult() {
    List<MonthlySalaryCost> expected = List.of(mock(MonthlySalaryCost.class));
    when(monthKpiRepo.getSalaryCostByMonth()).thenReturn(expected);

    List<MonthlySalaryCost> result = service.getSalaryCostByMonth();

    assertThat(result).isEqualTo(expected);
    verify(monthKpiRepo).getSalaryCostByMonth();
  }

  @Test
  void getEmployeesByDepartment_shouldReturnExpectedResult() {
    List<DepartmentEmployees> expected = List.of(mock(DepartmentEmployees.class));
    when(deptRepo.getEmployeesByDepartment()).thenReturn(expected);

    List<DepartmentEmployees> result = service.getEmployeesByDepartment();

    assertThat(result).isEqualTo(expected);
    verify(deptRepo).getEmployeesByDepartment();
  }

  @Test
  void getSalaryByDepartment_shouldReturnExpectedResult() {
    List<DepartmentSalary> expected = List.of(mock(DepartmentSalary.class));
    when(deptRepo.getSalaryByDepartment()).thenReturn(expected);

    List<DepartmentSalary> result = service.getSalaryByDepartment();

    assertThat(result).isEqualTo(expected);
    verify(deptRepo).getSalaryByDepartment();
  }

  @Test
  void getEmployeesByJobTitle_shouldReturnExpectedResult() {
    List<JobTitleEmployees> expected = List.of(mock(JobTitleEmployees.class));
    when(itRepo.getEmployeesByJobTitle()).thenReturn(expected);

    List<JobTitleEmployees> result = service.getEmployeesByJobTitle();

    assertThat(result).isEqualTo(expected);
    verify(itRepo).getEmployeesByJobTitle();
  }
}
