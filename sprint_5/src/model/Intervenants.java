package model;

import java.io.File;
import java.util.ArrayList;

public class Intervenants {
	
	private ArrayList <String[]> allIntervenants = new ArrayList <String[]> ();
	static File csvpath = new File("data/intervenants2014_2015.csv");
	private int nbIntervenants;
	private Projets projets;
	private Encadrer encadrer;
	
	/**
	 * Intervenants()
	 * Constructeur de la clsse Intervenants
	 * 
	 * @param projets tout les projets
	 * 
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public Intervenants(Projets projets, Encadrer encadrer){
		
		allIntervenants = CSVLibrairie.readCSV(csvpath, ";");
		
		nbIntervenants = allIntervenants.size();
		
		this.projets = projets;
		
		this.encadrer = encadrer;
		
	}
	
/////////////////////////////////////////////////GET//////////////////////////////////////////////////////
	
	public String getNbClient(String idIntervenant){
		
		Encadrer encadrer = new Encadrer();
		
		ArrayList<String[]> allEncadrer = encadrer.getAllEncadrer();
		
		int nbClient = 0;
		
		for(int i = 0; i < allEncadrer.size(); i++){
			if(allEncadrer.get(i)[1].trim().equals(idIntervenant.trim()) && allEncadrer.get(i)[2].trim().equals("1")){
				nbClient++;
			}
		}
		
		String nbClientString = String.valueOf(nbClient);
		
		return nbClientString;
	}

	public String getNbSuperviseur(String idIntervenant){
		
		Encadrer encadrer = new Encadrer();
		
		ArrayList<String[]> allEncadrer = encadrer.getAllEncadrer();
		
		int nbSuperviseur = 0;
		
		for(int i = 0; i < allEncadrer.size(); i++){
			if(allEncadrer.get(i)[1].trim().equals(idIntervenant.trim()) && allEncadrer.get(i)[2].trim().equals("2")){
				nbSuperviseur++;
			}
		}
		
		String nbSuperviseurString = String.valueOf(nbSuperviseur);
		
		return nbSuperviseurString;
	}
	/**
	 * getAllIntervenants()
	 * @return ArrayList de tout les intervenants
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public ArrayList<String []> getAllIntervenants() {
		return allIntervenants;
	}
	
	/**
	 * getIntervenants (id)
	 * @param id id de l'intervenant
	 * @return String[] de l'intervenant concerné ou null
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public String[] getIntervenants(String id) {
		
		for (int i=1; i < allIntervenants.size();i++) {
			if (id.equals(allIntervenants.get(i)[0])){
				return allIntervenants.get(i);
			}
		}
		
		return null;//si on a pas trouvé l'intervenant : on renvoie null
	}
	
	/**
	 * getIntervenants (nom)
	 * @param nom nom de l'intervenant
	 * @return String[] de l'intervenant concerné ou null
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public ArrayList<String[]> getIntervenantsByName (String nom) {
		
		ArrayList<String[]> retour = new ArrayList<String[]>();
		
		for (int i=1; i < allIntervenants.size();i++) {
			if (nom.equals(allIntervenants.get(i)[2])){
				retour.add(allIntervenants.get(i));
			}
		}
		return retour;
	}
	
	/**
	 * getIntervenantsByFirstName (fNom)
	 * @param fNom prénom de l'intervenant
	 * @return String[] de l'intervenant concerné ou null
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public ArrayList<String[]> getIntervenantsByFirstName (String fNom) {
		
		ArrayList<String[]> retour = new ArrayList<String[]>();
		
		for (int i=1; i < allIntervenants.size();i++) {
			if (fNom.equals(allIntervenants.get(i)[1])){
				retour.add(allIntervenants.get(i));
			}
		}
		return retour;
	}
	
	/**
	 * getIntervenantsByNameAndFirstName (nom, fNom)
	 * @param fnom prénom de l'intervenant
	 * @param nom nom de l'intervenant
	 * @return String[] de l'intervenant concerné ou null
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public String getIntervenantsByNameAndFirstName (String fnom, String nom) {
		
		String retour = "0";
		
		for (int i=1; i < allIntervenants.size();i++) {
			if (allIntervenants.get(i)[1].contains(fnom) && allIntervenants.get(i)[2].contains(nom)){
				retour = allIntervenants.get(i)[0];
			}
		}
		return retour;
	}
	
	/**
	 * getAffichage() retourne une ArrayList du contenu du fichier mais avec l'id du projet, le nom du sujet , et le role
	 * @param projets tout les projets
	 * @param sujets tout les sujets
	 * @return ArrayList contenant tout ce qu'il faut afficher
	 */
	public ArrayList<String[]> getAffichage(Projets projets, Sujets sujets){
		
		ArrayList<String[]> retourAffichage = new ArrayList<String[]>();
		
		for(int i = 0; i < nbIntervenants; i++){ // pour chaque intervenant
			
			ArrayList<String[]> encadrerCourant = new ArrayList<String[]>();
			
			String[] intervenantCourant = allIntervenants.get(i);
			
			
			encadrerCourant = encadrer.getAllEncadrerForIdIntervenant(intervenantCourant[0]);
			
			if(encadrerCourant.size() != 0){
				for(int j = 0; j < encadrerCourant.size(); j++){ // pour chaque projet auquel l'intervenant courant est affilié
					
					String[] retourCourant = new String[6]; // idIntervenant - prenomIntervenant - nomIntervenant - idProjet - nomSujet - Rôle
					
					String[] encadrerCourant2 = encadrerCourant.get(j);
					String[] projetCourant;
					String[] sujetCourant;
					
					try{
						projetCourant = projets.getProject(Integer.parseInt(encadrerCourant2[0].trim()));// on récupere le projet
						System.out.println("id : " + encadrerCourant2[0].trim());
						sujetCourant = sujets.getSujet(Integer.parseInt(projetCourant[2].trim()));
						
						retourCourant[3] = encadrerCourant2[0]; // idProjet
						
						retourCourant[4] = sujetCourant[1];// nomSujet
	
						if(encadrerCourant2[2].trim().equals("1")){// Rôle
							retourCourant[5] = "Client";
						}else if(encadrerCourant2[2].trim().equals("2")){// Rôle
							retourCourant[5] = "Superviseur";
						}else if(encadrerCourant2[2].trim().equals("3")){// Rôle
							retourCourant[5] = "Support_Technique";
						}
						
						if(j == 0){// 1ere ligne
							retourCourant[0] = intervenantCourant[0];// idIntervenant
							retourCourant[1] = intervenantCourant[1];// prenomIntervenant
							retourCourant[2] = intervenantCourant[2];// nomIntervenant
							
	
						}else { // les autres lignes
							retourCourant[0] = " ";// idIntervenant
							retourCourant[1] = " ";// prenomIntervenant
							retourCourant[2] = " ";// nomIntervenant
						}
						retourAffichage.add(retourCourant);
						
					}catch(java.lang.NumberFormatException e){
						retourCourant[0] = intervenantCourant[0];// idIntervenant
						retourCourant[1] = intervenantCourant[1];// prenomIntervenant
						retourCourant[2] = intervenantCourant[2];// nomIntervenant
						retourCourant[3] = " "; // idProjet						
						retourCourant[4] = " ";// nomSujet
						retourCourant[5] = " ";// fonction
						
						retourAffichage.add(retourCourant);
						
					}
				}
			}else {
				String[] retourCourant = new String[6]; // idIntervenant - prenomIntervenant - nomIntervenant - idProjet - nomSujet - Rôle
				retourCourant[0] = intervenantCourant[0];// idIntervenant
				retourCourant[1] = intervenantCourant[1];// prenomIntervenant
				retourCourant[2] = intervenantCourant[2];// nomIntervenant
				retourCourant[3] = " ";// nomIntervenant
				retourCourant[4] = " ";// nomIntervenant
				retourCourant[5] = " ";// nomIntervenant
				
				retourAffichage.add(retourCourant);
				
			}
			
	
		}
		
		return retourAffichage;
	}
	
