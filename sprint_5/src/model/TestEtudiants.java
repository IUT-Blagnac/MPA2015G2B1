package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestEtudiants extends TestCase{

	static int totalAssertions = 0;
	static int bilanAssertions = 0;
	
	String csvPath = "data/etudiants2014_2015.csv";
	Etudiants etudiantClass = new Etudiants(new Encadrer());
	
/////////////////////////////////////////////TEST Etudiants////////////////////////////////////////////////

	/**
	* test_getAllEtu est un test de la fonction getAllEtu de la classe Etudiants
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getAllEtu() throws Exception {
	
		ArrayList<String[]> etudiantsList;
		
		etudiantsList = etudiantClass.getAllEtu();
		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			int i = 0;
			
			while ((ligne = br.readLine())!=null){
				totalAssertions++ ;
				assertEquals("Vérification retour getAllEtu : ", etudiantsList.get(i)[0]+";"+ etudiantsList.get(i)[1] + ";" + etudiantsList.get(i)[2] + ";" + etudiantsList.get(i)[3] , ligne);
				bilanAssertions++ ;
				i++;
			}
			
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
		
		}
	}
	
	/**
	* test_getEtu_byID est un test de la fonction getEtu de la classe Etudiants
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getEtu_byID() throws Exception {
		
		String[] resultID50;
		String[] id50Attendu = { "G", "50", "OUDDANE", "Florian" };
		String[] resultNull;
		
		resultID50 = etudiantClass.getEtu(50);
		resultNull = etudiantClass.getEtu(93);
		
		totalAssertions++;
		assertEquals(resultID50[0].toString(), id50Attendu[0].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultID50[1].toString(), id50Attendu[1].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultID50[2].toString(), id50Attendu[2].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultID50[3].toString(), id50Attendu[3].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultNull, null);
		bilanAssertions++;
	
	}
	
	/**
	* test_getEtuByName est un test de la fonction getEtuByName de la classe Etudiants
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getEtuByName() {
		
		// System.out.println(etudiantClass.getNbEtu());
		ArrayList<String[]> resultID50 = new ArrayList<String[]>();
		ArrayList<String[]> id50Attendu = new ArrayList<String[]>();
		ArrayList<String[]> resultNull = new ArrayList<String[]>();
		
		String[] tabResulat1 = {"C","31","GARIBAL","Florian"};
		
		id50Attendu.add(tabResulat1);
		
		resultID50 = etudiantClass.getEtuByName("GARIBAL");
		resultNull = etudiantClass.getEtuByName("UNICORN");//get out
		
		for(int i =0; i<resultID50.size(); i++){
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[0], resultID50.get(i)[0]);
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[1].toString(), resultID50.get(i)[1].toString());
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[2].toString(),resultID50.get(i)[2].toString());
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[3].toString(), resultID50.get(i)[3].toString());
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(resultNull.size(), 0);
			bilanAssertions++;
		}
	
	}
	
	/**
	* test_getEtuByFirstName est un test de la fonction getEtuByFirstName de la classe Etudiants
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getEtuByFirstName() {
	
		ArrayList<String[]> resultID50 = new ArrayList<String[]>();
		ArrayList<String[]> id50Attendu = new ArrayList<String[]>();
		ArrayList<String[]> resultNull = new ArrayList<String[]>();
		
		String[] tabResulat1 = {"C","31","GARIBAL","Florian"};
		String[] tabResulat2 = { "G", "50", "OUDDANE", "Florian" };
		
		id50Attendu.add(tabResulat1);
		id50Attendu.add(tabResulat2);
		
		resultID50 = etudiantClass.getEtuByFirstName("Florian");
		resultNull = etudiantClass.getEtuByFirstName("TWOCORN");//get out
		
		for(int i =0; i<resultID50.size(); i++){
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[0], resultID50.get(i)[0]);
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[1].toString(), resultID50.get(i)[1].toString());
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[2].toString(),resultID50.get(i)[2].toString());
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[3].toString(), resultID50.get(i)[3].toString());
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(resultNull.size(), 0);
			bilanAssertions++;
		}
	
	}
	
	/**
	* test_getNbEtu test la fonction getNbEtu de la classe Etudiant
	* 
	* @since sprint_1bis
	* @version sprint_2
	* 
	* @throws Exception Exception de lecture du fichier
	*/
	public void test_getNbEtu() throws Exception {
		
		int nbEtu = etudiantClass.getNbEtu();
		
		int realNbEtu = 0;
		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			
			
			while ((br.readLine())!=null){
				realNbEtu ++;
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
	
		totalAssertions++;
		assertEquals(realNbEtu, nbEtu);
		bilanAssertions++;
		
	}
	
	/**
	* test_getAffichage test la fonction getAffichage de la classe Etudiants
	*/
	public void test_getAffichage() {
		
		ArrayList<String[]> etudiantsList;
		
		etudiantsList = etudiantClass.getAffichage(new Projets(new Etudiants(new Encadrer()), new Encadrer()), new Sujets(), new Intervenants(new Projets(new Etudiants(new Encadrer()), new Encadrer()), new Encadrer()));
		
		String[] retourLigne1 = {"A", "41", "LARROUY","Etienne", "1", "Archeologie"};
		
		totalAssertions++ ;
		assertEquals("Vérification retour getAllEtu : ", retourLigne1[0] + "" + retourLigne1[1] + "" + retourLigne1[2] + "" + retourLigne1[3] + "" + retourLigne1[4] + "" + retourLigne1[5] , etudiantsList.get(1)[0]+ "" + etudiantsList.get(1)[1]+ "" + etudiantsList.get(1)[2]+ "" + etudiantsList.get(1)[3]+ "" + etudiantsList.get(1)[4]+ "" + etudiantsList.get(1)[5]);
		bilanAssertions++ ;
				
	}
	
	/**
	 * test_setGroupe test la fonction setGroupe de la classe Etudiants
	 * ATTENTION, le groupe de l'étudiant 71 est modifié puis remis à "Q", si l'étudiant est supprimé le test ne passera pas, de plus si son groupe à été modifier, penser a le modifier  à nouveau
	 */
	public void test_setGroupe(){
		
		etudiantClass.setGroupe("71", "testSetGroupe");
		
		String groupe = "Q";
		
		Boolean ok = false;
		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains("testSetGroupe")){
	
					ok = true;
				}
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
		
		totalAssertions++ ;
		assertTrue(ok);
		bilanAssertions++ ;
			
		etudiantClass.setGroupe("71", groupe);
	}
	
	/**
	 * test_setEtudiant test la fonction setEtudiant de la classe Etudiant
	 */
	public void test_setEtudiant(){
		
		String[] etudiantChangement = {"71", "ZARIOHTEST", "AbdellahTEST"};
		String[] etudiantReel = {"71", "ZARIOH", "Abdellah"};
		
		etudiantClass.setEtudiant(etudiantChangement);
		
		Boolean ok = false;
		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains("71") && ligne.contains("ZARIOHTEST")  && ligne.contains("AbdellahTEST")){
	
					ok = true;
				}
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
		
		totalAssertions++ ;
		assertTrue(ok);
		bilanAssertions++ ;
			
		etudiantClass.setEtudiant(etudiantReel);
	}
	
	/**
	 * test_addEtudiant test la fonction addEtudiant de la classe etudiant
	 * ATTENTION le test passe si la fonction getEtuByName ne marche pas, cependant elle doit être implementé de plus  un étudiant test sera présent dans le fichier
	 */
	public void test_addEtudiant() {
		
		String[] newEtudiant = {"TEST", "test"};
		
		etudiantClass.addEtudiant(newEtudiant[0], newEtudiant[1]);
		
		Boolean ok = false;
		
		String id = "";
		
		try{
			InputStream ips = new FileInputStream(csvPath);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newEtudiant[0])  && ligne.contains(newEtudiant[1])){
					ok = true;
				}
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
		
		id = etudiantClass.getEtuByName("TEST").get(0)[1];
		
		totalAssertions++ ;
		assertTrue(ok);
		bilanAssertions++ ;
		
		etudiantClass.removeEtudiant(Integer.parseInt(id));
	}
	
	/**
	 * test_removeEtudiant test la fonction removeEtudian de la classe etudiant
	 * ATTENTION le test ne passe si la fonction getEtuByName ne marche pas
	 */
	public void test_removeEtudiant() {
		
		String[] newEtudiant = {"TEST", "test"};
		
		etudiantClass.addEtudiant(newEtudiant[0], newEtudiant[1]);
		
		Boolean ok = true;
		
		String id = etudiantClass.getEtuByName("TEST").get(0)[1];
		
		etudiantClass.removeEtudiant(Integer.parseInt(id));

		
		try{
			InputStream ips = new FileInputStream(csvPath);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newEtudiant[0])  && ligne.contains(newEtudiant[1])){
					ok = false;
				}
			}
		
			br.close(); 
		
		}catch (Exception e){
			System.out.println(e.toString());
		}
		
		totalAssertions++ ;
		assertTrue(ok);
		bilanAssertions++ ;
	}
	
	/**
	 * test_removeGroupeInEtudiant test la fonction removeGroupeInEtudiant de la classe Etudiants
	 * ATTENTION le test passe si la fonction setGroupe ne marche pas, cependant elle doit être implémenter, de plus les étudiants étant affilié au groupe Q n'auront plus de groupe
	 * ATTENTION si un etudiant est ajouté au groupe Q, il n'aura plus de groupe
	 */
	public void test_removeGroupeInEtudiant(){
		
		etudiantClass.removeGroupeInEtudiant("Q");
		
		Boolean ok = true;
		
		try{
			InputStream ips = new FileInputStream(csvPath);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains("16")  || ligne.contains("37") || ligne.contains("58") || ligne.contains("71")){
					if(ligne.substring(0, 1).equals("Q"))
						ok = false;
				}
			}
		
			br.close(); 
		
		}catch (Exception e){
			System.out.println(e.toString());
		}
		
		totalAssertions++ ;
		assertTrue(ok);
		bilanAssertions++ ;
		
		etudiantClass.setGroupe("16", "Q");
		etudiantClass.setGroupe("37", "Q");
		etudiantClass.setGroupe("58", "Q");
		etudiantClass.setGroupe("71", "Q");
	}
	 /**
	  * main de la classe Test
	  *
	  * @param args arguments
	  * 
	  * @since sprint_1bis
	  * @version sprint_2
	  */
	  public static void main(String[] args) {
		  
		  junit.textui.TestRunner.run(new TestSuite(TestEtudiants.class));
		
		  if (bilanAssertions == totalAssertions) { 
			  System.out.print("Bravo !"); 
		  }
		  else  { 
			  System.out.print("OUPS !"); 
		  }
		
		  System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");
		  
	  } // fin main

}
