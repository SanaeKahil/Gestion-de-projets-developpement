<%@page import="presentation.Model.Notification"%>
<%@page import="presentation.Model.Technologie"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="presentation.Model.Developeur"%>
<%@page import="java.util.ArrayList"%>
<%@page import="presentation.Model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notifications</title>
  <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/sideBar.css">
  <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/bootstrap.min.css">
  <script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/bootstrap.bundle.min.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/jquery.js"></script>
  <script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
  <style>
  	th,td{
  	  text-align: center;
  	}
  </style>
</head>
<body>
  <div class="d-flex "> 
     <!-- Debut Side Bare -->
 <% 
 	ArrayList<Notification> list=(ArrayList<Notification>)request.getAttribute("listNot");
 %>     
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
     <% if(role.equals("Devloppeur")){ %>
	   <li class="nav-item">
        	<a href="ProfileDevloppeur?idDev=<%=idUser%>" class="nav-link text-white" aria-current="page">
          	<svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          		Profile
        	</a>
      	   </li>
	      <li>
	      <a href="projetEnCours?idDev=<%=idUser %>" class="nav-link text-white" >
	          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
	          Taches
	        </a>
	      </li> 
	    <% }else if(role.equals("ChefProjet")){ %>
		  <li class="nav-item">
	        <a href="/GestionProjets/PageChefProjets?id=<%= idUser %>&etat=projet" class="nav-link " aria-current="page">
	          <svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
	          Consulter projet
	        </a>
	      </li>
	      <li>
	      	<a href="/GestionProjets/PageChefProjets?etat=ProjetService" class="nav-link text-white" >
	          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
	        Service
	        </a>
	      </li>
	    <% } %>
	    <li>
	      <% int nbrNotifcation=(int)request.getAttribute("nbr"); %>
	      <a href="" class="nav-link active" >
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
        <div class="container mt-5">
  <% if(!list.isEmpty()){ %>
    <h1>Notifications</h1>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th scope="col">date notification</th>
                <th scope="col">Notification</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <tr>
               <% for(Notification not : list) { %>
                <td ><%= not.getDateNotification() %></td>
                <td><%= not.getDescription() %></td>
                <% if(not.getIsVue()==1) { %>
                <td>
					<i class="fas fa-eye" style="color: green;"></i>
                <%}else{ %>
                <td>
                  <form action="/GestionProjets/notifisVue" method="POST">
                    <input type="text" name="id" value="<%=not.getIdUser() %>" hidden>
                    <input type="text" name="idNotif" value="<%=not.getId() %>" hidden>
					<button type="submit" ><i class="fas fa-eye-slash" style="color: red;"></i></button>
                  </form>
                </td>
                <%} %>
            </tr>
 			<%  } %>        </tbody>
    </table>
    
            <%}else{%>
            	<div class="alert alert-info" role="alert">
                    Aucune notification disponible.
                </div>
           <% }%> 
</div>
       
    </div>
</div>
</body>
</html>