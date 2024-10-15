package metier;

import java.sql.ResultSet;
import java.sql.SQLException;

import presentation.Model.Projet;
import presentation.Model.Service;
import presentation.Model.Tache;
import data.GestionTachesData;
public class GestionsTaches {
	private GestionTachesData data=new GestionTachesData();
	public Projet getProjetEnCours(String idDev) {
		Projet projetEnCours;
		try {
			ResultSet res=data.getProjetEnCours(idDev);
			res.next();
			projetEnCours=new Projet(res.getString("NomProjet"),res.getString("Descprojet"),res.getString("Date_debut"),res.getString("Date_fin"),res.getInt("duree"),res.getString("ProjetID"),null,null);
			res=data.getProjetEnCours(idDev);
			Service service;
			while(res.next()){
				boolean existe=false;
				service=new Service(res.getString("durreservice"),res.getString("Descrservice"),res.getString("ServiceID"));
					for (Service service1 : projetEnCours.getListService())
						if(service1.getServiceID().equals(service.getServiceID()))
							existe=true;
				if(!existe)
					projetEnCours.getListService().add(service);
			}
			String idS="";
			for (int i=0;i<projetEnCours.getListService().size();i++) {
				res=data.getProjetEnCours(idDev);
				while (res.next()) {
					idS=projetEnCours.getListService().get(i).getServiceID();
					if(res.getString("ServiceID").equals(idS)) {
						Tache t=new Tache(res.getString("Descriptiontache"), res.getString("Pourcentage"),res.getString("TacheID"));
						projetEnCours.getListService().get(i).getListTaches().add(t);				
					}
				}
			}
			System.out.println("gooood");
			return projetEnCours;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	public boolean updateAvancement(String idTach, String avancement) {
		try {
			data.updateAvancement(idTach,avancement);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
