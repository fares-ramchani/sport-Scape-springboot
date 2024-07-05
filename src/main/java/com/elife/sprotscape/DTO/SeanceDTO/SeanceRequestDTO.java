package com.elife.sprotscape.DTO.SeanceDTO;

import com.elife.sprotscape.Entities.Stade;
import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeanceRequestDTO {
    private Long idSeance;
    private String jourSeance;
    private String debutSeance;
    private String finSeance;
    private boolean estDisponible;
}
