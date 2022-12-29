package com.resume.api.application.services.workexperience;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.application.ports.in.workexperience.CreateWorkExperienceUseCase;
import com.resume.api.application.ports.out.workexperience.WorkExperienceGateway;
import com.resume.api.application.services.exceptions.workexperience.DuplicateWorkExperienceException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CreateWorkExperienceService implements CreateWorkExperienceUseCase {

  private final WorkExperienceGateway gateway;

  public CreateWorkExperienceService(WorkExperienceGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public WorkExperience execute(WorkExperience workexperience) throws Exception {
    Optional<WorkExperience> workexperienceById = gateway.getWorkExperience(workexperience.getId());
    if (workexperienceById.isEmpty()) {
      return gateway.createWorkExperience(workexperience);
    }
    Object[] args = {workexperience.getId()};
    throw new DuplicateWorkExperienceException("duplicateWorkExperience", args);
  }
}
