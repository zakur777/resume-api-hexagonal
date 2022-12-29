package com.resume.api.application.services.workexperience;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.application.ports.in.workexperience.GetAllWorkExperiencesUseCase;
import com.resume.api.application.ports.out.workexperience.WorkExperienceGateway;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetAllWorkExperiencesService implements GetAllWorkExperiencesUseCase {

  private final WorkExperienceGateway gateway;

  public GetAllWorkExperiencesService(WorkExperienceGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public List<WorkExperience> execute(Void unused) throws Exception {
    return gateway.getAllWorkExperiences();
  }
}
