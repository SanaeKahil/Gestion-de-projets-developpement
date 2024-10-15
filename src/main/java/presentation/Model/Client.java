package presentation.Model;

public class Client extends Personne{
    private String CIN,idCli;
	public Client(int id,String nom, String prenom,String CIN) {
		super(id,nom, prenom);
		this.CIN=CIN;
	}
	

	public String getIdCli() {
		return idCli;
	}


	@Override
	public String toString() {
		return "Client [CIN=" + CIN + ", idCli=" + idCli + ", nom=" + nom + ", prenom=" + prenom + ", telephone="
				+ telephone + ", address=" + address + ", id=" + id + ", getIdCli()=" + getIdCli() + ", getCIN()="
				+ getCIN() + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom() + ", getTelephone()="
				+ getTelephone() + ", getAddress()=" + getAddress() + ", getId()=" + getId() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


	public void setIdCli(String idCli) {
		this.idCli = idCli;
	}
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
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
