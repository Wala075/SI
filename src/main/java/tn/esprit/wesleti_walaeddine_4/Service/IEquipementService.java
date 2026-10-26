package tn.esprit.wesleti_walaeddine_4.Service;

import tn.esprit.wesleti_walaeddine_4.Domain.Equipement;

import java.util.List;

public interface IEquipementService {
    Equipement getById(long id);
    Equipement add(Equipement equipement);
    Equipement update(Equipement equipement);
    void deleteById(long id);
    List<Equipement> findAll();

    // métier
    List<Equipement> findByVehicule(long idVehicule);
}
