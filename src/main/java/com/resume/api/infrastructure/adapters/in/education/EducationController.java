package com.resume.api.infrastructure.adapters.in.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.application.services.education.CreateEducationService;
import com.resume.api.application.services.education.DeleteEducationService;
import com.resume.api.application.services.education.GetAllEducationsService;
import com.resume.api.application.services.education.GetEducationService;
import com.resume.api.application.services.education.UpdateEducationService;
import com.resume.api.application.services.exceptions.education.DuplicateEducationException;
import com.resume.api.application.services.exceptions.education.EducationNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/educations")
public class EducationController {

  protected static final Logger logger = Logger.getLogger(EducationController.class.getName());

  private final CreateEducationService createEducationService;

  private final GetEducationService getEducationService;

  private final GetAllEducationsService getAllEducationsService;

  private final UpdateEducationService updateEducationService;

  private final DeleteEducationService deleteEducationService;

  public EducationController(
      CreateEducationService createEducationService,
      GetEducationService getEducationService,
      GetAllEducationsService getAllEducationsService,
      UpdateEducationService updateEducationService,
      DeleteEducationService deleteEducationService) {
    this.createEducationService = createEducationService;
    this.getEducationService = getEducationService;
    this.getAllEducationsService = getAllEducationsService;
    this.updateEducationService = updateEducationService;
    this.deleteEducationService = deleteEducationService;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Education> getEducationById(@PathVariable("id") Long id)
      throws Exception {
    logger.info(
        String.format(
            "get-education-service getEducationById() invoked:{%s} for {%s} ",
            createEducationService.getClass().getName(), id));
    Optional<Education> educationOptional;
    Education education;
    try {
      educationOptional = getEducationService.execute(id);
      education = educationOptional.get();

    } catch (EducationNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised getEducation REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised getEducation REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(education, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Education>> getAllEducation() throws Exception {
    logger.info(
        String.format(
            "get-all-education-service getAllEducation() invoked:{%s} for {%s} ",
            createEducationService.getClass().getName(), "ALL-SKILL"));
    List<Education> educations;
    try {
      educations = getAllEducationsService.execute(null);
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createEducation REST Call", ex);
      throw ex;
    }
    return educations.size() > 0
        ? new ResponseEntity<>(educations, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/create")
  public ResponseEntity<Education> createEducation(
      @RequestBody Education education) throws Exception {
    logger.info(
        String.format(
            "create-education-service createEducation() invoked:{%s} for {%s} ",
            createEducationService.getClass().getName(), education));
    Education educationResponse;
    try {
      educationResponse = createEducationService.execute(education);
    } catch (DuplicateEducationException ex) {
      logger.log(Level.WARNING, "Exception raised createEducation REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createEducation REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(educationResponse, HttpStatus.CREATED);
  }

  @PostMapping("/update")
  public ResponseEntity<Education> updateEducation(
      @RequestBody Education education) throws Exception {
    logger.info(
        String.format(
            "update-education-service updateEducation() invoked:{%s} for {%s} ",
            createEducationService.getClass().getName(), education));
    Education educationResponse;
    try {
      educationResponse = updateEducationService.execute(education);
    } catch (EducationNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised createEducation REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createEducation REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(educationResponse, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Boolean> deleteEducation(@PathVariable("id") Long id)
      throws Exception {
    logger.info(
        String.format(
            "delete-education-service deleteEducation() invoked:{%s} for {%s} ",
            createEducationService.getClass().getName(), id));
    Boolean isEducationDeleted;
    Optional<Education> educationOptional;
    try {
      educationOptional = getEducationService.execute(id);
      isEducationDeleted = deleteEducationService.execute(educationOptional.get());
    } catch (EducationNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised createEducation REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createEducation REST Call", ex);
      throw ex;
    }
    if (isEducationDeleted) {
      return new ResponseEntity<>(true, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
  }
}
