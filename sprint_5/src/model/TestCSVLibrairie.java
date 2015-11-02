package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Classe testant toutes les fonctions crées dans le package model
 * @author gkueny
 *
 *@since sprint_2
 *@version srpint_2
 *
 */
public class TestCSVLibrairie extends TestCase{
	
	static int totalAssertions = 0;
	static int bilanAssertions = 0;
	
	
/////////////////////////////////////////////TEST CSVLibrairie////////////////////////////////////////////////

	/**
	 * test_contenu_valeurRetour_readCSV est un test de la fonction readCSV de CSVLibrairie
	 * @throws Exception Exeption de lecture du fichier
	 */
	public void test_contenu_valeurRetour_readCSV() throws Exception {
	  	
		ArrayList<String[]> csvContents = new ArrayList<String[]>();
		
		File csvFileMac = new File("data/test.csv");
		
		String[] tabCSV;
		
		int cpt = 0;
		  
		String[] rigthContents = {"Sexe", "Prénom" ,"Année de naissance", "M", "Alphonse" , "1932", "F" , "Béatrice" ,"1964", "F" ,"Charlotte" , "1988"};
			
		csvContents = CSVLibrairie.readCSV(csvFileMac);
			
		for(int i = 0; i < csvContents.size();i++){
			
			tabCSV = csvContents.get(i);
			
			for(int j = 0; j < tabCSV.length && cpt < rigthContents.length ;j++){
			
				totalAssertions++ ;
				assertTrue("lib.readCSV(csvFile, ',') retourne une arrayList de contenu : " + tabCSV[j] + " , mais il était attendu : "+ rigthContents[cpt], tabCSV[j].equals(rigthContents[cpt]));
				bilanAssertions++ ;
				cpt++;
			 
			}
		 } 
	}
  
	 /**
	  * test_fichier_saveCSV est un test de la fonction saveCSV de la classe CSVLibrairie
	  * @throws Exception Exception de lecture du fichier
	  */
	public void test_fichier_saveCSV() throws Exception {
	  	
	  ArrayList<String[]> csvContents = new ArrayList<String[]>();

	  File csvFileMac = new File("data/testSave.csv");
	  
	  String[] contentsTrueLigne1 = {"Sexe", "Prénom" ,"Année de naissance"} ;
	  String[] contentsTrueLigne2 = {"M", "Alphonse" , "1932"} ;
	  
	  csvContents.add(contentsTrueLigne1);
	  csvContents.add(contentsTrueLigne2);
	  
	  CSVLibrairie.saveCSV(csvFileMac, csvContents, ",");

	  try{
			InputStream ips = new FileInputStream(csvFileMac); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			int i = 0;
			
			while ((ligne = br.readLine())!=null){
				totalAssertions++ ;
				  assertEquals("Vérification retour saveCSV : ", csvContents.get(i)[0]+","+ csvContents.get(i)[1] + "," + csvContents.get(i)[2] , ligne);
				bilanAssertions++ ;
				i++;
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	  
	  csvFileMac.delete();

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
		  
		  junit.textui.TestRunner.run(new TestSuite(TestCSVLibrairie.class));
		
		  if (bilanAssertions == totalAssertions) { 
			  System.out.print("Bravo !"); 
		  }
		  else  { 
			  System.out.print("OUPS !"); 
		  }
		
		  System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");
		  
	  } // fin main

}
