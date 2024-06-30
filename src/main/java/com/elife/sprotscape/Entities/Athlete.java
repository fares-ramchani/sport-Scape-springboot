package com.elife.sprotscape.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Athlete {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idAthlete;
  private String nom;
  private String prenom;
  private String telephone;
  private String mail;
}
