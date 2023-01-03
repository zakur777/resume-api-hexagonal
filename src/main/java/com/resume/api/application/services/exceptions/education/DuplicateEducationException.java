package com.resume.api.application.services.exceptions.education;

public class DuplicateEducationException extends Exception {

  private static final long serialVersionUID = -8890080495441147845L;

  private String message;
  private Object[] args;

  public DuplicateEducationException(String message) {
    this.message = String.format("Education with %s is not found.", message);
  }

  public DuplicateEducationException(Object[] args) {
    this.args = args;
  }

  public DuplicateEducationException(String message, Object[] args) {
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
