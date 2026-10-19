package tn.esprit.wesleti_walaeddine_4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.wesleti_walaeddine_4.Domain.Vehicule;

public interface IVehiculeRepository extends JpaRepository<Vehicule,Long> {
}
