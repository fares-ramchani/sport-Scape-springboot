package com.elife.sprotscape.coucheService.servicesAthlete;

import com.elife.sprotscape.DTO.AthleteDTO.AthleteRequestDTO;
import com.elife.sprotscape.Entities.Athlete;

import java.util.List;

public interface servicesAthlete {
  public void ajouter_un_Athlete(AthleteRequestDTO AthleteRequestDTO);
  public Athlete getAthlete(Long id) ;
  public List<Athlete> getToutAthlete();
  public void modifierAthlete(AthleteRequestDTO AthleteRequestDTO);
  public void deleteAthlete(Long id);
}
