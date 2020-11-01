package ma.ensa.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.entities.ArticleQualifie;
import ma.ensa.entities.Referee;
import ma.ensa.metier.ArticleMetier;
import ma.ensa.metier.RefereeMetier;


@RestController
public class RefereeRestService {

	@Autowired 
	ArticleMetier articleMetier;
	@Autowired
	RefereeMetier refereeMetier;
	
	@PreAuthorize("hasAnyAuthority({'ADMIN','MEMBRE_COMITE'})")
	@RequestMapping(value="/referees", method=RequestMethod.POST)
	public void addReferee(
			@RequestParam String refereeUsrName,
			@RequestParam String nom,
			@RequestParam String prenom,
			@RequestParam String specialite
			) {
		refereeMetier.ajouterReferee(refereeUsrName, nom, prenom, specialite);
	}
	
	@PreAuthorize("hasAnyAuthority({'ADMIN','MEMBRE_COMITE'})")
	@RequestMapping(value="/referees/{refereeUsrName}", method=RequestMethod.GET)
	public Referee getReferee(@PathVariable String refereeUsrName){
		return refereeMetier.getReferee(refereeUsrName);
	}
	
	@PreAuthorize("hasAnyAuthority({'ADMIN','MEMBRE_COMITE'})")
	@RequestMapping(value="/articles/{articleID}/referees/{refereeUsrName}", method=RequestMethod.POST)
	public void refereetoArticle(
			@PathVariable Long articleID,
			@PathVariable String refereeUsrName) {
		refereeMetier.refereetoArticle(refereeUsrName, articleID);
	}
	
	@PreAuthorize("hasAuthority('REFEREE')")
	@RequestMapping(value="/articles/{articleID}/manuscrit", method=RequestMethod.GET)
	public String articleManuscrit(@PathVariable Long articleID) {
		return refereeMetier.articleManuscrit(articleID);
	}
	
	@PreAuthorize("hasAuthority('REFEREE')")
	@RequestMapping(value="/articles/{articleID}/referees/{refereeUsrName}/commentaires", method=RequestMethod.POST)
	public void evaluerArticle(
			@PathVariable Long articleID,
			@PathVariable String usernameReferee,
			@RequestParam(name = "qualification", required = false) ArticleQualifie qualification,
			@RequestParam(name = "remarque", required = false) String remarque
			){
		refereeMetier.articleEvaluation(articleID, usernameReferee, qualification, remarque);
	}
}
