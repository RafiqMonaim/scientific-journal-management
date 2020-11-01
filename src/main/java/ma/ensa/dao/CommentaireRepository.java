package ma.ensa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import ma.ensa.entities.Article;
import ma.ensa.entities.Commentaire;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long>{
	List<Commentaire> findByArticle(Article article);
}
