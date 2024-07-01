package com.elife.sprotscape.coucheService.serviceSeance;

import com.elife.sprotscape.DAO.SeanceRepository.SeanceRepository;
import com.elife.sprotscape.DTO.SeanceDTO.SeanceRequestDTO;
import com.elife.sprotscape.Entities.Seance;
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
    @Override
    public void addSeance(SeanceRequestDTO seanceRequestDTO) {
        Seance seance=new Seance();
        seance.setJourSeance(seanceRequestDTO.getJourSeance());
        seance.setDebutSeance(seanceRequestDTO.getDebutSeance());
        seance.setFinSeance(seanceRequestDTO.getFinSeance());
        seance.setEstDisponible(true);
        seanceRepository.save(seance);
    }

    @Override
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
    }
}
