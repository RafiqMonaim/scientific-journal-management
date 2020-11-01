package ma.ensa.entities;
import java.util.ArrayList;

import java.util.List;





import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Article {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String contenu;
	private String resume;
	private String motCle;
	@Enumerated(EnumType.STRING)
	private ArticleState articleState;
	
	/*@OneToMany
	private List<Utilisateur> user;*/
	
	@ManyToOne
	private Revue revue;

	@ManyToMany
	private List<Referee> referees;
	
	@OneToMany(mappedBy = "article")
	private List<Commentaire> commentaires;

	@OneToMany(mappedBy = "article")
	private List<ArticleAuteur> articlesAuteur;
	
	public Article(String contenu, String motCle, String resume , ArticleState articleState) {
		super();
		this.contenu = contenu;
		this.resume = resume;
		this.motCle = motCle;
		this.articleState = articleState;
	}
	public void AjouterReferee(Referee referee) {
		if (this.referees == null) {
			this.referees = new ArrayList<Referee>();
			this.referees.add(referee);
		}
		this.referees.add(referee);
	}

	public void AjouterCommentaire(Commentaire commentaire) {
		if (this.commentaires == null) {
			this.commentaires = new ArrayList<Commentaire>();
			this.commentaires.add(commentaire);
		}
		this.commentaires.add(commentaire);
	}
}
