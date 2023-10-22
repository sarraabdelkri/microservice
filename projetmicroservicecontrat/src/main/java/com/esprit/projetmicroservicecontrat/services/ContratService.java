package com.esprit.projetmicroservicecontrat.services;

import com.esprit.projetmicroservicecontrat.entites.Contrat;

import java.util.List;

public interface ContratService {
    List<Contrat> getAllContrats();

    Contrat addContrat(Contrat contrat);

    Contrat updateContrat(long idContrat,Contrat contrat);


    Contrat getContratById(long idContrat);

    String deleteContrat(long idContrat);
}
