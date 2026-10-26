package tn.esprit.wesleti_walaeddine_4.Service;

import tn.esprit.wesleti_walaeddine_4.Domain.Client;

import java.util.List;

public interface IClientService {
    Client getById(long id);
    Client add(Client client);
    Client update(Client client);
    void deleteById(long id);
    List<Client> findAll();

    // métier
    Client findByEmail(String email);
    List<Client> rechercher(String motCle);
}
