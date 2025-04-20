package com.antharos.analytics.domain.candidate.repository;

import com.antharos.analytics.domain.candidate.Candidate;
import java.util.UUID;

public interface MessageProducer {
  void sendMessage(UUID id, final String subject, Candidate candidate);

  void sendCandidateApplied(Candidate candidate);
}
