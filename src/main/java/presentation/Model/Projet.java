package presentation.Model;

import java.util.ArrayList;

import presentation.Model.Service;

public class Projet {
	private String nomPrj,desc,dateDebut,dateFin,projetID;
	int durre;
	private Client client;
	private ChefProjet chefProjet;
	private ArrayList<Service> service=new ArrayList<Service>();
	public Projet(String nomPrj, String desc, String dateDebut, String dateFin,int durre2,String projetID,ChefProjet chefPrejet, Client client) {
		super();
		this.nomPrj = nomPrj;
		this.desc = desc;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.client = client;
		this.chefProjet = chefPrejet;
		this.durre=durre2;
		this.projetID=projetID;
	}
	public Projet(String idProjet, String nomProjet, String descProjet, String dateD, String dateF) {
		super();
		this.projetID = idProjet;
		this.nomPrj = nomProjet;
		this.desc= descProjet;
		this.dateDebut= dateD;
		this.dateFin= dateF;
	}



	public Projet(String idProjet, String nomProjet) {
		super();
		this.projetID = idProjet;
		this.nomPrj = nomProjet;
	}

	public String getNomPrj() {
		return nomPrj;
	}
	public void setNomPrj(String nomPrj) {
		this.nomPrj = nomPrj;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public ChefProjet getChefProjet() {
		return chefProjet;
	}
	public void setChefProjet(ChefProjet chefProjet) {
		this.chefProjet = chefProjet;
	}
	public int getDurre() {
		return durre;
	}
	public void setDurre(int durre) {
		this.durre = durre;
	}
	public String getProjetID() {
		return projetID;
	}
	public void setProjetID(String projetID) {
		this.projetID = projetID;
	}
	public ArrayList<Service> getListService() {
		return service;
	}
	@Override
	public String toString() {
		return "Projet [nomPrj=" + nomPrj + ", desc=" + desc + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", projetID=" + projetID + ", durre=" + durre + "]";
	}	
	
}
