package presentation.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.GestionProjet;
import presentation.Model.ChefProjet;
import presentation.Model.Client;
import presentation.Model.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

/**
 * Servlet implementation class Projet
 */
@WebServlet({"/ajouterProjet","/consulterProjet","/verfieProjet","/modifierProjet"})
public class Projet extends HttpServlet {
	private static final long serialVersionUID = 1L;     
    /**
     * @see HttpServlet#HttpServlet()
     */
	private ArrayList<User> listCHef;
	private GestionProjet gestionProjet;
	private User user=new User(0,"");
    public Projet() {
    	listCHef=new ArrayList<User>();
    	gestionProjet=new GestionProjet();

        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
 	    System.out.println("tst" + path);
 	    HttpSession session = request.getSession();
 	    this.user=(User) session.getAttribute("user");
		request.setAttribute("user",this.user);
 	    listCHef.clear();
		for (User user1 : gestionProjet.listeChefProjet()) {
 				listCHef.add(user1);
 				System.out.println(user1.getId() + "   "+user1.getNom()+"   "+user1.getPrenom());
 			}
 		  request.setAttribute("listCHef", listCHef);
 		  request.setAttribute("listProjet",gestionProjet.listeProjet());
 		  request.setAttribute("succes", 2);
 		  ArrayList<Client> listCLi=gestionProjet.listClient();
 		  request.setAttribute("listClient",listCLi);
 	    if(path.equals("/consulterProjet")) { 
// 	    	Boolean isModified=
 	    	request.setAttribute("listProjet",gestionProjet.listeProjet());
 		    request.getRequestDispatcher("/interfacesGraphique/consulterProjet.jsp").forward(request, response);
 	    }
 	    else {
 	    	request.getRequestDispatcher("/interfacesGraphique/ajouterProjet.jsp").forward(request, response);
 	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user",this.user);
		String path=request.getServletPath();
	 	request.setAttribute("listCHef", gestionProjet.listeChefProjet());
		if(path.equals("/ajouterProjet")) {
		 String nomPrj=request.getParameter("nom");
	     String description= request.getParameter("description");
	     String dateDebut=request.getParameter("dateDemarage");
	     String dateFin=request.getParameter("dateLivraison");
	     String cin= request.getParameter("cin");
	     String nomCli= request.getParameter("nomCli");
	     String prenomCli= request.getParameter("prenomCli");
	     String telephone=request.getParameter("telephone");
	     String nomchefPrejet=request.getParameter("nomChefProjet");
	     ChefProjet chefProjet=new ChefProjet(Integer.parseInt(nomchefPrejet),"", ""); 
	     SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	     int durre=0;
	     try {
			Date dateD=dateFormat.parse(dateDebut);
			Date dateF=dateFormat.parse(dateFin);
			durre=(int) (dateF.getTime()-dateD.getTime())/(24*60*60*1000);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}		
	     System.out.print(chefProjet.getId());
	     Client client=new Client(1,nomCli, prenomCli, cin);
	     presentation.Model.Projet projet=new presentation.Model.Projet(nomPrj,description, dateDebut, dateFin,durre,"0",chefProjet,client);
	     if(gestionProjet.existeProjet(projet)){
	    	 System.out.println("existe");
	     }
	     else {
	 		request.setAttribute("listCHef", gestionProjet.listeChefProjet());
	 	    request.setAttribute("listProjet",gestionProjet.listeProjet());
	 		ArrayList<Client> listCLi=gestionProjet.listClient();
	 		request.setAttribute("listClient",listCLi);
	    	if(gestionProjet.addProjet(projet))
	    	    request.setAttribute("succes",1);
	    	else
	    		request.setAttribute("succes",0);
	    	request.getRequestDispatcher("/interfacesGraphique/ajouterProjet.jsp").forward(request, response);
	     }
	}else if(path.equals("/consulterProjet")) {
		String operation=request.getParameter("operation");
		System.out.println(path+" : "+operation);
		String nomProjet=request.getParameter("projet");
		request.setAttribute("succes",10);
    	request.setAttribute("listProjet",gestionProjet.listeProjet());
		if(operation.equals("supprimer"))
			gestionProjet.removeProjet(nomProjet);
		else if(operation.equals("modifer")) {
			 String nomPrj=request.getParameter("nom");
		     String description= request.getParameter("description");
		     String dateDebut=request.getParameter("dateDemarage");
		     String dateFin=request.getParameter("dateLivraison");
		     String cin= request.getParameter("cin");
		     String nomCli= request.getParameter("nomCli");
		     String prenomCli= request.getParameter("prenomCli");
		     String telephone=request.getParameter("telephone");
		     String nomchefPrejet=request.getParameter("nomChefProjet");
		     String idPrj=request.getParameter("idPrj");
		     int idCli=Integer.parseInt(request.getParameter("idCli"));
		     ChefProjet chefProjet=new ChefProjet(Integer.parseInt(nomchefPrejet),"", ""); 
		     SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		     int durre=0;
		     try {
				Date dateD=dateFormat.parse(dateDebut);
				Date dateF=dateFormat.parse(dateFin);
				durre=(int) (dateF.getTime()-dateD.getTime())/(24*60*60*1000);
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}		
		     Client client=new Client(idCli,nomCli, prenomCli, cin);
		     client.setTelephone(telephone);
		     presentation.Model.Projet projet=new presentation.Model.Projet(nomPrj,description, dateDebut, dateFin,durre,idPrj,chefProjet,client);
		     gestionProjet.modifierProjet(projet);
		}	
 	    	request.setAttribute("listProjet",gestionProjet.listeProjet());
		    request.getRequestDispatcher("/interfacesGraphique/consulterProjet.jsp").forward(request, response);
		}
	}	
 }
