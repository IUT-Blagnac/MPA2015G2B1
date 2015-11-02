package model;

import java.io.File;
import java.util.ArrayList;
 
/**
  * classe Sujet, Stock les données d'un fichier csv au format sujet (colonne id, nom titre)
  * @author gkueny
  *@since sprint_1bis
  *@version sprint_2
  */
public class Sujets {

	private ArrayList <String[]> allSubject = new ArrayList <String[]> ();
	
	static File csvpath = new File("data/sujets2014_2015.csv");
	
	int nbSubject;
	
	/**
	 * Subject()
	 * Constructeur de la classe Subject
	 */
	public Sujets(){
		allSubject = CSVLibrairie.readCSV(csvpath, ";");
		
		nbSubject = allSubject.size();
	}
	
	
//////////////////////////////////////GET//////////////////////////////////////
	/**
	 * getAllSujets()
	 * @return ArrayList de tout les sujets
	 */
	public ArrayList<String []> getAllSujets() {
		return allSubject;
	}
	
	/**
	 * getSujet (id)
	 * 
	 * @param id id du sujet
	 * 
	 * @return String[] du sujet si le sujet est trouvé
	 * @return null sinon
	 * 
	 *  @since sprint_1bis
	 *@version sprint_2
	 */
	public String[] getSujet (int id) {
		
		String newId = String.valueOf(id);
		
		for (int i=1; i < allSubject.size();i++) {
			if (newId.equals(allSubject.get(i)[0])){
				return allSubject.get(i);
			}
		}
		return null;
	}
	
	public String getGroupe(String id){
		
		Projets projets = new Projets(new Etudiants(), new Encadrer());
		
		ArrayList<String[]> allProjet = new ArrayList<String[]>();
		
		allProjet = projets.getAllProjets();
		
		for(int i = 0; i < allProjet.size(); i++){
			if(allProjet.get(i)[2].trim().equals(id.trim())){
				return allProjet.get(i)[1];
			}
		}
		
		return "";
	}
	
	/**
	 * getSujetByNom (id)
	 * 
	 * @param nom nom du sujet
	 * 
	 * @return String[] du sujet si le sujet est trouvé
	 * @return null sinon
	 * 
	 *  @since sprint_1bis
	 *@version sprint_2
	 */
	public String[] getSujetByNom (String nom) {
	
		
		for (int i=1; i < allSubject.size();i++) {
			if (nom.equals(allSubject.get(i)[1])){
				return allSubject.get(i);
			}
		}
		return null;
	}
	
	/**
	 * getNbSujets()
	 * @return nombre de sujets
	 */
	public int getNbSujets() {
		return nbSubject;
	}

	
	
//////////////////////////////////////SET//////////////////////////////////////	
	/**
	 * setSubject(newSubject)
	 * Modifie un sujet
	 * 
	 * @param newSubject tableau du nouveau sujet	
	 *  
	 *@since sprint_1bis
	 *@version sprint_2
	 * */
	public void setSubject(String[] newSubject){
		int i=0;
		while (!allSubject.get(i)[0].equals(newSubject[0]))
		{
			i++;
		}
		allSubject.set(i, newSubject);
		CSVLibrairie.saveCSV(csvpath, allSubject, ";");
	}
	
	
	
//////////////////////////////////////ADD - Remove//////////////////////////////////////	
	/**
	 * addSubject(nom, titre)
	 * Crée un nouveau sujet
	 * 
	 * @param nom nom du sujet
	 * @param titre titre du sujet
	 * 
	 * @since sprint_1bis
	 *@version sprint_2
	 */
	public void addSubject(String nom, String titre) {
		
		String subjectToAdd[] = {nom, titre};
		
		int nbLine = this.allSubject.size();
		
		String [] lastLine = allSubject.get(nbLine-1);
		String nbSubject = lastLine[0];
		
		int idSubject = Integer.parseInt(nbSubject);
		
		idSubject ++;
		
		String idNewSubject = String.valueOf(idSubject);
		
		String [] newSubject = {idNewSubject,subjectToAdd [0], subjectToAdd [1]} ;
		
		allSubject.add(newSubject);
		
		CSVLibrairie.saveCSV(csvpath, allSubject, ";");
		
		this.nbSubject ++;
	}
	
	/**
	 * removeSujet(id)
	 * Supprime un sujet dans la liste
	 * 
	 * @param id du sujet
	 * 
	 * @since sprint_1bis
	 *@version sprint_2
	 */
	public void removeSujet(int id) {
		
		String oldId = String.valueOf(id);
		
		for (int i=1; i<allSubject.size(); i++){
			
			if(allSubject.get(i)[0].equals(oldId)){
				
				allSubject.remove(i);
				
				this.nbSubject --;
			}
		}
		
		CSVLibrairie.saveCSV(csvpath, allSubject, ";");
	}
}
