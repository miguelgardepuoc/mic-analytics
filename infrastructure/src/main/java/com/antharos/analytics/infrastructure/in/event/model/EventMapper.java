package com.antharos.analytics.infrastructure.in.event.model;

import com.antharos.analytics.domain.EmployeeEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class EventMapper {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static EmployeeEvent mapToEmployeeEvent(String jsonMessage) throws IOException {
    JsonNode root = objectMapper.readTree(jsonMessage);
    JsonNode payload = root.get("payload");

    EmployeeEvent event = new EmployeeEvent();
    event.setId(UUID.fromString(payload.get("id").asText()));
    event.setDni(payload.get("dni").asText());
    event.setName(payload.get("name").asText());
    event.setSurname(payload.get("surname").asText());
    event.setRole(payload.get("role").asText());
    event.setTelephoneNumber(payload.get("telephoneNumber").asText());
    event.setUsername(payload.get("username").asText());
    event.setEmployeeNumber(payload.get("employeeNumber").asLong());
    event.setDepartmentId(payload.get("departmentId").asText());
    event.setCorporateEmail(payload.get("corporateEmail").asText());
    event.setSalary(new BigDecimal(payload.get("salary").asText()));
    event.setHiringDate(LocalDate.parse(payload.get("hiringDate").asText()));
    event.setStatus(payload.get("status").asText());
    event.setJobTitleId(payload.get("jobTitleId").asText());
    event.setEventType(root.get("eventName").asText());

    return event;
  }
}
