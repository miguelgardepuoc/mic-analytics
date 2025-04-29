package com.antharos.analytics.domain.service;

import java.time.LocalDate;

public interface MaterializeKpiForNewMonthUseCase {
  void execute(LocalDate month);
}
