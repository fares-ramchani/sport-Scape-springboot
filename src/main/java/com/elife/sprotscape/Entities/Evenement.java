package com.elife.sprotscape.Entities;

import com.elife.sprotscape.enums.Estpayer;
import com.elife.sprotscape.enums.Etat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;
    private String nomEvenement;
    private String description;
    private String lieu;
    private long prix;
    private long nombreParticipant;
    @Enumerated(EnumType.STRING)
    private Estpayer estPayer;
    @Enumerated(EnumType.STRING)
    private Etat etatEvenement;
}
