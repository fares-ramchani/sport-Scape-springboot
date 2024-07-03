package com.elife.sprotscape.Entities;

import com.elife.sprotscape.enums.Etat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartenaire;
    private String nomPartenaire;
    private String nomResponsable;
    private String telephoneResponsable;
    private String adressePartenaire;
    private String emailResponsable;
    private String photoActivite;
    private String typephotoActivite;
    private String nomphotoActivite;
    @Enumerated(EnumType.STRING)
    private Etat etatPartenaire;
}
