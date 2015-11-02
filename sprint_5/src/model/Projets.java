package model;
import java.io.File;
import java.util.ArrayList;

/**
 * class Projet contenant les données du fichier au format Projet (id, groupe, sujet, client, superviseur, support_technique)
 * @author gkueny
 * 
 *Attention un projet ne peut avoir de client, superviseur ou support_technique avant d'avoir un sujet attribué
 *S'il n'y a pas de sujet, client ou superviseur, on met le champs à : "";
 *@since sprint_1bis
 *@version sprint_2
 */
public class Projets {

	private ArrayList <String[]> allProjet = new ArrayList <String[]> ();

	static File csvpath = new File("data/projets2014_2015.csv");
	int nbProjects;
	
	Etudiants etudiants;
	Encadrer encadrer;

	/**
	 * Project()
	 * Constructeur de la classe Project.
	 * 
	 * @param etudiants tout les étudiants
	 * @since sprint_1bis
	 *@version sprint_2
	 */
	public Projets(Etudiants etudiants, Encadrer encadrer){
		
		allProjet = CSVLibrairie.readCSV(csvpath, ";");
		
		this.etudiants = etudiants;
		
		this.encadrer = encadrer;
		
		nbProjects = allProjet.size();
		
		
	}

////////////////////////////////////////////GET////////////////////////////////////////////
	/**
	 * getAllProject()
	 * @return ArrayList de tout les Projets
	 * @since sprint_1bis
	 *@version sprint_2
	 */
	public ArrayList<String []> getAllProjets() {
		return allProjet;
	}


	/**
	 * getProject (id)
	 * 
	 * @param id id du projet
	 * 
	 * @return String[] du projet si le projet est trouvé
	 * @return null sinon
	 *  @since sprint_1bis
	 *@version sprint_2
	 */
	public String[] getProject (int id) {
		
		String newId = String.valueOf(id);
		
		for (int i=1; i < allProjet.size();i++) {
			if (newId.equals(allProjet.get(i)[0])){
				return allProjet.get(i);
			}
		}
		return null;
	}

