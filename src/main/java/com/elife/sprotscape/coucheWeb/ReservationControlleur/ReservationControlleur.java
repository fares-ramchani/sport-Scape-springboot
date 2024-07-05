package com.elife.sprotscape.coucheWeb.ReservationControlleur;

import com.elife.sprotscape.DTO.ReservationDTO.ReservationRequestDTO;
import com.elife.sprotscape.Entities.Reservation;
import com.elife.sprotscape.coucheService.ServiceReservation.ServiceReservation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/reservations")
@CrossOrigin("*")
public class ReservationControlleur {
private ServiceReservation serviceReservation;
    @PostMapping(path = "/addReservation")
    public void addReservation(@RequestBody ReservationRequestDTO reservationRequestDTO, @RequestParam("idAthlete") Long idAthlete, @RequestParam("idStade") Long idStade, @RequestParam("nomActivite") String nomActivite){
        serviceReservation.addReservation(reservationRequestDTO,idAthlete,idStade,nomActivite);
    }
    @PutMapping(path = "/updateReservation{id}")
    public void updateReservation(@RequestBody ReservationRequestDTO reservationRequestDTO,@PathVariable Long id){
        serviceReservation.updateReservation(reservationRequestDTO,id);
    }
    @PutMapping(path = "/accepterReservation/{id}")
    public void accepterReservation(@PathVariable Long id){
        serviceReservation.accepterReservation(id);
    }
    @PutMapping(path = "/passerReservation/{id}")
    public void passerReservation(@PathVariable Long id){
        serviceReservation.passerReservation(id);
    }
    @PutMapping(path = "/annulerReservation/{id}")
    public void annulerReservation(@PathVariable Long id){
        serviceReservation.annulerReservation(id);
    }
    @GetMapping(path = "/getAllReservation")
    public List<Reservation> getAllReservation() {
        return serviceReservation.getAllReservation();
    }
    @GetMapping(path = "/getAllReservationById/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return serviceReservation.getReservationById(id);
    }

    @GetMapping(path = "/getReservationByJourAndAthlete")
    public List<Reservation> getReservationByJourAndAthlete(@RequestParam String jour, @PathVariable Long id) {
        return serviceReservation.getReservationByJourAndAthlete(jour,id);
    }
    @GetMapping(path = "/getReservationByJourAndStade")
    public List<Reservation> getReservationByJourAndStade(@RequestParam String jour, @PathVariable Long id) {
        return serviceReservation.getReservationByJourAndStade(jour,id);
    }
}