package tn.esprit.wesleti_walaeddine_4.WebController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.wesleti_walaeddine_4.Domaine.Vehicule;
import tn.esprit.wesleti_walaeddine_4.Services.IVehiculeService;
import tn.esprit.wesleti_walaeddine_4.Services.Impls.VehiculeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {

    private final IVehiculeService vehiculeService;

    @PostMapping("add")
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @GetMapping
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @GetMapping("{id}")
    public Vehicule findById(@PathVariable("id") Long id) {
        return vehiculeService.findById(id);
    }

    @PutMapping("{id}")
    public Vehicule update(@PathVariable("id") Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Long id) {
        vehiculeService.deleteById(id);
    }


    @PutMapping("/{idVehicule}/{idAgence}")
    Vehicule assignToAgence(@PathVariable Long idVehicule,@PathVariable Long idAgence) {
    return vehiculeService.assignToAgence(idVehicule,idAgence);
    }
    @PutMapping("/{idVehicule}/equipements/{idEquipement}")
    public Vehicule assignToEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.assignToEquipement(idVehicule, idEquipement);
    }
    @PutMapping("unassignToAgence/{idVehicule}/{idAgence}")
    Vehicule unassignToAgence(@PathVariable Long idVehicule,@PathVariable Long idAgence){
        return vehiculeService.UnassignToAgence(idVehicule,idAgence);

    }
    @PutMapping("unassignToEquipement/{idVehicule}/{idEquipement}")
    public Vehicule unassignToEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.unassignToEquipement(idVehicule, idEquipement);
    }



    }
