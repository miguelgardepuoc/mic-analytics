package com.antharos.analytics.application.find;

import java.util.UUID;
import lombok.Value;

@Value(staticConstructor = "of")
public class FindJobOfferQuery {
  UUID jobOfferId;
}
