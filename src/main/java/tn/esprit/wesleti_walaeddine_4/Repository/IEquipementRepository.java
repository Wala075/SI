package tn.esprit.wesleti_walaeddine_4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.wesleti_walaeddine_4.Domain.Equipement;

import java.util.List;

public interface IEquipementRepository extends JpaRepository<Equipement, Long> {

    // jointure sur la table vehicule_equipement via le côté inverse "vehicules"
    List<Equipement> findByVehiculesIdVehicule(Long idVehicule);
}
