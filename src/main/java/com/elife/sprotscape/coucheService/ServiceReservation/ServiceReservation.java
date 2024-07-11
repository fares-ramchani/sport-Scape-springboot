package com.elife.sprotscape.coucheService.ServiceReservation;

import com.elife.sprotscape.DTO.ReservationDTO.ReservationRequestDTO;
import com.elife.sprotscape.Entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ServiceReservation {
    public void addReservation(ReservationRequestDTO reservationRequestDTO,Long idAthlete, Long idStade,String nomActivite);
    public void updateReservation(ReservationRequestDTO reservationRequestDTO,Long id);
    public void annulerReservation(Long id);
    public void accepterReservation(Long id);
    public void passerReservation(Long id);
    public Reservation getReservationById(Long id);
    public List<Reservation> getAllReservation();
    public List<Reservation> getReservationByJourAndAthlete(String jour,Long id);
    public List<Reservation> getReservationByJourAndStade(String jour,Long id);
    public List<Reservation> getAllReservationByAthlete(Long id);
    public List<Reservation> getAllReservationByStade(Long id);
}