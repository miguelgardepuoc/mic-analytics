package com.antharos.analytics.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.antharos.analytics.domain.DepartmentDistribution;
import com.antharos.analytics.domain.EmployeeEvent;
import com.antharos.analytics.domain.EmployeeKpi;
import com.antharos.analytics.domain.ItDistribution;
import com.antharos.analytics.domain.repository.DepartmentDistributionRepository;
import com.antharos.analytics.domain.repository.EmployeeKpiRepository;
import com.antharos.analytics.domain.repository.ItDistributionRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class EventHandlerUtilUnitTest {

  @Test
  void whenUpdateEmployeeCount_thenIncrementsAndSavesCorrectly() {
    EmployeeKpiRepository repository = mock(EmployeeKpiRepository.class);
    LocalDate month = LocalDate.of(2025, 5, 1);
    EmployeeKpi existing = new EmployeeKpi();
    existing.setTotalEmployees(10L);
    when(repository.findByMonth(month)).thenReturn(Optional.of(existing));

    EventHandlerUtil.updateEmployeeCount(repository, month, 5);

    assertEquals(15L, existing.getTotalEmployees());
    verify(repository).save(existing);
  }

  @Test
  void whenUpdateEmployeeCountWithoutExisting_thenCreatesNewAndSaves() {
    EmployeeKpiRepository repository = mock(EmployeeKpiRepository.class);
    LocalDate month = LocalDate.of(2025, 5, 1);
    when(repository.findByMonth(month)).thenReturn(Optional.empty());

    EventHandlerUtil.updateEmployeeCount(repository, month, 3);

    verify(repository)
        .save(argThat(kpi -> kpi.getMonth().equals(month) && kpi.getTotalEmployees() == 3));
  }

  @Test
  void whenUpdateDepartmentDistribution_thenUpdatesAndSavesCorrectly() {
    DepartmentDistributionRepository repository = mock(DepartmentDistributionRepository.class);
    UUID departmentId = UUID.randomUUID();
    DepartmentDistribution existing = new DepartmentDistribution();
    existing.setTotalEmployees(5L);
    existing.setTotalSalary(new BigDecimal("10000"));
    when(repository.findByDepartmentId(departmentId)).thenReturn(Optional.of(existing));

    EventHandlerUtil.updateDepartmentDistribution(
        repository, departmentId, 2, new BigDecimal("3000"));

    assertEquals(7L, existing.getTotalEmployees());
    assertEquals(new BigDecimal("13000"), existing.getTotalSalary());
    verify(repository).save(existing);
  }

  @Test
  void whenUpdateDepartmentDistributionWithoutExisting_thenCreatesAndSavesNew() {
    DepartmentDistributionRepository repository = mock(DepartmentDistributionRepository.class);
    UUID departmentId = UUID.randomUUID();
    when(repository.findByDepartmentId(departmentId)).thenReturn(Optional.empty());

    EventHandlerUtil.updateDepartmentDistribution(
        repository, departmentId, 1, new BigDecimal("500"));

    verify(repository)
        .save(
            argThat(
                dist ->
                    dist.getDepartmentId().equals(departmentId)
                        && dist.getTotalEmployees() == 1L
                        && dist.getTotalSalary().compareTo(new BigDecimal("500")) == 0));
  }

  @Test
  void whenUpdateItDistribution_thenUpdatesAndSavesCorrectly() {
    ItDistributionRepository repository = mock(ItDistributionRepository.class);
    UUID jobTitleId = UUID.randomUUID();
    ItDistribution existing = new ItDistribution();
    existing.setTotalEmployees(4L);
    when(repository.findByJobTitleId(jobTitleId)).thenReturn(Optional.of(existing));

    EventHandlerUtil.updateItDistribution(repository, jobTitleId, 3);

    assertEquals(7L, existing.getTotalEmployees());
    verify(repository).save(existing);
  }

  @Test
  void whenUpdateItDistributionWithoutExisting_thenCreatesNewAndSaves() {
    ItDistributionRepository repository = mock(ItDistributionRepository.class);
    UUID jobTitleId = UUID.randomUUID();
    when(repository.findByJobTitleId(jobTitleId)).thenReturn(Optional.empty());

    EventHandlerUtil.updateItDistribution(repository, jobTitleId, 2);

    verify(repository)
        .save(
            argThat(
                dist -> dist.getJobTitleId().equals(jobTitleId) && dist.getTotalEmployees() == 2L));
  }

  @Test
  void whenBelongsToTechnologyDepartment_thenReturnsTrue() {
    EmployeeEvent event = new EmployeeEvent();
    event.setDepartmentId("a4a77bc5-e352-4ac0-8ec3-d3af8271f61f");

    boolean result = EventHandlerUtil.belongsToTechnologyDepartment(event);

    assertTrue(result);
  }

  @Test
  void whenNotBelongsToTechnologyDepartment_thenReturnsFalse() {
    EmployeeEvent event = new EmployeeEvent();
    event.setDepartmentId("some-other-id");

    boolean result = EventHandlerUtil.belongsToTechnologyDepartment(event);

    assertFalse(result);
  }

  @Test
  void whenNullEvent_thenBelongsToTechnologyDepartmentReturnsFalse() {
    EventHandlerUtil.belongsToTechnologyDepartment(null);
    boolean result = false;

    assertFalse(result);
  }
}
