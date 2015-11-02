package model;

import java.io.File;
import java.util.ArrayList;


public class Intervenants {
	
	private ArrayList <String[]> allIntervenants = new ArrayList <String[]> ();
	static File csvpath = new File("data/intervenants2014_2015.csv");
	private int nbIntervenants;
	private Projets projets;
	
	/**
	 * Intervenants()
	 * Constructeur de la clsse Intervenants
	 * 
	 * @param projets tout les projets
	 * 
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public Intervenants(Projets projets){
		
		allIntervenants = CSVLibrairie.readCSV(csvpath, ";");
		
		nbIntervenants = allIntervenants.size();
		
		this.projets = projets;
	}
	
/////////////////////////////////////////////////GET//////////////////////////////////////////////////////
	
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
		
		ArrayList <String[]> allIntervenantsAffichage = new ArrayList <String[]> ();
		String intervenantCourant[];
		String id;
		ArrayList <String[]> projetRetour;
		String sujetID = " ";
		String sujet[] ;
		String role = " ";
		
		for(int i = 0; i < nbIntervenants; i++){ // boucle principal parcourant chaque lignes
			
			intervenantCourant = allIntervenants.get(i);
				
			id = intervenantCourant[0]; // on récupere l'id de l'intervenant
			
			projetRetour = projets.getProjectByIntervenant(id);
			
			String[] intervenantRetourNoProjet = new String[6];
	
		
			
			if(projetRetour.size() != 0){// on a trouvé le projet à l'intervenant
				
	
				for(int j = 0; j < projetRetour.size(); j++){
					
					String[] intervenantRetour = new String[6];
					String[] projetCourant;
					
					projetCourant = projetRetour.get(j);
					
					
					if(projetCourant[3].equals(id)){
						role = "Client";
					}else if(projetCourant[4].equals(id)){
						role = "Superviseur";
					}
					if(projetCourant[5].equals(id)){
						role = "Support_technique";
					}
					
					sujetID = projetCourant[2];
					
					sujet = sujets.getSujet(Integer.parseInt(sujetID));
					
					if(j == 0){
						intervenantRetour[0] = id;
						intervenantRetour[1] = intervenantCourant[1];
						intervenantRetour[2] = intervenantCourant[2];
					}else {
						intervenantRetour[0] = " ";
						intervenantRetour[1] = " " ;
						intervenantRetour[2] = " ";
						
					}
					
					intervenantRetour[3] = projetCourant[0];
					intervenantRetour[4] = sujet[1];
					intervenantRetour[5] = role;
					
					//System.out.println("projet : " + intervenantRetour[0] + " - " + intervenantRetour[1]+ " - " + intervenantRetour[2] + " - " + intervenantRetour[3] + " - " + intervenantRetour[4] + " - " +intervenantRetour[5]);

					
					allIntervenantsAffichage.add(intervenantRetour);
				}
				
			}else {
				intervenantRetourNoProjet[0] = id;
				intervenantRetourNoProjet[1] = intervenantCourant[1];
				intervenantRetourNoProjet[2] = intervenantCourant[2];
				
				intervenantRetourNoProjet[3] = " ";
				intervenantRetourNoProjet[4] = " ";
				intervenantRetourNoProjet[5] = " ";
				
				allIntervenantsAffichage.add(intervenantRetourNoProjet);
			}
					
			
		}

		return allIntervenantsAffichage;
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
	
	/**
	 * findExpressionInName retourne tout les intervenant yant le nom contenant l'expression renseigné
	 * @param Expression expression a trouver
	 * @return les intervenants concernés
	 */
	public ArrayList<String[]> findExpressionInName (String Expression)
	{
		int i=0;
		ArrayList<String[]> foundIntervenants = new ArrayList<String[]>();
		while(i<nbIntervenants)
		{
			if (allIntervenants.get(i)[2].toLowerCase().contains(Expression.toLowerCase()))
			{
				foundIntervenants.add(allIntervenants.get(i));
			}
			i++;
		}
		if (foundIntervenants.size()>0)
		{
			return foundIntervenants;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * findExpressionInFirstName retourne tout les intervenant yant le prénom contenant l'expression renseigné 
	 * @param Expression à trouver
	 * @return les intervenants concernés
	 */
	public ArrayList<String[]> findExpressionInFirstName (String Expression)
	{
		int i=0;
		ArrayList<String[]> foundIntervenants = new ArrayList<String[]>();
		while(i<nbIntervenants)
		{
			if (allIntervenants.get(i)[1].toLowerCase().contains(Expression.toLowerCase()))
			{
				foundIntervenants.add(allIntervenants.get(i));
			}
			i++;
		}
		if (foundIntervenants.size()>0)
		{
			return foundIntervenants;
		}
		else
		{
			return null;
		}
	}
}
