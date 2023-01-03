package com.resume.api.application.services.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.application.ports.in.certificate.GetCertificateUseCase;
import com.resume.api.application.ports.out.certificate.CertificateGateway;
import com.resume.api.application.services.exceptions.certificate.CertificateNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GetCertificateService implements GetCertificateUseCase {

  private final CertificateGateway gateway;

  public GetCertificateService(CertificateGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Optional<Certificate> execute(Long id) throws Exception {
    Optional<Certificate> certificate = gateway.getCertificate(id);
    if (certificate.isPresent()) {
      return certificate;
    }
    Object[] args = {id};
    throw new CertificateNotFoundException("certificateNotFound", args);
  }
}
