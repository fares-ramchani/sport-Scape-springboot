package com.elife.sprotscape.coucheWeb.StadeControlleur;

import com.elife.sprotscape.Entities.Stade;
import com.elife.sprotscape.coucheService.serviceStade.serviceStade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/stade")
@CrossOrigin("*")
public class StadeControlleur {
  private serviceStade serviceStade;
  @PostMapping(path = "/ajouterStade")
  public void ajouterStade(@RequestParam("stadeRequestDTO") String stadeRequestDTO,
                           @RequestParam("image1") MultipartFile image1,
                           @RequestParam("image2") MultipartFile image2,
                           @RequestParam("image3") MultipartFile image3,
                           @RequestParam("image4") MultipartFile image4,
                          @RequestParam("image5") MultipartFile image5) throws IOException {

    serviceStade.ajouterStade(stadeRequestDTO,image1,image2,image3,image4,image5);

  }
  @GetMapping(path = "/getToutLesStades")
  public List<Stade> getToutLesStade(){
    return serviceStade.getTousLesStade();
  }

  @GetMapping(path = "/getStadeByIdAndActivite/{id}")
  public Stade getStadeByIdAndActivite(@PathVariable("id") Long id,@RequestParam("nomActivite") String nomActivite){
    return serviceStade.getStadeByIdAndActivite(id,nomActivite);
  }
  @GetMapping(path = "/getStadeById/{id}")
  public Stade getStadeById(@PathVariable Long id){
    return serviceStade.getStadeById(id);
  }
  @GetMapping(path = "/getStadeByVilleAndActivite")
  public List<Stade> getStadeByVilleAndActivite(@RequestParam("ville") String ville,@RequestParam("nomActivite") String nomActivite){
    return serviceStade.getStadeByVilleAndActivite(ville,nomActivite);
  }
}
