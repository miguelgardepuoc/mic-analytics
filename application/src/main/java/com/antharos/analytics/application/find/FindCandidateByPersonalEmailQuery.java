package com.antharos.analytics.application.find;

import lombok.Value;

@Value(staticConstructor = "of")
public class FindCandidateByPersonalEmailQuery {
  String personalEmail;
}
