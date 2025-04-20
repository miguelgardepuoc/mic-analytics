package com.antharos.analytics.infrastructure.out.repository.candidate;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateEntity {

  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "created_by", nullable = false)
  private String createdBy;

  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @Column(name = "last_modified_at")
  private Date lastModifiedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = new Date();
  }
}
