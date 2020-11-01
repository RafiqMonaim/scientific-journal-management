package ma.ensa.security.entities;

public enum Roles {
	  ADMIN("ADMIN"),
	  REFEREE ("REFEREE"),
	  AUTEUR("AUTEUR"),
	  MEMBRE_COMITE ("MEMBRE_COMITE_EDITORIAL"),
	  UTILISATEUR("UTILISATEUR");
	 private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	 Roles (String role){
		    this.role = role;
		  }
	  
	  
}
