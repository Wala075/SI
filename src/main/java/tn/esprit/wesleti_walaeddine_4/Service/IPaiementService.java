package tn.esprit.wesleti_walaeddine_4.Service;

import tn.esprit.wesleti_walaeddine_4.Domain.Paiement;

import java.math.BigDecimal;
import java.util.List;

public interface IPaiementService {
    Paiement getById(long id);
    Paiement add(Paiement paiement);
    Paiement update(Paiement paiement);
    void deleteById(long id);
    List<Paiement> findAll();

    // métier
    Paiement payer(long idContrat, Paiement paiement);
    List<Paiement> findByContrat(long idContrat);
    BigDecimal resteAPayer(long idContrat);
}
