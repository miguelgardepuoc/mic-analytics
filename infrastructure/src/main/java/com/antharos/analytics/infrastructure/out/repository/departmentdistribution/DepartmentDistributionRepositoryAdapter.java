package com.antharos.analytics.infrastructure.out.repository.departmentdistribution;

import com.antharos.analytics.domain.DepartmentDistribution;
import com.antharos.analytics.domain.DepartmentEmployees;
import com.antharos.analytics.domain.DepartmentSalary;
import com.antharos.analytics.domain.repository.DepartmentDistributionRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DepartmentDistributionRepositoryAdapter implements DepartmentDistributionRepository {

  private final JpaDepartmentDistributionRepository jpaRepository;
  private final DepartmentDistributionMapper mapper;

  @Override
  public List<DepartmentEmployees> getEmployeesByDepartment() {
    return this.jpaRepository.findAll().stream()
        .map(d -> new DepartmentEmployees(d.getDepartmentId(), d.getTotalEmployees()))
        .toList();
  }

  @Override
  public List<DepartmentSalary> getSalaryByDepartment() {
    return this.jpaRepository.findAll().stream()
        .map(
            d ->
                new DepartmentSalary(
                    d.getDepartmentId(),
                    d.getTotalSalary() == null ? BigDecimal.ZERO : d.getTotalSalary()))
        .toList();
  }

  @Override
  public Optional<DepartmentDistribution> findByDepartmentId(UUID departmentId) {
    return this.jpaRepository.findById(departmentId).map(this.mapper::toDomain);
  }

  @Override
  public void save(DepartmentDistribution departmentDistribution) {
    this.jpaRepository.save(this.mapper.toEntity(departmentDistribution));
  }
}
