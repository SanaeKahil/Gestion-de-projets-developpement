<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="presentation.Model.Projet" %>
 <%@ page import="presentation.Model.User" %>
 <%@ page import="metier.GestionProjet" %>
 <%@ page import="java.text.SimpleDateFormat" %>
 <%@ page import="java.util.ArrayList" %>
 <%@ page import="java.util.Date" %>
 <%@ page import="java.time.LocalDate"%>
 <%@ page import="java.time.format.DateTimeFormatter"%>
 <%@ page import="java.io.File" %>
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
      Consulter projet 
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
                <th>Options</th>
            </tr>
        </thead>
        <%-- <tbody>
            <tr>
                <td>1</td>
                <td> <%= request.getAttribute("nom") %> </td>
                <td><%= request.getAttribute("desc") %></td>
                <td><%= request.getAttribute("dateD") %> </td>
                <td><%= request.getAttribute("dateF") %> </td>
                <td>  
                   <a href="/GestionProjets/PageChefProjets?etat=metho" class="btn btn-sm btn-primary" > Consulter  </a>
                      
                 </td>
            </tr>
            
          
          
            
        </tbody> --%>
        <tbody>
       <% ArrayList<Projet> listeProjet = (ArrayList<Projet>) request.getAttribute("listeProjet"); %>
            <% for (Projet projet : listeProjet) { %>
                <tr>
                     <td><%= projet.getProjetID() %></td>
                    <td><%= projet.getNomPrj() %></td>
                    <td><%= projet.getDesc() %></td>
                    <td><%= projet.getDateDebut() %></td>
                    <td><%= projet.getDateFin() %></td>
                    <%
                    String pattern = "yyyy-MM-dd";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                    String dateF = projet.getDateFin(); // Utilisez la m�me variable "dateF" ici
                    LocalDate dateFin = LocalDate.parse(dateF, formatter); // Utilisez la m�me variable "dateFin" ici
                    LocalDate now = LocalDate.now();
                    int comparison = dateFin.compareTo(now);
                 	   System.out.println("comp date "+comparison);
                     %>
                    <td> <a href="/GestionProjets/PageChefProjets?projet=<%= projet.getProjetID() %>&etat=metho" class= "btn btn-primary <% if(comparison<0){ %> disabled"  <%}else {%>"<%} %>> Consulter </a></td>
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