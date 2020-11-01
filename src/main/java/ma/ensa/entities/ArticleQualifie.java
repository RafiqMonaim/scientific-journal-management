package ma.ensa.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @ToString
public enum ArticleQualifie {
		ACCEPTE_AVEC_MODIFICATIONS ("Nous avons accepté votre article modifié"),	 
		ACCEPTE ("Nous avons accepté votre article"),
		REFUSE("Nous avons refusé votre article");
		 private String qualification;

		public String getQualification() {
			return qualification;
		}

		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
	   
}
