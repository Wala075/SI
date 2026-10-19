package tn.esprit.wesleti_walaeddine_4.WebController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.wesleti_walaeddine_4.Domain.Vehicule;
import tn.esprit.wesleti_walaeddine_4.Service.IVehiculeService;

@RestController
@RequestMapping("vehicules")
@RequiredArgsConstructor
public class VehiculeRestController {
    private final IVehiculeService iVehiculeService;
    @PostMapping("add")
    Vehicule create(Vehicule vehicule){
        return iVehiculeService.add(vehicule);
    }
}
