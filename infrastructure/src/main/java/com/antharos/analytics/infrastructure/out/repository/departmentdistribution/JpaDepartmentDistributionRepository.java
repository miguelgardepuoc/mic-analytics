package com.antharos.analytics.infrastructure.out.repository.departmentdistribution;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDepartmentDistributionRepository
    extends JpaRepository<DepartmentDistributionEntity, UUID> {}
