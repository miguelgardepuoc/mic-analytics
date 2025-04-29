package com.antharos.analytics.infrastructure.out.repository.employeekpi;

import com.antharos.analytics.domain.EmployeeKpi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeKpiMapper {
  EmployeeKpi toDomain(EmployeeKpiEntity entity);

  EmployeeKpiEntity toEntity(EmployeeKpi domain);
}
