package model;

public enum Role {
	
	Client("CLient"),
	Superviseur("Superviseur"),
	Support_technique("Support_technique");
	
	 private String name = "";
	 
	//Constructeur
	 Role(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	  }
}
