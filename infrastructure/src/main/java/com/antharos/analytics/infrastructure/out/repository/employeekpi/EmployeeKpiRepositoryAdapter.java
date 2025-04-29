package com.antharos.analytics.infrastructure.out.repository.employeekpi;

import com.antharos.analytics.domain.EmployeeKpi;
import com.antharos.analytics.domain.MonthlyEmployeeCount;
import com.antharos.analytics.domain.repository.EmployeeKpiRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeeKpiRepositoryAdapter implements EmployeeKpiRepository {

  private final JpaEmployeeKpiRepository jpaRepository;
  private final EmployeeKpiMapper mapper;

  @Override
  public List<MonthlyEmployeeCount> getEmployeeCountByMonth() {
    return this.jpaRepository.findAll().stream()
        .collect(
            Collectors.groupingBy(
                EmployeeKpiEntity::getMonth,
                Collectors.summingLong(EmployeeKpiEntity::getTotalEmployees)))
        .entrySet()
        .stream()
        .map(e -> new MonthlyEmployeeCount(e.getKey(), e.getValue()))
        .toList();
  }

  @Override
  public Optional<EmployeeKpi> findByMonth(LocalDate month) {
    return this.jpaRepository.findById(new EmployeeKpiId(month)).map(this.mapper::toDomain);
  }

  @Override
  public void save(EmployeeKpi employeeKpi) {
    this.jpaRepository.save(this.mapper.toEntity(employeeKpi));
  }
}
