package com.resume.api.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "work_experience")
public class WorkExperienceDAO extends BaseDAO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String company;

  private String position;

  @Column(name = "start_period")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startPeriod;

  @Column(name = "end_period")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endPeriod;

  @Column(name = "resume_id")
  private Long resumeId;

  /*@ManyToOne
  @JoinColumn(name = "resume_id", insertable = false, updatable = false)
  private ResumeDAO resume;*/
}
