package presentation.Model;

import java.util.ArrayList;

public class Service {
  private String durreService,DescrService,ServiceID;
  private ArrayList<Tache> listTaches=new ArrayList<Tache>();
public Service(String durreService, String descrService, String serviceID) {
	super();
	this.durreService = durreService;
	DescrService = descrService;
	ServiceID = serviceID;
}
public Service(String idService, String serviceDesc) {
	
	this.ServiceID = idService;
	this.DescrService = serviceDesc;
}
public String getDurreService() {
	return durreService;
}
public void setDurreService(String durreService) {
	this.durreService = durreService;
}
public String getDescrService() {
	return DescrService;
}
public void setDescrService(String descrService) {
	DescrService = descrService;
}
public String getServiceID() {
	return ServiceID;
}
public void setServiceID(String serviceID) {
	ServiceID = serviceID;
}
public ArrayList<Tache> getListTaches() {
	return listTaches;
}
@Override
public String toString() {
	return "Service [durreService=" + durreService + ", DescrService=" + DescrService + ", ServiceID=" + ServiceID
			+ "]";
}
  
}
