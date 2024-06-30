package com.elife.sprotscape.DTO.EvenementDTO;
import com.elife.sprotscape.enums.Estpayer;
import com.elife.sprotscape.enums.Etat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EvenementRequestDTO {
    private Long idEvenement;
    private String nomEvenement;
    private String description;
    private String lieu;
    private long prix;
    private long nombreParticipant;
    private Estpayer estPayer;
    private Etat etatEvenement;
}