package ma.ensa.entities;

import java.util.List;




import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Auteur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String username;
	private String nom;
	private String prenom;
	private String affiliation;
	@Transient  // pour que la propriété ne soit pas persistante dans la BD
	private boolean isAuteurExact;
	
	/*@ManyToOne
	private Revue revue; */
		
	@OneToMany(mappedBy = "auteur")
	private List<ArticleAuteur> articlesAuteur;
	
	/*@OneToOne
	private Role role;*/
	public Auteur(String username, String nom, String prenom, String affiliation) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.affiliation = affiliation;
	}
	
	
}
