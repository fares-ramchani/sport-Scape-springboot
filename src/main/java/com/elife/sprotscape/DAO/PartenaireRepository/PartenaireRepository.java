package com.elife.sprotscape.DAO.PartenaireRepository;

import com.elife.sprotscape.Entities.Partenaire;
import com.elife.sprotscape.enums.Etat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartenaireRepository  extends JpaRepository<Partenaire,Long> {
    List<Partenaire> findByEtatPartenaire(Etat etatPartenariat);
}
