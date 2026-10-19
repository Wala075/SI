package tn.esprit.wesleti_walaeddine_4.Service;

import tn.esprit.wesleti_walaeddine_4.Domain.Vehicule;

import java.util.List;

public interface IVehiculeService {
    Vehicule getById(long id);
    Vehicule add(Vehicule vehicule);
    Vehicule update(Vehicule vehicule);
    void deleteById(long id);
    List<Vehicule> findAll();
}
