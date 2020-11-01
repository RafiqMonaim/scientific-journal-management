package ma.ensa.web.visualisation;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.ensa.entities.Auteur;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ArticleShortInfos {
	private String resume;
	private String motCle;
	private List<Auteur> coAuteurs;
	private Auteur auteurExact;
	
}
