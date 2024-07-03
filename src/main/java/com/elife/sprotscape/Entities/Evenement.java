package com.elife.sprotscape.Entities;

import com.elife.sprotscape.enums.Etat;

import jakarta.persistence.Column;
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
    public Evenement(String name, String type, byte[] picbyte) {
        this.nomphotoEvenement = name;
        this.typephotoEvenement = type;
        this.photoEvenement = picbyte;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;
    @Column(unique = true)
    private String nomEvenement;
    private String description;
    private String lieu;
    private long prix;
    private long nombreParticipant;
    private Boolean estPayer;
    @Enumerated(EnumType.STRING)
    private Etat etatEvenement;
    @Column(name = "photoEvenement", length = 50000000)
    private byte[] photoEvenement;
    @Column(name = "typephotoEvenement")
    private String typephotoEvenement;
    private String nomphotoEvenement;

    // @OneToOne
    // @JoinColumn(name = "idStade", insertable = false, updatable = false)
    // private Stade stade;

}
