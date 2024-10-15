 <div class="main-contaier d-flex " >
   <% int idUser=user.getId();%>
		<div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 280px;">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
      <span class="fs-4">SDSI</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="/GestionProjets/PageChefProjets?id=<%= idUser %>&etat=projet" class="nav-link active" aria-current="page">
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
     <li>
	<% int nbrNotifcation=(int)request.getAttribute("nbr"); %>
	      <a href="notification?id=<%=idUser%>" class="nav-link text-white" >
	           <i class="fa-regular fa-bell"></i>
	          <svg class="bi me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
	          Notifications <%=nbrNotifcation %>
	        </a>
	      </li>
    </ul>
    <hr>
    <div class="dropdown">
      <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
        <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
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