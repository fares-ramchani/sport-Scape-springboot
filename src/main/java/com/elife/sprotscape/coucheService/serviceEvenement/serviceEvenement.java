package com.elife.sprotscape.coucheService.serviceEvenement;

import java.util.List;

import com.elife.sprotscape.DTO.EvenementDTO.EvenementRequestDTO;
import com.elife.sprotscape.Entities.Evenement;

public interface serviceEvenement {
    public void addEvenement(EvenementRequestDTO evenementRequestDTO);

    public Evenement getEvenementById(Long id);

    public List<Evenement> getAllEvenement();

    public void updateEvenement(EvenementRequestDTO evenementRequestDTO, Long id);

    public void deleteEvenement(Long id);
}
