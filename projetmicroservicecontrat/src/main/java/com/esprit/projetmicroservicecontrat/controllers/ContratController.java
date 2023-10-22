package com.esprit.projetmicroservicecontrat.controllers;
import com.esprit.projetmicroservicecontrat.entites.Contrat;
import com.esprit.projetmicroservicecontrat.services.ContratService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ContratController {
    ContratService contratService;


    @GetMapping("/getAllContrats")
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }

    @PostMapping("/addContrat")
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.addContrat(contrat);
    }

    @PutMapping("/updateContrat/{idContrat}")
    public void updateContrat(@PathVariable long idContrat, @RequestBody Contrat newContrat) {
        contratService.updateContrat(idContrat, newContrat);
    }

    @GetMapping("/getContratById/{idContrat}")
    public Contrat getContratById(@PathVariable("idContrat") long idContrat) {
        return contratService.getContratById(idContrat);
    }

    @DeleteMapping("/deleteContrat/{idContrat}")
    public String deleteContrat(@PathVariable("idContrat") long idContrat) {
        return contratService.deleteContrat(idContrat);
    }

}
