package com.elife.sprotscape.coucheService.servicesActivite;

import java.util.List;

import com.elife.sprotscape.DTO.ActiviteDTO.ActiviteRequestDTO;
import com.elife.sprotscape.Entities.Activite;

public interface serviceActivite {
    public void addActivite(ActiviteRequestDTO ActiviteRequestDTO);

    public Activite getActiviteById(Long id);

    public List<Activite> getAllActivite();

    public void updateActivite(ActiviteRequestDTO ActiviteRequestDTO, Long id);

    public void deleteActivite(Long id);
}
