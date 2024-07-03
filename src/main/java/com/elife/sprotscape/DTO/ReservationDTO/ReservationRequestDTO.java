package com.elife.sprotscape.DTO.ReservationDTO;

import com.elife.sprotscape.Entities.Athlete;
import com.elife.sprotscape.Entities.Stade;
import com.elife.sprotscape.enums.EtatReservation;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {
    private String jourReservation;
    private String heureReservation;
    private EtatReservation etatReservation;
   /* @ManyToOne()
    private Athlete athlete;
    @ManyToOne()
    private Stade stade;*/
}
