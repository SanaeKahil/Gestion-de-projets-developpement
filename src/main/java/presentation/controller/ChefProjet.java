package presentation.controller;






import presentation.Model.Developeur;
import presentation.Model.Methodologie;
import presentation.Model.Personne;
import presentation.Model.Projet;
import presentation.Model.Service;
import presentation.Model.Technologie;
import presentation.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.GestionNotifications;
import metier.GestionProjetChef;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import data.Auth;

/**
 * Servlet implementation class ChefProjet
 */
@WebServlet("/PageChefProjets")
public class ChefProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Projet>listeProjet;
	private ArrayList<Projet>listeProjetService;
	private ArrayList<Methodologie>listeMetho;
	private ArrayList<Technologie>listeTech;
	private ArrayList<Developeur>listeDevs;
	private ArrayList<Developeur>listeDevsEquipe;
	private GestionProjetChef gestionProjet;
	private Personne personne;
	private String[] tabTechnoId;
	private String idMethod;
	private Projet projet;
	private int idUser;
	private GestionNotifications gestionNotifications=new GestionNotifications();

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChefProjet() {
        super();
        listeProjet=new ArrayList<Projet>();
        listeProjetService=new ArrayList<Projet>();
        listeMetho=new ArrayList<Methodologie>();
        listeTech=new ArrayList<Technologie>();
        listeDevs=new ArrayList<Developeur>();
        listeDevsEquipe=new ArrayList<Developeur>();
        gestionProjet =new GestionProjetChef();
        
      
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 // response.getWriter().append("Served at: ").append(request.getContextPath());
		  HttpSession session = request.getSession();
		  String etat=request.getParameter("etat");
		  String idprojet=request.getParameter("projet");
	      session.setAttribute("idProjet", idprojet);
	      User user=(User) session.getAttribute("user");
	      String idU=Integer.toString(user.getId());
	      System.out.println("id :  : "+user.getId());
		 int nbrNotif=gestionNotifications.nbrNotif(idU);
			request.setAttribute("nbr",nbrNotif);
		  request.setAttribute("listNot",gestionNotifications.getAllNotification(idU));
		  if(etat.equals("projet")) {
		  String id=request.getParameter("id");
		   idUser = Integer.parseInt(id);
		   session.setAttribute("idUser", id);
		 
		 
		  System.out.println(" iciii"); 
		   for (Projet projet:gestionProjet.listeProjet(idUser)) { 
			   listeProjet.add(projet);
		      //System.out.println(" iciii"+projet.getNomProjet()+" "+projet.getDescProjet() +" "+projet.getDateD() +" "+projet.getDateF());
				/*
				 * request.setAttribute("nom", projet.getNomProjet());
				 * request.setAttribute("desc", projet.getDescProjet());
				 * request.setAttribute("dateD", projet.getDateD());
				 * request.setAttribute("dateF", projet.getDateF());
				 */
		      listeProjet = gestionProjet.listeProjet(idUser);
		      request.setAttribute("listeProjet", listeProjet);
		   } 
		 
		  // System.out.println("hna nom :"+ user.getNom());
		   request.setAttribute("user", user);
		   request.getRequestDispatcher("/interfacesGraphique/consulterProjetChef.jsp").forward(request,response);
	     }

			  if(etat.equals("metho")) {
				     listeTech = gestionProjet.ListeTechno();
				     request.setAttribute("listeTech", listeTech);
				     listeMetho = gestionProjet.listeMethod();
				     request.setAttribute("listeMetho", listeMetho);
				     //chercher projet
				     int idP=Integer.parseInt(idprojet);
				      projet=gestionProjet.infosProjet(idP);
				     //
				     request.setAttribute("projet", projet);
				     request.setAttribute("user", user);
				  request.getRequestDispatcher("/interfacesGraphique/ajouterMethTechn.jsp").forward(request,response);
			  }
			  
			  //Service
			  if(etat.equals("ProjetService")) {
				// int idUser=(int) session.getAttribute("idUser");
			   listeProjetService=gestionProjet.listeProjetService(idUser);
			   request.setAttribute("listeProjetService", listeProjetService);
			   request.setAttribute("user", user);
			  request.getRequestDispatcher("/interfacesGraphique/ajouterService.jsp").forward(request,response);
			  }
			  
			  if(etat.equals("Service")) {
				  String idProjetS=request.getParameter("projetService");
				  int  idProjetService=Integer.parseInt(idProjetS);
				  // liste dev de la table equipe
				  listeDevsEquipe=gestionProjet.listeDevloppeurEquipe(idProjetService);
				  //
				  request.setAttribute("listeDevsEquipe",listeDevsEquipe );	
				  request.setAttribute("idProjetService",idProjetS );	
				  request.setAttribute("user", user);
				  request.getRequestDispatcher("/interfacesGraphique/ServiceTache.jsp").forward(request,response);
			  }
			  
		      
		   }
		 

		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action=request.getParameter("action");
		 HttpSession session = request.getSession();
		  User user=(User) session.getAttribute("user");
	      String idU=Integer.toString(user.getId());
	      System.out.println(user);
		 int nbrNotif=gestionNotifications.nbrNotif(idU);
			request.setAttribute("nbr",nbrNotif);
		request.setAttribute("listNot",gestionNotifications.getAllNotification(idU));
		if(action.equals("test")) {
		listeDevs.clear();
		
		 idMethod=request.getParameter("idmethod");
		 System.out.println("Method "+idMethod);
		 tabTechnoId = request.getParameterValues("tabTechnoId[]");
		 
		
		if (tabTechnoId != null) {
		    for (String idTechno : tabTechnoId) {
		    	int id = Integer.parseInt(idTechno);		  
		    	List<Developeur> listeDev = gestionProjet.ListeDevloppeurs(id);
		        for( Developeur dev : listeDev) {
		        	 boolean exists = false;
		        	 for(Developeur devF :listeDevs) {
		        		 //modifier type
		        	                if(devF.getId()==dev.getId()) {
		        	                	System.out.println("dev :"+devF.getId());
		        	                    devF.getTechnologies().addAll(dev.getTechnologies());
		        	                    exists = true;
		        	                    break;
		        	                }
		        	            }
		        	            
		        	            if(!exists) {
		        	                listeDevs.add(new Developeur(dev.getId(),dev.getTechnologies(),dev.getNom(),dev.getPrenom()));
		        	            }
		        	        }
 
		        	}
		        }
		   for(Developeur dev :listeDevs) {
			   System.out.println(dev.getNom());
		   }
		//json
		   JSONObject jsonData = new JSONObject();	  
		try {
			jsonData.put("listeDevs", listeDevs);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		//System.out.println(tabIdTechno);
	    
		// Définir le type de contenu de la réponse comme "application/json"
		response.setContentType("application/json");
	        
		// Écrire les données JSON dans la réponse
		response.getWriter().write(jsonData.toString());

	     }
		
		
		
		 if(action.equals("insererEquipe")) {			
			 int resultat1=0;
			  int resultat2=0;
			  int resultat3=0;
			  int resultat4=0;
			  String  idProjet=(String) session.getAttribute("idProjet");
			  int idP = Integer.parseInt(idProjet);
			  
			  String[] tabDevEquipeId = request.getParameterValues("tabDevEquipeId[]");
			  String dateReunion=request.getParameter("dateReunion");
			  int idMethodologie = Integer.parseInt(idMethod);
			  if ( tabDevEquipeId!= null) {
				 for(String idDevEquipe :tabDevEquipeId) {
					  int idDev = Integer.parseInt(idDevEquipe);
					  // ajouter les dev de projet
					  System.out.println("dev equipe :"+idDevEquipe);
					   resultat1=gestionProjet.AddEquipe(idDev,idP);
					   // envoyer notification
					   String desc = "Bonjour, vous êtes affecté(e) au projet. Nous avons une réunion le " + dateReunion + ".";
					   System.out.println(desc);
					   //desc += dateReunion;
					   resultat3=gestionProjet.envoyerNotification(idDev,desc);
					   System.out.println("noptif "+resultat3);
					   
				}
			 }
			  resultat2=gestionProjet.ModifierProjet(idMethodologie, idP ,dateReunion);
			  for(String idTechnologie:tabTechnoId) {
					 int idTechno=Integer.parseInt(idTechnologie);
					 resultat4=gestionProjet.AddTechnoProjet(idP,idTechno);
				 }
			  Boolean status;
			  if(resultat1==1 && resultat2==1 && resultat3==1 && resultat4==1) {
				      status=true;
					
				}else {
					 status=false;
					
				}
			  
			   JSONObject jsonData = new JSONObject();	  
				try {
					jsonData.put("status", status);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				
				response.setContentType("application/json");
				response.getWriter().write(jsonData.toString());

		  
	  }
    // service 
		 if(action.equals("ServiceTache")) {
			String[] DevEquipe=request.getParameterValues("listeDev[]");
			String[] Taches=request.getParameterValues("taches[]");
			String service =request.getParameter("service");
			String idP=request.getParameter("idProjet");
			int idProjetService=Integer.parseInt(idP);
			int resultat1=0;
			int resultat2=0;
			Service serviceInfo;
			System.out.println(" test service tache ");
			System.out.println(" la description service :"+service);
			int c=DevEquipe.length;
			System.out.println("le nbr des taches :"+c);
			resultat1=gestionProjet.AddService(service,idProjetService);
			for(int i=0;i<c;i++) {
				String tache  = Taches[i];
				String equipe = DevEquipe[i];
				int idDev=Integer.parseInt(equipe);
				//Ajouter service
			
				//recuperer id service 
				serviceInfo=gestionProjet.infosService(service);
				String idService=serviceInfo.getServiceID();
				int idS=Integer.parseInt(idService);
				//Ajouter tache
				resultat2=gestionProjet.AddTache(idS,idDev,tache);
				//test
				System.out.println("la tache ["+i+"] est :"+tache);
				System.out.println("le dev ["+i+"] est :"+equipe);
			}
			
		 }
		 
	
	}
		 
	

	}


	
