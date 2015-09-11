package fil.iagl.iir.entite;

public class Utilisateur {

	private Integer id;

	private String nom;

	private String password;

	private String mail;
	
	
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", username=" + nom + ", password=" + password + "]";
	}

}
