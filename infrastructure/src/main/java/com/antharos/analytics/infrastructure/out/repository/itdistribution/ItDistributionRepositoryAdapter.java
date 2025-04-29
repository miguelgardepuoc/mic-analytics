package com.antharos.analytics.infrastructure.out.repository.itdistribution;

import com.antharos.analytics.domain.ItDistribution;
import com.antharos.analytics.domain.JobTitleEmployees;
import com.antharos.analytics.domain.repository.ItDistributionRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItDistributionRepositoryAdapter implements ItDistributionRepository {

  private final JpaItDistributionRepository jpaRepository;
  private final ItDistributionMapper mapper;

  @Override
  public List<JobTitleEmployees> getEmployeesByJobTitle() {
    return this.jpaRepository.findAll().stream()
        .map(j -> new JobTitleEmployees(j.getJobTitleId(), j.getTotalEmployees()))
        .toList();
  }

  @Override
  public Optional<ItDistribution> findByJobTitleId(UUID jobTitleId) {
    return jpaRepository.findById(jobTitleId).map(mapper::toDomain);
  }

  @Override
  public void save(ItDistribution itDistribution) {
    jpaRepository.save(mapper.toEntity(itDistribution));
  }
}
