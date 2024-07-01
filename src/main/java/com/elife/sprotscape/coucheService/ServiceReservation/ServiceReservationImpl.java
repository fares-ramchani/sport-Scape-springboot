package com.elife.sprotscape.coucheService.ServiceReservation;

import com.elife.sprotscape.DAO.ReservationRepository.ReservationRepository;
import com.elife.sprotscape.DAO.SeanceRepository.SeanceRepository;
import com.elife.sprotscape.DTO.ReservationDTO.ReservationRequestDTO;
import com.elife.sprotscape.Entities.Reservation;
import com.elife.sprotscape.Entities.Seance;
import com.elife.sprotscape.enums.EtatReservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class ServiceReservationImpl implements ServiceReservation{
    private ReservationRepository reservationRepository;
    private SeanceRepository seanceRepository;
    @Override
    public void addReservation(ReservationRequestDTO reservationRequestDTO) {
        Seance seance=seanceRepository.findByJourSeanceAndHeureDebutSeance(reservationRequestDTO.getJourReservation(),reservationRequestDTO.getHeureReservation());
        if(seance.isEstDisponible()){
            Reservation reservation=new Reservation();
            reservation.setJourReservation(reservationRequestDTO.getJourReservation());
            reservation.setHeureReservation(reservationRequestDTO.getHeureReservation());
            reservation.setEtatReservation(EtatReservation.EN_COURS);
            reservationRepository.save(reservation);
        }
        else return;
    }

    @Override
    public void updateReservation(ReservationRequestDTO reservationRequestDTO,Long id) {
        Reservation reservation=reservationRepository.findById(id).get();
        if(!Objects.equals(reservationRequestDTO.getJourReservation(), "")){
            reservation.setJourReservation(reservationRequestDTO.getJourReservation());
        }
        if(!Objects.equals(reservationRequestDTO.getHeureReservation(), "")){
            reservation.setHeureReservation(reservationRequestDTO.getHeureReservation());
        }
        if(!Objects.equals(reservationRequestDTO.getEtatReservation(), "")){
            reservation.setEtatReservation(reservationRequestDTO.getEtatReservation());
        }
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation=reservationRepository.findById(id).get();
        reservationRepository.delete(reservation);
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationByJour(String jour) {
        return reservationRepository.findByJourReservation(jour);
    }
}
