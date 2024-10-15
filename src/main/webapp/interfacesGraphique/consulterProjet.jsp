<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="presentation.Model.Projet" %>
<%@ page import="presentation.Model.User" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulter Projet</title>
  <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/sideBar.css">
  <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/bootstrap.min.css">
  <script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/bootstrap.min.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/jquery.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
</head>
<body>

 
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
        	<a href="ajouterProjet" class="nav-link  text-white" aria-current="page">
          	<svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          		Ajouter projet
        	</a>
      	   </li>
	      <li>
	      <a href="consulterProjet" class="nav-link active" >
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
   
    <div class="contenue  flex-grow-1  mt-5 mb-5 me-3 ms-0">
			
			 <table class="table table-striped table-bordered">
      <tr>
        <th>Nom Projet</th>
        <th>Date Demmarage</th>
        <th>Date Livraison</th>
        <th>Chef Projet</th>
        <th>Client </th>
        <th></th>
      </tr>
    <%  ArrayList<Projet> list=(ArrayList<Projet>)request.getAttribute("listProjet"); %> 
    <%    
    	int i=0;
    	for(Projet projet : list) {
    		String nom=projet.getChefProjet().getNom();
    		String prenom=projet.getChefProjet().getPrenom();
    		String nomC=projet.getClient().getNom();
    		String prenomC=projet.getClient().getPrenom();
    		%> 
      <tr>
        <td><%= projet.getNomPrj() %></td>
        <td><%= projet.getDateDebut() %></td>
        <td><%= projet.getDateFin() %></td>
        <td><%= nom %> <%= prenom %></td>
        <td><%= nomC %> <%= prenomC %></td>
        <td>
          <td>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifierModal" 
                data-projet-nom="<%= projet.getNomPrj() %>"
                data-projet-description="<%= projet.getDesc() %>"
                data-projet-date-debut="<%= projet.getDateDebut() %>"
                data-projet-date-fin="<%= projet.getDateFin() %>"
                data-chef-projet-id="<%= projet.getChefProjet().getId() %>"
                data-chef-projet-nom="<%= projet.getChefProjet().getNom() %>"
                data-chef-projet-prenom="<%= projet.getChefProjet().getPrenom() %>"
                data-client-cin="<%=projet.getClient().getCIN()%>"
                data-client-nom="<%=projet.getClient().getNom()%>"
                data-client-prenom="<%=projet.getClient().getPrenom()%>"
                data-client-telephone="<%=projet.getClient().getTelephone()%>"
                data-client-id="<%=projet.getClient().getId()%>"
                data-projet-id="<%=projet.getProjetID()%>"
                >
                Modifier
            </button>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#supprimerModal"
                data-projet-nom="<%= projet.getNomPrj() %>">
                Supprimer
            </button>
        </td>        </td>
      </tr>    
        <% 
          i++;
    	} %>
  </table>
      <div class="modal fade" id="modifierModal" tabindex="-1" aria-labelledby="modifierModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifierModalLabel">Modifier Projet</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                     <div class="card bg-info ">
        <div class="card-body ">
       <form method="POST"  action="consulterProjet"> 
         <div class="row ">
          <div class="card CardinfoProjet  col-md-6">
            <div class="card-body">
              <h4 class="card-title text-center">Info Projet</h4>   
                 <input  id="idPrj" name="idPrj" hidden>    
                <div class="mb-3 mt-3">
                  <label for="nom" class="form-label">Nom Projet:</label>
                  <input type="text" class="form-control InfoProjet" id="nom"  name="nom">
                </div>
                <div class="mb-3">
                  <label for="description">Description:</label>
                  <textarea class="form-control InfoProjet" rows="5" id="description" name="description" ></textarea>
                </div>
                <div class="mb-3">
                  <label for="dateDemarage" class="form-label">Date demarage:</label>
                  <input type="date" class="form-control InfoProjet" id="dateDemarage"  name="dateDemarage" >
                </div>
                <div class="mb-3">
                  <label for="dateLivraison" class="form-label">Date Livraison:</label>
                  <input type="date" class="form-control InfoProjet" id="dateLivraison"  name="dateLivraison" >
                </div>
            </div>
          </div>
          <div class="card CardinfoClient   col-md-6 " >
            <div class="card-body">
              <h4 class="card-title text-center">Info Client</h4>
                <div class="mb-3 mt-3">
               <a></a>  <label for="cin" class="form-label">CIN :</label>
                  <input type="text" class="form-control" id="cin"  name="cin" >
                </div>
                <div class="mb-3">
                  <label for="nomCli">Nom:</label>
                  <input type="text" class="form-control" rows="5" id="nomCli" name="nomCli" >
                <div class="mb-3">
                  <label for="prenomCli">Prenom:</label>
                  <input type="text" class="form-control" rows="5" id="prenomCli" name="prenomCli" >
                </div>
                <div class="mb-3">
                  <label for="telephone" class="form-label">Telephone:</label>
                  <input type="tel" class="form-control" id="telephone"  name="telephone" >
                </div>
                 <input hidden value="modifer" id="operation" name="operation">
                 <input hidden  id="idCli" name="idCli">
              </div>
            </div>
          </div>
          </div>
          <div class="card CardinfoClient  mt-2 " >
            <div class="card-body">
              <h4 class="card-title text-center">Info Chef Projet</h4>
                <div class="mb-3 mt-3">
                  <label for="nomChefProjet" class="form-label">Chef Projet :</label>
                  <select name="nomChefProjet" id="nomChefProjet" class="form-select">                 
           		   <%
           		     ArrayList<User> listUser=(ArrayList<User>)request.getAttribute("listCHef");
           		   %>
		        	
				  <%
			      int succes=(int)request.getAttribute("succes");
				  for(User user1  : listUser ) {	
					  %>
		                <option value="<%= user1.getId() %>">
		                   <%=user1.getNom() %> <%=user1.getPrenom() %>               
		                </option selected>
		       	    <% }
				  %>        
                  </select>
                </div>
            </div>
          </div>
          <div class="d-flex justify-content-center mt-2">
             <input class="btn btn-primary" type="submit" value="Modifer">
          </div>
      </form> 
        </div>
      </div>
            </div>
        </div>
    </div>
