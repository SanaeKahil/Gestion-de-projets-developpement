package presentation.Model;

public class Notification {
   private String dateNotification,description,id,idUser;
   private int isVue;
public Notification(String idUser,String id, String dateNotification, String description, int isVue) {
	super();
	this.dateNotification = dateNotification;
	this.description = description;
	this.isVue = isVue;
	this.id=id;
	this.idUser=idUser;
}
public String getDateNotification() {
	return dateNotification;
}
public void setDateNotification(String dateNotification) {
	this.dateNotification = dateNotification;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getIsVue() {
	return isVue;
}
public void setIsVue(int isVue) {
	this.isVue = isVue;
}
@Override
public String toString() {
	return "Notification [dateNotification=" + dateNotification + ", description=" + description + ", isVue=" + isVue
			+ "]";
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getIdUser() {
	return idUser;
}
public void setIdUser(String idUser) {
	this.idUser = idUser;
}
   
}
