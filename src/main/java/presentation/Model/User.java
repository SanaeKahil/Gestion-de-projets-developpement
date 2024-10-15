package presentation.Model;

public class User  extends Personne{
	private String role,email,motPass;
	protected int id;
	public User(int id,String nom, String prenom) {
		super(id, nom,prenom);
		this.id=id;
	}	
	public User(int id, String role) {
		super(id);
		this.role = role;
		this.id = id;
	}


	public User(int id, String nom, String prenom, String role) {
		super(id, nom, prenom);
		this.role = role;
		
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotPass() {
		return motPass;
	}

	public void setMotPass(String motPass) {
		this.motPass = motPass;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
