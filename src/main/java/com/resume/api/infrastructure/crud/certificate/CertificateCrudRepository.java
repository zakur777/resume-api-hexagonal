package com.resume.api.infrastructure.crud.certificate;

import com.resume.api.infrastructure.models.CertificateDAO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificateCrudRepository extends JpaRepository<CertificateDAO, Long> {
  @Query("select c from CertificateDAO c where c.status='1'")
  List<CertificateDAO> getAllCertificates();
}
