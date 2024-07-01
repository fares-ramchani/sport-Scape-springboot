package com.elife.sprotscape.coucheWeb.SeanceControlleur;

import com.elife.sprotscape.DTO.SeanceDTO.SeanceRequestDTO;
import com.elife.sprotscape.Entities.Seance;
import com.elife.sprotscape.coucheService.serviceSeance.ServiceSeance;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/Seances")
@CrossOrigin("*")
public class SeanceControlleur {
    private ServiceSeance serviceSeance;
    @PostMapping(path = "/addSeance")
    public void addSeance(@RequestBody SeanceRequestDTO seanceRequestDTO){
        serviceSeance.addSeance(seanceRequestDTO);
    }
    @PutMapping(path = "/updateSeance")
    public void updateSeance(@RequestBody SeanceRequestDTO seanceRequestDTO){
        serviceSeance.updateSeance(seanceRequestDTO);
    }
    @DeleteMapping(path = "/deleteSeance/{id}")
    public void deleteSeance(@PathVariable Long id){
        serviceSeance.deleteSeance(id);
    }
    @GetMapping(path = "/getAllSeance")
    public List<Seance> getAllSeance() {
        return serviceSeance.getAllSeance();
    }
    @GetMapping(path = "/getSeanceById/{id}")
    public Seance getSeanceById(@PathVariable Long id){
        return serviceSeance.getSeanceById(id);
    }
    @GetMapping(path = "/getSeanceByDisponibilite")
    public List<Seance> getAllSeance(@RequestParam boolean dispo) {
        return serviceSeance.getSeanceByDisbonibilite(dispo);
    }
    @GetMapping(path = "/getSeanceByJour")
    public List<Seance> getSeanceByJour(@RequestParam String jour) {
        return serviceSeance.getSeanceByJour(jour);
    }
}
