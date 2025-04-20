package com.antharos.analytics.domain.repository;

import com.antharos.analytics.domain.JobTitleEmployees;
import java.util.List;

public interface ItDistributionRepository {
  List<JobTitleEmployees> getEmployeesByJobTitle();
}
