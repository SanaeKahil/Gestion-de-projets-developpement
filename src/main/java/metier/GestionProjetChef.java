package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.ChefProjet;	
import data.Auth;
import presentation.Model.Developeur;
import presentation.Model.Methodologie;
import presentation.Model.Personne;
import presentation.Model.Projet;
import presentation.Model.Service;
import presentation.Model.Technologie;

public class GestionProjetChef implements GestionProjetChefInterface {
	private ChefProjet chefProjet;
	private Auth auth;

	public GestionProjetChef() {
		super();
		chefProjet =new ChefProjet();
		auth =new Auth();
	}
	public ArrayList<Projet> listeProjet(int id){
		ArrayList<Projet> listeProjet=new ArrayList<Projet>();
		try {
			ResultSet rs=chefProjet.ListeProjet(id);
			while (rs.next()) {
				listeProjet.add(new Projet(rs.getString("ProjetID"),rs.getString("Nomprojet"),rs.getString("Descprojet"),rs.getString("Date_debut"),rs.getString("Date_Fin")));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return listeProjet;
	   }
	

	public ArrayList<Methodologie> listeMethod(){
		ArrayList<Methodologie> listeMethod=new ArrayList<Methodologie>();
		try {
			ResultSet rs=chefProjet.ListeMethodologies();
			while (rs.next()) {
				listeMethod.add(new Methodologie(rs.getString("MethodologieID"),rs.getString("Nommetho")));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return listeMethod;
	   }
	

	public ArrayList<Technologie> ListeTechno(){
		ArrayList<Technologie> listeTechno=new ArrayList<Technologie>();
		try {
			ResultSet rs=chefProjet.ListeTechnologies();
			while (rs.next()) {
				listeTechno.add(new Technologie(rs.getString("TechnologieID"),rs.getString("Nomtech")));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return listeTechno;
	   }
	
	// id techno tab
	
	public ArrayList<Developeur> ListeDevloppeurs(int idTechno){
		ArrayList<Developeur> listeDev=new ArrayList<Developeur>();
		
		try {
			ResultSet rs=chefProjet.ListeDevloppeurs(idTechno);
			while (rs.next()) {
				ArrayList<String> listeT=new ArrayList<String>();
				listeT.add(rs.getString("NomTech"));
				listeDev.add(new Developeur(rs.getInt("DevloppeurID"),listeT,rs.getString("Nom"),rs.getString("Prenom")));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		
		return listeDev;
	}
//supp
//	public Personne InfosPersonne(int id) {
//		Personne P = null;
//		try {
//			ResultSet rs=auth.chercherPersonne(id);
//			while (rs.next()) {
//				 P=new Personne(rs.getString("Nom"),rs.getString("Prenom"),rs.getInt("PersonneID"));	
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.print(e.getMessage());
//		}	   
//		return P;
//	}
//	
	public Technologie infosTechnologie(int id) {
		Technologie T = null;
		try {
			ResultSet rs=chefProjet.ChercherTechnologie(id);
			while (rs.next()) {
				 T= new Technologie(rs.getString("TechnologieID"),rs.getString("NomTech"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return T;
	}
	public Projet infosProjet(int idProjet) {
		Projet P = null;
		try {
			ResultSet rs=chefProjet.ChercherProjet(idProjet);
			while (rs.next()) {
				 P= new Projet(rs.getString("ProjetID"),rs.getString("Nomprojet"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return P;
	}
	public Methodologie infosMethodologie(int idMethodologie) {
		Methodologie M = null;
		try {
			ResultSet rs=chefProjet.ChercherMethodologie(idMethodologie);
			while (rs.next()) {
				 M= new Methodologie(rs.getString("MethodologieID"),rs.getString("Nommetho"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return M;
	}
	
	public int AddEquipe(int idDev ,int idProjet) {
		int rs=chefProjet.insererEquipe(idDev, idProjet);
		if(rs==1) {
			return 1;
		}
		return 0;	   
	}
	
	public int ModifierProjet(int idMethod ,int idProjet ,String dateR) {
		int rs=chefProjet.ModifierProjetMethodologie(idMethod, idProjet ,dateR);
		if(rs==1) {
			return 1;
		}
		return 0;	   
	}
	public int envoyerNotification(int idDev,String desc ) {
		int rs=chefProjet.insererNotification(idDev, desc);
		if(rs==1) {
			return 1;
		}
		return 0;	   
	}
	
	public int AddTechnoProjet(int idProjet,int idTechno) {
		int rs=chefProjet.insererTechnoProjet(idProjet, idTechno);
		if(rs==1) {
			return 1;
		}
		return 0;	   
	}
	public ArrayList<Projet> listeProjetService(int id){
		ArrayList<Projet> listeProjet=new ArrayList<Projet>();
		try {
			ResultSet rs=chefProjet.ListeProjetService(id);
			while (rs.next()) {
				listeProjet.add(new Projet(rs.getString("ProjetID"),rs.getString("Nomprojet"),rs.getString("Descprojet"),rs.getString("Date_debut"),rs.getString("Date_Fin")));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return listeProjet;
	   }
	
	public ArrayList<Developeur> listeDevloppeurEquipe(int idProjet){
		ArrayList<Developeur> listeDevloppeurEquipe=new ArrayList<Developeur>();
		try {
			ResultSet rs=chefProjet.DevloppeurEquipe(idProjet);
			while (rs.next()) {
				listeDevloppeurEquipe.add(new Developeur(rs.getInt("PersonneID"),rs.getString("Nom"),rs.getString("prenom")));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return listeDevloppeurEquipe;
	   }
	
	public int AddService(String descService,int idP) {
		int rs=chefProjet.insererService(descService,idP);
		if(rs==1) {
			return 1;
		}
		return 0;	   
	}

	public Service infosService(String ServiceDesc) {
		Service S = null;
		try {
			ResultSet rs=chefProjet.chercherService(ServiceDesc);
			while (rs.next()) {
				 S= new Service(rs.getString("ServiceID"),rs.getString("Descrservice"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return S;
	}
	public int AddTache(int idService ,int idDev ,String TacheDesc) {
		int rs=chefProjet.insererTache(idService,idDev,TacheDesc);
		if(rs==1) {
			return 1;
		}
		return 0;	   
	}
	
}


