<%@page import="presentation.Model.Developeur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="presentation.Model.Projet" %>
  <%@ page import="presentation.Model.User" %>
  <%@ page import="metier.GestionProjet" %>
    <%@ page import="java.util.ArrayList" %>
     <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Service-Tache</title>

	<link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/sideBar.css">
	 <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/bootstrap.min.css">
	 <script src="http://localhost:8080/GestionProjets/js/datatables.min.js"></script> 
  	<script src="http://localhost:8080/GestionProjets/js/bootstrap.bundle.min.js"></script>
 	 <script src="http://localhost:8080/GestionProjets/js/jquery.js"></script>
  	<script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
  	<script src="http://localhost:8080/GestionProjets/js/ClassApp/GestionProjet.js"></script>
  	
  	<!-- modal sweet alert -->
  	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  	
</head>

<body>
   <% User user= (User)request.getAttribute("user"); %>
  <div class="d-flex ">
    <!-- Debut Side Bare -->
    <%@ include file="../../Layout/SideBar.jsp" %>
     <!-- Fin SIde Bare -->
    <div class="contenue  flex-grow-1  mt-5 mb-5 ">
    <form  action="/GestionProjets/PageChefProjets" method="post">	
      <input type="hidden" name="action" value="ServiceTache">
      <input type="hidden" name="idProjet" value=<%=request.getAttribute("idProjetService") %>>
   		 
 		<div class="card">
  		
  			<div class="card-body">
  			
	  			<h6 class="mb-4 mt-2">
	    			 Ajouter dans le projet : <%=request.getAttribute("idProjetService")%>
	 			</h6>
	 			<hr>
  				<div class="card-body-tache">
  				 
					<div class="row row-card-tache mt-3">
						<div class="col-sm-6">
	  						<div class="mb-4">
	  							<label  class="form-label"> Service</label>
	    	 				 	<input type="text" name="service" id="service" class="form-control" placeholder="Saisir un service" required > 
	  						</div>
	  					</div>
					</div>
  				<div class="row mt-3">
  					
  					<div class="col-sm-6">
  						<div>
  							<label class="form-label"> Tache </label>
	    					<input type="text" name="taches[]" class="form-control" placeholder="Saisir une tache" required >
  						</div>
  					</div>
  					<div class="col-sm-4">
  						<div>
	  						<label class="ml-5 form-label"> Equipe</label>
			    			 <% ArrayList<Developeur> listeDev = (ArrayList<Developeur>)request.getAttribute("listeDevsEquipe"); %>    
			    			 <select name="listeDev[]" class="form-control" >
			    	  			 <% for (Developeur dev : listeDev) { %>
			  						<option value=<%=dev.getId() %>><%=dev.getNom() + " " + dev.getPrenom()%></option>
			  					 <%} %>
							</select>
  						</div>
  					</div>
  					<div class="col-sm-2" style="display: flex; align-items: center;justify-content: center;">
  						 
  						<a href="javascript:void(0);" id="btnTache" class="btn btn-sm btn-primary mt-4 mx-2 float-end"> + </a>
  						 
  					</div>
  					
  				</div>
  				
    			 
   			 	</div>
   			 	<div class="row">
  						<div class="col-sm-12">
  							
  							<button type="submit" class="btn btn-primary float-end mt-5"> Enregistrer</button>
  							<button type="reset" class="btn btn-danger float-end mx-2 mt-5"> Retour</button>
  						</div>
  					</div>
            </div>
         </div>
   
     
    </div>
     
  
  <!-- </div> -->
  
    </div>
   
</body>
<script>
$(document)
.ready(function() {


})
.on('click', '#btnTache', function (){
				
		let dv_tache= '<div class="row row-card-tache mt-3">'
					
					+'<div class="col-sm-6">'
					+'	<div>'
					+		'<label class="form-label"> Tache </label>'
    				+'	<input type="text" name="taches[]" class="form-control" placeholder="Saisir une tache" required >'
						+'</div>'
				+'	</div>'
				+	'<div class="col-sm-4">'
				+	'	<div>'
  					+	'<label class="ml-5 form-label"> Equipe</label>'
		    			+' <select name="listeDev[]" class="form-control" >'
		    			+" <% for (Developeur dev : listeDev) { %>"
						+"<option value=<%=dev.getId()%>><%=dev.getNom() + " " + dev.getPrenom()%></option>"
						+" <%} %>"
						+'</select>'
					+	'</div>'
					+'</div>'
				+'	<div class="col-sm-2" style="display: flex; align-items: center;justify-content: center;">'
						 
					+'	<a href="javascript:void(0);" id="btnAnnulerTache" class="btn btn-sm btn-danger mt-4 float-end"> - </a>'
					+'	<a href="javascript:void(0);" id="btnTache" class="btn btn-sm btn-primary mt-4 mx-2 float-end"> + </a>'
						 
					+'</div>'
					
				+'</div>';

				$(".card-body-tache").append(dv_tache);

		/* swal({
		  title: "Ajout� avec success",
		  //text: "Once deleted, you will not be able to recover this imaginary file!",
		  icon: "success",
		  buttons: ["Ajouter nouveau service", "go page acceuil!"],
		})
		.then((willDelete) => {
		  if (willDelete) {
		    swal("Poof! Your imaginary file has been deleted!", {
		      icon: "success",
		    });
		  } else {
		    swal("Your imaginary file is safe!");
		  }
		});
 */
 })
 
.on('click','#btnAnnulerTache',function(){
	
	$(this).closest(".row-card-tache").remove();
	
	
}) 



</script>
</html>