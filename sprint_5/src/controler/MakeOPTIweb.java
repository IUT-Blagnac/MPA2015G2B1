package controler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.Encadrer;
import model.Etudiants;
import model.Intervenants;
import model.Projets;
import model.Sujets;


/**
 * MakeOPTIweb permet de créer une page html de consultation des projets tutorés gérés par OPTI.
 * @author Mathis
 * @since sprint4
 * @version sprint4
 */
public class MakeOPTIweb {
	
	private String destination;
	
	/**
	 * Constructeur de la classe MakeOPTI avec parametres.
	 * @param str
	 * 		str indique le nom du fichier de destination.
	 * @since sprint4
	 * @version sprint4
	 */
	public MakeOPTIweb(String str){
		this.destination = str;
		
		String begin = "";
		String credit = "";
		String projet = "";
		String sujet = "";
		String etudiant = "";
		String intervenant = "";
		String end = "";
		
		String intJSON = "";
		String etuJSON = "";
		
		System.out.println("HTML :\n0/8");
		HTMLEcrase();
		
		System.out.println("1/8");
		begin = writeHTMLBegin();
		
		System.out.println("2/8");
		credit = writeHTMLCredits();
		
		System.out.println("3/8");
		projet = writeHTMLProjet();
		
		System.out.println("4/8");
		sujet = writeHTMLSujets();
		
		System.out.println("5/8");
		etudiant = writeHTMLEtu();
		
		System.out.println("6/8");
		intervenant = writeHTMLInt();
		
		System.out.println("7/8");
		end = writeHTMLEnd();
		
		System.out.println("8/8\nJSON :\n0/2");
		
		etuJSON = writeJSONEtu();
		System.out.println("1/2");
		
		intJSON = writeJSONInt();
		System.out.println("2/2");
		
		try {
			BufferedWriter fichier = new BufferedWriter(new FileWriter(destination, true));
			
			fichier.write(begin);
			fichier.write(credit);
			fichier.write(projet);
			fichier.write(sujet);
			fichier.write(etudiant);
			fichier.write(intervenant);
			fichier.write(end);
			
			fichier.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedWriter fichier = new BufferedWriter(new FileWriter("../OPTIweb/test/intervenants2014_2015.json", false));
			
			fichier.write(intJSON);
			
			fichier.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedWriter fichier = new BufferedWriter(new FileWriter("../OPTIweb/test/etudiants2014_2015.json", false));

			fichier.write(etuJSON);
			
			fichier.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * La méthode main créé un objet MakeOPTIweb
	 * @param args
	 * @since sprint4
	 * @version sprint4
	 */
	public static void main(String[] args) {
		new MakeOPTIweb("../OPTIweb/test/OPTIweb.html");
	}
	
	/**
	 * La méthode HTMLEcrase efface le contenu du fichier de destination
	 * @since sprint4
	 * @version sprint4
	 */
	private void HTMLEcrase(){
		try {
			BufferedWriter fichier = new BufferedWriter(new FileWriter(destination, false));
			fichier.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * La méthode writeHTMLBegin permet d'écrire dans le document, le debut du fichier html
	 * @since sprint4
	 * @version sprint4
	 */
	public static String writeHTMLBegin(){

		String content = "";

		
		content += "<!DOCTYPE html>";
		content += System.lineSeparator();
		content += "<html>";
		content += System.lineSeparator();
		content += "<head>";
		content += System.lineSeparator();
		content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
		content += System.lineSeparator();
		content += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";
		content += System.lineSeparator();
		content += "<meta name=\"generator\" content=\"OPTIweb VOPTIweb\" />";
		content += System.lineSeparator();
		content += "<title>0.1 - V0.1</title>";
		content += System.lineSeparator();
		content += "<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />";
		content += System.lineSeparator();
		content += "<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" />";
		content += System.lineSeparator();
		content += "<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\">";
		content += System.lineSeparator();
		content += "</script>";
		content += System.lineSeparator();
		content += "<script>";
		content += System.lineSeparator();
		content += "$(document).bind('mobileinit',function(){";
		content += System.lineSeparator();
		content += "$.mobile.changePage.defaults.changeHash = false;";
		content += System.lineSeparator();
		content += "$.mobile.hashListeningEnabled = false;";
		content += System.lineSeparator();
		content += "$.mobile.pushStateEnabled = false;";
		content += System.lineSeparator();
		content += "};";
		content += System.lineSeparator();
		content += "</script>";
		content += System.lineSeparator();
		content += "<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\">";
		content += System.lineSeparator();
		content += "</script><style type='text/css'>";
		content += System.lineSeparator();
		content += "@media all and (orientation:portrait) { .landscape {display: none;} }";
		content += System.lineSeparator();
		content += "@media all and (orientation:landscape) { .landscape {display: inline;} }";
		content += System.lineSeparator();
		content += "</style>";
		content += System.lineSeparator();
		content += "</head>";
		content += System.lineSeparator();
		content += "<body>";
		content += System.lineSeparator();
		///
		content += "<!-- DEBUT page accueil -->";
		content += System.lineSeparator();
		content += "<div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V0.1\">";
		content += System.lineSeparator();
		content += "<div data-role=\"header\" data-add-back-btn=\"true\">";
		content += System.lineSeparator();
		content += "<h1>P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">orés</span> 2014-2015<br/>Département INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1>";
		content += System.lineSeparator();
		content += "<a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Crédits</a>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"content\">";
		content += System.lineSeparator();
		content += "<ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">";
		content += System.lineSeparator();
		content += "  <li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li>";
		content += System.lineSeparator();
		content += "  <li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li>";
		content += System.lineSeparator();
		content += "  <li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li>";
		content += System.lineSeparator();
		content += "  <li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li>";
		content += System.lineSeparator();
		content += "</ul>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"footer\">";
		content += System.lineSeparator();
		content += " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<!-- FIN page accueil -->";
		content += System.lineSeparator();

		
		return content;
	}

	/**
	 * La méthode writeHTMLEnd permet d'écrire dans le document, lz fin du fichier html
	 * @since sprint4
	 * @version sprint4
	 */
	public static String writeHTMLEnd(){

		String content = "";
		
		content += "<script>";
		content += System.lineSeparator();
		content += "// li click handler which fills the projects search bar";
		content += System.lineSeparator();
		content += "// with the value of the current data-find attribute";
		content += System.lineSeparator();
		content += "$( 'li[data-find]' ).on( 'click',function(event){";
		content += System.lineSeparator();
		content += "$(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change'); });";
		content += System.lineSeparator();
		content += "</script>";
		content += System.lineSeparator();
		content += "</body>";
		content += System.lineSeparator();
		content += "</html>";

		return content;

	}
	
	/**
	 * La méthode writeHTMLAccueil permet d'écrire dans le document, la partie accueil.
	 * @since sprint4
	 * @version sprint4
	 */
	public static String writeHTMLProjet(){

		Etudiants etudiants = new Etudiants();
		Encadrer encadrer = new Encadrer();
		Sujets sujets = new Sujets();
		Projets projets = new Projets(etudiants, encadrer);
		Intervenants intervenants = new Intervenants(projets);
		
		String content = "";
		
		content += "<!-- DEBUT page projets -->";
		content += System.lineSeparator();
		content +="<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">";
		content += System.lineSeparator();
		content +="<div data-role=\"header\" data-add-back-btn=\"true\">";
		content += System.lineSeparator();
		content +="<h1>Projets 2014-2015</h1>";
		content += System.lineSeparator();
		content +="</div>";
		content += System.lineSeparator();
		content +="<div data-role=\"content\">";
		content += System.lineSeparator();
		content +="<form class=\"ui-filterable\">";
		content +="<input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\">";
		content +="</form>";
		content +="<ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\">";
		ArrayList<String[]> allPro = projets.getAffichageForHTML(etudiants, intervenants, sujets);
		
		for(int i = 0; i < allPro.size(); i++){

			String[] projetCourant = allPro.get(i);//1 group, 2 sujet(nom;titre), 3 client, 4 supperviseur, 5 support tech, 6 etudiants
			
			content +="<li>";
			content +="<p>";
			content +="<b>[" + projetCourant[2].split(";")[0] + "]</b> " + projetCourant[2].split(";")[1];
			content +="</p>";
			content +="<p>";
			content +="<b>Client :</b> " + projetCourant[3];
			content +="</p>";
			content +="<p>";
			content +="<b>Superviseur :</b> " + projetCourant[4];
			content +="</p>";
			content +="<p>";
			content +="<b>Groupe " + projetCourant[1] + " :</b> " + projetCourant[6];
			content +="</p>";
			content +="</li>";
		
		}
		
		content +="</ol>";
		content += System.lineSeparator();
		content +="</div>";
		content += System.lineSeparator();
		content +="<div data-role=\"footer\">";
		content += System.lineSeparator();
		content +="<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4>";
		content += System.lineSeparator();
		content +="</div>";
		content += System.lineSeparator();
		content +="</div>";
		content += System.lineSeparator();
		content +="<!-- FIN page projets -->";
		content += System.lineSeparator();
		
		return content;
	}
	
	/**
	 * La méthode writeHTMLCredits permet d'écrire dans le document, la partie Credit
	 * @since sprint4
	 * @version sprint4
	 */
	public static String writeHTMLCredits(){

		String content = "";
		
		content += "<div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V0.1 - Crédits\">";
		content += System.lineSeparator();
		content += "<div data-role=\"header\" data-add-back-btn=\"true\">";
		content += System.lineSeparator();
		content += "<h1>Crédits</h1>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"content\">";
		content += System.lineSeparator();
		content += "<p>Cette application a été réalisée dans le cadre du module M3301/MPA du DUT Informatique à l'IUT de Blagnac.</p>";
		content += System.lineSeparator();
		content += "<ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\">";
		content += System.lineSeparator();
		content += "<li data-role=\"list-divider\">Product Owner</li>";
		content += System.lineSeparator();
		content += "<li>André PÉNINOU</li>";
		content += System.lineSeparator();
		content += "<li>Université Toulouse 2 - IUT de Blagnac<br/>Département INFORMATIQUE</li>";
		content += System.lineSeparator();
		content += "</ul>";
		content += System.lineSeparator();
		content += "<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">";
		content += System.lineSeparator();
		content += "<li data-role=\"list-divider\">Membres de l'équipe enseignante</li>";
		content += System.lineSeparator();
		content += "<li>Jean-Michel BRUEL</li><li>Jean-Michel INGLEBERT</li><li>André PÉNINOU</li><li>Olivier ROQUES</li>";
		content += System.lineSeparator();
		content += "</ul>";
		content += System.lineSeparator();
		content += "<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">";
		content += System.lineSeparator();
		content += "<li data-role=\"list-divider\">Membres de l'équipe de développement</li>";
		content += System.lineSeparator();
		content += "<li>Alex JACQUOT-FERNANDEZ</li><li>Nigel KOZLOWSKY</li><li>Gaëtan KUENY</li><li>Mathis PEZOU</li><li>Pol-Hervé RAILLARD</li><li>Nicolas RIBEREAU</li>";
		content += System.lineSeparator();
		content += "</ul>";
		content += System.lineSeparator();
		content += "<ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\">";
		content += System.lineSeparator();
		content += "<li data-role=\"list-divider\">Propulsé par</li>";
		content += System.lineSeparator();
		content += "<li><a href=\"http://jquerymobile.com/\" target=\"autrePage\">http://jquerymobile.com/</a></li>";
		content += System.lineSeparator();
		content += "<li><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\">http://fortawesome.github.io/Font-Awesome/</a></li>";
		content += System.lineSeparator();
		content += "</ul>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"footer\">";
		content += System.lineSeparator();
		content += "<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();

		return content;
	}
	
	/**
	 * La méthode writeHTMLEtu permet d'écrire dans le document, la partie Etudiant
	 * @since sprint4
	 * @version sprint4
	 */
	public static String writeHTMLEtu(){
		Etudiants etudiants = new Etudiants();
			
		String content = "";
		content += "<!-- DEBUT page etudiants -->";
		content += System.lineSeparator();
		content += "<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\">";
		content += System.lineSeparator();
		content += "<div data-role=\"header\" data-add-back-btn=\"true\">";
		content += System.lineSeparator();
		content += "<h1>Etudiants 2014-2015</h1>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"content\">";
		content += System.lineSeparator();
		content += "<form class=\"ui-filterable\"><input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\"></form><ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\"><li data-role=\"list-divider\">Etudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span></li>";

		ArrayList<String[]> allEtu = etudiants.getAllEtu();

		Collections.sort(allEtu, new Comparator<String[]>() {
	        @Override
	        public int compare(String[]  etu1, String[]  etu2)
	        {

	            return  etu1[2].compareTo(etu2[2]);
	        }
	    });
		
		for(int i = 0; i < allEtu.size(); i++){
			
			String[] etudiantCourant = allEtu.get(i);
			if(!etudiantCourant[1].trim().equals("id")){
				content += "<li data-find=\""+ etudiantCourant[3] + " " + etudiantCourant[2] + "\"><a href=\"#projets\">" + etudiantCourant[2] + " " + etudiantCourant[3] + "<span class=\"ui-li-count\" title=\"Groupe\">Groupe " + etudiantCourant[0] + "</span></a></li>";

			}

		}

		content += "</ol>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"footer\">";
		content += System.lineSeparator();
		content += "<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<!-- FIN page etudiants -->";
		content += System.lineSeparator();
		
		return content;
	}
	
	/**
	 * La méthode writeHTMLInt permet d'écrire dans le document, la partie Intervenant
	 * @since sprint4
	 * @version sprint4
	 */
	public static String writeHTMLInt(){
		Intervenants intervenants = new Intervenants(new Projets(new Etudiants(), new Encadrer()));
		
		String content = "";
		
		content += "<!-- DEBUT page intervenants -->";
		content += System.lineSeparator();
		content += "<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1\">";
		content += System.lineSeparator();
		content += "<div data-role=\"header\" data-add-back-btn=\"true\">";
		content += System.lineSeparator();
		content += "<h1>Intervenants 2014-2015</h1>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"content\">";
		content += System.lineSeparator();
		content += "<form class=\"ui-filterable\"><input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\"></form><ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\"><li data-role=\"list-divider\">Intervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span></li>";

		ArrayList<String[]> allIntervenant = intervenants.getAllIntervenants();

		for(int i = 1; i < allIntervenant.size(); i++){

			String[] intervenantsCourant = allIntervenant.get(i);
		
			content += "<li data-find=\""+ intervenantsCourant[2] + " " + intervenantsCourant[1] + "\"><a href=\"#projets\">" + intervenantsCourant[2] + " " + intervenantsCourant[1] + "<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> " + intervenants.getNbClient(intervenantsCourant[0]) + "</span><span class=\"ui-li-count\" title=\"Superviseur\"> " + intervenants.getNbSuperviseur(intervenantsCourant[0]) + "</span></a></li>";
		}

		content += "</ul>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"footer\">";
		content += System.lineSeparator();
		content += "<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<!-- FIN page intervenants -->";
		content += System.lineSeparator();
		
		return content;
	}
	
	/**
	 * La méthode writeHTMLSujets permet d'écrire dans le document, la partie Sujet
	 * @since sprint4
	 * @version sprint4
	 */
	public static String writeHTMLSujets(){
			
		String content = "";
		
		content += "<!-- DEBUT page sujets -->";
		content += System.lineSeparator();
		content += "<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\">";
		content += System.lineSeparator();
		content += "<div data-role=\"header\" data-add-back-btn=\"true\">";
		content += System.lineSeparator();
		content += "<h1>Sujets 2014-2015</h1>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"content\">";
		content += System.lineSeparator();
		content += "<form class=\"ui-filterable\"><input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\"></form><ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\"><li data-role=\"list-divider\">Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span></li>";
		
		Sujets suj = new Sujets();
		ArrayList<String[]> allSuj = new ArrayList<String[]>();
		
		allSuj = suj.getAllSujets();
		
		for (int i=1;i<allSuj.size();i++){
		
			content += "<li data-find=\"["+allSuj.get(i)[1] +"]\"><a href=\"#projets\">["+allSuj.get(i)[1] +"] <br/><div style=\"white-space:normal;\"><span><b>" + allSuj.get(i)[2] + "</b></span><span class=\"ui-li-count\">"+suj.getGroupe(allSuj.get(i)[0]) +"</span></div></a></li>";
		}
			
		content += "</ol>";
		content += System.lineSeparator();
		
		
		content += "</div>";
		content += System.lineSeparator();
		content += "<div data-role=\"footer\">";
		content += System.lineSeparator();
		content += "<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "</div>";
		content += System.lineSeparator();
		content += "<!-- FIN page sujets -->";

		return content;
	}
	
	/**
	 * 
	 */
	public static String writeJSONInt(){
		
			
			Intervenants inter = new Intervenants(new Projets(new Etudiants(), new Encadrer()));
			ArrayList<String[]> allInt = new ArrayList<String[]>();
			
			String content = "";
			
			allInt = inter.getAllIntervenants();
			
			content += "[";
			content += System.lineSeparator();
			
			for (int i=1;i<allInt.size();i++){
				if(i == 1){
					content += "{\"prenom\": \"" +allInt.get(i)[1]+ "\",";
				}else{
					content += ",{\"prenom\": \"" +allInt.get(i)[1]+ "\",";
					
				}
				
				content += System.lineSeparator();
				
				content += "\"nom\": \"" +allInt.get(i)[2]+ "\"}";
				
				content += System.lineSeparator();
				
			}

			content += "]";

			return content;
	}
	
	/**
	 * 
	 */
	public static String writeJSONEtu(){


			Etudiants etudiants = new Etudiants();

			ArrayList<String[]> allEtu = new ArrayList<String[]>();

			allEtu = etudiants.getAllEtu();
			
			String content = "";
			
			content += "[" ;
			content += System.lineSeparator();

			for(int i = 1; i < allEtu.size(); i++){
				content += "{\"group\": \"" +allEtu.get(i)[0]+ "\",";
				content += System.lineSeparator();
				content += "\"id\": \"" +allEtu.get(i)[1]+ "\",";
				content += System.lineSeparator();
				content += "\"nom\": \"" +allEtu.get(i)[2]+ "\",";
				content += System.lineSeparator();
				if(i == allEtu.size() - 1){
					content += "\"prenom\": \"" +allEtu.get(i)[3]+ "\"}";
				}else {
					content += "\"prenom\": \"" +allEtu.get(i)[3]+ "\"},";
				}
				content += System.lineSeparator();
				
			}
			
			content += "]";

			return(content);

	}
	
	/**
	 * getDestination est un geteur revoyant l'adresse de destination
	 * @return reourne la destination du fichier à écrire
	 */
	public String getDestination(){
		return destination;
	}
	
}
