package com.antharos.analytics.application;

import static org.mockito.Mockito.*;

import com.antharos.analytics.application.handlers.EmployeeEventHandler;
import com.antharos.analytics.domain.EmployeeEvent;
import java.util.List;
import org.junit.jupiter.api.Test;

class EmployeeEventDispatcherServiceUnitTest {

  @Test
  void whenMatchingHandlerFound_thenHandlerHandlesEvent() {
    EmployeeEventHandler handler = mock(EmployeeEventHandler.class);
    EmployeeEvent event = new EmployeeEvent();
    event.setEventType("HIRE");

    when(handler.canHandle("HIRE")).thenReturn(true);

    EmployeeEventDispatcherService dispatcher =
        new EmployeeEventDispatcherService(List.of(handler));

    dispatcher.processEvent(event);

    verify(handler).canHandle("HIRE");
    verify(handler).handle(event);
  }

  @Test
  void whenNoMatchingHandlerFound_thenNoHandlerIsCalled() {
    EmployeeEventHandler handler = mock(EmployeeEventHandler.class);
    EmployeeEvent event = new EmployeeEvent();
    event.setEventType("FIRE");

    when(handler.canHandle("FIRE")).thenReturn(false);

    EmployeeEventDispatcherService dispatcher =
        new EmployeeEventDispatcherService(List.of(handler));

    dispatcher.processEvent(event);

    verify(handler).canHandle("FIRE");
    verify(handler, never()).handle(any());
  }

  @Test
  void whenMultipleHandlersOnlyFirstMatchIsUsed_thenBreaksAfterHandling() {
    EmployeeEventHandler handler1 = mock(EmployeeEventHandler.class);
    EmployeeEventHandler handler2 = mock(EmployeeEventHandler.class);
    EmployeeEvent event = new EmployeeEvent();
    event.setEventType("PROMOTION");

    when(handler1.canHandle("PROMOTION")).thenReturn(true);
    when(handler2.canHandle("PROMOTION")).thenReturn(true); // Would match if reached

    EmployeeEventDispatcherService dispatcher =
        new EmployeeEventDispatcherService(List.of(handler1, handler2));

    dispatcher.processEvent(event);

    verify(handler1).handle(event);
    verify(handler2, never()).handle(any());
  }
}
