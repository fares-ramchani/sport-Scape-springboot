package com.elife.sprotscape.coucheWeb.PropritaireDeStadeControlleur;

import com.elife.sprotscape.Entities.propritaireDeStade;
import com.elife.sprotscape.coucheService.servicesPropritaireDeStade.servicesPropritaireDeStade;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/propritaire")
@CrossOrigin("*")
public class PropritaireDeStadeControlleur {
  private servicesPropritaireDeStade servicesPropritaireDeStade;



  @PostMapping(path = "/ajouterPropritaire")
  public void ajouterpropritaire(@RequestParam("propritaireDeStadeRequestDTO") String propritaireDeStadeRequestDTO,
                            @RequestParam("logo") MultipartFile file) throws IOException, MessagingException {

    servicesPropritaireDeStade.ajouter_un_PropritaireDeStade(propritaireDeStadeRequestDTO,file);

  }
  @GetMapping(path = "/GetlogosPropritaire")
  public propritaireDeStade getPropritaire(@RequestParam String id)  {

    return servicesPropritaireDeStade.getPropritaireDeStade(id);

  }
  @GetMapping(path = "/GetTousPropritaire")
  public List<propritaireDeStade> gettousPropritaire()  {

    return servicesPropritaireDeStade.getToutPropritaireDeStade();

  }
  @PostMapping(path = "/VerifierCompte")
  public void VerifierCompte(@RequestBody String entrepriseee)  {

     servicesPropritaireDeStade.VerifierCompte(entrepriseee);

  }
  @GetMapping(path = "/nombreDecompteVerifier")
  public long nombreDecompteVerifier()  {

    return servicesPropritaireDeStade.nombreDecompteVerifier();

  }
  @GetMapping(path = "/nombreDecompteNonVerifier")
  public long nombreDecompteNonVerifier()  {

    return servicesPropritaireDeStade.nombreDecompteNonVerifier();

  }
}
