package tn.esprit.wesleti_walaeddine_4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.wesleti_walaeddine_4.Domain.Contrat;

import java.util.List;
import java.util.Optional;

public interface IContratRepository extends JpaRepository<Contrat, Long> {

    Optional<Contrat> findByReservationIdReservation(Long idReservation);

    List<Contrat> findByValide(boolean valide);
}
