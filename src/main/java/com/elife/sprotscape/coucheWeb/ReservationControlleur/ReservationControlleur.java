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
    public void addReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){
        serviceReservation.addReservation(reservationRequestDTO);
    }
    @PutMapping(path = "/updateReservation{id}")
    public void updateReservation(@RequestBody ReservationRequestDTO reservationRequestDTO,@PathVariable Long id){
        serviceReservation.updateReservation(reservationRequestDTO,id);
    }
    @DeleteMapping(path = "/deleteReservation/{id}")
    public void deleteReservation(@PathVariable Long id){
        serviceReservation.deleteReservation(id);
    }
    @GetMapping(path = "/getAllReservation")
    public List<Reservation> getAllReservation() {
        return serviceReservation.getAllReservation();
    }
    @GetMapping(path = "/getAllReservationById/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return serviceReservation.getReservationById(id);
    }
    @GetMapping(path = "/getReservationByJour")
    public List<Reservation> getReservationByJour(@RequestParam String jour) {
        return serviceReservation.getReservationByJour(jour);
    }
}