package com.resume.api.application.domains.entities;

public class Education extends GenericEducation {

  private Long id;

  private String status;

  public Education() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
