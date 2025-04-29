package com.antharos.analytics.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeEvent {
  private UUID id;
  private String dni;
  private String name;
  private String surname;
  private String role;
  private String telephoneNumber;
  private String username;
  private Long employeeNumber;
  private String departmentId;
  private String corporateEmail;
  private BigDecimal salary;
  private LocalDate hiringDate;
  private String status;
  private String jobTitleId;
  private String eventType;
}
