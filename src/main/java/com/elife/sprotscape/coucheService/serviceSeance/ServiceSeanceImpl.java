package com.elife.sprotscape.coucheService.serviceSeance;

import com.elife.sprotscape.DAO.ActiviteRepository.ActiviteRepository;
import com.elife.sprotscape.DAO.SeanceRepository.SeanceRepository;
import com.elife.sprotscape.DAO.StadeRepository.StadeRepository;
import com.elife.sprotscape.DTO.SeanceDTO.SeanceRequestDTO;
import com.elife.sprotscape.Entities.Activite;
import com.elife.sprotscape.Entities.Seance;
import com.elife.sprotscape.Entities.Stade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
@Transactional
public class ServiceSeanceImpl implements ServiceSeance{
    private SeanceRepository seanceRepository;
    private StadeRepository stadeRepository;
    private ActiviteRepository activiteRepository;
    @Override
    public void addSeance(SeanceRequestDTO seanceRequestDTO, Long id, String nomActivite) {
        Stade stade =stadeRepository.findById(id).get();
        Activite activite=activiteRepository.findByNomActivite(nomActivite);
        Seance searshSeance=seanceRepository.findByJourSeanceAndDebutSeanceAndStadeAndActivite(seanceRequestDTO.getJourSeance(),seanceRequestDTO.getDebutSeance(),stade,activite);
        if (stade !=null && searshSeance == null){
            Seance seance=new Seance();
            seance.setJourSeance(seanceRequestDTO.getJourSeance());
            seance.setDebutSeance(seanceRequestDTO.getDebutSeance());
            seance.setFinSeance(seanceRequestDTO.getFinSeance());
            seance.setActivite(activite);
            seance.setEstDisponible(true);
            seanceRepository.save(seance);
        }
    }

    @Override
    public List<Seance> getSeanceByJourAndStadeAndDisbonibiliteAndActivite(String jour, Long id, String nomActivite) {
        Stade stade =stadeRepository.findById(id).get();
        Activite activite=activiteRepository.findByNomActivite(nomActivite);
        if (stade != null && activite !=null){
            return seanceRepository.findByJourSeanceAndStadeAndEstDisponibleAndActivite(jour,stade,true,activite);
        }
        else return null;
    }

  /*  @Override
    public void updateSeance(SeanceRequestDTO seanceRequestDTO) {
        Seance seance=seanceRepository.findById(seanceRequestDTO.getIdSeance()).get();
        if(!Objects.equals(seanceRequestDTO.getJourSeance(), "")){
            seance.setJourSeance(seanceRequestDTO.getJourSeance());
        }
        if(!Objects.equals(seanceRequestDTO.getDebutSeance(), "")){
            seance.setDebutSeance(seanceRequestDTO.getDebutSeance());
        }
        if(!Objects.equals(seanceRequestDTO.getFinSeance(), "")){
            seance.setFinSeance(seanceRequestDTO.getFinSeance());
        }
        if(!Objects.equals(seanceRequestDTO.isEstDisponible(), "")){
            seance.setEstDisponible(seanceRequestDTO.isEstDisponible());
        }

    }

    @Override
    public void deleteSeance(Long id) {
        Seance seance=seanceRepository.findById(id).get();
        seanceRepository.delete(seance);
    }

    @Override
    public Seance getSeanceById(Long id) {
        return seanceRepository.findById(id).get();
    }

    @Override
    public List<Seance> getSeanceByDisbonibilite(boolean dispo) {
        return seanceRepository.findByEstDisponible(dispo);
    }

    @Override
    public List<Seance> getAllSeance() {
        return seanceRepository.findAll();
    }

    @Override
    public List<Seance> getSeanceByJour(String jour) {
        return seanceRepository.findByJourSeance(jour);
    }*/
}
