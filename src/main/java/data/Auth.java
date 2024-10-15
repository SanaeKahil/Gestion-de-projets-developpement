package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {

	// La fonction d'authentification pour les trois Users (Directeur, ChefProjet, Developpeur)
	private Connexion con;
	public Auth() {
		con = new Connexion();
 		con.connect();
		// TODO Auto-generated constructor stub
	}
	public ResultSet authenticationUser(String email, String motDePasse) {
		ResultSet res = null;
		String req = "SELECT * FROM user  WHERE Email = ? AND MotDepasse = ?  ";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	      
	        st.setString(1, email);  
	        st.setString(2, motDePasse); 
	        //st.setString(3, role); 
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public ResultSet chercherPersonne(int idPersonne) {
		ResultSet res = null;
		String req = "SELECT * FROM user u,personne p WHERE p.PersonneID=u.UserID AND u.UserID=?";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	         st.setInt(1, idPersonne);
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }

	
}