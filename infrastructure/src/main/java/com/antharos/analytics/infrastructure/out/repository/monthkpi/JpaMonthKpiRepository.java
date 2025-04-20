package com.antharos.analytics.infrastructure.out.repository.monthkpi;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMonthKpiRepository extends JpaRepository<MonthKpiEntity, LocalDate> {}
