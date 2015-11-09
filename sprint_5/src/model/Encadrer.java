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
	* getAllEncadrer()
	* @return ArrayList de tout les lignes du fichier Encadrer
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
	
	/**
	 * getAllEncadrerForIdIntervenant cherche toute les lignes ayant l'id passer en parametre
	 * @param id id de l'intervenant
	 * @return Les projet dont les superviseur ont l'id passer en parametre
	 */
	public ArrayList<String []> getAllEncadrerForIdIntervenant(String id){
		
		 ArrayList <String[]> retour= new ArrayList <String[]> ();
		
		for(int i = 0; i<allEncadrer.size(); i++){
			if(allEncadrer.get(i)[1].equals(id)){
				retour.add(allEncadrer.get(i));
			}
		}
		
		return retour;
	}
	
	
/////////////////////////////////////////////AUTRES FONCTIONS///////////////////////////////////////////
	
	public void addEncadrer(String idProjet, String idIntervenant, String idRole){
		
		String[] newEncadrer = {idProjet,idIntervenant,idRole};
		
		Boolean b = false;
		
		for(int i = 0; i < nbEncadrer; i++){
			if(allEncadrer.get(i)[0].trim().equals(idProjet.trim()) && allEncadrer.get(i)[1].trim().equals(idIntervenant.trim()) ){
				allEncadrer.get(i)[2] = idRole;
				b = true;
			}else if (allEncadrer.get(i)[0].trim().equals(idProjet.trim()) && allEncadrer.get(i)[2].trim().equals(idRole.trim())){
				allEncadrer.get(i)[1] = idIntervenant;
				b = true;
			}
		}
		
		if(!b){
			allEncadrer.add(newEncadrer);
			nbEncadrer ++;
		}
		
		CSVLibrairie.saveCSV(csvpath, allEncadrer, ";");
	}

	public void removeEncadrer(String id, String idIntervenant){
		
		for(int i = 0; i < allEncadrer.size(); i++){
			if(allEncadrer.get(i)[0].equals(id) && allEncadrer.get(i)[1].equals(idIntervenant)){
				allEncadrer.remove(i);
				nbEncadrer --;
			}
		}
		
		CSVLibrairie.saveCSV(csvpath, allEncadrer, ";");
	}
	
	
	public void removeEncadrer(String id){
		
		for(int i = 0; i < allEncadrer.size(); i++){
			if(allEncadrer.get(i)[0].trim().equals(id.trim())){
				allEncadrer.remove(i);
				i--;
				nbEncadrer --;
			}
		}
		CSVLibrairie.saveCSV(csvpath, allEncadrer, ";");
	}
	
	/**
	 * removeIntervenant cherche toute les lignes ayant l'id intervenant passer en parametre
	 * @param id id de l'intervenant
	 */
	public void removeIntervenant(String id){
		
		for(int i = 0; i<nbEncadrer; i++){
			if(allEncadrer.get(i)[1].trim().equals(id.trim())){
				allEncadrer.remove(i);
				nbEncadrer--;
			}
		}
	
	}
}
