package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionNotificationData {

	Connexion cnx;
	public GestionNotificationData() {
		cnx=new Connexion();
		cnx.connect();
	}
	public ResultSet getNotification(String idUser) throws SQLException {
		String req="SELECT * FROM `notifications` WHERE UserID=?";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,idUser);
		return st.executeQuery();
	}
	public ResultSet getNbrNotif(String idDev) throws SQLException {
		String req="select COUNT(NotificationsID) as nbr FROM notifications WHERE isVue=0 and UserID=?";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,idDev);
		return st.executeQuery();
	}
	public void marquerVue(String idNot) throws SQLException {
		String req="UPDATE notifications SET isVue =1 WHERE NotificationsID =?";
		PreparedStatement st=cnx.Cnx.prepareStatement(req);
		st.setString(1,idNot);
		st.executeUpdate();
	}

}
