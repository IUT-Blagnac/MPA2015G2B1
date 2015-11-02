package model;


import java.io.File;
import java.util.ArrayList;

/**
 * classe voeux , stock les voeux de chaque groupe
 * @author gkueny
 *
 */
public class Voeux {

	private ArrayList<String[]> allVoeux = new ArrayList <String[]> ();
	
	static File csvpath = new File("data/voeux.csv");
	
	int nbVoeux;
	
	/**
	 * Voeux Contructeur de la classe Voeux
	 */
	public Voeux(){
		
		allVoeux = CSVLibrairie.readCSV(csvpath, ";");
		
		nbVoeux = allVoeux.size();
	}
	
	/**
	 * getAllVoeux récupère tout les voeux
	 */
	public ArrayList<String[]> getAllVoeux(){
		return allVoeux;
	}
	
	
	public ArrayList<String[]> getVoeuxByGroup(String groupe){
		
		 String[] ligne;
		 ArrayList<String[]> retour = new ArrayList<String[]>();
		 
		for (int i=1; i < allVoeux.size();i++) {
			
			if (groupe.equals(allVoeux.get(i)[0])){
				ligne = allVoeux.get(i);
				retour.add(ligne);	
			}
		}
		
		return retour;
	}
	
	public void addVoeuxForGroup(String groupe, String idSujet, String numero){
		
		String[] newVoeux = {groupe,idSujet,numero};
		
		System.out.println("on est avant la boucle : " + groupe + " ; " + idSujet + " ; " + numero);
		
		Boolean ok = false;
		for (int i=0; i < allVoeux.size();i++) {
			
			System.out.println("on est dans la boucle : " + groupe + " ; " + idSujet + " ; " + numero);
			System.out.println("on est dans la boucle2 : " + allVoeux.get(i)[0] + " ; " + allVoeux.get(i)[1] + " ; " + allVoeux.get(i)[2]);
			if (groupe.equals(allVoeux.get(i)[0]) && idSujet.equals(allVoeux.get(i)[1])){
				
					System.out.println("hey, c'est égale");
					ok =true;
					allVoeux.set(i, newVoeux);
					CSVLibrairie.saveCSV(csvpath, allVoeux, ";");
					break;
				
			}
		}
		
		if(!ok){
			allVoeux.add(newVoeux);
			nbVoeux++;
			CSVLibrairie.saveCSV(csvpath, allVoeux, ";");
		}

		
	}
}
