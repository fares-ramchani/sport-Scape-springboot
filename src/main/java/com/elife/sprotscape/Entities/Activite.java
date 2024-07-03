package com.elife.sprotscape.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String photoActivite;
    private String typephotoActivite;
    private String nomphotoActivite;
    private long dureeActivite;

    // @ManyToOne
    // @JoinColumn(name = "idReservation", insertable = false, updatable = false)
    // private Reservation reservation;
    // private long idReservation;
}
