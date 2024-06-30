package com.elife.sprotscape.DTO.AthleteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AthleteRequestDTO {
  private Long idAthlete;
  private String nom;
  private String prenom;
  private String telephone;
  private String mail;
  private String MotDePasse;
}
