package presentation.Model;

public abstract class Personne {
	protected String nom;
	protected String prenom,telephone,address;
	protected int id;
	public Personne(int id,String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.id=id;
	
	}
	
	public Personne(int idPersonne) {
		super();
		this.id= idPersonne;
	}


	public Personne(String nom, String prenom, int idPersonne) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.id= idPersonne;
	}


	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Personne(String nom, String prenom,String telephone,String address) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
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
	
	

}
