package com.elife.sprotscape.coucheService.serviceSeance;

import com.elife.sprotscape.DTO.SeanceDTO.SeanceRequestDTO;
import com.elife.sprotscape.Entities.Seance;

import java.util.List;

public interface ServiceSeance {
    public void addSeance(SeanceRequestDTO seanceRequestDTO);
    public void updateSeance(SeanceRequestDTO seanceRequestDTO);
    public void deleteSeance(Long id);
    public Seance getSeanceById(Long id);
    public List<Seance> getSeanceByDisbonibilite(boolean dispo);
    public List<Seance> getAllSeance();
    public List<Seance> getSeanceByJour(String jour);
}
