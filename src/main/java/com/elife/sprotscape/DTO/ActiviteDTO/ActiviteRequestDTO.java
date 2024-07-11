package com.elife.sprotscape.DTO.ActiviteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActiviteRequestDTO {
    private Long idActivite;
    private String nomActivite;
    private long nombreParticipant;
    private long prix;
    private String activiteRecommandee;
    private String nomphotoActivite;
    private long dureeActivite;
    private String PhotoActivit;
}
