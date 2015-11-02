package model;

import java.io.File;
import java.util.ArrayList;

public class Encadrer {
	private ArrayList <String[]> allEncadrer= new ArrayList <String[]> ();

	static File csvpath = new File("data/encadrer.csv");
	int nbEncadrer;
	

	/**
	 * Encadrer()
	 * Constructeur de la classe Encadrer.
	 * 
	 * @since sprint_3
	 *@version sprint_3
	 */
	public Encadrer(){
		
		allEncadrer = CSVLibrairie.readCSV(csvpath, ";");

		nbEncadrer = allEncadrer.size();
		
		
	}
	
////////////////////////////////////////////GET////////////////////////////////////////////
	/**
	* getAllProject()
	* @return ArrayList de tout les Projets
	* @since sprint_1bis
	*@version sprint_2
	*/
	public ArrayList<String []> getAllEncadrer() {
		return allEncadrer;
	}
	/**
	 * getNbEncadrer()
	 * @return nombre total de ligne dans le fichier encadrer
	 *@since sprint_1bis
	 *@version sprint_2
	 */
	public int getNbEncadrer(){
		return nbEncadrer;
	}
	
	/**
	 * getAllEncadrerForIdProjet cherche toute les lignes ayant l'id passer en parametre
	 * @param id id du projet
	 * @return Les superviseurs du projet id
	 */
	public ArrayList<String []> getAllEncadrerForIdProjet(String id){
		
		 ArrayList <String[]> retour= new ArrayList <String[]> ();
		
		for(int i = 0; i<allEncadrer.size(); i++){
			if(allEncadrer.get(i)[0].equals(id)){
				retour.add(allEncadrer.get(i));
			}
		}
		
		return retour;
	}
	
	public void addEncadrer(String idProjet, String idIntervenant, String idRole){
		
		String[] newEncadrer = {idProjet,idIntervenant,idRole};
		
		allEncadrer.add(newEncadrer);
		
		CSVLibrairie.saveCSV(csvpath, allEncadrer, ";");
	}

	public void removeEncadrer(String id, String idIntervenant){
		
		for(int i = 0; i < allEncadrer.size(); i++){
			if(allEncadrer.get(i)[0].equals(id) && allEncadrer.get(i)[1].equals(idIntervenant) ){
				allEncadrer.remove(i);
			}
		}
		
		CSVLibrairie.saveCSV(csvpath, allEncadrer, ";");
	}
}
