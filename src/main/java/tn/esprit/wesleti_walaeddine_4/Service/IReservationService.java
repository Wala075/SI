package tn.esprit.wesleti_walaeddine_4.Service;

import tn.esprit.wesleti_walaeddine_4.Domain.Reservation;
import tn.esprit.wesleti_walaeddine_4.Domain.StatutReservation;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {
    Reservation getById(long id);
    Reservation add(Reservation reservation);
    Reservation update(Reservation reservation);
    void deleteById(long id);
    List<Reservation> findAll();

    // métier
    Reservation reserver(long idClient, long idVehicule, LocalDate dateDebut, LocalDate dateFin);
    Reservation confirmer(long idReservation);
    Reservation annuler(long idReservation);
    Reservation terminer(long idReservation);
    List<Reservation> findByClient(long idClient);
    List<Reservation> findByVehicule(long idVehicule);
    List<Reservation> findByStatut(StatutReservation statut);
}
