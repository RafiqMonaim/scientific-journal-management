package ma.ensa.metier;

import java.util.ArrayList;


import java.util.List;

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
import ma.ensa.entities.ArticleAuteur;
import ma.ensa.entities.ArticleState;
import ma.ensa.entities.Auteur;
import ma.ensa.entities.Commentaire;
import ma.ensa.exceptions.BadRequestException;
import ma.ensa.web.visualisation.ArticleAllInfos;
import ma.ensa.web.visualisation.ArticleShortInfos;
@Service
@Transactional
public class ArticleMetier {
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
	ArticleAuteurRepository articleAuteurRepository;
	@Autowired
	RevueMetier revueMetier; 
	public List<ArticleAllInfos> getArticlesPosted() {
		List<ArticleAllInfos> listArticles = new ArrayList<ArticleAllInfos>();
		List<Article> articles = articleRepository.getPostedArticles();
			for (Article article : articles) {
				ArticleAllInfos art = new ArticleAllInfos();
				List<ArticleAuteur> auteurs = articleAuteurRepository.findByArticle(article);
				List<Auteur> coAuteurs = new ArrayList<Auteur>();
					for (ArticleAuteur atr : auteurs) {
						if (atr.isAuteurExact()) {
							atr.getAuteur().setAuteurExact(true);
							art.setAuteurExact(atr.getAuteur());
						}else {
							coAuteurs.add(atr.getAuteur());
						}
					}
					art.setCoAuteurs(coAuteurs);
					art.setMotCle(article.getMotCle());
					art.setResume(article.getResume());
					art.setContenu(article.getContenu());
					listArticles.add(art);
				}
	 return listArticles;
	}
	
	
	
	public List<Commentaire> refereeRapport(Long articleID) {
		Article article = getArticle(articleID);
		return commentaireRepository.findByArticle(article);
	}
	
	
	public void addArticle(String resume,String motCle, String contenu, String auteurUsrName, Long RevueID) {
		Article article = new Article(resume, motCle, contenu, ArticleState.EVALUATION);
		if (auteurRepository.findByUsername(auteurUsrName) != null) {
			Auteur auteur = auteurRepository.findByUsername(auteurUsrName);
			article.setRevue(revueMetier.getRevue(RevueID));
			articleRepository.save(article); 
			ArticleAuteur articleAtrs = new ArticleAuteur(true, article, auteur);
			articleAuteurRepository.save(articleAtrs);
		}	
	}
	public void addArticlesAuteur(String articleAuteurUsrName,Long articleID) {
		Auteur coAtrs = auteurRepository.findByUsername(articleAuteurUsrName);
		Article article = getArticle(articleID);
		ArticleAuteur articleAtrs = new ArticleAuteur(false, article, coAtrs);
		articleAuteurRepository.save(articleAtrs);
	}
	public Article editArticle(Long id,String resume, String contenu, String motCle) {
			Article article = getArticle(id);
			if (resume != null) {
				article.setResume(resume);
			}
			if (motCle != null) {
				article.setMotCle(motCle);
			}
			if (contenu != null) {
				article.setContenu(contenu);
			}
			return article;
	}
	public  ArticleState articleSuivie(String userName) {
		Auteur auteur = auteurRepository.findByUsername(userName);
		List<ArticleAuteur> articleAtr = articleAuteurRepository.findByAuteur(auteur);
		if (!articleAtr.isEmpty()) {
			return articleAuteurRepository.findByAuteur(auteur).get(0).getArticle().getArticleState();
		}else {
			throw new BadRequestException("We could not find any articles associated to this author");
		}
		
	}
	public List<ArticleShortInfos> getInfosArticlesPosted(String auteurName, String motCle) {
		List<ArticleShortInfos> listArticles = new ArrayList<ArticleShortInfos>();
		List<Article> articles = articleRepository.getPostedArticles();
			for (Article article : articles) {
				ArticleShortInfos art = new ArticleShortInfos();
				List<ArticleAuteur> auteurs = articleAuteurRepository.findByArticle(article);
				List<Auteur> coAuteurs = new ArrayList<Auteur>();
					for (ArticleAuteur auteur : auteurs) {
						if (auteur.isAuteurExact()) {
							auteur.getAuteur().setAuteurExact(true);
							art.setAuteurExact(auteur.getAuteur());
						}else {
							coAuteurs.add(auteur.getAuteur());
						}
					}
					art.setCoAuteurs(coAuteurs);
					art.setMotCle(article.getMotCle());
					art.setResume(article.getResume());
				
				if (auteurName!=null ) {
					if (art.getAuteurExact().getNom().equals(auteurName) ) {
						listArticles.add(art);
					}
				}else if (motCle!=null) {
					if (art.getMotCle().contains(motCle)) {
						listArticles.add(art);
					}
				}else{
					listArticles.add(art);
				}
			}
			
		
	 return listArticles;
	}
	
	public Article articleQualifier(Long articleID, ArticleState qualification) {
		Article article = getArticle(articleID);
		article.setArticleState(qualification);
		return articleRepository.save(article);
	}
	public List<Auteur> getAuteursByArticle(Article article){
		List<ArticleAuteur> articlesAtrs = articleAuteurRepository.findByArticle(article);
		List<Auteur> auteurs = new ArrayList<Auteur>();
		for (ArticleAuteur atr : articlesAtrs) {
			if (atr.isAuteurExact()) {
				atr.getAuteur().setAuteurExact(true);
				auteurs.add(atr.getAuteur());
			}else {
				auteurs.add(atr.getAuteur());	
			}
		}
		return auteurs;
	}
	
	public Article getArticle(Long id) {
		if (articleRepository.findById(id).isPresent()) {
			return articleRepository.findById(id).get();
		}else {
			throw new BadRequestException("We could not find the article wanted");
		}
	}
	public Article deleteArticle(Long id) {
		Article article = getArticle(id);
		List<ArticleAuteur> articleAtrs = articleAuteurRepository.findByArticle(article);
		for (ArticleAuteur atr : articleAtrs) {
			articleAuteurRepository.deleteById(atr.getId());
		}
		if (article.getArticleState() == ArticleState.PUBLICATION) {
			 articleRepository.deleteById(id);
			 return article;
		}
		else {
			throw new BadRequestException("you can't delete this article");
		}
		
		
	}
	
	
}
