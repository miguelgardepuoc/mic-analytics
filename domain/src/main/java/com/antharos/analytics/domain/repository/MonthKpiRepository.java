package com.antharos.analytics.domain.repository;

import com.antharos.analytics.domain.MonthKpi;
import com.antharos.analytics.domain.MonthlySalaryCost;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MonthKpiRepository {
  List<MonthlySalaryCost> getSalaryCostByMonth();

  Optional<MonthKpi> findByMonth(LocalDate month);

  void save(MonthKpi monthKpi);
}
