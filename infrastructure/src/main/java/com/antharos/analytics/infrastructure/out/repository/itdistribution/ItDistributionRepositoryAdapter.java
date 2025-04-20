package com.antharos.analytics.infrastructure.out.repository.itdistribution;

import com.antharos.analytics.domain.JobTitleEmployees;
import com.antharos.analytics.domain.repository.ItDistributionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItDistributionRepositoryAdapter implements ItDistributionRepository {

  private final JpaItDistributionRepository jpaRepository;

  @Override
  public List<JobTitleEmployees> getEmployeesByJobTitle() {
    return this.jpaRepository.findAll().stream()
        .map(j -> new JobTitleEmployees(j.getJobTitleId(), j.getTotalEmployees()))
        .toList();
  }
}
