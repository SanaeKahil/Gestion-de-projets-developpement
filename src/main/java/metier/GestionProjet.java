package metier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import data.GestionProjetData;
import presentation.Model.ChefProjet;
import presentation.Model.Client;
import presentation.Model.Projet;
import presentation.Model.User;
public class GestionProjet implements GestionProjetInterface {
   private GestionProjetData prj;
   private Client client;
   public GestionProjet() {
	prj=new GestionProjetData();
}
public ArrayList<User> listeChefProjet(){
	ArrayList<User> listChef=new ArrayList<User>();
	try {
		ResultSet list=prj.listChefProjet();
		while (list.next()) {
		   listChef.add(new User(list.getInt("PersonneID"), list.getString("Nom"),list.getString("Prenom")));	
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.print(e.getMessage());
	}	   
	return listChef;
   }
public ArrayList<Projet> listeProjet(){
	ArrayList<Projet> listPrj=new ArrayList<Projet>();
	try {
		ResultSet list=prj.listProjet();
		while (list.next()) {
			Client clienttMP=new Client(list.getInt("idClient"), list.getString("nomClient"), list.getString("prenomClient"), list.getString("Cin"));
			clienttMP.setTelephone(list.getString("telephone"));
			System.out.println(clienttMP);
			listPrj.add(new Projet(list.getString("Nomprojet"),list.getString("Descprojet"),list.getString("Date_debut"),list.getString("Date_fin"),list.getInt("duree"),list.getString("ProjetID"),new ChefProjet(list.getInt("idChefProjet"), list.getString("nomChefProjet"),list.getString("penomChefProjet")),clienttMP));	
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.print(e.getMessage());
	}	   
	return listPrj;
   }
public boolean existeProjet(Projet projet) {
	try {
		ResultSet res=prj.getInfoProjet(projet.getNomPrj());
		return res.next();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}
public boolean addProjet(Projet projet) {
	try {
	  Client clientTmp=clientExiste(projet.getClient().getCIN());
	if(clientTmp!=null)//client deja existe
		projet.getClient().setId(clientTmp.getId());
	else {
		prj.insererPersonn(projet.getClient());
		ResultSet pers=prj.getInfoLastPersonne();
        pers.next();
        projet.getClient().setId(pers.getInt("PersonneID"));
		prj.inserClient(projet.getClient());
	}
		prj.insertProjet(projet);
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}	
}
public Client clientExiste(String cin) {
	System.out.print("new cin : "+cin);
	try {
		ResultSet res=prj.getInfoCLient(cin);
		if(res.next()) {
			client=new Client(res.getInt("ClientID"),res.getString("Nom"),res.getString("Prenom"), res.getString("Cin"));
			client.setAddress(res.getString("Addresse"));
			client.setTelephone(res.getString("Telephone"));
			System.out.println(res.getInt("ClientID"));
			return client;
		}else {
			System.out.println(res.getInt("ClientID"));
			return null;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
public ArrayList<Client> listClient() {
	// TODO Auto-generated method stub
	ArrayList<Client> listCliente=new ArrayList<Client>();

    try {
		ResultSet res=prj.getListClient();
		while (res.next()) {
			Client clientTmp=new Client(res.getInt("ClientID"),res.getString("Nom"),res.getString("Prenom"),res.getString("Cin"));
			clientTmp.setTelephone(res.getString("Telephone"));
			listCliente.add(clientTmp);
			System.out.println(clientTmp);
		}	    
	} catch (SQLException e) {
		e.printStackTrace();
	}
    return listCliente;
}
public boolean removeProjet(String nomProjet) {
	try {
		prj.deleteProjet(nomProjet);
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}
public boolean modifierProjet(Projet projet) {
	try {
		Client existClient=clientExiste(projet.getClient().getCIN());
		System.out.println("modifier2");
		if(existClient!=null) {
			   prj.updateClient(projet.getClient());
			   System.out.println(existClient.getCIN()+" : "+existClient.getId());
		}
		else {
			  prj.insererPersonn(projet.getClient());
			  ResultSet per=prj.getInfoLastPersonne();
			  per.next();
		      projet.getClient().setId(per.getInt("PersonneID"));
		      System.out.println("idCliLast" +  per.getInt("PersonneID"));
			  prj.inserClient(projet.getClient());
		}
		prj.updateProjet(projet);
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
}
}
