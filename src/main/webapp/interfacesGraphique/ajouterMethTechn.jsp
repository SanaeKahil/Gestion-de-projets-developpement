<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="presentation.Model.Methodologie" %>
  <%@ page import="presentation.Model.Technologie" %>
    <%@ page import="presentation.Model.User" %>
    <%@ page import="presentation.Model.Developeur" %>
       <%@ page import="presentation.Model.Projet" %>
  <%@ page import="metier.GestionProjetChef" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Consulter projet</title>

	<link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/sideBar.css">
<!-- 	<link rel="stylesheet" href="http://localhost:9090/GestionProjets/css/chefProjet.css"> -->
	 <link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/bootstrap.min.css">
	 <script src="http://localhost:8080/GestionProjets/js/datatables.min.js"></script> 
  	<script src="http://localhost:8080/GestionProjets/js/bootstrap.bundle.min.js"></script>
 	 <script src="http://localhost:8080/GestionProjets/js/jquery.js"></script>
  	<script src="http://localhost:8080/GestionProjets/js/all.min.js"></script>
  	<script src="http://localhost:8080/GestionProjets/js/ClassApp/GestionProjet.js"></script>
  	<link rel="stylesheet" href="http://localhost:8080/GestionProjets/css/PageAjouterMethTechn.css">
  	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<style>


</style>

<body>
   <% User user= (User)request.getAttribute("user"); %>
 
  <div class="d-flex ">
    <!-- Debut Side Bare -->
      <%@ include file="../../Layout/SideBar.jsp" %>
   <!--  Fin SIde Bare-->
 <div class="contenue  flex-grow-1  mt-5 mb-5 ">
  <!--  <form action="#" id="chercheTechno" method="POST"> -->
  
   <!--  <input type="hidden" name="action" value="chercherDev">-->
  <div class="card">
      <div class="card-body">
  		  	<h6 class="mb-4 mt-2">
  		  	    <% Projet projet= (Projet)request.getAttribute("projet"); %>
	        	le projet : <%= projet.getNomPrj() %>
	 		</h6>
	 		<hr>

   		  <% ArrayList<Methodologie> listeMetho = (ArrayList<Methodologie>) request.getAttribute("listeMetho"); %>
        	   <% if(listeMetho!=null) {
        	   for (Methodologie metho : listeMetho) { %>
               <div class="mb-3">
          		<div class="form-check form-check-inline ">
            		<input class="form-check-input" type="radio" id=<%= metho.getIdMethod() %> name="methodProjet"  value=<%= metho.getIdMethod() %>>
           			 <label class="form-check-label" for=<%= metho.getIdMethod() %>>
            			<%= metho.getNomMethod() %>
           			 </label>
          		</div>
            <% } }%>
  			</div>


  
  
            <hr>
            <h6 class="mb-4 mt-2">
	        	Technologies de devloppement 
	 		</h6>
	 		<hr>
       <fieldset class="checkbox-group">
	
       <% ArrayList<Technologie> listeTech = (ArrayList<Technologie>) request.getAttribute("listeTech"); %>
            <% if(listeTech!=null){
              for (Technologie techno : listeTech) { %>
               
              
					     

	
	<div class="checkbox">
		<label class="checkbox-wrapper">
			<input type="checkbox" class="checkbox-input" id="technologies" name="technologies" value=<%= techno.getId() %>/>
			<span class="checkbox-tile">	
				<span class="checkbox-label"><%= techno.getNom() %> </span>
			</span>
		</label>
	</div>
	
	
	
            <% }} %>
            
            </fieldset>
              <a href="javascript:void(0)" id="btnTechno" class="btn btn-primary float-end"> Rechercher </a> 
  <!--   <button type="submit" class="btn btn-primary float-end">Rechercher �quipe </button>-->
         
    </div>
  </div>
   <!-- </form>  -->
    </div>
   
   
   
   
