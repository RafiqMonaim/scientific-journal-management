package ma.ensa.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class RegistrationForm {
	
	public enum Role {
		  AUTEUR("AUTEUR"),
		  UTILISATEUR("UTILISATEUR");
		  private String role;

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		private Role(String role) {
			this.role = role;
		}
		  
	}
	private String username;
	private String nom;
	private String prenom;
	private String affiliation;
	private Role role;
	private String password;
	private String repassword;

}
