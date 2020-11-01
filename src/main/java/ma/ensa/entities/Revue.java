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
public class Revue {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String politique;
	
	
	@OneToMany(mappedBy = "revue")
	private List<ComiteMembre> membresComite;
	@OneToMany(mappedBy = "revue")
	private List<Article> articles;
	
	
	public Revue(String politique) {
		super();
		this.politique = politique;
	}
}