</body>
<script>
$(document)
.ready(function() {

   // alert("check if your are in this page")

})
.on('click', '#btnTechno', function (){

    var tabTechnoId = []; // D�claration du tableau
   
    var idmethod =0; //val initial
    if(document.querySelector('input[name="methodProjet"]:checked')){
    	idmethod = document.querySelector('input[name="methodProjet"]:checked').value;
    	
    }
    else{
    	
    	swal("Error!", "Vous devez choisir une m�thodologie!", "error")
    	return false; // comme Break;
    }
   

    // Parcours de toutes les cases � cocher "technologies" qui sont coch�es
    $("input[name='technologies']:checked").each(function() {
        tabTechnoId.push(parseInt($(this).val())); // Ajout de la valeur de la case � cocher au tableau
    });
    
    console.log("tabTechnoId  : ",tabTechnoId);
    $.ajax({
        url: "/GestionProjets/PageChefProjets?action=test",
        method: "POST",
        dataType:'json',
        data: { tabTechnoId: tabTechnoId , idmethod: idmethod },
       // contentType: "application/json; charset=utf-8",
        success: function(response) {
  
        	$("#formInsertDev").remove();
        	
        	let listeDevs = response.listeDevs;
        	console.log(listeDevs);
        	let infoBody = ""; 
        	
        	listeDevs.forEach((item, index) => {
        		
        		let techno = "";
        		let idDev = item.idDevloppeur;
        		let toggleDev = "<div class='form-check form-switch'>"
	                				+"<input class='form-check-input' type='checkbox' role='switch' name='devEquipe' value='"+idDev+"' >"
	                			+"</div>";
                			
        		item.technologies.forEach((itm, idx) => {
        			techno += "<li>"+itm+"</li>";
            	});
        		
        		infoBody += "<tr><th>"+item.nom+"</th><th>"+item.prenom+"</th> <th> <ul> "+techno+"</ul> </th> <th>"+toggleDev+"</th> </tr>";
        	});
        	
        	
        	let tblDeveloppeur ="<div class='card' id='formInsertDev'>"
                +"<div class='card-body'>"
                +"<h6 class='mb-4 mt-2'>Former Equipe"
    	 		+"</h6>"
    	 		+"<hr>"

		         +"<label  for='dateReunion'>Date de r�union </label>"
			     +"<input  type='date' id='dateReunion' name='dateReunion' class='mx-2' required>"
        		 +"<table id='example' class='table table-striped table-bordered' style='width:100%;margin-top:50px'>"
        				+"<thead>  <tr><th>Nom</th> <th>Prenom</th> <th>Technologies </th> <th>Membre �quipe</th> </tr> </thead> "
				        +"<tbody>" 
				        +infoBody
				        +"</tbody>"
				        +"</table>"
				        +"<div class='form-group'>"
				        +"</div>"
				        +"<button class='btn btn-primary float-end' id='btnSendEquipe'>Former �quipe </button>"
				   
				   		+"</div>"
				   		+"</div>";
			
			$(".contenue").append(tblDeveloppeur);
        	
        }
    });
})

.on('click', '#btnSendEquipe', function (){
    
    var tabDevEquipeId = [];
    $("input[name='devEquipe']:checked").each(function() {
    	tabDevEquipeId.push(parseInt($(this).val())); 
    });
    
    let dateReunion = $("#dateReunion").val();
    
    $.ajax({
        url: "/GestionProjets/PageChefProjets?action=insererEquipe",
        method: "POST",
        dataType:'json',
        data: { tabDevEquipeId: tabDevEquipeId, dateReunion: dateReunion },
       // contentType: "application/json; charset=utf-8",
        success: function(response) {
        	
        	let checkStatus = response.status;//BOOLEAN
        	if(checkStatus== true)
        		{
        		
        		swal("Success!", "Vous avez former l'equipe!", "success", {
        			  button: "Acceuil",
        			}).then((value) => {
        				 // swal(`The returned value is:`);*
        				if(value)
        				window.location.href = "/GestionProjets/PageChefProjets?id="+<%= user.getId() %>+"&etat=projet";
        			});
        		
        		}
        	else swal("echec!", "un probleme !", "error")
        	
        }
    });
})
</script>

 

</html>
