package com.elife.sprotscape.coucheService.serviceStade;

import com.elife.sprotscape.Entities.Activite;
import com.elife.sprotscape.Entities.Stade;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface serviceStade {
  void ajouterStade(String stadeRequestDTO, MultipartFile image1, MultipartFile image2, MultipartFile image3,MultipartFile image4,MultipartFile image5) throws IOException;
  public List<Stade> getTousLesStade();
  Stade getStadeByIdAndActivite(Long id, String nomActivite);
  Stade getStadeById(Long id);
  List<Stade> getStadeByVilleAndActivite(String ville, String nomActivite);
}
