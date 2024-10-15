package presentation.Model;

import java.util.ArrayList;
import java.util.List;

public class Developeur extends User {
	ArrayList<Technologie> listTechnologie=new ArrayList<Technologie>();
	Boolean isDisponibe;
	int idDevloppeur;
	List <String> technologies;
	public Developeur(int id, String nom, String prenom, Boolean isDisponibe) {
		super(id, nom, prenom);
		this.isDisponibe=isDisponibe;
		// TODO Auto-generated constructor stub
	}
	public Developeur(int idDevloppeur, List<String> technologies, String nom, String prenom) {
		super(idDevloppeur,nom,prenom);
		this.idDevloppeur = idDevloppeur;
		this.technologies = technologies;
		this.nom = nom;
		this.prenom = prenom;
	}
	

	public List<String> getTechnologies() {
		return technologies;
	}
	public void setTechnologies(List<String> technologies) {
		this.technologies = technologies;
	}
	public Developeur(int idDevloppeur, String nom, String prenom) {
		super(idDevloppeur,nom,prenom);
		this.idDevloppeur = idDevloppeur;
		this.nom = nom;
		this.prenom = prenom;
	}
	public ArrayList<Technologie> getListTechnologie() {
		return listTechnologie;
	}
	public void setListTechnologie(ArrayList<Technologie> listTechnologie) {
		this.listTechnologie = listTechnologie;
	}
	
	@Override
	public String toString() {
		String tec="";
		for (Technologie technologie : listTechnologie)
			tec+=technologie.getNom();
		return "Developeur [listTechnologie=" +tec+ ", isDisponibe=" + isDisponibe + ", nom=" + nom
				+ ", prenom=" + prenom + ", telephone=" + telephone + ", address=" + address + ", id=" + id + "]";
	}	
}
