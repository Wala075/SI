package tn.esprit.wesleti_walaeddine_4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.wesleti_walaeddine_4.Domaine.Paiement;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
}
