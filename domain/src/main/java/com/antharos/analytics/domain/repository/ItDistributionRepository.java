package com.antharos.analytics.domain.repository;

import com.antharos.analytics.domain.ItDistribution;
import com.antharos.analytics.domain.JobTitleEmployees;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItDistributionRepository {
  List<JobTitleEmployees> getEmployeesByJobTitle();

  Optional<ItDistribution> findByJobTitleId(UUID jobTitleId);

  void save(ItDistribution itDistribution);
}
