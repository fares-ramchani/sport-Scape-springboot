package com.elife.sprotscape.coucheWeb.ActiviteControlleur;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elife.sprotscape.DTO.ActiviteDTO.ActiviteRequestDTO;
import com.elife.sprotscape.Entities.Activite;
import com.elife.sprotscape.coucheService.serviceActivite.serviceActivite;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/activite")
@CrossOrigin("*")
public class ActiviteControlleur {
  private serviceActivite serviceActivite;

  @PostMapping(path = "/add")
  // public void addActivite(@RequestParam("ActiviteRequestDTO") String
  // activiteRequestDTO,
  public ResponseEntity<?> addActivite(@RequestParam("ActiviteRequestDTO") String activiteRequestDTO,
      @RequestParam("logo") MultipartFile file) throws IOException {
    try {
      serviceActivite.addActivite(activiteRequestDTO, file);
      return ResponseEntity.ok("Activity added successfully");
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
    }
  }

  @PutMapping(path = "/update/{id}")
  public ResponseEntity<?> updateActivite(@RequestBody String activiteRequestDTO, Long id,
      MultipartFile file) throws IOException {
    try {
      serviceActivite.updateActivite(activiteRequestDTO, id, file);
      return ResponseEntity.ok("Activity updated successfully");
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
    }
  }

  @GetMapping(path = "/all")
  public List<Activite> getAllActivites() {
    return serviceActivite.getAllActivite();
  }

  @DeleteMapping(path = "/delete/{id}")
  public void deleteActivite(@RequestParam Long id) {
      serviceActivite.deleteActivite(id);
    
  }

  @GetMapping(path = "/ById/{id}")
  public Activite getActiviteById(@RequestParam Long id) {
    return serviceActivite.getActiviteById(id);
  }
  @GetMapping(path = "/ByName/{name}")
  public Activite findByName(@RequestParam String name){
    return serviceActivite.findByName(name);
  }
}
