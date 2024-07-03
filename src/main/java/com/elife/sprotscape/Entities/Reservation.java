package com.elife.sprotscape.Entities;

import com.elife.sprotscape.enums.EtatReservation;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private String jourReservation;
    private String heureReservation;
    @Enumerated(EnumType.STRING)
    private EtatReservation etatReservation;
    @ManyToOne()
    private Athlete athlete;
    @ManyToOne()
    private Stade stade;
}
