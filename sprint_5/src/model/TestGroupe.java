package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestGroupe extends TestCase{

	static int totalAssertions = 0;
	static int bilanAssertions = 0;
	
	String csvPath = "data/groupe.csv";
	Groupe groupeClass = new Groupe(new Projets(new Etudiants(new Encadrer()), new Encadrer()));
	
/////////////////////////////////////////////TEST Etudiants////////////////////////////////////////////////

	/**
	* test_getAllGroupe est un test de la fonction getAllGroupe de la classe Groupe
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getAllGroupe() throws Exception {
	
		ArrayList<String[]> groupeList;
		
		groupeList = groupeClass.getAllGroupe();
		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			int i = 0;
			
			while ((ligne = br.readLine())!=null){
				totalAssertions++ ;
				assertEquals("Vérification retour getAllGroupe : ", groupeList.get(i)[0]+";"+ groupeList.get(i)[1], ligne);
				bilanAssertions++ ;
				i++;
			}
			
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
		
		}
	}
	
	
	
	/**
	* test_getNbGroupe test la fonction getNbGroupe de la classe Groupe
	* 
	* @since sprint_1bis
	* @version sprint_2
	* 
	* @throws Exception Exception de lecture du fichier
	*/
	public void test_getNbGroupe() throws Exception {
		
		int nbGroupe = groupeClass.getNbGroupe();
		
		int realNbGroupe = 0;
		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			
			
			while ((br.readLine())!=null){
				realNbGroupe ++;
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
	
		totalAssertions++;
		assertEquals(realNbGroupe, nbGroupe);
		bilanAssertions++;
		
	}
	
	/**
	* test_getGroupeByName est un test de la fonction getGroupeByName de la classe Groupe
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getGroupeByName() {
		
		ArrayList<String[]> resultID50 = new ArrayList<String[]>();
		ArrayList<String[]> id50Attendu = new ArrayList<String[]>();
		ArrayList<String[]> resultNull = new ArrayList<String[]>();
		
		String[] tabResulat1 = {"A","A"};
		
		id50Attendu.add(tabResulat1);
		
		resultID50 = groupeClass.getGroupeByName("A");
		resultNull = groupeClass.getGroupeByName("ZZZZZZZZZZZZ");//get out
		
		for(int i =0; i<resultID50.size(); i++){
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[0], resultID50.get(i)[0]);
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(id50Attendu.get(i)[1].toString(), resultID50.get(i)[1].toString());
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals(resultNull.size(), 0);
			bilanAssertions++;
		}
	
	}
	
	/**
	 * test_addGroupe test la fonction addGroupe de la classe Groupe
	 */
	public void test_addGroupe() {
		
		String newGroupe = "test";
		
		groupeClass.addGroupe(newGroupe);
		
		Boolean ok = false;
		
		String id = "";
		
		try{
			InputStream ips = new FileInputStream(csvPath);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newGroupe)){
					ok = true;
				}
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
		
		id = groupeClass.getGroupeByName("test").get(0)[0];
		
		totalAssertions++ ;
		assertTrue(ok);
		bilanAssertions++ ;
		
		groupeClass.removeGroupe(id);
	}
	
	/**
	 * test_removeGroupe test la fonction removeGroupe de la classe Groupe
	 * ATTENTION le test ne passe si la fonction getGroupeByName ne marche pas
	 */
	public void test_removeGroupe() {
		
		String newGroupe = "TEST";
		
		groupeClass.addGroupe(newGroupe);
		
		Boolean ok = true;
		
		String id = groupeClass.getGroupeByName("TEST").get(0)[0];
		
		groupeClass.removeGroupe(id);

		
		try{
			InputStream ips = new FileInputStream(csvPath);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newGroupe)){
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
	  * main de la classe Test
	  *
	  * @param args arguments
	  * 
	  * @since sprint_1bis
	  * @version sprint_2
	  */
	  public static void main(String[] args) {
		  
		  junit.textui.TestRunner.run(new TestSuite(TestGroupe.class));
		
		  if (bilanAssertions == totalAssertions) { 
			  System.out.print("Bravo !"); 
		  }
		  else  { 
			  System.out.print("OUPS !"); 
		  }
		
		  System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");
		  
	  } // fin main

}
