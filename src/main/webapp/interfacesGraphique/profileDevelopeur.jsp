<%@page import="presentation.Model.Technologie"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="presentation.Model.Developeur"%>
<%@page import="java.util.ArrayList"%>
<%@page import="presentation.Model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter Projet</title>
  <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/sideBar.css">
  <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/bootstrap.min.css">
  <script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/bootstrap.bundle.min.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/jquery.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
   <style >
   	 .technology-list {
        list-style-type: none;
        padding: 0;
        column-count: 3;
        column-gap: 20px;
    }

    .technology-list li {
        margin-bottom: 8px;
        border: 1px solid #ccc;
        padding: 8px;
        border-radius: 4px;
        background-color: #f8f9fa;
    }
   </style>
</head>
<body>

<!-- Modèle d'erreur -->
   <% Developeur developeur=(Developeur)request.getAttribute("developeurs"); %>
   <% ArrayList<Technologie> listTechs=(ArrayList<Technologie>)request.getAttribute("listTech"); %>
  <div class="d-flex ">    <!-- Debut Side Bare -->
   <% User user= (User)request.getAttribute("user"); %>
      <div class="main-contaier d-flex " >
   <% int idUser=user.getId();%>
   <% String role=user.getRole(); %>
		<div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 280px;">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
      <span class="fs-4">SDSI</span>
    </a>
    <hr> 
    <ul class="nav nav-pills flex-column mb-auto">
	   <li class="nav-item">
        	<a href="ajouterProjet" class="nav-link active" aria-current="page">
          	<svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          		Profile
        	</a>
      	   </li>
	      <li>
	      <a href="projetEnCours?idDev=<%=developeur.getId() %>" class="nav-link text-white" >
	          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
	          Taches
	        </a>
	      </li> 
	      	      <li>
	      	      <% int nbrNotifcation=(int)request.getAttribute("nbr"); %>
	      <a href="notification?id=<%=developeur.getId() %>" class="nav-link text-white" >
	           <i class="fa-regular fa-bell"></i>
	          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
	          Notifications <%=nbrNotifcation %>
	        </a>
	      </li>     
    </ul>
    <hr>
    <div class="dropdown">
      <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
        <strong> <%= user.getNom()+ " "+user.getPrenom() %></strong>
      </a>     
    </div>
  </div>
        <div class="content ">
            <nav class="navbar navbar-expand-lg navbar-light ">
                <div class="container-fluid">
                    <div class="d-flex justify-content-between d-md-none d-block">
                        <button class="navbar-toggler"  type="button" onclick="$('#side-nav').css('margin-left','0');">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                    </div> 
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                      <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#"></a>
                      </li>
                    </ul>
                </div> 
               </div>
            </nav>
        </div> 
    </div>
      <!-- Fin SIde Bare -->
   
    <div class="col-md-8">
        <!-- Main Content -->
        <div class="content">
            <!-- Developer Profile Section -->
            <div class="card mt-3">
                <div class="card-header">
                    Developer Profile
                </div>
                <div class="card-body">
                    <h5 class="card-title"><%=developeur.getNom() %></h5>
                    <p class="card-text">Telephone: <%=developeur.getTelephone() %></p>
                    <p class="card-text">Address: <%=developeur.getAddress() %></p>
                    <p class="card-text">Technologies:</p>
                    <div class="col-md-12">
                       <ul class="technology-list">
                       
                            <%
                              for(Technologie  tech : developeur.getListTechnologie()){ %>
                            	  <li><%= tech.getNom() %></li>
                             <% }%>                           
                        </ul>
                    </div>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addTechnologyModal">
                        Add Technology
                    </button>
                    <div class="modal fade" id="addTechnologyModal" tabindex="-1" aria-labelledby="addTechnologyModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="addTechnologyModalLabel">Add New Technology</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <!-- Form to add new technology -->
								<form method="POST" action="/GestionProjets/ajouterTechnologie">
								    <div class="mb-3">
								        <input type="text" name="idDev" value="<%= developeur.getId() %>" hidden>
								        
								        <label for="technologyInput" class="form-label">Technology:</label>
								        <input type="text" name="tech" class="form-control" id="technologyInput" list="technologiesList">
								        <datalist id="technologiesList">
								            <% for(Technologie tech : listTechs){ %>
								            	<option value="<%=tech.getNom()%>">
								            <%} %>
								        </datalist>
								    </div>
								    
								    <button type="submit" class="btn btn-primary">Add Technology</button>
								</form>
                         </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>