package com.antharos.analytics.domain.repository;

import com.antharos.analytics.domain.MonthlySalaryCost;
import java.util.List;

public interface MonthKpiRepository {
  List<MonthlySalaryCost> getSalaryCostByMonth();
}
