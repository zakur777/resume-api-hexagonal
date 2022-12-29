package com.resume.api.application.services.exceptions.commons;

public class ErrorInfo {

  private String url;
  private String message;

  public ErrorInfo() {}

  public ErrorInfo(String message) {
    this.message = message;
  }

  public ErrorInfo(String url, String message) {
    this.url = url;
    this.message = message;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
