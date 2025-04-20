package com.antharos.analytics.infrastructure.out.event;

import com.fasterxml.jackson.databind.ObjectMapper;
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

  private final ObjectMapper objectMapper;

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
    String messageText = null;
    try {
      messageText = textMessage.getText();
      log.info("Processing message: {}", messageText);
    } catch (Exception e) {
      log.error("Error processing message: {}", messageText, e);
    }
  }
}
