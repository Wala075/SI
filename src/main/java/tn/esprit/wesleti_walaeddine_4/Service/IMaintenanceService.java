package tn.esprit.wesleti_walaeddine_4.Service;

import tn.esprit.wesleti_walaeddine_4.Domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance getById(long id);
    Maintenance add(Maintenance maintenance);
    Maintenance update(Maintenance maintenance);
    void deleteById(long id);
    List<Maintenance> findAll();

    // métier
    Maintenance planifier(long idVehicule, Maintenance maintenance);
    Maintenance terminer(long idMaintenance);
    List<Maintenance> findByVehicule(long idVehicule);
    List<Maintenance> findEnCours();
}
