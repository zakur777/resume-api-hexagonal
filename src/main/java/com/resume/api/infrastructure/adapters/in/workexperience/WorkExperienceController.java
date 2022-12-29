package com.resume.api.infrastructure.adapters.in.workexperience;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.application.services.exceptions.workexperience.DuplicateWorkExperienceException;
import com.resume.api.application.services.exceptions.workexperience.WorkExperienceNotFoundException;
import com.resume.api.application.services.workexperience.CreateWorkExperienceService;
import com.resume.api.application.services.workexperience.DeleteWorkExperienceService;
import com.resume.api.application.services.workexperience.GetAllWorkExperiencesService;
import com.resume.api.application.services.workexperience.GetWorkExperienceService;
import com.resume.api.application.services.workexperience.UpdateWorkExperienceService;
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
@RequestMapping(value = "v1/workExperiences")
public class WorkExperienceController {

  protected static final Logger logger = Logger.getLogger(WorkExperienceController.class.getName());

  private final CreateWorkExperienceService createWorkExperienceService;

  private final GetWorkExperienceService getWorkExperienceService;

  private final GetAllWorkExperiencesService getAllWorkExperiencesService;

  private final UpdateWorkExperienceService updateWorkExperienceService;

  private final DeleteWorkExperienceService deleteWorkExperienceService;

  public WorkExperienceController(
      CreateWorkExperienceService createWorkExperienceService,
      GetWorkExperienceService getWorkExperienceService,
      GetAllWorkExperiencesService getAllWorkExperiencesService,
      UpdateWorkExperienceService updateWorkExperienceService,
      DeleteWorkExperienceService deleteWorkExperienceService) {
    this.createWorkExperienceService = createWorkExperienceService;
    this.getWorkExperienceService = getWorkExperienceService;
    this.getAllWorkExperiencesService = getAllWorkExperiencesService;
    this.updateWorkExperienceService = updateWorkExperienceService;
    this.deleteWorkExperienceService = deleteWorkExperienceService;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<WorkExperience> getWorkExperienceById(@PathVariable("id") Long id)
      throws Exception {
    logger.info(
        String.format(
            "get-workExperience-service getWorkExperienceById() invoked:{%s} for {%s} ",
            createWorkExperienceService.getClass().getName(), id));
    Optional<WorkExperience> workExperienceOptional;
    WorkExperience workExperience;
    try {
      workExperienceOptional = getWorkExperienceService.execute(id);
      workExperience = workExperienceOptional.get();

    } catch (WorkExperienceNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised getWorkExperience REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised getWorkExperience REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(workExperience, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<WorkExperience>> getAllWorkExperience() throws Exception {
    logger.info(
        String.format(
            "get-all-workExperience-service getAllWorkExperience() invoked:{%s} for {%s} ",
            createWorkExperienceService.getClass().getName(), "ALL-SKILL"));
    List<WorkExperience> workExperiences;
    try {
      workExperiences = getAllWorkExperiencesService.execute(null);
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createWorkExperience REST Call", ex);
      throw ex;
    }
    return workExperiences.size() > 0
        ? new ResponseEntity<>(workExperiences, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/create")
  public ResponseEntity<WorkExperience> createWorkExperience(
      @RequestBody WorkExperience workExperience) throws Exception {
    logger.info(
        String.format(
            "create-workExperience-service createWorkExperience() invoked:{%s} for {%s} ",
            createWorkExperienceService.getClass().getName(), workExperience));
    WorkExperience workExperienceResponse;
    try {
      workExperienceResponse = createWorkExperienceService.execute(workExperience);
    } catch (DuplicateWorkExperienceException ex) {
      logger.log(Level.WARNING, "Exception raised createWorkExperience REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createWorkExperience REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(workExperienceResponse, HttpStatus.CREATED);
  }

  @PostMapping("/update")
  public ResponseEntity<WorkExperience> updateWorkExperience(
      @RequestBody WorkExperience workExperience) throws Exception {
    logger.info(
        String.format(
            "update-workExperience-service updateWorkExperience() invoked:{%s} for {%s} ",
            createWorkExperienceService.getClass().getName(), workExperience));
    WorkExperience workExperienceResponse;
    try {
      workExperienceResponse = updateWorkExperienceService.execute(workExperience);
    } catch (WorkExperienceNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised createWorkExperience REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createWorkExperience REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(workExperienceResponse, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Boolean> deleteWorkExperience(@PathVariable("id") Long id)
      throws Exception {
    logger.info(
        String.format(
            "delete-workExperience-service deleteWorkExperience() invoked:{%s} for {%s} ",
            createWorkExperienceService.getClass().getName(), id));
    Boolean isWorkExperienceDeleted;
    Optional<WorkExperience> workExperienceOptional;
    try {
      workExperienceOptional = getWorkExperienceService.execute(id);
      isWorkExperienceDeleted = deleteWorkExperienceService.execute(workExperienceOptional.get());
    } catch (WorkExperienceNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised createWorkExperience REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createWorkExperience REST Call", ex);
      throw ex;
    }
    if (isWorkExperienceDeleted) {
      return new ResponseEntity<>(true, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
  }
}
