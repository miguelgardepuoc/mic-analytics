package com.antharos.analytics.infrastructure.out.repository.departmentdistribution;

import com.antharos.analytics.domain.DepartmentDistribution;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentDistributionMapper {
  DepartmentDistribution toDomain(DepartmentDistributionEntity entity);

  DepartmentDistributionEntity toEntity(DepartmentDistribution domain);
}