	/**
	 * getProject (nom)
	 * 
	 * @param nom id du groupe
	 * 
	 * @return le String[] du projet si le projet est trouvé
	 * @return null sinon
	 * 
	 *  @since sprint_1bis
	 *@version sprint_2
	 */
	public String[] getProject (String nom) {
		
		for (int i=1; i < allProjet.size();i++) {
			
			if (nom.equals(allProjet.get(i)[1])){
				return allProjet.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * Renvoie les projets contenant l'intervenant
	 * 
	 * @param idIntervenant id de l'intervenant
	 * @return ArrrayList des Projets
	 */
	public ArrayList<String[]> getProjectByIntervenant(String idIntervenant){
		
		 ArrayList<String[]> retour = new ArrayList<String[]>();
		
		 ArrayList<String[]> allEncadrer = encadrer.getAllEncadrer();
		 
		 String idProjet = "";
		 String[] ligne;
		 
		 for (int i=1; i < allEncadrer.size();i++) {
				
			if (idIntervenant.equals(allEncadrer.get(i)[1])){
				idProjet = allEncadrer.get(i)[0];
				
			}
		}
		 
		 for (int i=1; i < allProjet.size();i++) {
				
				if (idProjet.equals(allProjet.get(i)[0])){
					ligne = allProjet.get(i);
					retour.add(ligne);	
				}
			}
		 
		 return retour;
		
	}
	
	/**
	 * getNbProject()
	 * @return nombre total de projets dans allProject
	 *@since sprint_1bis
	 *@version sprint_2
	 */
	public int getNbProject(){
		return nbProjects;
	}
	
	/**
	 * getAffichage() retourne une ArrayList du contenu du fichier mais avec l'id du projet, le nom du sujet , et le role
	 * @param intervenants tout les intervenant
	 * @param etudiants tout les étudiants
	 * @param sujets tout les sujets
	 * @return ArrayList contenant tout ce qu'il faut afficher
	 */
	public ArrayList<String[]> getAffichage(Etudiants etudiants,Intervenants intervenants, Sujets sujets){
		
		ArrayList <String[]> allProjetsAffichage = new ArrayList <String[]> ();
		String projetCourant[];
		String etudiantCourant[];
	
		String id;
		
		ArrayList <String[]> etudiantRetour;
		
		String[] sujetRetour;
		
		for(int i = 1; i < nbProjects; i++){ // boucle principal parcourant chaque lignes
			
			String[] projetRetour = new String[7];
			
			projetCourant = allProjet.get(i);
			
			projetRetour[0] = projetCourant[0];
			projetRetour[1] = projetCourant[1];
			
			//////////////////////Récuperation sujet////////////////////
			id = projetCourant[2]; // on récupere l'id du sujet
			
			try{
				sujetRetour = sujets.getSujet(Integer.parseInt(id.trim()));
				
				if(sujetRetour != null){// on a trouvé le sujet lié au projet
					
					projetRetour[2] = 	sujetRetour[1];
					
				}else {
					projetRetour[2] = " ";
				}
					
				
			}catch(NumberFormatException e){
				System.out.println("erreur parse");
				
				projetRetour[2] = " ";
			}
			
			//////////////////////Récuperation intervenants////////////////////
			
			ArrayList <String[]> allEncadrer = new ArrayList <String[]> ();
		
			String idIntervenant = "";
			String[] intervenantOfprojet;
			
			allEncadrer = encadrer.getAllEncadrerForIdProjet(projetCourant[0]);
			
			projetRetour[3] = " ";
			projetRetour[4] = " ";
			projetRetour[5] = " ";
			
			for(int b = 0; b < allEncadrer.size(); b++){//client - superviseur - support technique
				
				
				idIntervenant = allEncadrer.get(b)[1].trim();
			
				if(allEncadrer.get(b)[2].trim().equals("1")){
					
					intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
					
					if(intervenantOfprojet != null){
						projetRetour[3] = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
					}
	
				}else if(allEncadrer.get(b)[2].trim().equals("2")){//superviseur
					
					intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
					
					if(intervenantOfprojet != null){
						projetRetour[4] = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
					}
	
				}else if(allEncadrer.get(b)[2].trim().equals("3")){//support_tech
				
					
					intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
					
					if(intervenantOfprojet != null){
						projetRetour[5] = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
					}
	
				}
			}
			
			
			//////////////////////Récuperation etudiants////////////////////
			
			
			etudiantRetour = etudiants.getEtuByGroupe(projetCourant[1].trim());
			
			if(etudiantRetour.size() > 0){
				
				int size = etudiantRetour.size();
				
				for(int j = 0; j<size; j++){
					
					etudiantCourant = etudiantRetour.get(j);
			
					String[]projetRetour2 = new String[7];
					
					projetRetour2[6] = etudiantCourant[3] + " " + etudiantCourant[2];
					
					if(j != 0){
						projetRetour2[0] = " ";
						projetRetour2[1] = " ";
						projetRetour2[2] = " ";
						projetRetour2[3] = " ";
						projetRetour2[4] = " ";
						projetRetour2[5] = " ";
					}else {
						projetRetour2[0] = projetRetour[0];
						projetRetour2[1] = projetRetour[1];
						projetRetour2[2] = projetRetour[2];
						projetRetour2[3] = projetRetour[3];
						projetRetour2[4] = projetRetour[4];
						projetRetour2[5] = projetRetour[5];
					}
					
					allProjetsAffichage.add(projetRetour2);
				}
			}else {
				
				projetRetour[6] = " ";
				
				allProjetsAffichage.add(projetRetour);
			}
			
	
		}
		
		

		return allProjetsAffichage;
	}
	
	
	/**
	 * getAffichageWithNoEtudiants() retourne une ArrayList du contenu du fichier mais avec l'id du projet, le nom du sujet , et le role
	 * @param intervenants tout les intervenant
	 * @param sujets tout les sujets
	 * @return ArrayList contenant tout ce qu'il faut afficher
	 */
	public ArrayList<String[]> getAffichageWithNoEtudiants(Intervenants intervenants, Sujets sujets){
		
		ArrayList <String[]> allProjetsAffichage = new ArrayList <String[]> ();
		String projetCourant[];

	
		String id;

		String[] sujetRetour;
		
		for(int i = 1; i < nbProjects; i++){ // boucle principal parcourant chaque lignes
			
			String[] projetRetour = new String[6];
			
			projetCourant = allProjet.get(i);
			
			projetRetour[0] = projetCourant[0];
			projetRetour[1] = projetCourant[1];
			
			//////////////////////Récuperation sujet////////////////////
			id = projetCourant[2]; // on récupere l'id du sujet
			
			try{
				sujetRetour = sujets.getSujet(Integer.parseInt(id.trim()));
				
				if(sujetRetour != null){// on a trouvé le sujet lié au projet
					
					projetRetour[2] = 	sujetRetour[1];
					
				}else {
					projetRetour[2] = " ";
				}
					
				//////////////////////Récuperation intervenants////////////////////
			
				ArrayList <String[]> allEncadrer = new ArrayList <String[]> ();
			
				String idIntervenant = "";
				String[] intervenantOfprojet;
				
				allEncadrer = encadrer.getAllEncadrerForIdProjet(projetCourant[0]);
				
				projetRetour[3] = " ";
				projetRetour[4] = " ";
				projetRetour[5] = " ";
				
				for(int b = 0; b < allEncadrer.size(); b++){//client
				
					if(allEncadrer.get(b)[2].trim().equals("1")){
						
						idIntervenant = allEncadrer.get(b)[1].trim();
						
						intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
						
						if(intervenantOfprojet != null){
							projetRetour[3] = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
						}

					}else if(allEncadrer.get(b)[2].trim().equals("2")){//superviseur
						
						idIntervenant = allEncadrer.get(b)[1].trim();
						
						intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
						
						if(intervenantOfprojet != null){
							projetRetour[4] = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
						}

					}else if(allEncadrer.get(b)[2].trim().equals("3")){//support_tech
						
						idIntervenant = allEncadrer.get(b)[1].trim();
						
						intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
						
						if(intervenantOfprojet != null){
							projetRetour[5] = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
						}

					}
				}
				
				
				allProjetsAffichage.add(projetRetour);
				
			}catch(NumberFormatException e){
				//System.out.println("erreur parse");
				projetRetour[2] = " ";
				projetRetour[3] = " ";
				projetRetour[4] = " ";
				projetRetour[5] = " ";
				allProjetsAffichage.add(projetRetour);
				
			}
			
			
			
		}

		return allProjetsAffichage;
	}
	
	/**
	 * getAffichageWithNoEtudiantsIntervenants() retourne une ArrayList du contenu du fichier mais avec l'id du projet, le nom du sujet 
	 * @param sujets tout les sujets
	 * @return ArrayList contenant tout ce qu'il faut afficher
	 */
	public ArrayList <String[]> getAffichageWithNoEtudiantsIntervenants(Sujets sujets){
		
		ArrayList <String[]> allProjetsAffichage = new ArrayList <String[]> ();
		String projetCourant[];
	
		String id;
		
		
		String[] sujetRetour;
		
		for(int i = 1; i < nbProjects; i++){ // boucle principal parcourant chaque lignes
			
			String[] projetRetour = new String[3];
			
			projetCourant = allProjet.get(i);
			
			projetRetour[0] = projetCourant[0];
			projetRetour[1] = projetCourant[1];
			
			//////////////////////Récuperation sujet////////////////////
			id = projetCourant[2]; // on récupere l'id du sujet
			
			try{
				sujetRetour = sujets.getSujet(Integer.parseInt(id));
				
				if(sujetRetour != null){// on a trouvé le sujet lié au projet
					
					projetRetour[2] = 	sujetRetour[1];
					
				}else {
					projetRetour[2] = " ";
				}
					
				allProjetsAffichage.add(projetRetour);
			}catch(NumberFormatException e){
				System.out.println("erreur parse");
				
			}
			
			
			
		}

		return allProjetsAffichage;
	}
	
	/**
	 * getAffichageByGroupe() retourne un tableau d'une ligne du fichier mais avec l'id du projet, le nom du sujet , et le role
	 * @param intervenants tout les intervenant
	 * @param sujets tout les sujets
	 * @param groupe tout les groupes
	 * @return ArrayList contenant tout ce qu'il faut afficher
	 */
	@SuppressWarnings("unused")
	public String[] getAffichageByGroupe(Intervenants intervenants, Sujets sujets, String groupe){
		
		String projetCourant[];
	
		String id;
		
		
		String[] sujetRetour;
		
		String[] projetRetour = new String[6];
		
		for(int i = 1; i < nbProjects; i++){ // boucle principal parcourant chaque lignes
			
			if(allProjet.get(i)[1].equals(groupe)){
				
				projetCourant = allProjet.get(i);
				
				projetRetour[0] = projetCourant[0];
				projetRetour[1] = projetCourant[1];
				
				//////////////////////Récuperation sujet////////////////////
				id = projetCourant[2]; // on récupere l'id du sujet
	
				sujetRetour = sujets.getSujet(Integer.parseInt(id));
				
				if(sujetRetour != null){// on a trouvé le sujet lié au projet
					
					projetRetour[2] = 	sujetRetour[1];
					
				}else {
					projetRetour[2] = " ";
				}
					
				//////////////////////Récuperation intervenants////////////////////
			
				ArrayList <String[]> allEncadrer = new ArrayList <String[]> ();
			
				String idIntervenant = "";
				String[] intervenantOfprojet;
				
				allEncadrer = encadrer.getAllEncadrerForIdProjet(projetCourant[0]);
				
				projetRetour[3] = " ";
				projetRetour[4] = " ";
				projetRetour[5] = " ";
				
				for(int b = 0; b < allEncadrer.size(); b++){//client
				
					if(allEncadrer.get(b)[2].equals("1")){
						
						idIntervenant = allEncadrer.get(b)[1];
						
						intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
						
						if(intervenantOfprojet != null){
							projetRetour[3] = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
						}

					}else if(allEncadrer.get(b)[2].equals("2")){//superviseur
						
						idIntervenant = allEncadrer.get(b)[1];
						
						intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
						
						if(intervenantOfprojet != null){
							projetRetour[4] = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
						}

					}else if(allEncadrer.get(b)[2].equals("3")){//support_tech
						
						idIntervenant = allEncadrer.get(b)[1];
						
						intervenantOfprojet = intervenants.getIntervenants(idIntervenant);
						
						if(intervenantOfprojet != null){
							projetRetour[5] = intervenantOfprojet[1] + " " + intervenantOfprojet[2];
						}

					}
				}
			}
			break;
		}
		
		return projetRetour;
	}
	
	/**
	 * getAffichageByGroupeWithNoIntervenants() retourne un tableau d'une ligne du fichier mais avec l'id du projet, le nom du sujet 
	 * @param sujets tout les sujets
	 * @param groupe tout les groupes
	 * @return ArrayList contenant tout ce qu'il faut afficher
	 */
	@SuppressWarnings("unused")
	public String[] getAffichageByGroupeWithNoIntervenants(Sujets sujets, String groupe){
		
		String projetCourant[];
	
		String id;
		
		
		String[] sujetRetour;
		
		String[] projetRetour = new String[3];
		
		for(int i = 1; i < nbProjects; i++){ // boucle principal parcourant chaque lignes
			
			if(allProjet.get(i)[1].equals(groupe)){
				
				projetCourant = allProjet.get(i);
				
				projetRetour[0] = projetCourant[0];
				projetRetour[1] = projetCourant[1];
				
				//////////////////////Récuperation sujet////////////////////
				id = projetCourant[2]; // on récupere l'id du sujet
	
				sujetRetour = sujets.getSujet(Integer.parseInt(id));
				
				if(sujetRetour != null){// on a trouvé le sujet lié au projet
					
					projetRetour[2] = 	sujetRetour[1];
					
				}else {
					projetRetour[2] = " ";
				}
					
				
				
			}
			break;
		}
		
		return projetRetour;
	}
	
	
/////////////////////////////////////////SET///////////////////////////////
	
	
	/**
	 * setGroupe(idProjet, nouveauGroupe)
	 * Modifie le groupe d'un projet
	 * 
	 * @param idProject id du projer
	 * @param idNouveauGroupe nom du nouveau groupe
	 * 
	 *@since sprint_1bis
	 *@version sprint_2
	 */
	public void setGroupe(String idProject, String idNouveauGroupe)
	{
		int i=0;
		
		ArrayList<String[]> allEtudiant = etudiants.getAllEtu();
		
		while (!allProjet.get(i)[0].equals(idProject))
		{
			i++;
		}
		
		int sizeEtudiant = allEtudiant.size();
		String[] projetAvantModification = allProjet.get(i);
		
		for(int j = 0; j< sizeEtudiant; j++){ // parcours des étudiant
			
			String[] etudiantCourant = allEtudiant.get(i);
			
			if(etudiantCourant[0].equals(projetAvantModification[1])){ // si les groupe correspondent
				
				etudiants.setGroupe(String.valueOf(i), idNouveauGroupe);// on modifie leur groupe
			}
		}

		allProjet.get(i)[1]=idNouveauGroupe;
		
		CSVLibrairie.saveCSV(csvpath, allProjet, ";");
	}

	
///////////////////////////////////////Autres fonctions///////////////////////////////////////
	/**
	 * removeProject(id)
	 * Supprime un projet dans la liste
	 * 
	 * @param id du projet supprimé
	 *
	 * @since sprint_1bis
	 *@version sprint_2
	 */
	public void removeProject(int id) {
		
		String oldId = String.valueOf(id);
		
		for (int i=1; i<allProjet.size(); i++){
		
			if(allProjet.get(i)[0].equals(oldId)){

				String groupe = allProjet.get(i)[1];
				
				etudiants.removeGroupeInEtudiant(groupe);
				
				allProjet.remove(i);
				
				this.nbProjects --;
			}
			
		}
		
		CSVLibrairie.saveCSV(csvpath, allProjet, ";");
	}
	
	/**
	 * removeIntervenantInProject 
	 * @param id de l'intervenant
	 */
	public void removeIntervenantInProject(String id) {
		
		
		encadrer.removeIntervenant(id);


	}
	
	public void removeProjetForGroupe(String idGroupe) {
		
		for(int i = 0; i < nbProjects; i++){
			if(allProjet.get(i)[1].equals(idGroupe)){
				allProjet.remove(i);
				nbProjects --;
				break;
			}
		}
		
		CSVLibrairie.saveCSV(csvpath, allProjet, ";");
	}
	
	/**
	 * addSubject(groupe)
	 * Crée un nouveau sujet
	 * 
	 * @param groupe nom du groupe
	 */
	public void addProject(String groupe) {
		
		this.nbProjects ++;
		
		String idNewSubject = String.valueOf(nbProjects);
		String [] newProject2 = {idNewSubject, groupe, " "};
		
		allProjet.add(newProject2);
		
		CSVLibrairie.saveCSV(csvpath, allProjet, ";");
	}
	
	/**
	 * addSujet(idSUjet)
	 * Crée un nouveau sujet
	 * 
	 * @param id id du projet
	 * @param idSUjet id du sujet
	 */
	public void addSujet(String id, String idSujet) {
		
		for(int i = 0; i < nbProjects; i++){
			if(allProjet.get(i)[0].equals(id)){
				allProjet.get(i)[2] = idSujet;
			}
		}
		
		CSVLibrairie.saveCSV(csvpath, allProjet, ";");
	}
}
