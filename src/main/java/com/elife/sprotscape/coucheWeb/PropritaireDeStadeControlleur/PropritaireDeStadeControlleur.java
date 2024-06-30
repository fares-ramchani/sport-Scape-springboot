package com.elife.sprotscape.coucheWeb.PropritaireDeStadeControlleur;

import com.elife.sprotscape.coucheService.servicesPropritaireDeStade.servicesPropritaireDeStade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/propritaire")
@CrossOrigin("*")
public class PropritaireDeStadeControlleur {
  private servicesPropritaireDeStade servicesPropritaireDeStade;



  @PostMapping(path = "/ajouterPropritaire")
  public void ajouterClient(@RequestParam("propritaireDeStadeRequestDTO") String propritaireDeStadeRequestDTO,
                            @RequestParam("logo") MultipartFile file) throws IOException {

    servicesPropritaireDeStade.ajouter_un_PropritaireDeStade(propritaireDeStadeRequestDTO,file);

  }
}
