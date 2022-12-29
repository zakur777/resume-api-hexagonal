package com.resume.api.application.services.workexperience;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.application.ports.in.workexperience.DeleteWorkExperienceUseCase;
import com.resume.api.application.ports.out.workexperience.WorkExperienceGateway;
import com.resume.api.application.services.exceptions.workexperience.WorkExperienceNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DeleteWorkExperienceService implements DeleteWorkExperienceUseCase {

  private final WorkExperienceGateway gateway;

  public DeleteWorkExperienceService(WorkExperienceGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Boolean execute(WorkExperience workExperience) throws Exception {
    Optional<WorkExperience> workExperienceById = gateway.getWorkExperience(workExperience.getId());
    if (workExperienceById.isPresent()) {
      workExperience.setStatus("0");
      return gateway.deleteWorkExperience(workExperience);
    }
    Object[] args = {workExperience.getId()};
    throw new WorkExperienceNotFoundException("workExperienceNotFound", args);
  }
}
