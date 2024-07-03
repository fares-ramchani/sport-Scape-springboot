package com.elife.sprotscape.coucheService.serviceEvenement;

import java.util.List;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.elife.sprotscape.DTO.EvenementDTO.EvenementRequestDTO;
import com.elife.sprotscape.Entities.Evenement;

public interface serviceEvenement {
    public void addEvenement(String evenementRequestDTO, MultipartFile File) throws IOException;

    public Evenement getEvenementById(Long id);

    public List<Evenement> getAllEvenement();

    public void updateEvenement(String evenementRequestDTO, Long id, MultipartFile File)
            throws IOException;

    public void deleteEvenement(Long id);

    public Evenement findByName(String name);
}
