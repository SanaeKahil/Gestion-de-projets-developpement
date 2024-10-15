package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionTachesData {

	Connexion cnx;
	public GestionTachesData() {
		cnx=new Connexion();
		cnx.connect();
	}
	public ResultSet getProjetEnCours(String idDev) throws SQLException {
		String req="SELECT projet.*,service.*,tache.*\r\n"
					+ "FROM projet,service,tache\r\n"
					+ "WHERE tache.DevloppeurID=?\r\n"
					+ "AND tache.ServiceID=service.ServiceID\r\n"
					+ "AND service.ProjetID=projet.ProjetID;";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);	
		st.setString(1,idDev);
		return st.executeQuery();
	}
	public void updateAvancement(String idTach, String avancement) throws SQLException {
		String req="UPDATE tache SET Pourcentage=? WHERE TacheID=?";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);	
		st.setString(1,avancement);
		st.setString(2,idTach);
		st.executeUpdate();
	}
}
