package ma.ensa.web.visualisation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class ArticleAllInfos extends ArticleShortInfos{
	private String contenu;
}
