package model;

import java.io.File;
import java.util.ArrayList;

/**
 * Classe Etudiant qui contiendra le contenu du fichier de format étudiant (groupe, id, prenom, nom)
 * @author gkueny
 *@since sprint_1bis
  *@version sprint_2
 */
public class Etudiants {
	
	private ArrayList <String[]> allEtudiant = new ArrayList <String[]> ();
	
	static File csvpath = new File("data/etudiants2014_2015.csv");
	
	private Encadrer encadrer;
	
	int nbEtu;
	
	/**
	 * Etudiant()
	 * Constructeur de la classe Etudiant
	 * @since sprint_1bis
	 *@version sprint_2
	 */
	public Etudiants(Encadrer encadrer){
		
		this.allEtudiant = CSVLibrairie.readCSV(Etudiants.csvpath, ";");
		
		this.nbEtu = allEtudiant.size();
		
		this.encadrer = encadrer;
		
	}

	
//////////////////////////////////////GET//////////////////////////////////////
	/**
	 * getAllEtu()
	 * @return ArrayList de tout les étudiants
	 *  @since sprint_1bis
	 *@version sprint_2
	 */
	public ArrayList<String []> getAllEtu() {
		return allEtudiant;
	}
	
	/**
	 * getEtud (id)
	 * @param id id de l'etudiant
	 * @return String[] de l'étudiant si étudiant trouvé
	 * @return null sinon
	 *  @since sprint_1bis
	 *@version sprint_2
	 */
	public String[] getEtu (int id) {
	
		String newId = String.valueOf(id);
		
		for (int i=1; i < allEtudiant.size();i++) {
			if (newId.equals(allEtudiant.get(i)[1])){ // si égal a l'id de la ligne courante
				return allEtudiant.get(i);
			}
		}
		
		return null;// null si on n'a pas trouvé
	}
	
	/**
	 * getEtuByName (nom)
	 * 
	 * @param nom nom recherché
	 * @return l'arrayList des étudiant si un ou plusieurs étudiants trouvés
	 * @return null sinon
	 *
	 * @since sprint_1bis
	 *@version sprint_2
	 */
	public ArrayList<String[]> getEtuByName (String nom) {
		
		ArrayList<String[]> allEtuByName = new ArrayList<String[]>();
		
		for (int i=1; i < allEtudiant.size();i++) {
			if (nom.equals(allEtudiant.get(i)[2])){
				allEtuByName.add(allEtudiant.get(i));
			}
		}
		return allEtuByName;
	}
	
	/**
	 * getEtuByFirstName (prenom)
	 * 
	 * @param prenom prenom recherché
	 * @return le String[] de l'étudiant si étudiant trouvé
	 * @return null sinon
	 *  @since sprint_1bis
	 *@version sprint_2
	 */
	public ArrayList<String[]> getEtuByFirstName (String prenom) {
		
		ArrayList<String[]> allEtuByFirstName = new ArrayList<String[]>();
		for (int i=1; i < allEtudiant.size();i++) {
			if (prenom.equals(allEtudiant.get(i)[3])){
				allEtuByFirstName.add(allEtudiant.get(i));
			}
		}
		return allEtuByFirstName;
	}
	
	public ArrayList<String[]> getEtuByGroupe (String groupe) {
		
		ArrayList<String[]> etuByGroupe = new ArrayList<String[]>();
		
		for (int i=1; i < allEtudiant.size();i++) {
			if (groupe.trim().equals(allEtudiant.get(i)[0].trim())){
				etuByGroupe.add(allEtudiant.get(i));
			}
		}
		return etuByGroupe;
	}
	
