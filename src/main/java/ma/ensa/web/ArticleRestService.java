package ma.ensa.web;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.entities.Article;
import ma.ensa.entities.ArticleState;
import ma.ensa.entities.Auteur;
import ma.ensa.entities.Commentaire;
import ma.ensa.web.visualisation.ArticleAllInfos;
import ma.ensa.web.visualisation.ArticleShortInfos;
import ma.ensa.metier.ArticleMetier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
public class ArticleRestService {
	@Autowired
	ArticleMetier articleMetier;
	private static final Logger logger = LogManager.getLogger(ArticleRestService.class);
	
	@PreAuthorize("hasAuthority('AUTEUR')")
	@RequestMapping(value="/coAuteurs/{coAuteurUsrName}/articles/{articleID}", method=RequestMethod.POST)
	public void addArticlesAuteur(
			@PathVariable String coAuteurUsrName,
			@PathVariable(name = "articleID") Long articleID) {
		articleMetier.addArticlesAuteur(coAuteurUsrName, articleID);
	}
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/articles/{articleID}/auteurs", method=RequestMethod.GET)
	public List<Auteur> getAuteursByArticle(@PathVariable Long articleID){
		Article article = articleMetier.getArticle(articleID);
		return articleMetier.getAuteursByArticle(article);
	}

	@PreAuthorize("hasAuthority('AUTEUR')")
	@RequestMapping(value="/articles/{articleID}/commentaires", method=RequestMethod.GET)
	public List<Commentaire> refereeRapport(@PathVariable Long articleID) {
		return articleMetier.refereeRapport(articleID);
	}
	
	@PreAuthorize("hasAuthority('AUTEUR')")
	@RequestMapping(value="/articles/{id}", method=RequestMethod.DELETE)
	public void deleteArticle(@PathVariable Long id) {
		 Article article = articleMetier.deleteArticle(id);
		 logger.info("article " + article.getId() +" has been deleted");
		 logger.info(article.toString());
	}

	@PreAuthorize("hasAnyAuthority({'ADMIN','REFEREE', 'MEMBRE_COMITE', 'AUTEUR','USER'})")
	// on donne l'autorité à n'importe quel utilisateur d'accéder aux articles
	@RequestMapping(value="/articles", method=RequestMethod.GET)
	public List<ArticleAllInfos> getArticles() {
		return articleMetier.getArticlesPosted();
	}
	
	@RequestMapping(value="/articles/infos", method=RequestMethod.GET)
	public List<ArticleShortInfos> getSomeInfoOfArticlesPosted(
			@RequestParam(name = "nomAuteur", required = false) String nomAuteur,
			@RequestParam(name = "motCle",required = false) String motCle) {
		return articleMetier.getInfosArticlesPosted(nomAuteur, motCle);
	}
	@PreAuthorize("hasAuthority('MEMBRE_COMITE')")
	@RequestMapping(value="/articles/{articleID}", method=RequestMethod.POST)
	public Article articleQualifier(
			@PathVariable Long articleID,
			@RequestParam(name = "qualification", required = false) ArticleState qualification) {
		return articleMetier.articleQualifier(articleID, qualification);
	}

	@PreAuthorize("hasAuthority('AUTEUR')")
	@RequestMapping(value="/articles/{articleID}", method=RequestMethod.PUT)
	public void updateArticle(
			@PathVariable Long articleID,
			@RequestParam(name = "resume", required = false) String resume,
			@RequestParam(name = "contenu", required = false) String contenu,
			@RequestParam(name = "motCle", required = false) String motCle)
			 {
		Article articleModified = articleMetier.editArticle(articleID, resume, motCle, contenu);
		logger.info("article " + articleModified.getId() +" has been modified");
		logger.info(articleModified.toString());
	}
	@PreAuthorize("hasAuthority('AUTEUR')")
	@RequestMapping(value="/auteurs/{auteurUsrName}/articles", method=RequestMethod.POST)
	public void addArticle(
			@PathVariable String auteurUsrName,
			@RequestParam(name = "revueID") Long revueID,
			@RequestParam(name = "resume", required = false) String resume,
			@RequestParam(name = "contenu", required = false) String contenu,
			@RequestParam(name = "motCle", required = false) String motCle)
			 {
		articleMetier.addArticle(resume, motCle, contenu, auteurUsrName, revueID);
	}
	@PreAuthorize("hasAuthority('AUTEUR')")
	@RequestMapping(value="/auteurs/{usrName}/articles", method=RequestMethod.GET)
	public ArticleState articleState(@PathVariable String usrName) {
		return articleMetier.articleSuivie(usrName);
	}
}
