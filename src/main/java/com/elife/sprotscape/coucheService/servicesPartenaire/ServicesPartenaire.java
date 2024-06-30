package com.elife.sprotscape.coucheService.servicesPartenaire;

import com.elife.sprotscape.DTO.PartenaireDTO.PartenaireRequestDTO;
import com.elife.sprotscape.Entities.Partenaire;
import com.elife.sprotscape.enums.Etat;

import java.util.List;

public interface ServicesPartenaire {
    public void ajouterPartenaire(PartenaireRequestDTO partenaireRequestDTO);
    public Partenaire getPartenaire(Long id) ;
    public List<Partenaire> getPartenaireByEtat(Etat etat) ;
    public List<Partenaire> getToutPartenaire();
    public void modifierPartenaire(PartenaireRequestDTO partenaireRequestDTO,Long id);
    public void deletePartenaire(Long id);
}
