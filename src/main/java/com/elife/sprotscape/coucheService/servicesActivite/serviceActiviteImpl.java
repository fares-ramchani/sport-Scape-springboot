package com.elife.sprotscape.coucheService.servicesActivite;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elife.sprotscape.DAO.ActiviteRepository.ActiviteRepository;
import com.elife.sprotscape.DTO.ActiviteDTO.ActiviteRequestDTO;
import com.elife.sprotscape.Entities.Activite;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class serviceActiviteImpl implements serviceActivite {
    private ActiviteRepository activiteRepository;

    @Override
    public void addActivite(ActiviteRequestDTO activiteRequestDTO) {
        Activite activite = new Activite();
        activite.setNomActivite(activiteRequestDTO.getNomActivite());
        activite.setPrix(activiteRequestDTO.getPrix());
        activite.setActiviteRecommandee(activiteRequestDTO.getActiviteRecommandee());
        activite.setPhotoActivite(activiteRequestDTO.getPhotoActivite());
        activite.setTypephotoActivite(activiteRequestDTO.getTypephotoActivite());
        activite.setNomphotoActivite(activiteRequestDTO.getNomphotoActivite());
        activite.setDureeActivite(activiteRequestDTO.getDureeActivite());
        activiteRepository.save(activite);
    }
    @Override
    public Activite getActiviteById(Long id) {
        return activiteRepository.findById(id).get();
    }

    @Override
    public List<Activite> getAllActivite() {
        return activiteRepository.findAll();
    }

    @Override
    public void updateActivite(ActiviteRequestDTO activiteRequestDTO, Long id) {
        Activite activite = activiteRepository.findById(id).get();

        if (activiteRequestDTO.getNomActivite() != "") {
            activite.setNomActivite(activiteRequestDTO.getNomActivite());
        }
        if (activiteRequestDTO.getPrix() != -1) {
            activite.setPrix(activiteRequestDTO.getPrix());
        }
        if (activiteRequestDTO.getActiviteRecommandee() != "") {
            activite.setActiviteRecommandee(activiteRequestDTO.getActiviteRecommandee());
        }
        if (activiteRequestDTO.getPhotoActivite() != "") {
            activite.setPhotoActivite(activiteRequestDTO.getPhotoActivite());
        }
        if (activiteRequestDTO.getTypephotoActivite() != "") {
            activite.setTypephotoActivite(activiteRequestDTO.getTypephotoActivite());
        }
        if (activiteRequestDTO.getNomphotoActivite() != "") {
            activite.setNomphotoActivite(activiteRequestDTO.getNomphotoActivite());
        }
        if (activiteRequestDTO.getNomActivite() != "") {
            activite.setDureeActivite(activiteRequestDTO.getDureeActivite());
        }
        if (activiteRequestDTO.getDureeActivite() != -1) {
            activite.setDureeActivite(activiteRequestDTO.getDureeActivite());
        }
        activiteRepository.save(activite);
    }

    @Override
    public void deleteActivite(Long id) {
        Activite activite = activiteRepository.findById(id).get();
        activiteRepository.delete(activite);
    }

}
