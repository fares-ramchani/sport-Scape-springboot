package com.elife.sprotscape.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activite {
  public Activite(String name, String type, byte[] picbyte) {
    this.nomphotoActivite = name;
    this.typephotoActivite = type;
    this.photoActivite = picbyte;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idActivite;
  @Column(unique = true)
  private String nomActivite;
  private long nombreParticipant;
  private long prix;
  private String activiteRecommandee;
  @Column(name = "photoActivite", length = 50000000)
  private byte[] photoActivite;
  @Column(name = "typephotoActivite")
  private String typephotoActivite;
  private String nomphotoActivite;
  private long dureeActivite;
  // @Transient
  // @OneToMany(mappedBy = "Activite", fetch = FetchType.LAZY)
  // private List<Stade> Stades;

  // @OneToMany
  // @JoinColumn(name = "idStade", insertable = false, updatable = false)
  // private Stade stade;
}
