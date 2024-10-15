package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.GestionProfileData;
import presentation.Model.Developeur;
import presentation.Model.Technologie;

public class GestionProfile {
	private GestionProfileData data=new GestionProfileData();
	public Developeur getProfile(String idDev) {
		try {
			ResultSet profile=data.getProfileDevelopeur(idDev);
			Developeur developeur=new Developeur(0, null, null,true);
			while(profile.next()) {
				developeur.setNom(profile.getString("Nom"));
				developeur.setPrenom(profile.getString("Prenom"));
				developeur.setTelephone(profile.getString("Telephone"));
				developeur.setAddress(profile.getString("Addresse"));
				developeur.setId(profile.getInt("PersonneID"));
				developeur.getListTechnologie().add(new Technologie(profile.getString("TechnologieID"),profile.getString("Nomtech")));				
			}
			return developeur;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}				
	}
	public boolean addTechnologieDev(String idDev, String tech) {
		try {
			ResultSet techExist=data.getInfoTechnologie(tech);
			String idTec="";
			if(techExist.next()) {
				idTec=techExist.getString("TechnologieID");
				data.insertNewTechDev(idDev,idTec);			
			}
			else {
				data.insertNewTech(tech);
				techExist=data.getInfoTechnologie(tech);
				techExist.next();
				idTec=techExist.getString("TechnologieID");
				data.insertNewTechDev(idDev,idTec);
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<Technologie> listTechnologie() {
		try {
			ResultSet listTech=data.getListTechno();
			ArrayList<Technologie> list=new ArrayList<Technologie>();
			while(listTech.next())
				list.add(new Technologie(listTech.getString("TechnologieID"),listTech.getString("Nomtech")));
		    return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
