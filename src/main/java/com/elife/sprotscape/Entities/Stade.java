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

public class Stade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idStade;
  private String nomStade;
  private String code;
  private String ville;
  private String Adresse;
  private String nombreDeJoueur;
  private String telephone;
  private String email;
  @ManyToOne
  @JoinColumn(name = "Activite")
  private Activite Activite;
  @Transient
  @OneToMany(mappedBy = "Stade",fetch = FetchType.LAZY)
  private List<imageStade> PhotosStade;
  @ManyToOne
  @JoinColumn(name = "propritaireDeStade")
  private propritaireDeStade propritaireDeStade;
}
