<%@page import="presentation.Model.Tache"%>
<%@page import="presentation.Model.Service"%>
<%@page import="presentation.Model.Projet"%>
<%@page import="presentation.Model.User"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projet en cours</title>
  <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/sideBar.css">
  <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/bootstrap.min.css">
  <script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/bootstrap.bundle.min.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/jquery.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/ClassApp/GestionProjet.js"></script>

</head>
<body>
     <% Projet projet=(Projet)request.getAttribute("projet");%>
     <div class="d-flex ">
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
	      <% String idDev=(String)request.getAttribute("idDev"); %>          
	   
        	<a href="ProfileDevloppeur?idDev=<%=idDev%>" class="nav-link ext-white" aria-current="page">
          	<svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          		Profile
        	</a>
      	   </li>      	   
	      <li>
	      <a href="" class="nav-link active" >
	          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
	          Taches
	        </a>
	      </li> 
	      	      <li>
	      	      <% int nbrNotifcation=(int)request.getAttribute("nbr"); %>
	      <a href="notification?id=<%=idDev %>" class="nav-link text-white" >
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
    <div class="container mt-5">
            <!-- Projet en cours -->
        <div class="card mb-3">
            <div class="card-header">
                Projet en cours
            </div>
            <div class="card-body">
                <!-- Intégrer ici les données du projet actuel du développeur -->
                <p>Nom du projet: <%=projet.getNomPrj() %></p>
                <p>Date de début: <%=projet.getDateDebut() %></p>
                <p>Date de livraison: <%=projet.getDateFin() %></p>
                <h3>les services a develloper</h3>
           <% for(Service service : projet.getListService()) { %>
                <div class="accordion" id="servicesAccordion<%=service.getServiceID()%>">
                  <div class="accordion-item">
                      <h2 class="accordion-header" id="serviceOneHeading<%=service.getServiceID()%>">
                          <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#serviceOneCollapse<%=service.getServiceID()%>" aria-expanded="true" aria-controls="serviceOneCollapse<%=service.getServiceID()%>">
                             <%=service.getDescrService() %>
                          </button>
                      </h2>
                      <div id="serviceOneCollapse<%=service.getServiceID()%>" class="accordion-collapse collapse " aria-labelledby="serviceOneHeading<%=service.getServiceID()%>" data-bs-parent="#servicesAccordion<%=service.getServiceID()%>">
                          <div class="accordion-body">
                          <% for(Tache tache : service.getListTaches()) { %>
                            <div class="card">
                              <div class="card-header" id="taskOneHeading">
                                  <h2 class="mb-0">
                                      <button class="btn btn-link" type="button" data-bs-toggle="collapse" data-bs-target="#taskOneCollapse" aria-expanded="true" aria-controls="taskOneCollapse">
                                          <%=tache.getDescriptiontache() %>
                                      </button>
                                  </h2>
                              </div>
                              <div class="card-body">
                                 Avancement  :  <div class="progress progress-sm">
                                                    <div class="progress-bar progress-bar-striped progress-bar-animated" style="width:<%=tache.getPourcentage()%>%"><%=tache.getPourcentage()%>%</div>  
                                                </div>
                                                <form method="post" action="modifierAvancement">
                                                  <div class="mb-3">
                                                      <input name="idTache" value="<%=tache.getTacheID()%>" hidden>
                                                      <label for="nouvelAvancement" class="form-label">Nouvel Avancement</label>
                                                      <input type="number" class="form-control form-control-sm" name="avancement" placeholder="Entrez le nouvel avancement">
                                                  </div>
                                                  <button type="submit" class="btn btn-primary">Modifier l'avancement</button>
                                              </form>  
                              </div>
                            </div>
                            <% } %>
                          </div>
                      </div>
                      <!-- ff -->
                  </div>
              </div>
            <% } %>
            </div>
        </div>
    </div>
  </div>
</body>
</html>