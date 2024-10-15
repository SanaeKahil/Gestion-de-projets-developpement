package metier;


import java.sql.ResultSet;
import java.sql.SQLException;



import data.Auth;
import presentation.Model.Personne;
import presentation.Model.User;



public class AuthMetier {
	private Auth auth;
//	private presentation.Model.User user;
	public AuthMetier() {
		auth=new Auth();
//		user=new presentation.Model.User(0, null, null);
	}
//	public presentation.Model.User authenticationUser(String email,String motPass,String role) {
	public User authenticationUser(String email,String motPass) {	
		User user = null;
		try {
			ResultSet res=auth.authenticationUser(email, motPass);
			while(res.next()) {
			 user=new User(res.getInt("UserID"),res.getString("Role"));
			// System.out.println("requete "+res.getInt("UserID"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
//			return user;
			
		}
		
		return user;
	}
    
	public User InfosPersonne(int id) {
		User user = null;
		try {
			ResultSet rs=auth.chercherPersonne(id);
			while (rs.next()) {
				 user=new User(rs.getInt("PersonneID"),rs.getString("Prenom"),rs.getString("Nom"),rs.getString("role"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		}	   
		return user;
	}
	
   
}
