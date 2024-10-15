package metier;
import java.util.ArrayList;
import presentation.Model.Projet;
public interface GestionProjetInterface {
	boolean addProjet(Projet projet);
	boolean modifierProjet(Projet projet) ;
	boolean removeProjet(String nomProjet);
	public ArrayList<Projet> listeProjet();
}
