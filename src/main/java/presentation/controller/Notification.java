package presentation.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.GestionNotifications;
import presentation.Model.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class Notification
 */
@WebServlet({"/notification","/notifisVue"})
public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */	
	private GestionNotifications gestionNotifications;
    public Notification() {
        super();
        this.gestionNotifications=new GestionNotifications();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idUser =request.getParameter("id");
 	    HttpSession session = request.getSession();
 	    User  user=(User)session.getAttribute("user");
		request.setAttribute("user",user);
		String idUs=Integer.toString(user.getId());
		ArrayList<presentation.Model.Notification> listNotification=gestionNotifications.getAllNotification(idUs);
		System.out.println(idUser);
		int nbrNotif=gestionNotifications.nbrNotif(idUs);
		request.setAttribute("nbr",nbrNotif);
		for (presentation.Model.Notification notification : listNotification) {
			System.out.println(notification);
		}
		request.setAttribute("idUser",idUs);
		request.setAttribute("listNot",listNotification);
		request.getRequestDispatcher("/interfacesGraphique/notifications.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
 	    HttpSession session = request.getSession();
 	    User  user=(User)session.getAttribute("user");
 	    request.setAttribute("user", user);
		String idNot=request.getParameter("idNotif");
		gestionNotifications.marquerVue(idNot);
		String idUser =request.getParameter("id");
		ArrayList<presentation.Model.Notification> listNotification=gestionNotifications.getAllNotification(idUser);
		request.setAttribute("idUser",idUser);
		System.out.println("id : "+idUser);
		int nbrNotif=gestionNotifications.nbrNotif(idUser);
		request.setAttribute("nbr",nbrNotif);
		for (presentation.Model.Notification notification : listNotification) {
			System.out.println(notification);
		}
		request.setAttribute("listNot",listNotification);
		request.getRequestDispatcher("/interfacesGraphique/notifications.jsp").forward(request, response);
	}

}
