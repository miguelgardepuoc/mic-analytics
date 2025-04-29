package com.antharos.analytics.infrastructure.out.repository.monthkpi;

import com.antharos.analytics.domain.MonthKpi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonthKpiMapper {
  MonthKpi toDomain(MonthKpiEntity entity);

  MonthKpiEntity toEntity(MonthKpi domain);
}
