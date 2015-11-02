package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestEncadrer extends TestCase{

	static int totalAssertions = 0;
	static int bilanAssertions = 0;

	String csvPath = "data/encadrer.csv";
	Encadrer encadrerClass = new Encadrer();
	/**
	* test_getAllEncadrer est un test de la fonction getAllEncadrer de la classe Encadrer
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_2
	*/
	public void test_getAllEncadrer() throws Exception {
	
		ArrayList<String[]> encadrerList;
		
		encadrerList = encadrerClass.getAllEncadrer();
		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			int i = 0;
			
			while ((ligne = br.readLine())!=null){
				totalAssertions++ ;
				assertEquals("Vérification retour getAllEncadrer : ", encadrerList.get(i)[0]+";"+ encadrerList.get(i)[1] + ";" + encadrerList.get(i)[2], ligne);
				bilanAssertions++ ;
				i++;
			}
			
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
		
		}
	}

	
	/**
	* test_getNbEncadrer test la fonction getNbEncadrer de la classe Encadrer
	* 
	* @since sprint_2
	* 
	* @throws Exception Exception de lecture du fichier
	*/
	public void test_getNbEncadrer() throws Exception {
	
		
		int nbEncadrer = encadrerClass.getNbEncadrer();
		
		int realNbEncadrer = 0;
		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			while ((br.readLine())!=null){
				realNbEncadrer ++;
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
	
		totalAssertions++;
		assertEquals("Vérification retour getNbEncadrer : ",realNbEncadrer, nbEncadrer);
		bilanAssertions++;
		
	}
	
	/**
	* test_getAllEncadrerForIdProjet test la fonction getAllEncadrerForIdProjet de la classe Encadrer
	* 
	* @since sprint_2
	*/
	public void test_getAllEncadrerForIdProjet(){
		
		String[] reponse1 = {"1","20","1"};
		String[] reponse2 = {"1","3","2"};
		
		ArrayList<String[]> retour = encadrerClass.getAllEncadrerForIdProjet(reponse1[0]);
		

		totalAssertions++;
		assertEquals("Vérification retour getAllEncadrerForIdProjet : ",2, retour.size());
		bilanAssertions++;
		
		if(retour.size()>1){
			totalAssertions++;
			assertEquals("Vérification retour getAllEncadrerForIdProjet : ",reponse1[0]+";"+reponse1[1]+";"+reponse1[2], retour.get(0)[0]+";"+retour.get(0)[1]+";"+retour.get(0)[2]);
			bilanAssertions++;
			
			totalAssertions++;
			assertEquals("Vérification retour getAllEncadrerForIdProjet : ",reponse2[0]+";"+reponse2[1]+";"+reponse2[2], retour.get(1)[0]+";"+retour.get(1)[1]+";"+retour.get(1)[2]);
			bilanAssertions++;
		}
		
	}
	
	
	
	/**
	 * test_addEncadrer test la fonction addEncadrer de la classe Encadrer
	 * Pour passer la fonction removeEncadrer doit marcher pour que le test marche
	 */
	public void test_addEncadrer() {
		
		String[] newEncadrer = {"TESTID","TESTIntervenant", "testRole"};
		
		encadrerClass.addEncadrer(newEncadrer[0], newEncadrer[1], newEncadrer[2]);
		
		Boolean ok = false;
		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newEncadrer[0])  && ligne.contains(newEncadrer[1])&& ligne.contains(newEncadrer[2])){
					ok = true;
				}
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
		
		String[] encadrerToremove = encadrerClass.getAllEncadrerForIdProjet("TESTID").get(0);
		
		totalAssertions++ ;
		assertTrue(ok);
		bilanAssertions++ ;
		
		encadrerClass.removeEncadrer(encadrerToremove[0],encadrerToremove[1]);
	}
	
	/**
	 * test_removeEncadrer test la fonction removeEncadrer de la classe Encadrer
	 */
	public void test_removeEncadrer() {
		
		String[] newEncadrer = {"TESTIDREMOVE","TESTIntervenant", "testRole"};
		
		encadrerClass.addEncadrer(newEncadrer[0], newEncadrer[1],newEncadrer[2]);
		
		Boolean ok = true;
		
		encadrerClass.removeEncadrer(newEncadrer[0],newEncadrer[1]);

		
		try{
			InputStream ips = new FileInputStream(csvPath); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newEncadrer[0])  && ligne.contains(newEncadrer[1])  && ligne.contains(newEncadrer[2])){
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
		  
		  junit.textui.TestRunner.run(new TestSuite(TestEncadrer.class));
		
		  if (bilanAssertions == totalAssertions) { 
			  System.out.print("Bravo !"); 
		  }
		  else  { 
			  System.out.print("OUPS !"); 
		  }
		
		  System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");
		  
	  } // fin main

}
