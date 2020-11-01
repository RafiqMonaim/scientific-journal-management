package ma.ensa.web;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.entities.Revue;
import ma.ensa.metier.RevueMetier;



@RestController
public class RevueRestService {
	@Autowired
	RevueMetier revueMetier;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/revues/{revueID}", method=RequestMethod.GET)
	public Revue getRevue(@PathVariable Long revueID){
		return revueMetier.getRevue(revueID);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/revues/{revueID}/comite", method=RequestMethod.POST)
	public void addComiteMembre(
			@PathVariable Long revueID,
			@RequestParam(name = "usrName", required = false) String usrName,
			@RequestParam(name = "nom", required = false) String nom,
			@RequestParam(name = "prenom", required = false) String prenom
			) {
		revueMetier.addComiteMembre(usrName, nom, prenom, revueID);
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/revues", method=RequestMethod.POST)
	public Revue addRevue(@RequestParam(name = "politique", required = false) String politique) {
		return revueMetier.addRevue(politique);
	}
	
	
	
	
}
