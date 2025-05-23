package com.antharos.analytics.application;

import static org.mockito.Mockito.*;

import com.antharos.analytics.domain.service.MaterializeKpiForNewMonthUseCase;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class KpiSchedulerTest {

  @Test
  void whenMaterializeIsCalled_thenUseCaseIsExecutedWithFirstDayOfCurrentMonth() {
    MaterializeKpiForNewMonthUseCase useCase = mock(MaterializeKpiForNewMonthUseCase.class);
    KpiScheduler scheduler = new KpiScheduler(useCase);

    scheduler.materialize();

    LocalDate expectedDate = LocalDate.now().withDayOfMonth(1);
    verify(useCase).execute(expectedDate);
  }
}
