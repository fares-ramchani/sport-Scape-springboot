package com.elife.sprotscape.DTO.propritaireDeStadeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class propritaireDeStadeRequestDTO {
  private String nom;
  private String prenom;
  private String telephone;
  private String mail;
  private String activiteSportive;
  private String entrepriseee;
}
