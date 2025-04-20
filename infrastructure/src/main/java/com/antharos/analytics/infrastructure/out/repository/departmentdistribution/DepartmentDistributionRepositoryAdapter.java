package com.antharos.analytics.infrastructure.out.repository.departmentdistribution;

import com.antharos.analytics.domain.DepartmentEmployees;
import com.antharos.analytics.domain.DepartmentSalary;
import com.antharos.analytics.domain.repository.DepartmentDistributionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DepartmentDistributionRepositoryAdapter implements DepartmentDistributionRepository {

  private final JpaDepartmentDistributionRepository jpaRepository;

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
                    d.getDepartmentId(), d.getTotalSalary() == null ? 0 : d.getTotalSalary()))
        .toList();
  }
}
