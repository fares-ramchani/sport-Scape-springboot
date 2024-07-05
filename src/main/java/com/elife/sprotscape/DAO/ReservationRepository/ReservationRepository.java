package com.elife.sprotscape.DAO.ReservationRepository;

import com.elife.sprotscape.Entities.Athlete;
import com.elife.sprotscape.Entities.Reservation;
import com.elife.sprotscape.Entities.Stade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    //Reservation findByJourReservationAndHeureReservation(String jour, DateTime heure);
  //  List<Reservation> findByJourReservation(String jour);
    List<Reservation> findByAthlete(Athlete athlete);
    List<Reservation> findByStade(Stade stade);
    List<Reservation> findByJourReservationAndAthlete(String jour,Athlete athlete);
    List<Reservation> findByJourReservationAndStade(String jour,Stade stade);
}
