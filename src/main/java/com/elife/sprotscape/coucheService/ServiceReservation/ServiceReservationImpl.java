package com.elife.sprotscape.coucheService.ServiceReservation;

import com.elife.sprotscape.DAO.ActiviteRepository.ActiviteRepository;
import com.elife.sprotscape.DAO.AthleteRepository.AthleteRepository;
import com.elife.sprotscape.DAO.ReservationRepository.ReservationRepository;
import com.elife.sprotscape.DAO.SeanceRepository.SeanceRepository;
import com.elife.sprotscape.DAO.StadeRepository.StadeRepository;
import com.elife.sprotscape.DTO.ReservationDTO.ReservationRequestDTO;
import com.elife.sprotscape.Entities.*;
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
    private AthleteRepository athleteRepository;
    private StadeRepository stadeRepository;
    private ActiviteRepository activiteRepository;
    @Override
    public void addReservation(ReservationRequestDTO reservationRequestDTO,Long idAthlete, Long idStade,String nomActivite) {
        Athlete athlete=athleteRepository.findById(idAthlete).get();
        Stade stade=stadeRepository.findById(idStade).get();
        Activite activite=activiteRepository.findByNomActivite(nomActivite);
        Seance seance=seanceRepository.findByJourSeanceAndDebutSeanceAndStadeAndActivite(reservationRequestDTO.getJourReservation(),reservationRequestDTO.getHeureReservation(),stade,activite);
        if(seance.isEstDisponible() && athlete!=null && stade!=null){
            Reservation reservation=new Reservation();
            reservation.setJourReservation(reservationRequestDTO.getJourReservation());
            reservation.setHeureReservation(reservationRequestDTO.getHeureReservation());
            reservation.setPrixTotal(reservationRequestDTO.getPrixTotal());
            reservation.setStade(stade);
            reservation.setAthlete(athlete);
            reservation.setEtatReservation(EtatReservation.EN_COURS);
            reservationRepository.save(reservation);

            seance.setIdSeance(seance.getIdSeance());
            seance.setJourSeance(seance.getJourSeance());
            seance.setDebutSeance(seance.getDebutSeance());
            seance.setFinSeance(seance.getFinSeance());
            seance.setEstDisponible(false);
            seanceRepository.save(seance);
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
        reservation.setAthlete(reservation.getAthlete());
        reservation.setStade(reservation.getStade());
        reservationRepository.save(reservation);
    }

    @Override
    public void annulerReservation(Long id) {
        Reservation reservation=reservationRepository.findById(id).get();
        Seance seance=seanceRepository.findByJourSeanceAndDebutSeanceAndStadeAndActivite(reservation.getJourReservation(),reservation.getHeureReservation(),reservation.getStade(),reservation.getStade().getActivite());
        reservation.setIdReservation(reservation.getIdReservation());
        reservation.setJourReservation(reservation.getJourReservation());
        reservation.setHeureReservation(reservation.getHeureReservation());
        reservation.setAthlete(reservation.getAthlete());
        reservation.setStade(reservation.getStade());
        reservation.setEtatReservation(EtatReservation.ANNULER);
        reservationRepository.save(reservation);

        seance.setIdSeance(seance.getIdSeance());
        seance.setJourSeance(seance.getJourSeance());
        seance.setDebutSeance(seance.getDebutSeance());
        seance.setFinSeance(seance.getFinSeance());
        seance.setEstDisponible(true);
        seanceRepository.save(seance);
    }

    @Override
    public void accepterReservation(Long id) {
        Reservation reservation=reservationRepository.findById(id).get();
        reservation.setIdReservation(reservation.getIdReservation());
        reservation.setJourReservation(reservation.getJourReservation());
        reservation.setHeureReservation(reservation.getHeureReservation());
        reservation.setAthlete(reservation.getAthlete());
        reservation.setStade(reservation.getStade());
        reservation.setEtatReservation(EtatReservation.ACCEPTER);
        reservationRepository.save(reservation);
    }

    @Override
    public void passerReservation(Long id) {
        Reservation reservation=reservationRepository.findById(id).get();
        reservation.setIdReservation(reservation.getIdReservation());
        reservation.setJourReservation(reservation.getJourReservation());
        reservation.setHeureReservation(reservation.getHeureReservation());
        reservation.setAthlete(reservation.getAthlete());
        reservation.setStade(reservation.getStade());
        reservation.setEtatReservation(EtatReservation.PASSEE);
        reservationRepository.save(reservation);
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
    public List<Reservation> getReservationByJourAndAthlete(String jour,Long id) {
        Athlete athlete=athleteRepository.findById(id).get();
        if (athlete != null) {
            return reservationRepository.findByJourReservationAndAthlete(jour,athlete);
        }
        else return null;
    }
    @Override
    public List<Reservation> getReservationByJourAndStade(String jour,Long id) {
        Stade stade=stadeRepository.findById(id).get();
        if (stade != null) {
            return reservationRepository.findByJourReservationAndStade(jour,stade);
        }
        else return null;
    }

    @Override
    public List<Reservation> getAllReservationByAthlete(Long id) {
        Athlete athlete=athleteRepository.findById(id).get();
        return reservationRepository.findByAthlete(athlete);
    }

    @Override
    public List<Reservation> getAllReservationByStade(Long id) {
        Stade stade=stadeRepository.findById(id).get();
        return reservationRepository.findByStade(stade);
    }
}
