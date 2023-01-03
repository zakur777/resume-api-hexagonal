package com.resume.api.application.services.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.application.ports.in.education.DeleteEducationUseCase;
import com.resume.api.application.ports.out.education.EducationGateway;
import com.resume.api.application.services.exceptions.education.EducationNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DeleteEducationService implements DeleteEducationUseCase {

  private final EducationGateway gateway;

  public DeleteEducationService(EducationGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Boolean execute(Education education) throws Exception {
    Optional<Education> educationById = gateway.getEducation(education.getId());
    if (educationById.isPresent()) {
      education.setStatus("0");
      return gateway.deleteEducation(education);
    }
    Object[] args = {education.getId()};
    throw new EducationNotFoundException("educationNotFound", args);
  }
}
