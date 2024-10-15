package presentation.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.GestionNotifications;
import metier.GestionProfile;
import presentation.Model.Developeur;
import presentation.Model.Technologie;
import presentation.Model.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class Profile
 */
@WebServlet({"/ProfileDevloppeur","/ajouterTechnologie"})
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GestionProfile gestionProfile=new GestionProfile();
    private GestionNotifications gestionNotifications=new GestionNotifications();
    private User user=new User(0,"");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		System.out.println("dcd ZSSS2000 : ");
		String idDev=request.getParameter("idDev");
		int nbrNotif=gestionNotifications.nbrNotif(idDev);
		System.out.println("dcd Zhmz : "+idDev);
		Developeur developeur=gestionProfile.getProfile(idDev);
		ArrayList<Technologie> listTechnologies=gestionProfile.listTechnologie();
		request.setAttribute("listTech",listTechnologies);
		request.setAttribute("developeurs", developeur);
		request.setAttribute("nbr",nbrNotif);
		request.setAttribute("id",idDev);
		request.getRequestDispatcher("/interfacesGraphique/profileDevelopeur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("user",this.user);
		String path=request.getServletPath();
		String idDev=request.getParameter("idDev");
		int nbrNotif=gestionNotifications.nbrNotif(idDev);
		if(path.equals("/ajouterTechnologie")) {		
			String tech=request.getParameter("tech");
			if(gestionProfile.addTechnologieDev(idDev,tech)){
				Developeur developeur=gestionProfile.getProfile(idDev);
				ArrayList<Technologie> listTechnologies=gestionProfile.listTechnologie();
				request.setAttribute("listTech",listTechnologies);
				request.setAttribute("developeurs", developeur);
				request.setAttribute("id",idDev);
				request.setAttribute("nbr",nbrNotif);
				request.getRequestDispatcher("/interfacesGraphique/profileDevelopeur.jsp").forward(request, response);
			}else {
				System.out.println("erreur lors insertion");
			}
		}		
	}
}
