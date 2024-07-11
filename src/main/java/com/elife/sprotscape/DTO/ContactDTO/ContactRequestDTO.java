package com.elife.sprotscape.DTO.ContactDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactRequestDTO {
    private Long idContact;
    private String nom;
    private String prenom;
    private Long telephone;
    private String sujet;
    private String mail;
    private String description;
}
