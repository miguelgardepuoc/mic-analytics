package com.antharos.analytics.infrastructure.in.event;

import com.antharos.analytics.application.EmployeeEventDispatcherService;
import com.antharos.analytics.domain.EmployeeEvent;
import com.antharos.analytics.infrastructure.in.event.model.EventMapper;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class MessageConsumerImpl implements MessageListener {

  private final EmployeeEventDispatcherService eventDispatcherService;

  @Override
  @JmsListener(
      destination = "${consumer.topic.name}",
      containerFactory = "jmsListenerContainerFactory")
  public void onMessage(jakarta.jms.Message message) {
    if (message instanceof TextMessage textMessage) {
      processMessage(textMessage);
    } else {
      log.error("Received unsupported message type: {}", message.getClass().getName());
    }
  }

  private void processMessage(TextMessage textMessage) {
    try {
      String messageText = textMessage.getText();
      log.info("Processing message: {}", messageText);
      EmployeeEvent employeeEvent = EventMapper.mapToEmployeeEvent(messageText);
      this.eventDispatcherService.processEvent(employeeEvent);
    } catch (Exception e) {
      log.error("Error processing message", e);
    }
  }
}
