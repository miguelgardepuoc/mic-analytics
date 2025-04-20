package com.antharos.analytics.domain.candidate.repository;

import com.antharos.analytics.domain.candidate.Candidate;
import java.util.List;

public interface CandidateRepository {
  List<Candidate> findAll();
}
