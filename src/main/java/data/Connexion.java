package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
	Connection Cnx;
	String url,pilote;
	public Connexion() {
		try {

			pilote=new String("com.mysql.cj.jdbc.Driver");
			Class c=Class.forName(pilote);
			url = new String("jdbc:mysql://127.0.0.1:3306/gestionprojets");
			} 
		catch( ClassNotFoundException e) {
				System.err.println("Erreur lors du chargement du pilote : " + e.getMessage()); 
			}
		
		}
	public void connect(){
		try {
			Cnx = DriverManager.getConnection(url,"root","");
			System.out.println("ok");
	
		} catch (SQLException e) {
			System.err.println("connexion n'est pas etablie");
		} 
	}	
		public void disconnect() {			
			try {
				Cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();			
			}
		}
}