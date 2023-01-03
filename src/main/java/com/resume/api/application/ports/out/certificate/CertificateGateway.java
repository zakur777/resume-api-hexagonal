package com.resume.api.application.ports.out.certificate;

import com.resume.api.application.domains.entities.Certificate;
import java.util.List;
import java.util.Optional;

public interface CertificateGateway {

  Certificate createCertificate(Certificate certificate);

  Certificate updateCertificate(Certificate certificate);

  Boolean deleteCertificate(Certificate certificate);

  Optional<Certificate> getCertificate(Long id);

  List<Certificate> getAllCertificates();
}
