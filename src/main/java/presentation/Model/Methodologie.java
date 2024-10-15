package presentation.Model;

public class Methodologie {
	 private String idMethod;
	 private String NomMethod;
	 
	public Methodologie(String idMethod, String nomMethod) {
		super();
		this.idMethod = idMethod;
		NomMethod = nomMethod;
	}

	

	public String getIdMethod() {
		return idMethod;
	}



	public void setIdMethod(String idMethod) {
		this.idMethod = idMethod;
	}



	public String getNomMethod() {
		return NomMethod;
	}

	public void setNomMethod(String nomMethod) {
		NomMethod = nomMethod;
	}
	 
	 
}
