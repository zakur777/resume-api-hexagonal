package com.resume.api.application.services.workexperience;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.application.ports.in.workexperience.UpdateWorkExperienceUseCase;
import com.resume.api.application.ports.out.workexperience.WorkExperienceGateway;
import com.resume.api.application.services.exceptions.workexperience.WorkExperienceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateWorkExperienceService implements UpdateWorkExperienceUseCase {

  private final WorkExperienceGateway gateway;

  public UpdateWorkExperienceService(WorkExperienceGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public WorkExperience execute(WorkExperience workExperience) throws Exception {
    if (gateway.getWorkExperience(workExperience.getId()).isPresent()) {
      return gateway.updateWorkExperience(workExperience);
    }
    Object[] args = {workExperience.getId()};
    throw new WorkExperienceNotFoundException("workExperienceNotFound", args);
  }
}
