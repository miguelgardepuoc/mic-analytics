package com.antharos.analytics.application.handlers;

import com.antharos.analytics.domain.EmployeeEvent;

public interface EmployeeEventHandler {
  void handle(EmployeeEvent event);

  boolean canHandle(String eventName);
}
