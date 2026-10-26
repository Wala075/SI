package tn.esprit.wesleti_walaeddine_4.Service;

import tn.esprit.wesleti_walaeddine_4.Domain.Employe;
import tn.esprit.wesleti_walaeddine_4.Domain.RoleEmploye;

import java.util.List;

public interface IEmployeService {
    Employe getById(long id);
    Employe add(Employe employe);
    Employe update(Employe employe);
    void deleteById(long id);
    List<Employe> findAll();

    // métier
    Employe affecterAgence(long idEmploye, long idAgence);
    List<Employe> findByAgence(long idAgence);
    List<Employe> findByRole(RoleEmploye role);
}
