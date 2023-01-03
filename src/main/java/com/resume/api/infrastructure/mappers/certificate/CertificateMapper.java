package com.resume.api.infrastructure.mappers.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.infrastructure.models.CertificateDAO;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CertificateMapper {
  @Mappings({
    @Mapping(source = "id", target = "id"),
    @Mapping(source = "institution", target = "institution"),
    @Mapping(source = "degree", target = "degree"),
    @Mapping(source = "description", target = "description"),
    @Mapping(source = "startDate", target = "startDate"),
    @Mapping(source = "endaDate", target = "endaDate"),
    @Mapping(source = "resumeId", target = "resumeId"),
    @Mapping(source = "status", target = "status")
  })
  Certificate toCertificate(CertificateDAO certificateDAO);

  List<Certificate> toCertificates(List<CertificateDAO> certificateDAOS);

  @InheritInverseConfiguration
  @Mappings({
    @Mapping(target = "createAt", ignore = true),
    @Mapping(target = "updateAt", ignore = true)
  })
  CertificateDAO toCertificateDAO(Certificate certificate);
}
