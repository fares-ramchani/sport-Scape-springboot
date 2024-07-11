package com.elife.sprotscape.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
  public propritaireDeStade( Long idPropritaire,String nom,
                             String prenom,String telephone,
                             String mail,String activiteSportive,
                             String entrepriseee,String name,String code
    ,String type,byte[] picbyte,boolean compteVerifier ){
    this.NomPhotoPropritaire=name;
    this.typePhotoPropritaire=type;
    this.PhotoPropritaire=picbyte;
    this.idPropritaire=idPropritaire;
    this.nom=nom;
    this.prenom=prenom;
    this.telephone=telephone;
    this.mail=mail;
    this.activiteSportive=activiteSportive;
    this.entrepriseee=entrepriseee;
    this.code=code;
    this.compteVerifier=compteVerifier;

  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPropritaire;
  private String nom;
  private String prenom;
  private String telephone;
  private String mail;
  private String activiteSportive;
  private String entrepriseee;
  private String NomPhotoPropritaire;
  private String code;
  private Boolean compteVerifier ;
  @Column(name="type")
  private String typePhotoPropritaire;
  @Column(name="picbyte",length = 50000000)
  private byte[] PhotoPropritaire;
  @Transient
  @OneToMany(mappedBy = "propritaireDeStade",fetch = FetchType.LAZY)
  private List<Stade> stade;
}
