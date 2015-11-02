package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestIntervenants extends TestCase{

	static int totalAssertions = 0;
	static int bilanAssertions = 0;
	private static Projets projets = new Projets(new Etudiants(new Encadrer()), new Encadrer());
	
/////////////////////////////////////////////TEST Etudiants////////////////////////////////////////////////

	/**
	* test_getAllIntervenants_retour est un test de la fonction getAllIntervenants de la classe Intervenants
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getAllIntervenants_retour() throws Exception {
	
		Intervenants intervenantsClass = new Intervenants(projets, new Encadrer());
		ArrayList<String[]> intervenantsList;
		
		intervenantsList = intervenantsClass.getAllIntervenants();
		
		try{
			InputStream ips = new FileInputStream("data/intervenants2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			int i = 0;
			
			while ((ligne = br.readLine())!=null){
				totalAssertions++ ;
				assertEquals("Vérification retour getAllIntervenants : ", intervenantsList.get(i)[0]+";"+ intervenantsList.get(i)[1] + ";" + intervenantsList.get(i)[2] , ligne);
				bilanAssertions++ ;
				i++;
			}
			
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
		
		}
	}
	
	/**
	* test_getIntervenants_byID est un test de la fonction getIntervenants de la classe Intervenants
	* @throws Exception Exception de lecture du fichier
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getIntervenants_byID() throws Exception {
	
		Intervenants intervenantClass = new Intervenants(projets, new Encadrer());
		
		String[] resultID50;
		String[] id50Attendu = {"20", "Evelyne", "TISSIER" };
		String[] resultNull;
		
		resultID50 = intervenantClass.getIntervenants("20");
		resultNull = intervenantClass.getIntervenants("93");
		
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
		assertEquals(resultNull, null);
		bilanAssertions++;
	
	}
	
	/**
	* test_getIntervenantsByName est un test de la fonction getIntervenantsByName de la classe Intervenants
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getIntervenantsByName() {
	
		Intervenants intervenantClass = new Intervenants(projets, new Encadrer());
		
		// System.out.println(etudiantClass.getNbEtu());
		ArrayList<String[]> resultID50 = new ArrayList<String[]>();
		ArrayList<String[]> id50Attendu = new ArrayList<String[]>();
		ArrayList<String[]> resultNull = new ArrayList<String[]>();
		
		String[] tabResulat1 = {"1","Remi","BOULLE"};
		
		id50Attendu.add(tabResulat1);
		
		resultID50 = intervenantClass.getIntervenantsByName("BOULLE");
		resultNull = intervenantClass.getIntervenantsByName("UNICORN");//get out
		
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
			assertEquals(resultNull.size(), 0);
			bilanAssertions++;
		}
	
	}
	
	/**
	* test_getIntervenantsByFirstName est un test de la fonction getIntervenantsByFirstName de la classe Intervenants
	* 
	* @since sprint_1bis
	* @version sprint_2
	*/
	public void test_getIntervenantsByFirstName() {
	
		Intervenants intervenantClass = new Intervenants(projets, new Encadrer());
		
		// System.out.println(etudiantClass.getNbEtu());
		ArrayList<String[]> resultID50 = new ArrayList<String[]>();
		ArrayList<String[]> id50Attendu = new ArrayList<String[]>();
		ArrayList<String[]> resultNull = new ArrayList<String[]>();
		
		String[] tabResulat1 = {"7","Laurent","DEMAY"};
		String[] tabResulat2 = {"8","Laurent","HARDY"};
		
		id50Attendu.add(tabResulat1);
		id50Attendu.add(tabResulat2);
		
		resultID50 = intervenantClass.getIntervenantsByFirstName("Florian");
		resultNull = intervenantClass.getIntervenantsByFirstName("TWOCORN");//get out
		
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
			assertEquals(resultNull.size(), 0);
			bilanAssertions++;
		}
	
	}
	
	/**
	* test_getNbIntervenants test la fonction getNbIntervenants de la classe Intervenants
	* 
	* @since sprint_1bis
	* @version sprint_2
	* 
	* @throws Exception Exception de lecture du fichier
	*/
	public void test_getNbIntervenants() throws Exception {
	
		Intervenants intervenantClass = new Intervenants(projets, new Encadrer());
		
		int nbIntervenant = intervenantClass.getNbIntervenants();
		
		int realNbIntervenant = 0;
		
		try{
			InputStream ips = new FileInputStream("data/intervenants2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			
			
			while ((br.readLine())!=null){
				realNbIntervenant ++;
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
	
		totalAssertions++;
		assertEquals(realNbIntervenant, nbIntervenant);
		bilanAssertions++;
		
	}
	
	
	public void test_getAffichage() {
		
		Intervenants intervenantClass = new Intervenants(projets, new Encadrer());
		ArrayList<String[]> intervenantsList;
		
		intervenantsList = intervenantClass.getAffichage(projets, new Sujets());
		
		String[] retourLigne1 = {"1", "Remi", "BOULLE","12", "RegExp", "Superviseur"};
		
		totalAssertions++ ;
		assertEquals("Vérification retour getAffichage : ", retourLigne1[0] + "" + retourLigne1[1] + "" + retourLigne1[2] + "" + retourLigne1[3] + "" + retourLigne1[4] + "" + retourLigne1[5] , intervenantsList.get(0)[0]+ "" + intervenantsList.get(0)[1]+ "" + intervenantsList.get(0)[2]+ "" + intervenantsList.get(0)[3]+ "" + intervenantsList.get(0)[4]+ "" + intervenantsList.get(0)[5]);
		bilanAssertions++ ;
				
	}
	
	/**
	 * test_setIntervenants test la fonction setIntervenants de la classe Intervenants
	 */
	public void test_setIntervenants(){
		
		Intervenants intervenantClass = new Intervenants(projets, new Encadrer());
		
		String[] newIntervenant = {"21","testSetIntervenant", "TEST"};
		String[] realIntervenant = {"21","Michèle", "VERDIER"};
		
		intervenantClass.setIntervenants(newIntervenant);
		
		Boolean ok = false;
		
		try{
			InputStream ips = new FileInputStream("data/intervenants2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains("testSetIntervenant") && ligne.contains("TEST")){
	
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
			
		intervenantClass.setIntervenants(realIntervenant);
	}
	
	
	/**
	 * test_addIntervenants test la fonction addIntervenants de la classe Intervenants
	 * ATTENTION le test passe si la fonction geIntervenantByName et removeIntervenant ne marche pas, cependant elles doivent être implementés de plus  un intervenant test sera présent dans le fichier
	 */
	public void test_addIntervenants() {
		
		Intervenants intervenantClass = new Intervenants(projets, new Encadrer());
		
		String[] newIntervenant = {"TEST", "test"};
		
		intervenantClass.addIntervenants(newIntervenant[1], newIntervenant[0]);
		
		Boolean ok = false;
		
		String id = "";
		
		try{
			InputStream ips = new FileInputStream("data/intervenants2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newIntervenant[0])  && ligne.contains(newIntervenant[1])){
					ok = true;
				}
			}
		
			br.close(); 
		
		}catch (Exception e){
		
			System.out.println(e.toString());
	
		}
		
		id = intervenantClass.getIntervenantsByName("TEST").get(0)[0];

		totalAssertions++ ;
		assertTrue(ok);
		bilanAssertions++ ;
		
		intervenantClass.removeIntervenant(id);
	}
	
	/**
	 * test_removeIntervenant test la fonction removeIntervenant de la classe Intervenants
	 * ATTENTION le test ne passe si la fonction getEtuByName et addIntervenants ne marche pas
	 */
	public void test_removeIntervenant() {
		
		Intervenants intervenantsClass = new Intervenants(projets, new Encadrer());
		
		String[] newIntervenant = {"TESTRemove", "testremove"};
		
		intervenantsClass.addIntervenants(newIntervenant[1], newIntervenant[0]);
		
		Boolean ok = true;
		
		String id = intervenantsClass.getIntervenantsByName("TESTRemove").get(0)[0];
		
		intervenantsClass.removeIntervenant(id);

		
		try{
			InputStream ips = new FileInputStream("data/intervenants2014_2015.csv"); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			
			while ((ligne = br.readLine())!=null){
				if(ligne.contains(newIntervenant[0])  || ligne.contains(newIntervenant[1])){
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
		  
		  junit.textui.TestRunner.run(new TestSuite(TestIntervenants.class));
		
		  		  
		  if (bilanAssertions == totalAssertions) { 
			  System.out.print("Bravo !"); 
		  }
		  else  { 
			  System.out.print("OUPS !"); 
		  }
		
		  System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");
		  
	  } // fin main

}
