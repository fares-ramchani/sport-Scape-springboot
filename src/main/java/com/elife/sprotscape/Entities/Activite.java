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
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idActivite;
  private String nomActivite;
  private long nombreParticipant;
  private long prix;
  private String activiteRecommandee;
  private byte[] photoActivite;
  private String typephotoActivite;
  private String nomphotoActivite;
  private long dureeActivite;
  @Transient
  @OneToMany(mappedBy = "Activite",fetch = FetchType.LAZY)
  private List<Stade> Stades;
}
