package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionProfileData {
	Connexion cnx;
	public GestionProfileData() {
		cnx=new Connexion();
		cnx.connect();
	}
	public ResultSet getProfileDevelopeur(String idDev) throws SQLException {
	String    req="SELECT personne.*,technologie.*\r\n"
				+ "FROM personne,technologie,user,devloppeur,technologiedevloppeur\r\n"
				+ "WHERE devloppeur.DevloppeurID=?\r\n"
				+ "and devloppeur.DevloppeurID=user.UserID\r\n"
				+ "AND user.UserID=personne.PersonneID\r\n"
				+ "AND technologiedevloppeur.TechnologieID=technologie.TechnologieID;";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);		
		st.setString(1,idDev);
		return st.executeQuery();
	}
	public void insertNewTechDev(String idDev, String tech) throws SQLException {
		String req="INSERT INTO technologiedevloppeur VALUES(?,?)";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,idDev);
		st.setString(2,tech);
		st.executeUpdate();
	}
	public ResultSet getInfoTechnologie(String tech) throws SQLException {
		String req="SELECT * FROM technologie WHERE Nomtech=?";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,tech);
		return st.executeQuery();
	}
	public ResultSet getListTechno() throws SQLException {
		String req="SELECT * FROM technologie ";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		return st.executeQuery();
	}
	public void insertNewTech(String tech) throws SQLException {
		String req="INSERT INTO technologie (Nomtech) VALUES (?)";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		System.out.println("mod");
		st.setString(1,tech);
		st.executeUpdate();		
	}

}