	public String[] getEtuByNameAndFirstName (String prenom, String nom) {
		
		
		for (int i=1; i < allEtudiant.size();i++) {
			System.out.println(prenom.trim() + "->"+ allEtudiant.get(i)[3].trim() + " et " + nom.trim() + "->"+ allEtudiant.get(i)[2].trim());
			if (prenom.trim().equals(allEtudiant.get(i)[3].trim()) && nom.trim().equals(allEtudiant.get(i)[2].trim())){
				System.out.println("hey");
				return allEtudiant.get(i);
			}
		}
		return null;
	}
	/**
	 * getNbEtu()
	 * @return nombre total d'étudiants dans allEtudian
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public int getNbEtu(){
		return allEtudiant.size();
	}
	
	/**
	 * getAffichage() retourne une ArrayList  du contenue du fichier mais avec l'id du projet, le nom du sujet , les intervenant
	 * @param projets tout les projets
	 * @param sujets tout les sujets
	 * @param intervenants tout les intervenants
	 * 
	 * @return affichage des étudiants
	 */
	public ArrayList<String[]> getAffichage(Projets projets, Sujets sujets, Intervenants intervenants){
		
		ArrayList <String[]> allEtudiantAffichage = new ArrayList <String[]> ();
	
		for(int i = 0; i < nbEtu; i++){ // boucle principal parcourant chaque lignes
		
			String etudiantCourant[] = allEtudiant.get(i);
			
				
			String groupe = etudiantCourant[0];
			
			String[] projetCourant;
			
			String idProjet = " ";
			String sujet = " ";
			String client = " ";
			String superviseur = " ";
			String support_technique = " ";
			
			projetCourant = projets.getProject(groupe);
			
			if(projetCourant != null){// on a trouvé le projet corrspondant au groupe de l'étudiant
	
				if(projetCourant.length > 0){ //récupération idProjet
					idProjet = projetCourant[0];
				}
				if(projetCourant.length > 2){ //récupération nom sujet
					sujet = projetCourant[2];
					
					String[] sujetCourant;
					
					try{
						sujetCourant = sujets.getSujet(Integer.parseInt(sujet));// recherche du sujet
					}catch(java.lang.NumberFormatException e){
						sujetCourant = null;
					}
					
					if(sujetCourant != null){ // si on trouve
						sujet = sujetCourant[1];
					}
					
				}
				//////////////////////Récuperation intervenants////////////////////
				
				ArrayList <String[]> allEncadrer = new ArrayList <String[]> ();
			
				String idIntervenant = "";
				String[] intervenantOfprojet;
				
				allEncadrer = encadrer.getAllEncadrerForIdProjet(projetCourant[0]);
				
				client = " ";
				superviseur = " ";
				support_technique = " ";
				
				for(int b = 0; b < allEncadrer.size(); b++){//client
					
				
					if(allEncadrer.get(b)[2].trim().equals("1")){
						
						idIntervenant = allEncadrer.get(b)[1].trim();
						
						intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
						
						if(intervenantOfprojet != null){
							client = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
						}
	
					}else if(allEncadrer.get(b)[2].trim().equals("2")){//superviseur
						
						idIntervenant = allEncadrer.get(b)[1].trim();
						
						intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
						
						if(intervenantOfprojet != null){
							superviseur = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
						}
	
					}else if(allEncadrer.get(b)[2].trim().equals("3")){//support_tech
						
						idIntervenant = allEncadrer.get(b)[1].trim();
						
						intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
						
						if(intervenantOfprojet != null){
							support_technique = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
						}
	
					}
					
				}//fin du for

				
			}//fin if
			String[] etudiantAffichage = {etudiantCourant[0],etudiantCourant[1], etudiantCourant[2], etudiantCourant[3],  idProjet, sujet, client, superviseur, support_technique}; 
			
			allEtudiantAffichage.add(etudiantAffichage);
			
		
		}
		return allEtudiantAffichage;
	}
	
	
	
//////////////////////////////////////SET//////////////////////////////////////
	/**
	 * setGroupe (idEtu, nomGroupe)
	 * @param id , l'id de l'étudiant auquel on veut appliquer la modification 
	 * @param nomGroupe
	 * 			le nouveau nom du groupe (vide si aucun groupe)
	 * 
	 * @return true s'il tout c'est bien passé, false s'il l'étudiant n'a pas été trouvé
	 * @return false sinon
	 * 
	 *  @since sprint_1bis
	 *@version sprint_2
	 */
	public boolean setGroupe (String id, String nomGroupe) {
		
		for (int i=1; i < allEtudiant.size();i++) {
			
			if (id.equals(allEtudiant.get(i)[1])){
				
				String[] changeEtu = allEtudiant.get(i);
				changeEtu[0] = nomGroupe;
				
				allEtudiant.set(i, changeEtu);
				
				CSVLibrairie.saveCSV(csvpath, allEtudiant, ";");
				
				return true;
				
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param str contient l'id, le prenom et le nom de l'étudiant a modifier
	 * @return true si tout c'est bien passé
	 * @return false sinon
	 */
	public boolean setEtudiant (String[] str) {
		
		for (int i=1; i < allEtudiant.size();i++) {
			
			if (str[0].equals(allEtudiant.get(i)[1])){
				
				String[] changeEtu = allEtudiant.get(i);
				changeEtu[2] = str[1];
				changeEtu[3] = str[2];
				
				allEtudiant.set(i, changeEtu);
				
				CSVLibrairie.saveCSV(csvpath, allEtudiant, ";");
				
				return true;
				
			}
		}
		
		return false;
	}	
	
//////////////////////////////////////ADD - Remove//////////////////////////////////////	
	/**
	* addEtudiant(prenom, nom)
	* Crée un nouveau etudiant
	* @param prenom prenom de l'étudiant
	* @param nom nom de l'étudiant
	* 
	*  @since sprint_1bis
	*@version sprint_2
	*/
	public void addEtudiant(String nom, String prenom) {
	
		String etudiantToAdd[] = {nom, prenom};
		
		int nbLine = this.allEtudiant.size();
		String [] lastLine = allEtudiant.get(nbLine-1);
		
		for(int i = 1; i < nbLine; i++){ // recherche de l'id le plus grand
			
			if(Integer.parseInt(lastLine[1]) < Integer.parseInt(allEtudiant.get(i)[1]))
				lastLine = allEtudiant.get(i);
		}

		String nbEtu = lastLine[1];
		
		int idEtudiant = Integer.parseInt(nbEtu);
		
		idEtudiant ++;
		
		String idNewEtudiant = String.valueOf(idEtudiant);
		
		String [] newEtudiant = {" ",idNewEtudiant,etudiantToAdd [0], etudiantToAdd [1]} ;
		
		allEtudiant.add(newEtudiant);
		this.nbEtu ++;
		
		CSVLibrairie.saveCSV(csvpath, allEtudiant, ";");
	}
	
	/**
	* removeEtudiant(id)
	* Supprime un etudiant dans la liste ou ne fais rien si l'etudiant n'existe pas
	* @param id de l'etudiant
	* 
	*  @since sprint_1bis
	*@version sprint_2
	*/
	public void removeEtudiant(int id) {
	
		String newId = String.valueOf(id);
		
		for (int i=1; i<allEtudiant.size(); i++){
		
			if(allEtudiant.get(i)[1].equals(newId)){
				
				allEtudiant.remove(i);
				this.nbEtu --;
			}
		}
	
		CSVLibrairie.saveCSV(csvpath, allEtudiant, ";");
	}	
	
	/**
	 * 
	 * @param groupe groupe a enlever
	 */
	public void removeGroupeInEtudiant(String groupe){
		for (int i=1; i<allEtudiant.size(); i++){
			
			String[] etudiantCourant = allEtudiant.get(i);
			
			if(etudiantCourant[0].equals(groupe)){

				etudiantCourant[0] = " ";
			}
			
			
			allEtudiant.set(i, etudiantCourant);
		}
		
		CSVLibrairie.saveCSV(csvpath, allEtudiant, ";");
	}
}
