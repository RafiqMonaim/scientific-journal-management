package ma.ensa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import ma.ensa.entities.Article;
import ma.ensa.entities.ArticleAuteur;
import ma.ensa.entities.Auteur;

@Repository
public interface ArticleAuteurRepository extends JpaRepository<ArticleAuteur, Long> {
	List<ArticleAuteur> findByAuteur(Auteur auteur);
	List<ArticleAuteur> findByArticle(Article article);
	
}
