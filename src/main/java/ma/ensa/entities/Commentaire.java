package ma.ensa.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Commentaire {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String remarque;
	@Enumerated(EnumType.STRING)
	private ArticleQualifie qualification;
	
	@ManyToOne
	private Referee referee;
	
	@ManyToOne
	private Article article;
	
	

	public Commentaire(Referee referee, Article article, String remarque, ArticleQualifie qualification) {
		super();
		this.referee = referee;
		this.article = article;
		this.remarque = remarque;
		this.qualification = qualification;
	}

}
