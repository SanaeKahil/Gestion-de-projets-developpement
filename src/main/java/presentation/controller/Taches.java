package presentation.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.GestionNotifications;
import metier.GestionsTaches;
import presentation.Model.Service;
import presentation.Model.Tache;
import presentation.Model.User;

import java.io.IOException;

/**
 * Servlet implementation class Taches
 */
@WebServlet({"/projetEnCours","/modifierAvancement"})
public class Taches extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GestionsTaches gestionsTaches=new GestionsTaches();
    private String idDev="";
    private GestionNotifications gestionNotifications=new GestionNotifications();
    private User user=new User(0,"");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Taches() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
 	    HttpSession session = request.getSession();
 	    this.user=(User) session.getAttribute("user");
		request.setAttribute("user",this.user);

		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.idDev=request.getParameter("idDev");
		presentation.Model.Projet projetEnCours=gestionsTaches.getProjetEnCours(idDev);
		System.out.println("Projet1 : "+projetEnCours);
		for (Service s : projetEnCours.getListService()) {
			System.out.println("S : "+s);
			for (Tache t : s.getListTaches())
				System.out.println("\t"+t);			
		}
		int nbrNotif=gestionNotifications.nbrNotif(this.idDev);
		request.setAttribute("idDev",this.idDev);
		request.setAttribute("nbr",nbrNotif);
		request.setAttribute("projet",projetEnCours);
	    request.getRequestDispatcher("/interfacesGraphique/consulterProjetDevelopeur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("user",this.user);

		String idTach=request.getParameter("idTache");
		String avancement=request.getParameter("avancement");
		gestionsTaches.updateAvancement(idTach,avancement);
		presentation.Model.Projet projetEnCours=gestionsTaches.getProjetEnCours(this.idDev);
		request.setAttribute("projet",projetEnCours);
		int nbrNotif=gestionNotifications.nbrNotif(this.idDev);
		request.setAttribute("idDev",this.idDev);
		request.setAttribute("nbr",nbrNotif);
		request.getRequestDispatcher("/interfacesGraphique/consulterProjetDevelopeur.jsp").forward(request, response);		
	}
}