</div>
          <form action="consulterProjet" method="post">
             <input hidden id="projet" name="projet">
             <input hidden value="supprimer" id="operation" name="operation">
   		<div class="modal fade" id="supprimerModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Confirmation de suppression</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                <button type="submit" class="btn btn-danger">Confirmer</button>
            </div>
        </div>
    </div>
</div>         
    	  </form>
        <script>
            $('#modifierModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget);
                var modal = $(this);
                var nom = button.data('projet-nom');
                var description = button.data('projet-description');
                var dateDebut = button.data('projet-date-debut');
                var dateFin = button.data('projet-date-fin');
                var chefProjetId = button.data('chef-projet-id');
                var chefProjetNom = button.data('chef-projet-nom');
                var chefProjetPrenom = button.data('chef-projet-prenom');
                var clientCin=button.data("client-cin");
                var clientNom=button.data("client-nom");
                var clientPrenom=button.data("client-prenom");
                var clientTelephone=button.data("client-telephone");
                var clientId=button.data("client-id");
                var idPrj=button.data("projet-id");
                modal.find('#nom').val(nom);
                modal.find('#description').val(description);
                modal.find('#dateDemarage').val(dateDebut);
                modal.find('#dateLivraison').val(dateFin);
                modal.find("#cin").val(clientCin);
                modal.find("#nomCli").val(clientNom);
                modal.find("#prenomCli").val(clientPrenom);
                modal.find("#telephone").val(clientTelephone);
                modal.find("#idCli").val(clientId);
                modal.find("#idPrj").val(idPrj);
            });       
            $('#supprimerModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget);
                var modal = $(this);
                var nom = button.data('projet-nom');
                $("#projet").val(nom);
                modal.find('.modal-body').html(' Etes-vous sure de  supprimer projet  ' + nom + '?');
            });
        </script>       
    </div>
  </div>
</body>
</html>