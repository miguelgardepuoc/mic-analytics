package com.antharos.analytics.domain;

import java.time.LocalDate;

public record MonthlyEmployeeCount(LocalDate month, long totalEmployees) {}
