package com.antharos.analytics.infrastructure.out.event.model;

import com.antharos.analytics.domain.candidate.Candidate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CandidateAppliedDomainMessage extends BaseMessage<Candidate> {

  public CandidateAppliedDomainMessage(String id, String subject, Candidate content) {
    super(id, subject, content);
  }
}
