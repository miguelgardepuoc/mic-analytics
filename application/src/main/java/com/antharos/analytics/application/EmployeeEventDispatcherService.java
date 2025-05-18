package com.antharos.analytics.application;

import com.antharos.analytics.application.handlers.EmployeeEventHandler;
import com.antharos.analytics.domain.EmployeeEvent;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeEventDispatcherService {
  private final List<EmployeeEventHandler> eventHandlers;

  public EmployeeEventDispatcherService(List<EmployeeEventHandler> eventHandlers) {
    this.eventHandlers = eventHandlers;
  }

  public void processEvent(EmployeeEvent event) {
    log.info("Dispatching employee event: {}", event.getEventType());

    boolean handled = false;
    for (EmployeeEventHandler handler : eventHandlers) {
      if (handler.canHandle(event.getEventType())) {
        handler.handle(event);
        handled = true;
        break;
      }
    }

    if (!handled) {
      log.warn("No handler found for event type: {}", event.getEventType());
    }
  }
}
