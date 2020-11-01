package ma.ensa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ComiteMembre {
	@Id
	private String username;
	private String nom;
	private String prenom;
	
	@ManyToOne
	private Revue revue;
	public ComiteMembre(String username, String nom, String prenom) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
	}
}
