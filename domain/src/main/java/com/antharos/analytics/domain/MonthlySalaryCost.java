package com.antharos.analytics.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MonthlySalaryCost(LocalDate month, BigDecimal totalSalary) {}
