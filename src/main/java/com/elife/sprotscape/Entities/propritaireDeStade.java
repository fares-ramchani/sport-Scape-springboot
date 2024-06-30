package com.elife.sprotscape.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class propritaireDeStade {
  public propritaireDeStade(String name,String type,byte[] picbyte){
    this.NomPhotoPropritaire=name;
    this.typePhotoPropritaire=type;
    this.PhotoPropritaire=picbyte;
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPropritaire;
  private String nom;
  private String prenom;
  private String telephone;
  private String mail;
  private String activiteSportive;
  private String Entreprise;
  private String NomPhotoPropritaire;
  private boolean compteVerifier ;
  @Column(name="type")
  private String typePhotoPropritaire;
  @Column(name="picbyte",length = 50000000)
  private byte[] PhotoPropritaire;

}
