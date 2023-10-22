package com.esprit.projetmicroservicecontrat.services;

import com.esprit.projetmicroservicecontrat.entites.Contrat;
import com.esprit.projetmicroservicecontrat.repositories.ContratRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@Service
public class ContratServiceImpl implements ContratService {
    ContratRepository contratRepository;

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }



    @Override
    public Contrat addContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat updateContrat(long idContrat, Contrat newContrat) {

        if (contratRepository.findById(idContrat).isPresent()) {
            Contrat existingContrat = contratRepository.findById(idContrat).get();

            // Mettez à jour les champs de l'objet existingContrat avec les valeurs de newContrat
            existingContrat.setType(newContrat.getType());
            existingContrat.setDescription(newContrat.getDescription());
            existingContrat.setEtat(newContrat.getEtat());
            existingContrat.setInstructor(newContrat.getInstructor());
            existingContrat.setSalaire(newContrat.getSalaire());

            // Enregistrez les modifications dans la base de données en utilisant le contratRepository
            return contratRepository.save(existingContrat);
        } else {
            // Contrat non trouvé, vous pouvez choisir de retourner null ou lever une exception
            return null;
        }
    }




    @Override
    public Contrat getContratById(long idContrat) {
        {
            return contratRepository.findById(idContrat).orElse(null);
        }


    }


    public String deleteContrat(long idContrat) {
        if (contratRepository.findById(idContrat).isPresent()) {
            contratRepository.deleteById(idContrat);
            return "contrat supprimé";
        } else
            return "contrat non supprimé";
    }
}
