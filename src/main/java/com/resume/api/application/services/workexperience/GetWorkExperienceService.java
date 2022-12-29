package com.resume.api.application.services.workexperience;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.application.ports.in.workexperience.GetWorkExperienceUseCase;
import com.resume.api.application.ports.out.workexperience.WorkExperienceGateway;
import com.resume.api.application.services.exceptions.workexperience.WorkExperienceNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GetWorkExperienceService implements GetWorkExperienceUseCase {

  private final WorkExperienceGateway gateway;

  public GetWorkExperienceService(WorkExperienceGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Optional<WorkExperience> execute(Long id) throws Exception {
    Optional<WorkExperience> workExperience = gateway.getWorkExperience(id);
    if (workExperience.isPresent()) {
      return workExperience;
    }
    Object[] args = {id};
    throw new WorkExperienceNotFoundException("workExperienceNotFound", args);
  }
}
