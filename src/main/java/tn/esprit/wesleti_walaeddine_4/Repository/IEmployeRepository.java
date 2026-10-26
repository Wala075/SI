package tn.esprit.wesleti_walaeddine_4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.wesleti_walaeddine_4.Domain.Employe;
import tn.esprit.wesleti_walaeddine_4.Domain.RoleEmploye;

import java.util.List;

public interface IEmployeRepository extends JpaRepository<Employe, Long> {

    List<Employe> findByAgenceIdAgence(Long idAgence);

    List<Employe> findByRole(RoleEmploye role);

    boolean existsByAgenceIdAgence(Long idAgence);
}
