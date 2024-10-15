package presentation.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.AuthMetier;
import presentation.Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthMetier authMetier;
	
	public Login() {
		super();
		authMetier=new AuthMetier();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// response.sendRedirect("login.jsp");
		
	}

	
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //Récuperer email et le mot de passe
		 String email = request.getParameter("email");
	     String motDePasse = request.getParameter("motDePasse");
         System.out.print(email);
	    if (email == null || email.isEmpty() || motDePasse == null || motDePasse.isEmpty()) {
	    	//Afficher message d'erreur
	    		//  messageErreur(response);
               //response.sendRedirect("/login");
	    } else {
	    	     User userExiste=authMetier.authenticationUser(email,motDePasse);
	             if (userExiste!=null) {  
	            	 //session
	       		     HttpSession session = request.getSession();
	       		     User user=authMetier.InfosPersonne(userExiste.getId());
	       		     user.setRole(userExiste.getRole());
	       		     user.setId(userExiste.getId());
	       		     //System.out.println("le nom :"+user.getNom());
	       		     session.setAttribute("user", user);
	       		     System.out.println("id : : : "+user.getId()+" : "+user.getRole());
	            	 //
	            	 if(userExiste.getRole().equals("Directeur")) {
	            		 response.sendRedirect("/GestionProjets/ajouterProjet");
	            	 }
	            	 if(userExiste.getRole().equals("ChefProjet")){
	            		  String role=userExiste.getRole();
	            		  int  idUser=userExiste.getId();
	            		 response.sendRedirect("/GestionProjets/PageChefProjets?id="+idUser+"&etat=projet");
	            		 
	             	 }
	            	 if(userExiste.getRole().equals("Devloppeur")){
	            		 System.out.print("dev ox"+user.getId());
	            		 response.sendRedirect("/GestionProjets/ProfileDevloppeur?idDev="+userExiste.getId()+"");
	            	 }
	             	
	             } else {    
	            	 // Les informations d'identification sont incorrectes            
	                // response.sendRedirect("/login");
	            	// messageErreur(response);
	             }
	            }
	 }
	  

	     
	}


