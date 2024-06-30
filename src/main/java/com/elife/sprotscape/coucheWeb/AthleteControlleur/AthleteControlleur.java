package com.elife.sprotscape.coucheWeb.AthleteControlleur;

import com.elife.sprotscape.DTO.AthleteDTO.AthleteRequestDTO;
import com.elife.sprotscape.Entities.Athlete;
import com.elife.sprotscape.coucheService.servicesAthlete.servicesAthlete;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/Athlete")
@CrossOrigin("*")
public class AthleteControlleur {
  private servicesAthlete servicesAthlete;


  @PostMapping(path = "/ajouterAthlete")
  public void ajouter_un_Athlete(@RequestBody AthleteRequestDTO AthleteRequestDTO){
    servicesAthlete.ajouter_un_Athlete(AthleteRequestDTO);
  }

  @PutMapping(path = "/modifierAthlete")
  public void modifierAthlete(@RequestBody AthleteRequestDTO AthleteRequestDTO){
    servicesAthlete.modifierAthlete(AthleteRequestDTO);
  }

  @GetMapping(path = "getToutAthlete")
  public List<Athlete> getToutAthlete(){
    return servicesAthlete.getToutAthlete();
  }
  @DeleteMapping(path = "deleteAthlete")
  public void deleteAthlete(@RequestParam Long id){
    servicesAthlete.deleteAthlete(id);
  }
  @GetMapping(path = "getAthleteById")
  public Athlete getAthleteById(@RequestParam Long id){
    return servicesAthlete.getAthlete(id);
  }
}
