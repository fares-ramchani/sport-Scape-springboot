package com.elife.sprotscape.coucheService.serviceEvenement;

import java.util.List;

import com.elife.sprotscape.DAO.EvenementRepository.EvenementRepository;
import com.elife.sprotscape.DTO.EvenementDTO.EvenementRequestDTO;
import com.elife.sprotscape.Entities.Evenement;
import com.elife.sprotscape.enums.Estpayer;
import com.elife.sprotscape.enums.Etat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
@Transactional
public class serviceEvenementImpl implements serviceEvenement {
    private EvenementRepository evenementRepository;

    @Override
    public void addEvenement(EvenementRequestDTO evenementRequestDTO) {
        Evenement evenement = new Evenement();
        evenement.setNomEvenement(evenementRequestDTO.getNomEvenement());
        evenement.setDescription(evenementRequestDTO.getDescription());
        evenement.setLieu(evenementRequestDTO.getLieu());
        evenement.setPrix(evenementRequestDTO.getPrix());
        evenement.setNombreParticipant(evenementRequestDTO.getNombreParticipant());
        evenement.setEtatEvenement(Etat.NON_TRAITER);
        evenement.setEstPayer(Estpayer.NON);
        evenementRepository.save(evenement);
    }

    @Override
    public Evenement getEvenementById(Long id) {
        return evenementRepository.findById(id).get();
    }

    @Override
    public List<Evenement> getAllEvenement() {
        return evenementRepository.findAll();
    }

    @SuppressWarnings("unlikely-arg-type")
    @Override
    public void updateEvenement(EvenementRequestDTO evenementRequestDTO, Long id) {
        Evenement evenement = evenementRepository.findById(id).get();

        if (evenementRequestDTO.getNomEvenement() != "") {
            evenement.setNomEvenement(evenementRequestDTO.getNomEvenement());
        }
        if (evenementRequestDTO.getDescription() != "") {
            evenement.setDescription(evenementRequestDTO.getDescription());
        }
        if (evenementRequestDTO.getLieu() != "") {
            evenement.setLieu(evenementRequestDTO.getLieu());
        }
        if (evenementRequestDTO.getPrix() != -1) {
            evenement.setPrix(evenementRequestDTO.getPrix());
        }
        if (evenementRequestDTO.getNombreParticipant() != -1) {
            evenement.setNombreParticipant(evenementRequestDTO.getNombreParticipant());
        }
        if (!evenementRequestDTO.getEstPayer().equals("")) {
            evenement.setEstPayer(evenementRequestDTO.getEstPayer());
        }
        if (!evenementRequestDTO.getEtatEvenement().equals("")) {
            evenement.setEtatEvenement(evenementRequestDTO.getEtatEvenement());
        }
        evenementRepository.save(evenement);
    }

    @Override
    public void deleteEvenement(Long id) {
        Evenement evenement = evenementRepository.findById(id).get();
        evenementRepository.delete(evenement);
    }

}
