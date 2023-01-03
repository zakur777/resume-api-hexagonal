package com.resume.api.application.services.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.application.ports.in.certificate.GetAllCertificatesUseCase;
import com.resume.api.application.ports.out.certificate.CertificateGateway;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetAllCertificatesService implements GetAllCertificatesUseCase {

  private final CertificateGateway gateway;

  public GetAllCertificatesService(CertificateGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public List<Certificate> execute(Void unused) throws Exception {
    return gateway.getAllCertificates();
  }
}
