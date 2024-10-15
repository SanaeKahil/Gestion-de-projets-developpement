package metier;

import java.util.ArrayList;

import presentation.Model.Developeur;
import presentation.Model.Methodologie;
import presentation.Model.Projet;
import presentation.Model.Service;
import presentation.Model.Technologie;

public interface GestionProjetChefInterface {
	public ArrayList<Projet> listeProjet(int id);
	public ArrayList<Methodologie> listeMethod();
	public ArrayList<Developeur> ListeDevloppeurs(int idTechno);
	public int AddEquipe(int idDev ,int idProjet);
	public int ModifierProjet(int idMethod ,int idProjet ,String dateR);
	public int AddTache(int idService ,int idDev ,String TacheDesc);
	public Service infosService(String ServiceDesc);
	public int AddService(String descService,int idP);
	public ArrayList<Developeur> listeDevloppeurEquipe(int idProjet);
	public ArrayList<Projet> listeProjetService(int id);
	public int AddTechnoProjet(int idProjet,int idTechno);
	public int envoyerNotification(int idDev,String desc );
	public Projet infosProjet(int idProjet);
	public Technologie infosTechnologie(int id);
	public ArrayList<Technologie> ListeTechno();
}
