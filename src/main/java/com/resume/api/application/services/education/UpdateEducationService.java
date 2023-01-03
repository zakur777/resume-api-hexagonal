package com.resume.api.application.services.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.application.ports.in.education.UpdateEducationUseCase;
import com.resume.api.application.ports.out.education.EducationGateway;
import com.resume.api.application.services.exceptions.education.EducationNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateEducationService implements UpdateEducationUseCase {

  private final EducationGateway gateway;

  public UpdateEducationService(EducationGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Education execute(Education education) throws Exception {
    if (gateway.getEducation(education.getId()).isPresent()) {
      return gateway.updateEducation(education);
    }
    Object[] args = {education.getId()};
    throw new EducationNotFoundException("educationNotFound", args);
  }
}
