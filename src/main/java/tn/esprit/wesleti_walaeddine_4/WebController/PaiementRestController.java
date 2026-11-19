package tn.esprit.wesleti_walaeddine_4.WebController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.wesleti_walaeddine_4.Domaine.Paiement;
import tn.esprit.wesleti_walaeddine_4.Services.IPaiementService;

import java.util.List;

/**
 * Lecture seule : un paiement est cree, modifie ou retire uniquement via son Contrat
 * (composition Contrat <>--- Paiement, cascade ALL + orphanRemoval).
 */
@RestController
@RequestMapping("paiements")
@RequiredArgsConstructor
public class PaiementRestController {

    private final IPaiementService paiementService;

    @GetMapping
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @GetMapping("{id}")
    public Paiement findById(@PathVariable("id") Long id) {
        return paiementService.findById(id);
    }
}
