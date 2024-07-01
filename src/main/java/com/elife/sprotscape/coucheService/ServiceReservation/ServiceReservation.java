package com.elife.sprotscape.coucheService.ServiceReservation;

import com.elife.sprotscape.DTO.ReservationDTO.ReservationRequestDTO;
import com.elife.sprotscape.Entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ServiceReservation {
    public void addReservation(ReservationRequestDTO reservationRequestDTO);
    public void updateReservation(ReservationRequestDTO reservationRequestDTO,Long id);
    public void deleteReservation(Long id);
    public Reservation getReservationById(Long id);
    public List<Reservation> getAllReservation();
    public List<Reservation> getReservationByJour(String jour);
}