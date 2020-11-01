package ma.ensa.entities;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ArticleAuteur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean isAuteurExact;
	
	@ManyToOne
	@JoinColumn(name="articleID")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name="auteurID")
	private Auteur auteur;
	
	public ArticleAuteur(boolean isAuteurExact, Article article, Auteur auteur) {
		super();
		this.article = article;
		this.auteur = auteur;
		this.isAuteurExact = isAuteurExact;
	}
}
