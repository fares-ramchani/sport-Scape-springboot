package com.elife.sprotscape.DAO.SeanceRepository;

import com.elife.sprotscape.Entities.Seance;
import com.google.api.client.util.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance,Long> {

    List<Seance> findByEstDisponible(boolean dispo);
    List<Seance> findByJourSeance(String jour);
    // Seance findByJourSeanceAndHeureDebutSeance(String jour, String heure);
}
