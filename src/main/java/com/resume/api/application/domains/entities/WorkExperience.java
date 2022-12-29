package com.resume.api.application.domains.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class WorkExperience {

  private Long id;
  private String company;
  private String position;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate startPeriod;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate endPeriod;

  private Long resumeId;

  private String status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public LocalDate getStartPeriod() {
    return startPeriod;
  }

  public void setStartPeriod(LocalDate startPeriod) {
    this.startPeriod = startPeriod;
  }

  public LocalDate getEndPeriod() {
    return endPeriod;
  }

  public void setEndPeriod(LocalDate endPeriod) {
    this.endPeriod = endPeriod;
  }

  public Long getResumeId() {
    return resumeId;
  }

  public void setResumeId(Long resumeId) {
    this.resumeId = resumeId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
