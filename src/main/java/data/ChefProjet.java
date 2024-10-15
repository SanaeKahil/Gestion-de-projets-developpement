package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ChefProjet {

	// La fonction d'authentification pour les trois Users (Directeur, ChefProjet, Developpeur)
	private Connexion con;
	public ChefProjet() {
		con = new Connexion();
 		con.connect();
		// TODO Auto-generated constructor stub
	}
	public ResultSet ListeProjet(int idChefProjet) {
		ResultSet res = null;
		String req = "SELECT * FROM projet WHERE ChefProjetID = ? ";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	      
	        st.setInt(1, idChefProjet);  
	       
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public ResultSet ListeMethodologies() {
		ResultSet res = null;
		String req = "SELECT * FROM methodologie ";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public ResultSet ListeTechnologies() {
		ResultSet res = null;
		String req = "SELECT * FROM technologie ";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public ResultSet ListeDevloppeurs(int idTechno) {
		ResultSet res = null;
		String req = " SELECT Distinct td.DevloppeurID,t.TechnologieID,t.NomTech ,p.Nom,p.Prenom FROM technologiedevloppeur td ,personne p,technologie t where td.TechnologieID="+idTechno+" and  td.DevloppeurID=p.PersonneID  and t.TechnologieID=td.TechnologieID";
		//String req = " SELECT td.DevloppeurID,td.TechnologieID ,p.Nom,p.Prenom FROM technologiedevloppeur td ,personne p where td.TechnologieID="+idTechno+" and  td.DevloppeurID=p.PersonneID ";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public ResultSet ChercherTechnologie(int idTechno) {
		ResultSet res = null;
		String req = " SELECT * FROM technologie t where t.TechnologieID="+idTechno+"";
		//String req = " SELECT td.DevloppeurID,td.TechnologieID ,p.Nom,p.Prenom FROM technologiedevloppeur td ,personne p where td.TechnologieID="+idTechno+" and  td.DevloppeurID=p.PersonneID ";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }

	public ResultSet ChercherProjet(int idProjet) {
		ResultSet res = null;
		String req = " SELECT * FROM projet  where ProjetID="+idProjet+"";
		
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public ResultSet ChercherMethodologie(int idMethodologie) {
		ResultSet res = null;
		String req = " SELECT * FROM methodologie  where MethodologieID="+idMethodologie+"";
		
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public int  insererEquipe(int idDev ,int idProjet) {
		int  res =0;
       String req = "INSERT INTO equipe (ProjetID , DevloppeurID ) VALUES (?, ?)";
		
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        st.setInt(1, idProjet);
	        st.setInt(2, idDev);
	        res = st.executeUpdate();
	        res=1;
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		     
    } 
	
	public int  ModifierProjetMethodologie(int idMethodologie ,int idProjet ,String dateR) {
		int res = 0 ;
       String req = "UPDATE projet SET MethodologieID = ? , date_Reunion=?  WHERE ProjetID = ?";
		
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	       // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	       // java.sql.Date sqlDate = new java.sql.Date(dateFormat.parse(dateR).getTime());
	        st.setInt(1, idMethodologie);
	        st.setString(2, dateR);
	        st.setInt(3, idProjet);
	        res = st.executeUpdate();
	        res=1;
	       
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		     
    } 
	
	public int  insererNotification(int idDev ,String dec) {
		int  res =0;
		 // LocalDate today = LocalDate.now();
	     //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	      //  String formattedDate = today.format(formatter);
       String req = "INSERT INTO notifications (UserID , Descrpnotif  ) VALUES (?, ? )";
		
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        st.setInt(1, idDev);
	        st.setString(2, dec);
	        res = st.executeUpdate();
	        res=1;
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		     
    } 

	
	public int  insererTechnoProjet(int idProjet ,int idTechno) {
		int  res =0;
		 // LocalDate today = LocalDate.now();
	     //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	      //  String formattedDate = today.format(formatter);
       String req = "INSERT INTO technologieprojet (ProjetID , TechnologieID ) VALUES (?, ? )";
		
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        st.setInt(1, idProjet);
	        st.setInt(2, idTechno);
	        res = st.executeUpdate();
	        res=1;
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		     
    } 
	
	public ResultSet ListeProjetService(int idChefProjet) {
		ResultSet res = null;
		String req = "SELECT * FROM projet WHERE ChefProjetID = ? And date_Reunion!=''";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	      
	        st.setInt(1, idChefProjet);  
	       
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public ResultSet DevloppeurEquipe(int idProjet) {
		ResultSet res = null;
		String req = "SELECT p.PersonneID,p.Nom,p.prenom FROM equipe e,personne p WHERE ProjetID=? and e.DevloppeurID=p.PersonneID";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	      
	        st.setInt(1, idProjet);  
	       
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public int  insererService(String ServiceDesc,int idP) {
		int  res =0;
		
       String req = "INSERT INTO service (Descrservice,ProjetID ) VALUES (?,?)";
		
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        st.setString(1, ServiceDesc);
	        st.setInt(2, idP);
	        res = st.executeUpdate();
	        res=1;
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		     
    }
	
	
	public ResultSet chercherService(String ServiceDesc) {
		ResultSet res = null;
		String req = "SELECT * FROM service WHERE  Descrservice= ? ";
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	      
	        st.setString(1, ServiceDesc);  
	       
	        res = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		      return res;
    }
	
	public int  insererTache(int idService ,int idDev ,String descTache) {
		int  res =0;
		 // LocalDate today = LocalDate.now();
	     //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	      //  String formattedDate = today.format(formatter);
       String req = "INSERT INTO tache (ServiceID,DevloppeurID ,Descriptiontache ) VALUES (?,?,?)";
		
		try {
	        PreparedStatement st = con.Cnx.prepareStatement(req);
	        st.setInt(1, idService);
	        st.setInt(2, idDev);
	        st.setString(3, descTache);
	        res = st.executeUpdate();
	        res=1;
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		     
    }
}