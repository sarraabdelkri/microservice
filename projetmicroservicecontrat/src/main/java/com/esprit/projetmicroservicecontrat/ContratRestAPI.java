package com.esprit.projetmicroservicecontrat;

import com.esprit.projetmicroservicecontrat.entites.Contrat;
import com.esprit.projetmicroservicecontrat.services.ContratService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/contrats")
public class ContratRestAPI {
	private String title = "Hello, I'm the candidate Microservice";
	@Autowired
	private ContratService contratService;
	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println(title);
		return title;
	}
	@PostMapping
	@RequestMapping(value = "/user")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Contrat> createContrat(@RequestBody Contrat contrat, KeycloakAuthenticationToken auth) {
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("user");

		if (hasUserRole) {
			return new ResponseEntity<>(contratService.addContrat(contrat), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	@PutMapping(value = "/{idContrat}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Contrat> updateContrat(@PathVariable(value = "idContrat") long idContrat,
												   @RequestBody Contrat contrat){
		return new ResponseEntity<>(contratService.updateContrat(idContrat,contrat), HttpStatus.OK);
	}
	@DeleteMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteContrat(@PathVariable(value = "idContrat") long idContrat, KeycloakAuthenticationToken auth){
		KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) auth.getPrincipal();
		KeycloakSecurityContext context = principal.getKeycloakSecurityContext();
		boolean hasUserRole = context.getToken().getRealmAccess().isUserInRole("admin");
		if (hasUserRole) {
			return new ResponseEntity<>(contratService.deleteContrat(idContrat), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
