package com.elife.sprotscape.DAO.ReservationRepository;

import com.elife.sprotscape.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    //Reservation findByJourReservationAndHeureReservation(String jour, DateTime heure);
    List<Reservation> findByJourReservation(String jour);
}
