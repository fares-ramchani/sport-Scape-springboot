package com.elife.sprotscape.coucheWeb.EvenementControlleur;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elife.sprotscape.DTO.EvenementDTO.EvenementRequestDTO;
import com.elife.sprotscape.Entities.Evenement;
import com.elife.sprotscape.coucheService.serviceEvenement.serviceEvenement;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/evenement")
public class EvenementControlleur {
private serviceEvenement serviceEvenement;

  @PostMapping(path = "/add")
  public void addEvenement(@RequestBody EvenementRequestDTO evenementRequestDTO){
    serviceEvenement.addEvenement(evenementRequestDTO);
  }

  @PutMapping(path = "/update/{id}")
  public void updateEvenement(@RequestBody EvenementRequestDTO evenementRequestDTO, Long id){
    serviceEvenement.updateEvenement(evenementRequestDTO, id);
  }

  @GetMapping(path = "/all")
  public List<Evenement> getAllEvenements(){
    return serviceEvenement.getAllEvenement();
  }
  @DeleteMapping(path = "delete/{id}")
  public void deleteEvenement(@RequestParam Long id){
    serviceEvenement.deleteEvenement(id);
  }
  @GetMapping(path = "ById/{id}")
  public Evenement getEvenementById(@RequestParam Long id){
    return serviceEvenement.getEvenementById(id);
  }
}
