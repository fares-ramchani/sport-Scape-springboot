package com.elife.sprotscape.coucheService.serviceSeance;

import com.elife.sprotscape.DTO.SeanceDTO.SeanceRequestDTO;
import com.elife.sprotscape.Entities.Seance;
import com.elife.sprotscape.Entities.Stade;

import java.util.List;

public interface ServiceSeance {
    public void addSeance(SeanceRequestDTO seanceRequestDTO, Long id, String nomActivite);
    public List<Seance> getSeanceByJourAndStadeAndDisbonibiliteAndActivite(String jour, Long id, String nomActivite);
   /* public void updateSeance(SeanceRequestDTO seanceRequestDTO);
    public void deleteSeance(Long id);
    public Seance getSeanceByIdAndStade(Long id);
    public List<Seance> getSeanceByDisbonibilite(boolean dispo);
    public List<Seance> getAllSeance();
    public List<Seance> getSeanceByJour(String jour);*/
    // public Seance findByJourSeanceAndHeureDebutSeance(String jour, String heure);
}
