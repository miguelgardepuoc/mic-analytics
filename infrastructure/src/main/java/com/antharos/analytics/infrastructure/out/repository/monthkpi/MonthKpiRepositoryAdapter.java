package com.antharos.analytics.infrastructure.out.repository.monthkpi;

import com.antharos.analytics.domain.MonthlySalaryCost;
import com.antharos.analytics.domain.repository.MonthKpiRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MonthKpiRepositoryAdapter implements MonthKpiRepository {

  private final JpaMonthKpiRepository jpaRepository;

  @Override
  public List<MonthlySalaryCost> getSalaryCostByMonth() {
    return this.jpaRepository.findAll().stream()
        .map(kpi -> new MonthlySalaryCost(kpi.getMonth(), kpi.getTotalSalary()))
        .toList();
  }
}
