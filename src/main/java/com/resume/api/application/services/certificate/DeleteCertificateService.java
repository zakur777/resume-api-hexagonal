package com.resume.api.application.services.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.application.ports.in.certificate.DeleteCertificateUseCase;
import com.resume.api.application.ports.out.certificate.CertificateGateway;
import com.resume.api.application.services.exceptions.certificate.CertificateNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DeleteCertificateService implements DeleteCertificateUseCase {

  private final CertificateGateway gateway;

  public DeleteCertificateService(CertificateGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Boolean execute(Certificate certificate) throws Exception {
    Optional<Certificate> certificateById = gateway.getCertificate(certificate.getId());
    if (certificateById.isPresent()) {
      certificate.setStatus("0");
      return gateway.deleteCertificate(certificate);
    }
    Object[] args = {certificate.getId()};
    throw new CertificateNotFoundException("certificateNotFound", args);
  }
}
