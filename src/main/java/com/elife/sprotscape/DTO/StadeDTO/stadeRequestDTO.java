package com.elife.sprotscape.DTO.StadeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class stadeRequestDTO {
  private String nom;
  private String code;
  private String ville;
  private String adresse;
  private String telephone;
  private String mail;
  private String activite;
  private String description;
  private String descriptionDetaillee;
}
