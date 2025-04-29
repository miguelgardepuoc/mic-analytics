package com.antharos.analytics.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record DepartmentSalary(UUID departmentId, BigDecimal totalSalary) {}
