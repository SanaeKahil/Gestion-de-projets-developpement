<%@page import="presentation.Model.Client"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="presentation.Model.User" %>
<%@ page import="presentation.Model.Projet" %>
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
    <script src="http://localhost:8080/GestionProjets/js/ClassApp/GestionProjet.js"></script>
  
</head>
<body>
   <% 
    	int succes=(int)request.getAttribute("succes");
    	if(succes==1) {
    		%>    
				<script>
					  $(document).ready(function () {
					    $('#successModal').modal('show');
					  });
			    </script>
				
    <%} else if(succes==0){
    	  %>
    	        <script>
					  $(document).ready(function () {
					    $('#errorModal').modal('show');
					  });
			    </script>
    	      
  		
   <% } %>
 <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="successModalLabel">Succès !</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        L'insertion a été effectuée avec succès.
      </div>
    </div>
  </div>
</div>

<!-- Modèle d'erreur -->
<div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="errorModalLabel">Erreur !</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Une erreur  lors  d'insertion.
      </div>
    </div>
  </div>
</div>
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
        	<a href="ajouterProjet" class="nav-link active" aria-current="page">
          	<svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          		Ajouter projet
        	</a>
      	   </li>
	      <li>
	      <a href="consulterProjet" class="nav-link text-white" >
	          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
	          Consulter Projet
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
   
    <div class="contenue  flex-grow-1  mt-5 mb-5 me-5 ms-0">
      <div class="card bg-info ">
        <div class="card-body ">
       <form method="POST"  action="ajouterProjet"> 
         <div class="row ">
          <div class="card CardinfoProjet  col-md-6">
            <div class="card-body">
              <h4 class="card-title text-center">Info Projet</h4>       
                <div class="mb-3 mt-3">
                  <label for="nom" class="form-label">Nom Projet:</label>
                  <input type="text" class="form-control InfoProjet" id="nom"  name="nom">
                  <p class="text-danger " id="prjEx">le nom de projet deja exist</p>
                </div>
	                <div class="mb-3">
					    <label for="description">Description:</label>
					    <textarea class="form-control InfoProjet" rows="5" id="description" name="description" ></textarea>
					</div>
					<div class="mb-3">
					    <label for="dateDemarage" class="form-label">Date démarage:</label>
					    <input type="date" class="form-control InfoProjet" id="dateDemarage" name="dateDemarage" >
					</div>
					<div class="mb-3">
					    <label for="dateLivraison" class="form-label">Date Livraison:</label>
					    <input type="date" class="form-control InfoProjet" id="dateLivraison" name="dateLivraison" >
					</div>
	            </div>
          </div>
		  <script>		
		  			desactiverChampsProjet();
		  			$("#prjEx").hide();
			        var listNomsprojet=[];
			        <% 
			        ArrayList<Projet> listP=(ArrayList<Projet>)request.getAttribute("listProjet");
			        for(Projet projet : listP){
			        %>
			        var x="<%= projet.getNomPrj()%>";
			        listNomsprojet.push(x);
			        <%}%>
			        $("#nom").on("input",verfie);			        
		   </script>
<!-- Modèle d'erreur -->
	       <div class="card CardinfoClient    col-md-6 " >
            <div class="card-body">
              <h4 class="card-title text-center">Info Client</h4>
                <div class="mb-3 mt-3">
                  <label for="cin" class="form-label">CIN :</label>
                  <input type="text" class="form-control" id="cin"  name="cin" >
                  <p class="text-info " id="clientEx">client deja exist</p>             
                </div>
                <div class="mb-3">
                  <label for="nomCli">Nom:</label>
                  <input type="text" class="form-control" rows="5" id="nomCli" name="nomCli">
                <div class="mb-3">
                  <label for="prenomCli">Prenom:</label>
                  <input type="text" class="form-control" rows="5" id="prenomCli" name="prenomCli" >
                </div>
                <div class="mb-3">
                  <label for="telephone" class="form-label">Telephone:</label>
                  <input type="tel" class="form-control" id="telephone"  name="telephone" >
                </div>
              </div>
            </div>
          </div>
          </div>
          <script >
          $("#clientEx").hide();
          var listClient=[];
	        <% 
	        ArrayList<Client> listC=(ArrayList<Client>)request.getAttribute("listClient");
	        for(Client client : listC){
	        %>
	        var x={
	        		id : "<%=client.getId()%>",
	        		nom : "<%=client.getNom()%>",
	        		prenom : "<%=client.getPrenom()%>",
	        		telephone : "<%=client.getTelephone()%>",
	        		addresse : "<%=client.getAddress()%>",
	        		cin : "<%=client.getCIN()%>"
	        };
	        listClient.push(x);
	        <%}%>
	        $("#cin").on("input",verfieClient);
          </script>
          <div class="card CardinfoClient  mt-2 " >
            <div class="card-body">
              <h4 class="card-title text-center">Info Chef Projet</h4>
                <div class="mb-3 mt-3">
                  <label for="nomChefProjet" class="form-label">Chef Projet :</label>
                  <select name="nomChefProjet" id="nomChefProjet" class="form-select">                 
           		   <%
           		     ArrayList<User> list=(ArrayList<User>)request.getAttribute("listCHef");
           		   %>
		        	
				  <% if(succes==2) {
				  for(User user1  : list ) { %>
		                <option value="<%= user1.getId() %>">
		                   <%=  user1.getNom() %>  <%=  user1.getPrenom() %>               
		                 </option>
		       	    <% }} %>        
                  </select>
                </div>
            </div>
          </div>
          <div class="d-flex justify-content-center mt-2">
             <input class="btn btn-primary" type="submit" onclick="verfieNom('naghach')">
          </div>
      </form> 
        </div>
      </div>
    </div>
  </div>
</body>
</html>