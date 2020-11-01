package ma.ensa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ma.ensa.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	@Query("select article from Article as article where  article.articleState ='POSTE'")
	List<Article> getPostedArticles();	
}
