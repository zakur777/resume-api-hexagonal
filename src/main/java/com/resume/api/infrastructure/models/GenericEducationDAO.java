package com.resume.api.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class GenericEducationDAO extends BaseDAO {

  private String institution;
  private String degree;
  private String description;
  @Column(name = "start_date")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate;
  @Column(name = "end_date")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endaDate;
  @Column(name = "resume_id")
  private Long resumeId;

}
