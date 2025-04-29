package com.antharos.analytics.infrastructure.out.repository.monthkpi;

import com.antharos.analytics.domain.MonthKpi;
import com.antharos.analytics.domain.MonthlySalaryCost;
import com.antharos.analytics.domain.repository.MonthKpiRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MonthKpiRepositoryAdapter implements MonthKpiRepository {

  private final JpaMonthKpiRepository jpaRepository;
  private final MonthKpiMapper mapper;

  @Override
  public List<MonthlySalaryCost> getSalaryCostByMonth() {
    return this.jpaRepository.findAll().stream()
        .map(kpi -> new MonthlySalaryCost(kpi.getMonth(), kpi.getTotalSalary()))
        .toList();
  }

  @Override
  public Optional<MonthKpi> findByMonth(LocalDate month) {
    return this.jpaRepository.findById(month).map(mapper::toDomain);
  }

  @Override
  public void save(MonthKpi monthKpi) {
    this.jpaRepository.save(this.mapper.toEntity(monthKpi));
  }
}
