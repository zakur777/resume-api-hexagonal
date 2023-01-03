package com.resume.api.infrastructure.adapters.out.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.application.ports.out.certificate.CertificateGateway;
import com.resume.api.infrastructure.crud.certificate.CertificateCrudRepository;
import com.resume.api.infrastructure.mappers.certificate.CertificateMapper;
import com.resume.api.infrastructure.models.CertificateDAO;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Transactional
@Repository
public class CertificateRepository implements CertificateGateway {

  private final CertificateCrudRepository certificateCrudRepository;
  private final CertificateMapper mapper;

  @Override
  public Certificate createCertificate(Certificate certificate) {
    CertificateDAO certificateDAO = mapper.toCertificateDAO(certificate);
    return mapper.toCertificate(certificateCrudRepository.save(certificateDAO));
  }

  @Override
  public Certificate updateCertificate(Certificate certificate) {
    CertificateDAO certificateDAO = mapper.toCertificateDAO(certificate);
    return mapper.toCertificate(certificateCrudRepository.save(certificateDAO));
  }

  @Override
  public Boolean deleteCertificate(Certificate certificate) {
    if (getCertificate(certificate.getId()).isPresent()) {
      CertificateDAO certificateDAO = mapper.toCertificateDAO(certificate);
      certificateCrudRepository.save(certificateDAO);
      return true;
    }
    return false;
  }

  @Override
  public Optional<Certificate> getCertificate(Long id) {
    return certificateCrudRepository
        .findById(id)
        .map(certificate -> mapper.toCertificate(certificate));
  }

  @Override
  public List<Certificate> getAllCertificates() {
    List<CertificateDAO> allCertificateDaos =
        certificateCrudRepository.getAllCertificates();
    return mapper.toCertificates(allCertificateDaos);
  }
}
