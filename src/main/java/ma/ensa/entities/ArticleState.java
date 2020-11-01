package ma.ensa.entities;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @ToString
public enum ArticleState {
	 EVALUATION("Votre article est en cours d'évaluation"),
	 PUBLICATION("Nous sommes entrain de vérifier votre article afin de le publier"),
	 ACCEPTE ("Nous avons accepté votre article"),
	 ACCEPTE_AVEC_MODIFICATIONS ("Nous avons accepté votre article modifié"),
	 DEMANDE_DE_MODIFICATIONS("Nous vous demandons de modifier votre article"),
	 ACCEPTE_APRES_MODIFICATIONS("Article accepté après modifications apportées"),
	 POSTE("Nous avons posté votre article"),
	 REFUSE("Nous avons refusé votre article"),
	 REFUS_COHERENCE("Nous avons refusé votre article en raison de cohérence");
	 private String state = "";

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	 
	 
}
