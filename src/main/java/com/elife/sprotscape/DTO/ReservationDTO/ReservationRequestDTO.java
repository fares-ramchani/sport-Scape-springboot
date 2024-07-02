package com.elife.sprotscape.DTO.ReservationDTO;

import com.elife.sprotscape.enums.EtatReservation;
import com.google.api.client.util.DateTime;
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
}
