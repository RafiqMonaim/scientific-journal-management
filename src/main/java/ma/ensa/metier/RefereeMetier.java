package ma.ensa.metier;

import javax.transaction.Transactional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ma.ensa.dao.ArticleAuteurRepository;
import ma.ensa.dao.ArticleRepository;
import ma.ensa.dao.AuteurRepository;
import ma.ensa.dao.CommentaireRepository;
import ma.ensa.dao.RefereeRepository;
import ma.ensa.dao.RevueRepository;
import ma.ensa.entities.Article;
import ma.ensa.entities.ArticleQualifie;
import ma.ensa.entities.ArticleState;
import ma.ensa.entities.Commentaire;
import ma.ensa.entities.Referee;
import ma.ensa.exceptions.BadRequestException;
import ma.ensa.security.dao.AppUserRepository;
import ma.ensa.security.entities.Roles;
import ma.ensa.security.service.AccountService;

@Service
@Transactional
public class RefereeMetier {
	@Autowired 
	ArticleRepository articleRepository;
	@Autowired 
	AuteurRepository auteurRepository;
	@Autowired
	CommentaireRepository commentaireRepository;
	@Autowired
	RefereeRepository refereeRepository;
	@Autowired
	RevueRepository revueRepository;
	@Autowired
	ArticleAuteurRepository articleAuteurAssoRepository;
	@Autowired 
	ArticleMetier articleMetier;
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	AccountService accountService;
	
	public Referee getReferee(String refereeUsrName) {
		if (refereeRepository.findByUsername(refereeUsrName) == null) {
			throw new BadRequestException("we could not find the referee");
		}else {
			return refereeRepository.findByUsername(refereeUsrName);
		}
	}
	
	public void refereetoArticle(String refereeUsrName, Long articleID) {
		Article article = articleMetier.getArticle(articleID);
		Referee referee = getReferee(refereeUsrName);
		article.AjouterReferee(referee);
		article.setArticleState(ArticleState.EVALUATION);
		articleRepository.save(article);
	}
	public Referee ajouterReferee(String usrName, String nom, String prenom, String specialite) {
		Referee referee = new Referee(usrName, nom, prenom, specialite);
		if (appUserRepository.findByUsername(referee.getUsername()) == null) {
			throw new BadRequestException("Cannot login");
		}	
		if (refereeRepository.findByUsername(referee.getUsername()) == null) {
			accountService.addRoleToUser(usrName, Roles.REFEREE);
		}else {
			throw new BadRequestException("there's already a referee"); 
		}
		return refereeRepository.save(referee);
	}
	
	public String articleManuscrit(Long articleID) {
		return articleMetier.getArticle(articleID).getContenu();
	}
	public Commentaire articleEvaluation(Long articleID, String refereeUsrName, ArticleQualifie qualification, String remarque) {
		Article article = articleMetier.getArticle(articleID);
		Referee referee = getReferee(refereeUsrName);
		if (article.getReferees().contains(referee)) {
			Commentaire commmentaire = new Commentaire(referee, article, remarque, qualification);
			return commentaireRepository.save(commmentaire);
		}else {
			throw new BadRequestException("you are not referee to this article");
		}
		
	}
}
	
