package com.resume.api.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseDAO {
  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createAt;

  @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updateAt;

  @Builder.Default private String status = "1";
}
