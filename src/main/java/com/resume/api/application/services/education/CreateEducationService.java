package com.resume.api.application.services.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.application.ports.in.education.CreateEducationUseCase;
import com.resume.api.application.ports.out.education.EducationGateway;
import com.resume.api.application.services.exceptions.education.DuplicateEducationException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CreateEducationService implements CreateEducationUseCase {

  private final EducationGateway gateway;

  public CreateEducationService(EducationGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Education execute(Education education) throws Exception {
    Optional<Education> educationById = gateway.getEducation(education.getId());
    if (educationById.isEmpty()) {
      return gateway.createEducation(education);
    }
    Object[] args = {education.getId()};
    throw new DuplicateEducationException("duplicateEducation", args);
  }

}
