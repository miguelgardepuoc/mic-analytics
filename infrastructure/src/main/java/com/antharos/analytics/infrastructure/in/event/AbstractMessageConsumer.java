package com.antharos.analytics.infrastructure.in.event;

import com.antharos.analytics.application.EmployeeEventDispatcherService;
import com.antharos.analytics.domain.EmployeeEvent;
import com.antharos.analytics.infrastructure.in.event.model.EventMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractMessageConsumer {

  protected final EmployeeEventDispatcherService eventDispatcherService;

  public AbstractMessageConsumer(EmployeeEventDispatcherService eventDispatcherService) {
    this.eventDispatcherService = eventDispatcherService;
  }

  protected void processMessageText(String messageText) {
    try {
      log.info("Processing message: {}", messageText);
      EmployeeEvent employeeEvent = EventMapper.mapToEmployeeEvent(messageText);
      this.eventDispatcherService.processEvent(employeeEvent);
    } catch (Exception e) {
      log.error("Error processing message", e);
    }
  }
}
