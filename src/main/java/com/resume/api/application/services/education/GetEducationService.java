package com.resume.api.application.services.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.application.ports.in.education.GetEducationUseCase;
import com.resume.api.application.ports.out.education.EducationGateway;
import com.resume.api.application.services.exceptions.education.EducationNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GetEducationService implements GetEducationUseCase {

  private final EducationGateway gateway;

  public GetEducationService(EducationGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Optional<Education> execute(Long id) throws Exception {
    Optional<Education> education = gateway.getEducation(id);
    if (education.isPresent()) {
      return education;
    }
    Object[] args = {id};
    throw new EducationNotFoundException("educationNotFound", args);
  }
}
