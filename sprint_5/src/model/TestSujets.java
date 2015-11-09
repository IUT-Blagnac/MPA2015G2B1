package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSujets extends TestCase{

	static int totalAssertions = 0;
	static int bilanAssertions = 0;

	/**
	* test_getAllSujets_retour est un test de la fonction getAllSujets de la classe Sujet
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_2
	*/
	public void test_getAllSujets_retour() throws Exception {
	
		Sujets sujetClass = new Sujets();
		ArrayList<String[]> sujetsList;
		
		sujetsList = sujetClass.getAllSujets();
		
		try{
			InputStream ips = new FileInputStream("data/sujets2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			int i = 0;
			
			while ((ligne = br.readLine())!=null){
				totalAssertions++ ;
				assertEquals("Vérification retour getAllSujets  : ", sujetsList.get(i)[0]+";"+ sujetsList.get(i)[1] + ";" + sujetsList.get(i)[2], ligne);
				bilanAssertions++ ;
				i++;
			}
			
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
		
		}
	}
	
	/**
	* test_getSujet est un test de la fonction getSujet de la classe Sujets
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_2
	*/
	public void test_getSujet() throws Exception {
	
		Sujets sujetClass = new Sujets();
		
		String[] resultID3;
		String[] id3Attendu = { "3", "Architekt", "Architekt" };
		String[] resultNull;
		
		resultID3 = sujetClass.getSujet(3);
		resultNull = sujetClass.getSujet(93);
		
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
	* test_getSujetByNom est un test de la fonction getSujetByNom de la classe Sujets
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_2
	*/
	public void test_getSujetByNom() throws Exception {
	
		Sujets sujetClass = new Sujets();
		
		String[] resultID3;
		String[] id3Attendu = { "3", "Architekt", "Architekt" };
		String[] resultNull;
		
		resultID3 = sujetClass.getSujetByNom("Architekt");
		resultNull = sujetClass.getSujetByNom("dkdepz^k");
		
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
	* test_getNbSujets test la fonction getNbSujets de la classe Sujets
	* 
	* @since sprint_2
	* 
	* @throws Exception Exception de lecture du fichier
	*/
	public void test_getNbSujets() throws Exception {
	
		Sujets sujetClass = new Sujets();
		
		int nbSujet = sujetClass.getNbSujets();
		
		int realNbSujet = 0;
		
		try{
			InputStream ips = new FileInputStream("data/sujets2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			
			
			while ((br.readLine())!=null){
				realNbSujet ++;
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
	
		totalAssertions++;
		assertEquals(realNbSujet, nbSujet);
		bilanAssertions++;
		
	}
	
	
	/**
	 * test_setSubject test la fonction setSubject de la classe Sujets
	 */
	public void test_setSubject(){
		
		Sujets sujetClass = new Sujets();
		String[] newSujet ={"3","testSet","test"};
		String[] realSujet ={"3","Architekt","Architekt"};
		sujetClass.setSubject(newSujet);
		
		Boolean ok = false;
		
		try{
			InputStream ips = new FileInputStream("data/sujets2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains("testSet")){
	
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
			
		sujetClass.setSubject(realSujet);
	}
	
	
	
	/**
	 * test_addSubject test la fonction addSubject de la classe Sujet
	 * Pour passer la fonction getSujetByName et removeSujet doivent marcher pour que le test marche
	 */
	public void test_addSubject() {
		
		Sujets sujetsClass = new Sujets();
		
		String[] newSujet = {"TEST", "test"};
		
		sujetsClass.addSubject(newSujet[0], newSujet[1]);
		
		Boolean ok = false;
		
		String id = "";
		
		try{
			InputStream ips = new FileInputStream("data/sujets2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newSujet[0])  && ligne.contains(newSujet[1])){
					ok = true;
				}
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
		
		id = sujetsClass.getSujetByNom("TEST")[0];
		
		totalAssertions++ ;
		assertTrue(ok);
		bilanAssertions++ ;
		
		sujetsClass.removeSujet(Integer.parseInt(id));
	}
	
	/**
	 * test_removeSujet test la fonction removeSujet de la classe sujet
	 * ATTENTION le test ne passe si la fonction getSujetByNom ne marche pas
	 */
	public void test_removeSujet() {
		
		Sujets sujetClass = new Sujets();
		
		String[] newSujet = {"TEST", "test"};
		
		sujetClass.addSubject(newSujet[0], newSujet[1]);
		
		Boolean ok = true;
		
		String id = sujetClass.getSujetByNom("TEST")[0];
		
		sujetClass.removeSujet(Integer.parseInt(id));

		
		try{
			InputStream ips = new FileInputStream("data/sujets2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newSujet[0])  && ligne.contains(newSujet[1])){
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
		  
		  junit.textui.TestRunner.run(new TestSuite(TestSujets.class));
		
		  if (bilanAssertions == totalAssertions) { 
			  System.out.print("Bravo !"); 
		  }
		  else  { 
			  System.out.print("OUPS !"); 
		  }
		
		  System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");
		  
	  } // fin main

}
