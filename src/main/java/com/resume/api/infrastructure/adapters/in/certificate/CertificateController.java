package com.resume.api.infrastructure.adapters.in.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.application.services.certificate.CreateCertificateService;
import com.resume.api.application.services.certificate.DeleteCertificateService;
import com.resume.api.application.services.certificate.GetAllCertificatesService;
import com.resume.api.application.services.certificate.GetCertificateService;
import com.resume.api.application.services.certificate.UpdateCertificateService;
import com.resume.api.application.services.exceptions.certificate.CertificateNotFoundException;
import com.resume.api.application.services.exceptions.certificate.DuplicateCertificateException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/certificates")
public class CertificateController {

  protected static final Logger logger = Logger.getLogger(CertificateController.class.getName());

  private final CreateCertificateService createCertificateService;

  private final GetCertificateService getCertificateService;

  private final GetAllCertificatesService getAllCertificatesService;

  private final UpdateCertificateService updateCertificateService;

  private final DeleteCertificateService deleteCertificateService;

  public CertificateController(
      CreateCertificateService createCertificateService,
      GetCertificateService getCertificateService,
      GetAllCertificatesService getAllCertificatesService,
      UpdateCertificateService updateCertificateService,
      DeleteCertificateService deleteCertificateService) {
    this.createCertificateService = createCertificateService;
    this.getCertificateService = getCertificateService;
    this.getAllCertificatesService = getAllCertificatesService;
    this.updateCertificateService = updateCertificateService;
    this.deleteCertificateService = deleteCertificateService;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Certificate> getCertificateById(@PathVariable("id") Long id)
      throws Exception {
    logger.info(
        String.format(
            "get-certificate-service getCertificateById() invoked:{%s} for {%s} ",
            createCertificateService.getClass().getName(), id));
    Optional<Certificate> certificateOptional;
    Certificate certificate;
    try {
      certificateOptional = getCertificateService.execute(id);
      certificate = certificateOptional.get();

    } catch (CertificateNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised getCertificate REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised getCertificate REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(certificate, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Certificate>> getAllCertificate() throws Exception {
    logger.info(
        String.format(
            "get-all-certificate-service getAllCertificate() invoked:{%s} for {%s} ",
            createCertificateService.getClass().getName(), "ALL-SKILL"));
    List<Certificate> certificates;
    try {
      certificates = getAllCertificatesService.execute(null);
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createCertificate REST Call", ex);
      throw ex;
    }
    return certificates.size() > 0
        ? new ResponseEntity<>(certificates, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PostMapping("/create")
  public ResponseEntity<Certificate> createCertificate(
      @RequestBody Certificate certificate) throws Exception {
    logger.info(
        String.format(
            "create-certificate-service createCertificate() invoked:{%s} for {%s} ",
            createCertificateService.getClass().getName(), certificate));
    Certificate certificateResponse;
    try {
      certificateResponse = createCertificateService.execute(certificate);
    } catch (DuplicateCertificateException ex) {
      logger.log(Level.WARNING, "Exception raised createCertificate REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createCertificate REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(certificateResponse, HttpStatus.CREATED);
  }

  @PostMapping("/update")
  public ResponseEntity<Certificate> updateCertificate(
      @RequestBody Certificate certificate) throws Exception {
    logger.info(
        String.format(
            "update-certificate-service updateCertificate() invoked:{%s} for {%s} ",
            createCertificateService.getClass().getName(), certificate));
    Certificate certificateResponse;
    try {
      certificateResponse = updateCertificateService.execute(certificate);
    } catch (CertificateNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised createCertificate REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createCertificate REST Call", ex);
      throw ex;
    }
    return new ResponseEntity<>(certificateResponse, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Boolean> deleteCertificate(@PathVariable("id") Long id)
      throws Exception {
    logger.info(
        String.format(
            "delete-certificate-service deleteCertificate() invoked:{%s} for {%s} ",
            createCertificateService.getClass().getName(), id));
    Boolean isCertificateDeleted;
    Optional<Certificate> certificateOptional;
    try {
      certificateOptional = getCertificateService.execute(id);
      isCertificateDeleted = deleteCertificateService.execute(certificateOptional.get());
    } catch (CertificateNotFoundException ex) {
      logger.log(Level.WARNING, "Exception raised createCertificate REST Call", ex);
      throw ex;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Exception raised createCertificate REST Call", ex);
      throw ex;
    }
    if (isCertificateDeleted) {
      return new ResponseEntity<>(true, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
  }
}
