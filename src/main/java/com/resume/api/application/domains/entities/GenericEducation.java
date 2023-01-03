package com.resume.api.application.domains.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class GenericEducation {

  private String institution;
  private String degree;
  private String description;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startDate;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endaDate;

  private Long resumeId;

  public String getInstitution() {
    return institution;
  }

  public void setInstitution(String institution) {
    this.institution = institution;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndaDate() {
    return endaDate;
  }

  public void setEndaDate(LocalDate endaDate) {
    this.endaDate = endaDate;
  }

  public Long getResumeId() {
    return resumeId;
  }

  public void setResumeId(Long resumeId) {
    this.resumeId = resumeId;
  }
}
