package com.elife.sprotscape.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeance;
    private String jourSeance;
    private String debutSeance;
    private String finSeance;
    private boolean estDisponible;
    @ManyToOne()
    private Stade stade;
    @ManyToOne()
    private Activite activite;
}
