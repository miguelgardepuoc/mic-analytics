package com.antharos.analytics.domain;

import java.time.LocalDate;

public record MonthlySalaryCost(LocalDate month, long totalSalary) {}
