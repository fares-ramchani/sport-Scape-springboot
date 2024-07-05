package com.elife.sprotscape.DAO.SeanceRepository;

import com.elife.sprotscape.Entities.Activite;
import com.elife.sprotscape.Entities.Seance;
import com.elife.sprotscape.Entities.Stade;
import com.google.api.client.util.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance,Long> {

    List<Seance> findByEstDisponible(boolean dispo);
    List<Seance> findByJourSeanceAndStadeAndEstDisponibleAndActivite(String jour, Stade stade,boolean dispo, Activite activite);
    Seance findByJourSeanceAndDebutSeanceAndStadeAndActivite(String jour, String heure, Stade stade, Activite activite);
}
