package com.resume.api.infrastructure.adapters.in.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.application.services.exceptions.skill.DuplicateSkillException;
import com.resume.api.application.services.exceptions.skill.SkillNotFoundException;
import com.resume.api.application.services.skill.CreateSkillService;
import com.resume.api.application.services.skill.DeleteSkillService;
import com.resume.api.application.services.skill.GetAllSkillsService;
import com.resume.api.application.services.skill.GetSkillService;
import com.resume.api.application.services.skill.UpdateSkillService;
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
@RequestMapping(value = "v1/skills")
public class SkillController {

  protected static final Logger logger = Logger.getLogger(SkillController.class.getName());

  private final CreateSkillService createSkillService;

  private final GetSkillService getSkillService;

  private final GetAllSkillsService getAllSkillsService;

  private final UpdateSkillService updateSkillService;

  private final DeleteSkillService deleteSkillService;

  public SkillController(
      CreateSkillService createSkillService,
      GetSkillService getSkillService,
      GetAllSkillsService getAllSkillsService,
      UpdateSkillService updateSkillService,
      DeleteSkillService deleteSkillService) {
    this.createSkillService = createSkillService;
    this.getSkillService = getSkillService;
    this.getAllSkillsService = getAllSkillsService;
    this.updateSkillService = updateSkillService;
    this.deleteSkillService = deleteSkillService;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Skill> getSkillById(@PathVariable("id") Long id) throws Exception {
    logger.info(
        String.format(
            "get-skill-service getSkillById() invoked:{%s} for {%s} ",
            createSkillService.getClass().getName(), id));
    Optional<Skill> skillOptional;
    Skill skill;
    try {
      skillOptional = getSkillService.execute(id);
      skill = skillOptional.get();

    } catch (SkillNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised getSkill REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createSkill REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(skill, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Skill>> getAllSkill() throws Exception {
    logger.info(
        String.format(
            "get-all-skill-service getAllSkill() invoked:{%s} for {%s} ",
            createSkillService.getClass().getName(), "ALL-SKILL"));
    List<Skill> skills;
    try {
      skills = getAllSkillsService.execute(null);
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createSkill REST Call", ex);
      throw ex;
    }
    return skills.size() > 0
        ? new ResponseEntity<>(skills, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/create")
  public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) throws Exception {
    logger.info(
        String.format(
            "create-skill-service createSkill() invoked:{%s} for {%s} ",
            createSkillService.getClass().getName(), skill));
    Skill skillResponse;
    try {
      skillResponse = createSkillService.execute(skill);
    } catch (DuplicateSkillException ex) {
      logger.log(Level.WARNING, "Exception raised createSkill REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createSkill REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(skillResponse, HttpStatus.CREATED);
  }

  @PostMapping("/update")
  public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) throws Exception {
    logger.info(
        String.format(
            "update-skill-service updateSkill() invoked:{%s} for {%s} ",
            createSkillService.getClass().getName(), skill));
    Skill skillResponse;
    try {
      skillResponse = updateSkillService.execute(skill);
    } catch (SkillNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised createSkill REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createSkill REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(skillResponse, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Boolean> deleteSkill(@PathVariable("id") Long id) throws Exception {
    logger.info(
        String.format(
            "delete-skill-service deleteSkill() invoked:{%s} for {%s} ",
            createSkillService.getClass().getName(), id));
    Boolean isSkillDeleted;
    Optional<Skill> skillOptional;
    try {
      skillOptional = getSkillService.execute(id);
      isSkillDeleted = deleteSkillService.execute(skillOptional.get());
    } catch (SkillNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised createSkill REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createSkill REST Call", ex);
      throw ex;
    }
    if (isSkillDeleted) {
      return new ResponseEntity<>(true, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
  }
}
