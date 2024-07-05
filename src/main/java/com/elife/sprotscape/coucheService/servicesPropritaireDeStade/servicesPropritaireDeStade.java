package com.elife.sprotscape.coucheService.servicesPropritaireDeStade;


import com.elife.sprotscape.DTO.propritaireDeStadeDTO.propritaireDeStadeRequestDTO;

import com.elife.sprotscape.Entities.propritaireDeStade;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface servicesPropritaireDeStade {
  public void ajouter_un_PropritaireDeStade(String propritaireDeStadeRequestDTO, MultipartFile File) throws IOException, MessagingException;
  public propritaireDeStade getPropritaireDeStade(String id) ;
  public List<propritaireDeStade> getToutPropritaireDeStade();
  public void modifierPropritaireDeStade(propritaireDeStadeRequestDTO propritaireDeStadeRequestDTO);
  public void deletePropritaireDeStade(Long id);
  public long nombreDecompteVerifier();
  public long nombreDecompteNonVerifier();
  public void VerifierCompte(String id);
}
