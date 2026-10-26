package tn.esprit.wesleti_walaeddine_4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.wesleti_walaeddine_4.Domain.Paiement;

import java.math.BigDecimal;
import java.util.List;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {

    List<Paiement> findByContratIdContrat(Long idContrat);

    // somme des paiements d'un contrat (null s'il n'y a aucun paiement)
    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.contrat.idContrat = :idContrat")
    BigDecimal totalPayeParContrat(@Param("idContrat") Long idContrat);
}
