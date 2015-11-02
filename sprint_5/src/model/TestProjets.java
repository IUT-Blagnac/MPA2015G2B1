package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestProjets extends TestCase{

	static int totalAssertions = 0;
	static int bilanAssertions = 0;
	
	Etudiants etudiants = new Etudiants(new Encadrer());
	Encadrer encadrer = new Encadrer();
	
/////////////////////////////////////////////TEST Etudiants////////////////////////////////////////////////

	/**
	* test_getAllProjets est un test de la fonction getAllProjets de la classe Projets
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_2
	*/
	public void test_getAllProjets() throws Exception {
	
		Projets projetsClass = new Projets(etudiants, encadrer);
		ArrayList<String[]> projetsList;
		
		projetsList = projetsClass.getAllProjets();
		
		try{
			InputStream ips = new FileInputStream("data/projets2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			int i = 0;
			
			while ((ligne = br.readLine())!=null){
				totalAssertions++ ;
				assertEquals("Vérification retour getAllProjets : ", projetsList.get(i)[0]+";"+ projetsList.get(i)[1] + ";" + projetsList.get(i)[2], ligne);
				bilanAssertions++ ;
				i++;
			}
			
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
		
		}
	}
	
	/**
	* test_getProject est un test de la fonction getProject de la classe Projets
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_2
	*/
	public void test_getProject() throws Exception {
	
		Projets projetClass = new Projets(etudiants,encadrer);
		
		String[] resultID3;
		String[] id3Attendu = { "1", "A", "2" };
		String[] resultNull;
		
		resultID3 = projetClass.getProject(1);
		resultNull = projetClass.getProject(93);
		
		totalAssertions++;
		assertEquals(resultID3[0].toString(), id3Attendu[0].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultID3[1].toString(), id3Attendu[1].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultID3[2].toString(), id3Attendu[2].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultNull, null);
		bilanAssertions++;
	
	}
	
	/**
	* test_getProjectByNom est un test de la fonction getProject de la classe Projets
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_2
	*/
	public void test_getProjectByNom() throws Exception {
	
		Projets projetClass = new Projets(etudiants,encadrer);
		
		String[] resultID3;
		String[] id3Attendu = { "1", "A", "2" };
		String[] resultNull;
		
		resultID3 = projetClass.getProject("A");
		resultNull = projetClass.getProject("QQ");
		
		totalAssertions++;
		assertEquals(resultID3[0].toString(), id3Attendu[0].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultID3[1].toString(), id3Attendu[1].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultID3[2].toString(), id3Attendu[2].toString());
		bilanAssertions++;
		
		totalAssertions++;
		assertEquals(resultNull, null);
		bilanAssertions++;
	
	}

	
	/**
	* test_getNbProjets test la fonction getNbProjets de la classe Projets
	* 
	* @since sprint_2
	* 
	* @throws Exception Exception de lecture du fichier
	*/
	public void test_getNbProjets() throws Exception {
	
		Projets projetClass = new Projets(etudiants, encadrer);
		
		int nbprojet = projetClass.getNbProject();
		
		int realNbProjet = 0;
		
		try{
			InputStream ips = new FileInputStream("data/projets2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			
			
			while ((br.readLine())!=null){
				realNbProjet ++;
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
	
		totalAssertions++;
		assertEquals(realNbProjet, nbprojet);
		bilanAssertions++;
		
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
