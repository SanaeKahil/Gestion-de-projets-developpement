package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jakartaee.commons.lang3.ObjectUtils.Null;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Result;

import presentation.Model.Client;
import presentation.Model.Projet;

public class GestionProjetData {
	Connexion cnx;
	public GestionProjetData() {
		cnx=new Connexion();
		cnx.connect();
	}
	public ResultSet listChefProjet() throws SQLException {
		String req="SELECT * FROM personne \r\n"
				+  " WHERE PersonneID IN (SELECT UserID FROM user WHERE Role=\"ChefProjet\")";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		return st.executeQuery();
	}
	public  ResultSet getInfoProjet(String nomPrj) throws SQLException {
		String req="SELECT projet.*,pC.Nom as nomClient,pC.Prenom as prenomClient,pC.PersonneID as idClient,client.Cin,pCh.Nom as nomChefProjet,pCh.Prenom as penomChefProjet ,pCh.PersonneID as idChefProjet\r\n"
				+ "FROM projet,personne pC,client,personne pCh,user\r\n"
				+ "WHERE projet.Nomprojet=?\r\n"
				+ "and client.ClientID=projet.ClientID\r\n"
				+ "and client.ClientID=pC.PersonneID\r\n"
				+ "and  projet.ChefProjetID=user.UserID\r\n"
				+ "and user.UserID=pCh.PersonneID;";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,nomPrj);
		return st.executeQuery();
	}
	public ResultSet listProjet() throws SQLException {
		String req="SELECT projet.*,pC.Nom as nomClient,pC.Prenom as prenomClient,pC.PersonneID as idClient,client.Cin,pC.Telephone as telephone,pCh.Nom as nomChefProjet,pCh.Prenom as penomChefProjet ,pCh.PersonneID as idChefProjet\r\n"
				+ "				FROM projet,personne pC,client,personne pCh,user\r\n"
				+ "				WHERE client.ClientID=projet.ClientID\r\n"
				+ "				and client.ClientID=pC.PersonneID\r\n"
				+ "				and  projet.ChefProjetID=user.UserID\r\n"
				+ "				and user.UserID=pCh.PersonneID";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		return st.executeQuery();
	}
	public void insertProjet(Projet projet) throws SQLException {
		String req="INSERT INTO `projet` (duree,Date_debut,Date_fin,Descprojet,Nomprojet,ChefProjetID,ClientID) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setLong(1,projet.getDurre());
		st.setString(2,projet.getDateDebut());
		st.setString(3,projet.getDateFin());
		st.setString(4,projet.getDesc()); 
		st.setString(5,projet.getNomPrj());
		st.setInt(6,projet.getChefProjet().getId());
		st.setInt(7,projet.getClient().getId());
		st.executeUpdate();
	}
	public ResultSet getInfoCLient(String cin) throws SQLException {
		// TODO Auto-generated method stub
		String req="SELECT personne.*,client.*\r\n"
				+  "FROM personne, client\r\n"
				+  "WHERE client.Cin=? \r\n"
				+  "and client.ClientID=personne.PersonneID";
	    PreparedStatement st=cnx.Cnx.prepareStatement(req);
	    st.setString(1,cin);
		return st.executeQuery();
	}
	public void inserClient(Client client) throws SQLException {
		String req="INSERT INTO client  VALUES (?,?)";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,client.getCIN());
		st.setString(2,client.getIdCli());
		st.executeUpdate();	
	}
	public void insererPersonn(Client client) throws SQLException {
		String req="INSERT INTO personne (Addresse,Nom,Prenom,Telephone) VALUES (?,?,?,?)";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,client.getAddress());
		st.setString(2,client.getNom());
		st.setString(3,client.getPrenom());
		st.setString(4,client.getTelephone());
		st.executeUpdate();	
	}
	public ResultSet getInfoLastPersonne() throws SQLException {
		String req="SELECT * FROM personne ORDER BY PersonneID DESC LIMIT 1";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		return st.executeQuery();
	}
	public ResultSet getLastClient() throws SQLException {
		String req="SELECT *FROM client ORDER BY ClientID DESC LIMIT 1";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		return st.executeQuery();
	}
	public ResultSet getListClient() throws SQLException {
		String req="SELECT personne.*,client.*\r\n"
				+  "FROM personne, client\r\n"
				+  "WHERE client.ClientID=personne.PersonneID";
	    PreparedStatement st=cnx.Cnx.prepareStatement(req);
		return st.executeQuery();
	}
	public void deleteProjet(String nomProjet) throws SQLException {
		String req="DELETE FROM projet WHERE Nomprojet=? ";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,nomProjet);
		st.executeUpdate();
	}
	public void updateProjet(Projet projet) throws SQLException {	
		String req="UPDATE projet SET "
					+ "duree=?,\r\n"
					+ "Date_debut=?,\r\n"
					+ "Date_fin=?,\r\n"
					+ "Descprojet=?,\r\n"
					+ "Nomprojet=?,\r\n"
					+ "ChefProjetID=?,\r\n"
					+ "ClientID=?,\r\n"
					+ "MethodologieID=null\r\n"
					+ "WHERE ProjetID=?";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setLong(1,projet.getDurre());
		st.setString(2,projet.getDateDebut());
		st.setString(3,projet.getDateFin());
		st.setString(4,projet.getDesc());
		st.setString(5,projet.getNomPrj());
		st.setLong(6,projet.getChefProjet().getId());
		st.setLong(7,projet.getClient().getId());
		st.setString(8,projet.getProjetID());
		System.out.println("idCl : "+projet.getClient().getId());
		st.executeUpdate();
	}
	public void updateClient(Client client) throws SQLException {
		String req="UPDATE personne SET "
					+ "Nom=?,\r\n"
					+ "Prenom=?,\r\n"
					+ "Telephone=?\r\n"
					+ "WHERE PersonneID=?";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,client.getNom());
		st.setString(2,client.getPrenom());
		st.setString(3,client.getTelephone());
		st.setLong(4,client.getId());
		System.out.println();
		System.out.println("req : "+ client.getTelephone());
        st.executeUpdate();		
	}
	public ResultSet getProfileDevelopeur(int id) {
		
		return null;
	}
}
