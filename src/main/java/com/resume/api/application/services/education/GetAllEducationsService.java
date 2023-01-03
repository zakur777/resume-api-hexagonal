package com.resume.api.application.services.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.application.ports.in.education.GetAllEducationsUseCase;
import com.resume.api.application.ports.out.education.EducationGateway;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetAllEducationsService implements GetAllEducationsUseCase {

  private final EducationGateway gateway;

  public GetAllEducationsService(EducationGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public List<Education> execute(Void unused) throws Exception {
    return gateway.getAllEducations();
  }
}
