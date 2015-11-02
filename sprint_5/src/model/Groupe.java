package model;

import java.io.File;
import java.util.ArrayList;

public class Groupe {

	private ArrayList <String[]> allGroupe= new ArrayList <String[]> ();
	
	private static File csvpath = new File("data/groupe.csv");
	private int nbGroupe;
	
	private Projets projets;
	
	public Groupe(Projets projets){
		
		allGroupe = CSVLibrairie.readCSV(csvpath, ";");
		
		nbGroupe = allGroupe.size();
		
		this.projets = projets;
	}
	
////////////////////////////////////////////GET////////////////////////////////////////////
	/**
	* getAllGroupe()
	* @return ArrayList de tout les lignes du fichier Groupe
	* @since sprint_2
	*@version sprint_2
	*/
	public ArrayList<String []> getAllGroupe() {
		return allGroupe;
	}
	
	/**
	* getAllGroupe()
	* @return ArrayList de tout les lignes du fichier Groupe
	* @since sprint_2
	*@version sprint_2
	*/
	public ArrayList<String []> getGroupeByName(String libelle) {
		
		ArrayList<String[]> allGroupeByName = new ArrayList<String[]>();
		
		for (int i=1; i < nbGroupe;i++) {
			if (libelle.equals(allGroupe.get(i)[1])){
				allGroupeByName.add(allGroupe.get(i));
			}
		}
		return allGroupeByName;
	}

	/**
	* getNbGroupe()
	* @return nombre total de ligne dans le fichier Groupe
	*@since sprint_1bis
	*@version sprint_2
	*/
	public int getNbGroupe(){
		return nbGroupe;
	}
	
	
	/**
	 * setGroupe(groupe)
	 * Modifie un groupe
	 * 
	 * @param groupe le groupe a modifier
	 * 	
	 * @since sprint_3
	 *@version sprint_3
	 */

	public void setGroupe(String[] groupe){
		
		int i=0;
		
		Etudiants etudiants = new Etudiants(new Encadrer());
		
		ArrayList<String[]> allEtudiant = etudiants.getAllEtu();
		
		while (!allGroupe.get(i)[0].equals(groupe[0]))
		{
			i++;
		}
		
		int sizeEtudiant = allEtudiant.size();
		String[] groupeAvantModification = allGroupe.get(i);
		
		for(int j = 0; j< sizeEtudiant; j++){ // parcours des étudiant
			
			String[] etudiantCourant = allEtudiant.get(i);
			
			if(etudiantCourant[0].equals(groupeAvantModification[1])){ // si les groupe correspondent
				
				etudiants.setGroupe(String.valueOf(i), groupe[1]);// on modifie leur groupe
			}
		}
		
		allGroupe.set(i, groupe);
		
		CSVLibrairie.saveCSV(csvpath, allGroupe, ";");
	}
/////////////////////////////////////////////AUTRES FONCTIONS///////////////////////////////////////////

	public void addGroupe(String libelle){
		
		String [] lastLine = allGroupe.get(nbGroupe-1);
		String idLastGroupe = lastLine[0];
		
		double id = Math.random() * ( 9 - 0 );
		int idInt = (int) id;
				
		String idGroupe = idLastGroupe+idInt;
		
		String[] newGroupe = {idGroupe, libelle};
		
		allGroupe.add(newGroupe);
		nbGroupe ++;
		
		this.projets.addProject(idGroupe);
		
	
		CSVLibrairie.saveCSV(csvpath, allGroupe, ";");
	}
	
	public void removeGroupe(String idGroupe){
	
		int i = 0;
		for(i = 0; i < nbGroupe; i++){
			if(allGroupe.get(i)[0].equals(idGroupe)){
				
				projets.removeProjetForGroupe(allGroupe.get(i)[0]);
				
				allGroupe.remove(i);
				nbGroupe --;
				break;
			}
		}
	
		CSVLibrairie.saveCSV(csvpath, allGroupe, ";");
	}
}
