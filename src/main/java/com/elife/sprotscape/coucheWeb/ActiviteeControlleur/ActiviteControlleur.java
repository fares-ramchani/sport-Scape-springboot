package com.elife.sprotscape.coucheWeb.ActiviteeControlleur;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elife.sprotscape.DTO.ActiviteDTO.ActiviteRequestDTO;
import com.elife.sprotscape.Entities.Activite;
import com.elife.sprotscape.coucheService.servicesActivite.serviceActivite;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/activite")
public class ActiviteControlleur {
  private serviceActivite serviceActivite;

  @PostMapping(path = "/add")
  public void addActivite(@RequestBody ActiviteRequestDTO activiteRequestDTO){
    serviceActivite.addActivite(activiteRequestDTO);
  }

  @PutMapping(path = "/update/{id}")
  public void updateActivite(@RequestBody ActiviteRequestDTO activiteRequestDTO, Long id){
    serviceActivite.updateActivite(activiteRequestDTO, id);
  }

  @GetMapping(path = "/all")
  public List<Activite> getAllActivites(){
    return serviceActivite.getAllActivite();
  }
  @DeleteMapping(path = "delete/{id}")
  public void deleteActivite(@RequestParam Long id){
    serviceActivite.deleteActivite(id);
  }
  @GetMapping(path = "ById/{id}")
  public Activite getActiviteById(@RequestParam Long id){
    return serviceActivite.getActiviteById(id);
  }
}
