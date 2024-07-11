package com.elife.sprotscape.coucheService.servicesPartenaire;

import com.elife.sprotscape.DAO.PartenaireRepository.PartenaireRepository;
import com.elife.sprotscape.DTO.PartenaireDTO.PartenaireRequestDTO;
import com.elife.sprotscape.Entities.Partenaire;
import com.elife.sprotscape.enums.Etat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
@Transactional
public class ServicesPartenaireImpl implements ServicesPartenaire{
    private PartenaireRepository partenaireRepository;
    @Override
    public void ajouterPartenaire(PartenaireRequestDTO partenaireRequestDTO) {
        Partenaire partenaire=new Partenaire();
        partenaire.setNomPartenaire(partenaireRequestDTO.getNomPartenaire());
        partenaire.setNomResponsable(partenaireRequestDTO.getNomResponsable());
        partenaire.setTelephoneResponsable(partenaireRequestDTO.getTelephoneResponsable());
        partenaire.setAdressePartenaire(partenaireRequestDTO.getAdressePartenaire());
        partenaire.setEmailResponsable(partenaireRequestDTO.getEmailResponsable());
        partenaire.setEtatPartenaire(Etat.NON_TRAITER);
        partenaireRepository.save(partenaire);
    }

    @Override
    public Partenaire getPartenaire(Long id) {
        return partenaireRepository.findById(id).get();
    }

    @Override
    public List<Partenaire> getPartenaireByEtat(Etat etat) {
        return partenaireRepository.findByEtatPartenaire(etat);
    }

    @Override
    public List<Partenaire> getToutPartenaire() {
        return partenaireRepository.findAll();
    }

    @Override
    public void modifierPartenaire(PartenaireRequestDTO partenaireRequestDTO,Long id) {
        Partenaire partenaire=partenaireRepository.findById(id).get();
        if(!Objects.equals(partenaireRequestDTO.getNomPartenaire(), "")){
            partenaire.setNomPartenaire(partenaireRequestDTO.getNomPartenaire());
        }
        if(!Objects.equals(partenaireRequestDTO.getNomResponsable(), "")){
            partenaire.setNomResponsable(partenaireRequestDTO.getNomResponsable());
        }
        if(!Objects.equals(partenaireRequestDTO.getAdressePartenaire(), "")){
            partenaire.setAdressePartenaire(partenaireRequestDTO.getAdressePartenaire());
        }
        if(!Objects.equals(partenaireRequestDTO.getTelephoneResponsable(), "")){
            partenaire.setTelephoneResponsable(partenaireRequestDTO.getTelephoneResponsable());
        }
        if(!Objects.equals(partenaireRequestDTO.getEmailResponsable(), "")){
            partenaire.setEmailResponsable(partenaireRequestDTO.getEmailResponsable());
        }
        if(!Objects.equals(partenaireRequestDTO.getEtatPartenaire(), "")){
            partenaire.setEtatPartenaire(partenaireRequestDTO.getEtatPartenaire());
        }
    }

    @Override
    public void deletePartenaire(Long id) {
        Partenaire partenaire=partenaireRepository.findById(id).get();
        partenaireRepository.delete(partenaire);
    }
}
