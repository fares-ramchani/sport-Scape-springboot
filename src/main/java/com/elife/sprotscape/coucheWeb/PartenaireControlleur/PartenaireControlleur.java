package com.elife.sprotscape.coucheWeb.PartenaireControlleur;

import com.elife.sprotscape.DTO.PartenaireDTO.PartenaireRequestDTO;
import com.elife.sprotscape.Entities.Partenaire;
import com.elife.sprotscape.coucheService.servicesPartenaire.ServicesPartenaire;
import com.elife.sprotscape.enums.Etat;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/partenaire")
@CrossOrigin("*")
public class PartenaireControlleur {
    private ServicesPartenaire servicesPartenaire;

    @PostMapping(path = "/ajouterPartenaire")
    public void ajouterPartenaire(@RequestBody PartenaireRequestDTO partenaireRequestDTO){
        servicesPartenaire.ajouterPartenaire(partenaireRequestDTO);
    }
    @PutMapping(path = "/modifierPartenaire/{id}")
    public void modifierPartenaire(@RequestBody PartenaireRequestDTO partenaireRequestDTO,@PathVariable Long id){
        servicesPartenaire.modifierPartenaire(partenaireRequestDTO,id);
    }
    @GetMapping(path = "/getToutPartenaire")
    public List<Partenaire> getToutPartenaire(){

        return servicesPartenaire.getToutPartenaire();
    }
    @DeleteMapping(path = "/deletePartenaire/{id}")
    public void deletePartenaire(@PathVariable Long id){

        servicesPartenaire.deletePartenaire(id);
    }
    @GetMapping(path = "/getPartenaireById/{id}")
    public Partenaire getPartenaireById(@PathVariable Long id){

        return servicesPartenaire.getPartenaire(id);
    }
    @GetMapping(path = "/getPartenaireByEtat")
    public List<Partenaire> getPartenaireByEtat(@RequestParam Etat etat){

        return servicesPartenaire.getPartenaireByEtat(etat);
    }
}
