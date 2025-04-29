package com.antharos.analytics.application;

import com.antharos.analytics.domain.service.MaterializeKpiForNewMonthUseCase;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@AllArgsConstructor
@Configuration
@EnableScheduling
public class KpiScheduler {
  private final MaterializeKpiForNewMonthUseCase useCase;

  @Scheduled(cron = "0 0 0 1 * ?")
  public void materialize() {
    LocalDate newMonth = LocalDate.now().withDayOfMonth(1);
    this.useCase.execute(newMonth);
  }
}
