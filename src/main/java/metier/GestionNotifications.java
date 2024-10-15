package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.GestionNotificationData;
import presentation.Model.Notification;

public class GestionNotifications {

	private GestionNotificationData data=new GestionNotificationData();
	public ArrayList<Notification> getAllNotification(String idUser) {
		try {
			ResultSet listNo=data.getNotification(idUser);
			ArrayList<Notification> list=new ArrayList<Notification>();
			while(listNo.next())
				list.add(new Notification(listNo.getString("UserID"),listNo.getString("NotificationsID"),listNo.getString("Datenotif"),listNo.getString("Descrpnotif"),listNo.getInt("isVue")));
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public int nbrNotif(String idDev) {
		try {
			ResultSet nbr=data.getNbrNotif(idDev);
			nbr.next();
			return nbr.getInt("nbr");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	public void marquerVue(String idNot) {
	    try {
			data.marquerVue(idNot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
