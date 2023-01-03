package com.resume.api.application.services.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.application.ports.in.certificate.CreateCertificateUseCase;
import com.resume.api.application.ports.out.certificate.CertificateGateway;
import com.resume.api.application.services.exceptions.certificate.DuplicateCertificateException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CreateCertificateService implements CreateCertificateUseCase {

  private final CertificateGateway gateway;

  public CreateCertificateService(CertificateGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Certificate execute(Certificate workexperience) throws Exception {
    Optional<Certificate> workexperienceById = gateway.getCertificate(workexperience.getId());
    if (workexperienceById.isEmpty()) {
      return gateway.createCertificate(workexperience);
    }
    Object[] args = {workexperience.getId()};
    throw new DuplicateCertificateException("duplicateCertificate", args);
  }
}
