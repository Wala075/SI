package tn.esprit.wesleti_walaeddine_4.Service;

import tn.esprit.wesleti_walaeddine_4.Domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence getById(long id);
    Agence add(Agence agence);
    Agence update(Agence agence);
    void deleteById(long id);
    List<Agence> findAll();

    // métier
    List<Agence> findByVille(String ville);
    List<Agence> rechercherParNom(String nom);
}
