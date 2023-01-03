package com.resume.api.application.services.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.application.ports.in.certificate.UpdateCertificateUseCase;
import com.resume.api.application.ports.out.certificate.CertificateGateway;
import com.resume.api.application.services.exceptions.certificate.CertificateNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateCertificateService implements UpdateCertificateUseCase {

  private final CertificateGateway gateway;

  public UpdateCertificateService(CertificateGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Certificate execute(Certificate certificate) throws Exception {
    if (gateway.getCertificate(certificate.getId()).isPresent()) {
      return gateway.updateCertificate(certificate);
    }
    Object[] args = {certificate.getId()};
    throw new CertificateNotFoundException("certificateNotFound", args);
  }
}
