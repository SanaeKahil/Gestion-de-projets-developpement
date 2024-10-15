<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="presentation.Model.Projet" %>
  <%@ page import="presentation.Model.User" %>
  <%@ page import="metier.GestionProjet" %>
    <%@ page import="java.util.ArrayList" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Consulter projet</title>

	<link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/sideBar.css">
	 <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/bootstrap.min.css">
	 <script src="http://localhost:8080/GestionProjets/js/datatables.min.js"></script> 
  	<script src="http://localhost:8080/GestionProjets/js/bootstrap.bundle.min.js"></script>
 	 <script src="http://localhost:8080/GestionProjets/js/jquery.js"></script>
  	<script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
  	<script src="http://localhost:8080/GestionProjets/js/ClassApp/GestionProjet.js"></script>
</head>

<body>
   <% User user= (User)request.getAttribute("user"); %>
  <div class="d-flex ">
    <!-- Debut Side Bare -->
    <%@ include file="../../Layout/SideBar.jsp" %>
    
     <!-- Fin SIde Bare -->
    <div class="contenue  flex-grow-1  mt-5 mb-5 ">
    <div class="card">
  <div class="card-header">
    Consulter projet-Ajouter Service 
  </div>
  <div class="card-body">
    <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
          
                <th>id</th>
                <th>Projet</th>
                <th>description</th>
                <th>date D�but</th>
                <th>date fin</th>
                <th>Service</th>
            </tr>
        </thead>
        <tbody>
       <% ArrayList<Projet> listeProjetService = (ArrayList<Projet>) request.getAttribute("listeProjetService"); %>
            <% for (Projet projet : listeProjetService) { %>
                <tr>
                     <td><%= projet.getProjetID() %></td>
                    <td><%= projet.getNomPrj() %></td>
                    <td><%= projet.getDesc() %></td>
                    <td><%= projet.getDateDebut() %></td>
                    <td><%= projet.getDateFin() %></td>
                    <td> <a href="/GestionProjets/PageChefProjets?projetService=<%= projet.getProjetID() %>&etat=Service" class= "btn btn-primary"> Ajouter </a></td>
                </tr>
            <% } %>
        </tbody>
     
    </table>
  </div>
</div>
     
  </div>
  <!-- </div> -->
  
    </div>
   
</body>
</html>