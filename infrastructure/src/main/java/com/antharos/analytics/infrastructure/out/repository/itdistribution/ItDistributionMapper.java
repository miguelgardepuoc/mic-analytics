package com.antharos.analytics.infrastructure.out.repository.itdistribution;

import com.antharos.analytics.domain.ItDistribution;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItDistributionMapper {
  ItDistribution toDomain(ItDistributionEntity entity);

  ItDistributionEntity toEntity(ItDistribution domain);
}
