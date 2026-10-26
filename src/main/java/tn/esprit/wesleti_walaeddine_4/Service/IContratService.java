package tn.esprit.wesleti_walaeddine_4.Service;

import tn.esprit.wesleti_walaeddine_4.Domain.Contrat;

import java.util.List;

public interface IContratService {
    Contrat getById(long id);
    Contrat add(Contrat contrat);
    Contrat update(Contrat contrat);
    void deleteById(long id);
    List<Contrat> findAll();

    // métier
    Contrat genererContrat(long idReservation);
    Contrat findByReservation(long idReservation);
    List<Contrat> findByValide(boolean valide);
}
