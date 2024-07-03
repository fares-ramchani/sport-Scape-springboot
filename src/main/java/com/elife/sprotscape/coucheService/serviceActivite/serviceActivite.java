package com.elife.sprotscape.coucheService.serviceActivite;

import java.util.List;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import com.elife.sprotscape.DTO.ActiviteDTO.ActiviteRequestDTO;
import com.elife.sprotscape.Entities.Activite;

public interface serviceActivite {
    public void addActivite(String activiteRequestDTO, MultipartFile File) throws IOException;

    public Activite getActiviteById(Long id);

    public List<Activite> getAllActivite();

    public void updateActivite(String activiteRequestDTO, Long id, MultipartFile File) throws IOException;

    public void deleteActivite(Long id);

    public Activite findByNomActivite(String name);
}