	/**
	 * getNbIntervenants()
	 * retourne le nombre d'intervenants
	 * @return nbIntervenants
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public int getNbIntervenants(){
		return nbIntervenants;
	}
	
	
/////////////////////////////////////////////////SET//////////////////////////////////////////////////////

	/**
	 * setIntervenants(newIntervenants)
	 * modifie l'intervenant ayant l'id correspondant avec  newIntervenants :  id, prenom , nom
	 * @param newIntervenants nouvel étudiant
	  * @since sprint_1bis
	 * @version sprint_2
	 */
	public void setIntervenants(String[] newIntervenants){
		int i=0;
		while (!allIntervenants.get(i)[0].equals(newIntervenants[0]))
		{
			i++;
		}
		allIntervenants.set(i, newIntervenants);
		
		CSVLibrairie.saveCSV(csvpath, allIntervenants, ";");
	}
	
	
	
/////////////////////////////////////////////////Autres fonctions//////////////////////////////////////////////////////

	/**
	 * addIntervenants(nom, titre)
	 * crée un nouvel intervenant
	 * @param prenom prenom de l'intervenant
	 * @param nom nom de l'intervenant
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public void addIntervenants(String prenom, String nom) {
		
		String IntervenantsToAdd[] = {prenom, nom};
		
		int nbLine = this.allIntervenants.size();
		
		String [] lastLine = allIntervenants.get(nbLine-1);
		String nbIntervenants = lastLine[0];
		
		int idIntervenants = Integer.parseInt(nbIntervenants);
		idIntervenants ++;
		
		String idNewIntervenants = String.valueOf(idIntervenants);
		
		String [] newIntervenants = new String [3];
		newIntervenants [0] = idNewIntervenants;
		newIntervenants [1] = IntervenantsToAdd [0];
		newIntervenants [2] = IntervenantsToAdd [1];
		
		allIntervenants.add(newIntervenants);
		this.nbIntervenants ++;
		
		CSVLibrairie.saveCSV(csvpath, allIntervenants, ";");
	}
	

	/**
	 * removeIntervenant(id)
	 * supprime l'intervenant spécifié s'il existe, sinon ne fais rien
	 * @param id id de l'intervenant
	 * 
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public void removeIntervenant(String id) {
		
		for (int i=1; i<allIntervenants.size(); i++){
			
			if(allIntervenants.get(i)[0].equals(id)){
				
				projets.removeIntervenantInProject(id);// on enleve la référence de l'intervenants dans les projets qui lui sont affiliés
				
				allIntervenants.remove(i);
				
				this.nbIntervenants --;
			}
		}
		CSVLibrairie.saveCSV(csvpath, allIntervenants, ";");
	}

}
