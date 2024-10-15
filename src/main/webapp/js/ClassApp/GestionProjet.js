function desactiverChampsProjet(){
	$("#description").prop("disabled", true);
	$("#dateLivraison").prop("disabled", true);
	$("#dateDemarage").prop("disabled", true);
}
function activerChampsProjet(){
	$("#description").prop("disabled", false);
	$("#dateLivraison").prop("disabled", false);
	$("#dateDemarage").prop("disabled", false);
}
function viderChampsProjet(){
	$("#description").val("");
	$("#dateLivraison").val("");
	$("#dateDemarage").val("");
}
function verfie(){
	var nom=$("#nom").val();
	console.log(listNomsprojet,nom);
	if(listNomsprojet.includes(nom)){
		$("#prjEx").show();
		desactiverChampsProjet();
	}else{
		$("#prjEx").hide();
		activerChampsProjet();
	}
}
function verfieClient(){
	var cin=$("#cin").val();
	var client=listClient.find(objet=>objet.cin==cin);
	console.log(cin,client);
	if(client){
		$("#clientEx").show();
		$("#nomCli").val(client.nom);
		$("#prenomCli").val(client.prenom);
		$("#telephone").val(client.telephone);
	}else{
		$("#clientEx").hide();
		$("#nomCli").val("");
		$("#prenomCli").val("");
		$("#telephone").val("");
	}   
}