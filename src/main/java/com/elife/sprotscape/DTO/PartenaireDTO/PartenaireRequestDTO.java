package com.elife.sprotscape.DTO.PartenaireDTO;

import com.elife.sprotscape.enums.Etat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PartenaireRequestDTO {
    private Long idPartenaire;
    private String nomPartenaire;
    private String nomResponsable;
    private String telephoneResponsable;
    private String adressePartenaire;
    private String emailResponsable;
    private String photoActivite;
    private String typephotoActivite;
    private String nomphotoActivite;
    private Etat etatPartenaire;
}
