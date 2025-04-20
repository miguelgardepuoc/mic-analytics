package com.antharos.analytics.infrastructure.out.repository.itdistribution;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItDistributionRepository extends JpaRepository<ItDistributionEntity, UUID> {}
