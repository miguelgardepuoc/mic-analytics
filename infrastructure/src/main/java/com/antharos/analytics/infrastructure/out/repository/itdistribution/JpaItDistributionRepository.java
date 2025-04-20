package com.antharos.analytics.infrastructure.out.repository.itdistribution;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItDistributionRepository extends JpaRepository<ItDistributionEntity, UUID> {}
