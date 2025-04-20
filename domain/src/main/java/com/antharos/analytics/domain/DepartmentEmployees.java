package com.antharos.analytics.domain;

import java.util.UUID;

public record DepartmentEmployees(UUID departmentId, long totalEmployees) {}
