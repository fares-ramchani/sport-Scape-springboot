package com.elife.sprotscape.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

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
  private Long prix;
  private String telephone;
  private String email;
  private String description;
  private String descriptionDetaillee;
  @ManyToOne
  @JoinColumn(name = "Activite")
  private Activite activite;
  @Transient
  @OneToMany(mappedBy = "Stade",fetch = FetchType.LAZY)
  private List<imageStade> photosStade;
  @ManyToOne
  @JoinColumn(name = "propritaireDeStade")
  private propritaireDeStade propritaireDeStade;
}
