package com.resume.api.application.services.exceptions.workexperience;

public class WorkExperienceNotFoundException extends Exception {

  private static final long serialVersionUID = -8890080495441147846L;

  private String message;
  private Object[] args;

  public WorkExperienceNotFoundException(String message) {
    this.message = String.format("WorkExperience with %s is not found.", message);
  }

  public WorkExperienceNotFoundException(Object[] args) {
    this.args = args;
  }

  public WorkExperienceNotFoundException(String message, Object[] args) {
    this.message = message;
    this.args = args;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object[] getArgs() {
    return args;
  }

  public void setArgs(Object[] args) {
    this.args = args;
  }
}
