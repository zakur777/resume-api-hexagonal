package com.resume.api.infrastructure.error;

import com.resume.api.application.services.exceptions.certificate.CertificateNotFoundException;
import com.resume.api.application.services.exceptions.certificate.DuplicateCertificateException;
import com.resume.api.application.services.exceptions.commons.ErrorInfo;
import com.resume.api.application.services.exceptions.skill.DuplicateSkillException;
import com.resume.api.application.services.exceptions.skill.SkillNotFoundException;
import com.resume.api.application.services.exceptions.workexperience.DuplicateWorkExperienceException;
import com.resume.api.application.services.exceptions.workexperience.WorkExperienceNotFoundException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EndpointErrorHandler {
  private static final String UNEXPECTED_ERROR = "Exception.unexpected";
  private final MessageSource messageSource;

  @Autowired
  public EndpointErrorHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(DuplicateSkillException.class)
  public ResponseEntity<ErrorInfo> handleDuplicateSkillException(
      HttpServletRequest request, DuplicateSkillException ex, Locale locale) {
    ErrorInfo response = new ErrorInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
    return new ResponseEntity<>(response, HttpStatus.IM_USED);
  }

  @ExceptionHandler(SkillNotFoundException.class)
  public ResponseEntity<ErrorInfo> handleNotFoundSkillException(
      HttpServletRequest request, SkillNotFoundException ex, Locale locale) {
    ErrorInfo response = new ErrorInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateWorkExperienceException.class)
  public ResponseEntity<ErrorInfo> handleDuplicateWorkExperienceException(
      HttpServletRequest request, DuplicateWorkExperienceException ex, Locale locale) {
    ErrorInfo response = new ErrorInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
    return new ResponseEntity<>(response, HttpStatus.IM_USED);
  }

  @ExceptionHandler(WorkExperienceNotFoundException.class)
  public ResponseEntity<ErrorInfo> handleNotFoundWorkExperienceException(
      HttpServletRequest request, WorkExperienceNotFoundException ex, Locale locale) {
    ErrorInfo response = new ErrorInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateCertificateException.class)
  public ResponseEntity<ErrorInfo> handleDuplicateCertificateException(
      HttpServletRequest request, DuplicateCertificateException ex, Locale locale) {
    ErrorInfo response = new ErrorInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
    return new ResponseEntity<>(response, HttpStatus.IM_USED);
  }

  @ExceptionHandler(CertificateNotFoundException.class)
  public ResponseEntity<ErrorInfo> handleNotFoundCertificateException(
      HttpServletRequest request, CertificateNotFoundException ex, Locale locale) {
    ErrorInfo response = new ErrorInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorInfo> handleException(HttpServletRequest request, Exception ex, Locale locale) {
    ErrorInfo response = new ErrorInfo();
    String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(errorMessage);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
