package presentation.Model;

public class Tache {
  private String descriptiontache,pourcentage,tacheID;

public Tache(String descriptiontache, String pourcentage, String tacheID) {
	super();
	this.descriptiontache = descriptiontache;
	this.pourcentage = pourcentage;
	this.tacheID = tacheID;
}

public String getDescriptiontache() {
	return descriptiontache;
}

public void setDescriptiontache(String descriptiontache) {
	this.descriptiontache = descriptiontache;
}

public String getPourcentage() {
	return pourcentage;
}

public void setPourcentage(String pourcentage) {
	this.pourcentage = pourcentage;
}

public String getTacheID() {
	return tacheID;
}

public void setTacheID(String tacheID) {
	this.tacheID = tacheID;
}

@Override
public String toString() {
	return "Tache [descriptiontache=" + descriptiontache + ", pourcentage=" + pourcentage + ", tacheID=" + tacheID
			+ "]";
}
  
}
