package presentation.Model;

public class Technologie {
   private String id,nom;

public Technologie(String id, String nom) {
	super();
	this.id = id;
	this.nom = nom;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}
   
}
