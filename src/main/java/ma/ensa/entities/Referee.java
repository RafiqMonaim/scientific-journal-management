package ma.ensa.entities;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Referee {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String nom;
	private String prenom;
	private String specialite;
	
	
	@OneToMany(mappedBy = "referee")
	private List<Commentaire> commentaires;

	public Referee(String username, String nom, String prenom, String specialite) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
	}
	
}
