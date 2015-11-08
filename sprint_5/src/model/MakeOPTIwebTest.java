package model;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import controler.MakeOPTIweb;


/**
 * MakeOPTIwebTest est la classe testant la classe MakeOPTIweb
 * @author gkueny
 *
 */
public class MakeOPTIwebTest extends TestCase{

	static int totalAssertions = 0;
	static int bilanAssertions = 0;
	
	/**
	 * La méthode  test_writeHTMLBegin test la méthode writeHTMLBegin
	 * 
	 * @since sprint_4
	 */
	public void test_writeHTMLBegin() {
		
		String[] donnees = {"<!DOCTYPE html>",
							"<html>",
							"<head>",
							"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />",
							"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">",
							"<meta name=\"generator\" content=\"OPTIweb VOPTIweb\" />",
							"<title>0.1 - V0.1</title>",
							"<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />",
							"<link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" />",
							"<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\">",
							"</script>",
							"<script>",
							"$(document).bind('mobileinit',function(){",
							"$.mobile.changePage.defaults.changeHash = false;",
							"$.mobile.hashListeningEnabled = false;",
							"$.mobile.pushStateEnabled = false;",
							"});",
							"</script>",
							"<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\">",
							"</script><style type='text/css'>",
							"@media all and (orientation:portrait) { .landscape {display: none;} }",
							"@media all and (orientation:landscape) { .landscape {display: inline;} }",
							"</style>",
							"</head>",
							"<body>",
							 "<!-- DEBUT page accueil -->",
							 "<div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V0.1\">",
							 "<div data-role=\"header\" data-add-back-btn=\"true\">",
							 "<h1>P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">orés</span> 2014-2015<br/>Département INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1>",
							 "<a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Crédits</a>",
							 "</div>",
							 "<div data-role=\"content\">",
							 "<ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">",
							 "  <li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li>",
							 "  <li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li>",
							 "  <li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li>",
							 "  <li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li>",
							 "</ul>",
							 "</div>",
							 "<div data-role=\"footer\">",
							 " <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>",
							 "</div>",
							 "</div>",
							 "<!-- FIN page accueil -->"
							};
		
		String [] retour = MakeOPTIweb.writeHTMLBegin().split(System.lineSeparator());
		
		for(int i = 0; i < retour.length && i < donnees.length; i++){
			totalAssertions++ ;
			assertEquals(donnees[i], retour[i]);
			bilanAssertions++ ;
		}
	}
	
	/**
	 * La méthode test_writeHTMLEnd test la méthode writeHTMLEnd
	 * 
	 * @since sprint_4
	 */
	public void test_writeHTMLEnd() {
		
		String[] donnees = {"<script>",
							"// li click handler which fills the projects search bar",
							"// with the value of the current data-find attribute",
							"$( 'li[data-find]' ).on( 'click',function(event){",
							"$(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change'); });",
							"</script>",
							"</body>",
							"</html>"
							};
		
		String [] retour = MakeOPTIweb.writeHTMLEnd().split(System.lineSeparator());
		
		for(int i = 0; i < retour.length && i < donnees.length; i++){
			totalAssertions++ ;
			assertEquals(donnees[i], retour[i]);
			bilanAssertions++ ;
		}
	}
	
	 /** La méthode test_writeHTMLCredits test la méthode writeHTMLCredits
	 * 
	 * @since sprint_4
	 */
	public void test_writeHTMLCredits() {
		
		String[] donnees = {"<div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V0.1 - Crédits\">",
							"<div data-role=\"header\" data-add-back-btn=\"true\">",
							"<h1>Crédits</h1>",
							"</div>",
							"<div data-role=\"content\">",
							"<p>Cette application a été réalisée dans le cadre du module M3301/MPA du DUT Informatique à l'IUT de Blagnac.</p>",
							"<ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\">",
							"<li data-role=\"list-divider\">Product Owner</li>",
							"<li>André PÉNINOU</li>",
							"<li>Université Toulouse 2 - IUT de Blagnac<br/>Département INFORMATIQUE</li>",
							"</ul>",
							"<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">",
							"<li data-role=\"list-divider\">Membres de l'équipe enseignante</li>",
							"<li>Jean-Michel BRUEL</li><li>Jean-Michel INGLEBERT</li><li>André PÉNINOU</li><li>Olivier ROQUES</li>",
							"</ul>",
							"<ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">",
							"<li data-role=\"list-divider\">Membres de l'équipe de développement</li>",
							"<li>Alex JACQUOT-FERNANDEZ</li><li>Nigel KOZLOWSKY</li><li>Gaëtan KUENY</li><li>Mathis PEZOU</li><li>Pol-Hervé RAILLARD</li><li>Nicolas RIBEREAU</li>",
							"</ul>",
							"<ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\">",
							"<li data-role=\"list-divider\">Propulsé par</li>",
							"<li><a href=\"http://jquerymobile.com/\" target=\"autrePage\">http://jquerymobile.com/</a></li>",
							"<li><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\">http://fortawesome.github.io/Font-Awesome/</a></li>",
							"</ul>",
							"</div>",
							"<div data-role=\"footer\">",
							"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>",
							"</div>",
							"</div>"
							};
		
		String [] retour = MakeOPTIweb.writeHTMLCredits().split(System.lineSeparator());
		
		for(int i = 0; i < retour.length && i < donnees.length; i++){
			totalAssertions++ ;
			assertEquals(donnees[i], retour[i]);
			bilanAssertions++ ;
		}
	}
	
	 /** La méthode test_writeHTMLProjet test la méthode writeHTMLCredits
	 * 
	 * @since sprint_4
	 */
	public void test_writeHTMLProjet() {
		
		String[] donnees = {"<!-- DEBUT page projets -->",
							"<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">",
							"<div data-role=\"header\" data-add-back-btn=\"true\">",
							"<h1>Projets 2014-2015</h1>",
							"</div>",
							"<div data-role=\"content\">",
							"<form class=\"ui-filterable\"><input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\"></form><ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\"><li><p><b>[Archeologie]</b> Groupe de recherche Chasséen Méridional</p><p><b>Client :</b> TISSIER Evelyne</p><p><b>Superviseur :</b> CANUT Marie-Françoise</p><p><b>Groupe A :</b>  Etienne LARROUY -  Victor PINQUIER -  Antoine RIVALIER -  Jean-Sebastien TRILLE</p></li><li><p><b>[SWAML]</b> Site web association \"Marie Louise\"</p><p><b>Client :</b> LAFON Isabelle</p><p><b>Superviseur :</b> DEMAY Laurent</p><p><b>Groupe B :</b>  Julie BADETS -  Nicolas GAUTHIER -  Theophane GIRARD -  Simon PREVIDENTE</p></li><li><p><b>[ReconfMI]</b> Développement interface graphique</p><p><b>Client :</b> STOLF Patricia</p><p><b>Superviseur :</b> CANUT Marie-Françoise</p><p><b>Groupe C :</b>  Julien CORDEAU-GONORD -  Florian GARIBAL -  Serge MOLINA -  Romain NOTO</p></li><li><p><b>[GESDEP]</b> Finalisation et déploiement de l'application de gestion des déplacements des personnels</p><p><b>Client :</b> DARAN Xavier</p><p><b>Superviseur :</b> REDON Laurence</p><p><b>Groupe D :</b>  Tim DAZAYOUS -  Alexandre ERB -  Gabriel GODARD -  Corentin HERMET -  Nathan LOPEZ</p></li><li><p><b>[SimulMI]</b> Développement de simulateur</p><p><b>Client :</b> STOLF Patricia</p><p><b>Superviseur :</b> DE MICHIEL Marianne</p><p><b>Groupe E :</b>  Adrien AVILES -  Aurelien BERNIER LEVALOIS -  Allan PERRIN -  Theo PIBOUBES</p></li><li><p><b>[BDM NoSQL]</b> Développement d'un logiciel de conception d'une base de données multidimensionnelles</p><p><b>Client :</b> TESTE Olivier</p><p><b>Superviseur :</b> ROQUES Olivier</p><p><b>Groupe F :</b>  Arnauld ALEX -  Nicolas AUBERT -  Tiphaine MARTINEZ -  Kristen VIGUIER</p></li><li><p><b>[E-ICGD]</b> Environnement d'intégration continue de génération de documentation</p><p><b>Client :</b> BRUEL Jean-Michel</p><p><b>Superviseur :</b> ROQUES Olivier</p><p><b>Groupe G :</b>  Pierre JEANJEAN -  Quentin LACOSTE -  Florian OUDDANE -  Anselme REVUZ</p></li><li><p><b>[PrestaShop]</b> Application et tutoriel sur Prestashop (logiciel e-commerce gratuit )</p><p><b>Client :</b> NONNE Laurent</p><p><b>Superviseur :</b> CHANCOGNE Laurent</p><p><b>Groupe H :</b>  Adrien ANDUZE -  Alexandre MAGISSON -  Anais RIGAL</p></li><li><p><b>[ApexEComm]</b> Application et tutoriel Oracle Apex pour un site d'e-commerce</p><p><b>Client :</b> NONNE Laurent</p><p><b>Superviseur :</b> TESTE Olivier</p><p><b>Groupe I :</b>  Camille ALRAM -  Yassine CHAHID KSABI -  Remi HANNE -  Edwin MARTIN</p></li><li><p><b>[SoftVolley]</b> SoftVolley : explication de stratégies de jeu au Volley-ball</p><p><b>Client :</b> MOULIN Cyril</p><p><b>Superviseur :</b> PÉNINOU André</p><p><b>Groupe J :</b>  Tristan BAVEREZ -  Clement BOUSQUET -  Alexane DUROZIER</p></li><li><p><b>[Prodif]</b> Refactoring de l'application Java PRODIF</p><p><b>Client :</b> BRUEL Jean-Michel</p><p><b>Superviseur :</b> OBER Iulian</p><p><b>Groupe K :</b>  Lucas GAMEZ -  Alexis LIEU -  Mathieu POUX -  Mehdi SLAMNIA</p></li><li><p><b>[RegExp]</b> Application pédagogique d'apprentissage des expressions régulières par l'expérience</p><p><b>Client :</b> SOTIN Pascal</p><p><b>Superviseur :</b> BOULLE Rémi</p><p><b>Groupe L :</b>  Benjamin COLLIGNON -  Jean-Philippe PERE -  Nathan SANTACANA -  Vimel VERON</p></li><li><p><b>[Refactor]</b> Refactoring de site web statique en site web dynamique</p><p><b>Client :</b> BRUEL Jean-Michel</p><p><b>Superviseur :</b> OBER Iulian</p><p><b>Groupe M :</b>  Nicolas BOCHU -  Jean-Brice CANIHAC -  Allan DIAZ -  Vincent EYCHENNE -  Sylvain MAILLOT</p></li><li><p><b>[Architekt]</b> Architekt</p><p><b>Client :</b> CHANCOGNE Laurent</p><p><b>Superviseur :</b> INGLEBERT Jean-Michel</p><p><b>Groupe N :</b>  Alexandre BONNET -  Timothee FICAT -  Killian TESSIER -  Thomas VINCENOT</p></li><li><p><b>[Architekt]</b> Architekt</p><p><b>Client :</b> CHANCOGNE Laurent</p><p><b>Superviseur :</b> SOTIN Pascal</p><p><b>Groupe O :</b>  Mael AUBERT -  Bilal EL YASSEM -  Guillaume FOURNET -  Aurelien SCUOTTO -  Damien WOJTOWICZ</p></li><li><p><b>[Carsat]</b> Questionnaire client sur page web et traitement des données</p><p><b>Client :</b> HARDY Laurent</p><p><b>Superviseur :</b> DEMAY Laurent</p><p><b>Groupe P :</b>  Jeremy ALVES NETO -  Anais CHAUMEIL -  Lea FOISSAC -  Leo JOSEPH -  Marine SUTARIK</p></li><li><p><b>[PrestaShop]</b> Application et tutoriel sur Prestashop (logiciel e-commerce gratuit )</p><p><b>Client :</b> NONNE Laurent</p><p><b>Superviseur :</b> VERDIER Michèle</p><p><b>Groupe Q :</b>  Quentin CESPEDES -  Alex JACQUOT-FERNANDEZ -  Hanae RHAYOUR -  Abdellah ZARIOH</p></li></ol>",
							"</div>",
							"<div data-role=\"footer\">",
							"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4>",
							"</div>",
							"</div>",
							"<!-- FIN page projets -->"
							};
		
		String [] retour = MakeOPTIweb.writeHTMLProjet().split(System.lineSeparator());
		
		for(int i = 0; i < retour.length && i < donnees.length; i++){
			totalAssertions++ ;
			assertEquals(donnees[i], retour[i]);
			bilanAssertions++ ;
		}
	
	}

	 /** La méthode test_writeHTMLEtu test la méthode writeHTMLEtu
	 * 
	 * @since sprint_4
	 */
	public void test_writeHTMLEtu() {
		
		String[] donnees = {"<!-- DEBUT page etudiants -->",
							"<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\">",
							"<div data-role=\"header\" data-add-back-btn=\"true\">",
							"<h1>Etudiants 2014-2015</h1>",
							"</div>",
							"<div data-role=\"content\">",
							"<form class=\"ui-filterable\"><input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\"></form><ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\"><li data-role=\"list-divider\">Etudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span></li><li data-find=\"Arnauld ALEX\"><a href=\"#projets\">ALEX Arnauld<span class=\"ui-li-count\" title=\"Groupe\">Groupe F</span></a></li><li data-find=\"Camille ALRAM\"><a href=\"#projets\">ALRAM Camille<span class=\"ui-li-count\" title=\"Groupe\">Groupe I</span></a></li><li data-find=\"Jeremy ALVES NETO\"><a href=\"#projets\">ALVES NETO Jeremy<span class=\"ui-li-count\" title=\"Groupe\">Groupe P</span></a></li><li data-find=\"Adrien ANDUZE\"><a href=\"#projets\">ANDUZE Adrien<span class=\"ui-li-count\" title=\"Groupe\">Groupe H</span></a></li><li data-find=\"Nicolas AUBERT\"><a href=\"#projets\">AUBERT Nicolas<span class=\"ui-li-count\" title=\"Groupe\">Groupe F</span></a></li><li data-find=\"Mael AUBERT\"><a href=\"#projets\">AUBERT Mael<span class=\"ui-li-count\" title=\"Groupe\">Groupe O</span></a></li><li data-find=\"Adrien AVILES\"><a href=\"#projets\">AVILES Adrien<span class=\"ui-li-count\" title=\"Groupe\">Groupe E</span></a></li><li data-find=\"Julie BADETS\"><a href=\"#projets\">BADETS Julie<span class=\"ui-li-count\" title=\"Groupe\">Groupe B</span></a></li><li data-find=\"Tristan BAVEREZ\"><a href=\"#projets\">BAVEREZ Tristan<span class=\"ui-li-count\" title=\"Groupe\">Groupe J</span></a></li><li data-find=\"Aurelien BERNIER LEVALOIS\"><a href=\"#projets\">BERNIER LEVALOIS Aurelien<span class=\"ui-li-count\" title=\"Groupe\">Groupe E</span></a></li><li data-find=\"Nicolas BOCHU\"><a href=\"#projets\">BOCHU Nicolas<span class=\"ui-li-count\" title=\"Groupe\">Groupe M</span></a></li><li data-find=\"Alexandre BONNET\"><a href=\"#projets\">BONNET Alexandre<span class=\"ui-li-count\" title=\"Groupe\">Groupe N</span></a></li><li data-find=\"Clement BOUSQUET\"><a href=\"#projets\">BOUSQUET Clement<span class=\"ui-li-count\" title=\"Groupe\">Groupe J</span></a></li><li data-find=\"Jean-Brice CANIHAC\"><a href=\"#projets\">CANIHAC Jean-Brice<span class=\"ui-li-count\" title=\"Groupe\">Groupe M</span></a></li><li data-find=\"Quentin CESPEDES\"><a href=\"#projets\">CESPEDES Quentin<span class=\"ui-li-count\" title=\"Groupe\">Groupe Q</span></a></li><li data-find=\"Yassine CHAHID KSABI\"><a href=\"#projets\">CHAHID KSABI Yassine<span class=\"ui-li-count\" title=\"Groupe\">Groupe I</span></a></li><li data-find=\"Anais CHAUMEIL\"><a href=\"#projets\">CHAUMEIL Anais<span class=\"ui-li-count\" title=\"Groupe\">Groupe P</span></a></li><li data-find=\"Benjamin COLLIGNON\"><a href=\"#projets\">COLLIGNON Benjamin<span class=\"ui-li-count\" title=\"Groupe\">Groupe L</span></a></li><li data-find=\"Julien CORDEAU-GONORD\"><a href=\"#projets\">CORDEAU-GONORD Julien<span class=\"ui-li-count\" title=\"Groupe\">Groupe C</span></a></li><li data-find=\"Tim DAZAYOUS\"><a href=\"#projets\">DAZAYOUS Tim<span class=\"ui-li-count\" title=\"Groupe\">Groupe D</span></a></li><li data-find=\"Allan DIAZ\"><a href=\"#projets\">DIAZ Allan<span class=\"ui-li-count\" title=\"Groupe\">Groupe M</span></a></li><li data-find=\"Alexane DUROZIER\"><a href=\"#projets\">DUROZIER Alexane<span class=\"ui-li-count\" title=\"Groupe\">Groupe J</span></a></li><li data-find=\"Bilal EL YASSEM\"><a href=\"#projets\">EL YASSEM Bilal<span class=\"ui-li-count\" title=\"Groupe\">Groupe O</span></a></li><li data-find=\"Alexandre ERB\"><a href=\"#projets\">ERB Alexandre<span class=\"ui-li-count\" title=\"Groupe\">Groupe D</span></a></li><li data-find=\"Vincent EYCHENNE\"><a href=\"#projets\">EYCHENNE Vincent<span class=\"ui-li-count\" title=\"Groupe\">Groupe M</span></a></li><li data-find=\"Timothee FICAT\"><a href=\"#projets\">FICAT Timothee<span class=\"ui-li-count\" title=\"Groupe\">Groupe N</span></a></li><li data-find=\"Lea FOISSAC\"><a href=\"#projets\">FOISSAC Lea<span class=\"ui-li-count\" title=\"Groupe\">Groupe P</span></a></li><li data-find=\"Guillaume FOURNET\"><a href=\"#projets\">FOURNET Guillaume<span class=\"ui-li-count\" title=\"Groupe\">Groupe O</span></a></li><li data-find=\"Lucas GAMEZ\"><a href=\"#projets\">GAMEZ Lucas<span class=\"ui-li-count\" title=\"Groupe\">Groupe K</span></a></li><li data-find=\"Florian GARIBAL\"><a href=\"#projets\">GARIBAL Florian<span class=\"ui-li-count\" title=\"Groupe\">Groupe C</span></a></li><li data-find=\"Nicolas GAUTHIER\"><a href=\"#projets\">GAUTHIER Nicolas<span class=\"ui-li-count\" title=\"Groupe\">Groupe B</span></a></li><li data-find=\"Theophane GIRARD\"><a href=\"#projets\">GIRARD Theophane<span class=\"ui-li-count\" title=\"Groupe\">Groupe B</span></a></li><li data-find=\"Gabriel GODARD\"><a href=\"#projets\">GODARD Gabriel<span class=\"ui-li-count\" title=\"Groupe\">Groupe D</span></a></li><li data-find=\"Remi HANNE\"><a href=\"#projets\">HANNE Remi<span class=\"ui-li-count\" title=\"Groupe\">Groupe I</span></a></li><li data-find=\"Corentin HERMET\"><a href=\"#projets\">HERMET Corentin<span class=\"ui-li-count\" title=\"Groupe\">Groupe D</span></a></li><li data-find=\"Alex JACQUOT-FERNANDEZ\"><a href=\"#projets\">JACQUOT-FERNANDEZ Alex<span class=\"ui-li-count\" title=\"Groupe\">Groupe Q</span></a></li><li data-find=\"Pierre JEANJEAN\"><a href=\"#projets\">JEANJEAN Pierre<span class=\"ui-li-count\" title=\"Groupe\">Groupe G</span></a></li><li data-find=\"Leo JOSEPH\"><a href=\"#projets\">JOSEPH Leo<span class=\"ui-li-count\" title=\"Groupe\">Groupe P</span></a></li><li data-find=\"Quentin LACOSTE\"><a href=\"#projets\">LACOSTE Quentin<span class=\"ui-li-count\" title=\"Groupe\">Groupe G</span></a></li><li data-find=\"Etienne LARROUY\"><a href=\"#projets\">LARROUY Etienne<span class=\"ui-li-count\" title=\"Groupe\">Groupe A</span></a></li><li data-find=\"Alexis LIEU\"><a href=\"#projets\">LIEU Alexis<span class=\"ui-li-count\" title=\"Groupe\">Groupe K</span></a></li><li data-find=\"Nathan LOPEZ\"><a href=\"#projets\">LOPEZ Nathan<span class=\"ui-li-count\" title=\"Groupe\">Groupe D</span></a></li><li data-find=\"Alexandre MAGISSON\"><a href=\"#projets\">MAGISSON Alexandre<span class=\"ui-li-count\" title=\"Groupe\">Groupe H</span></a></li><li data-find=\"Sylvain MAILLOT\"><a href=\"#projets\">MAILLOT Sylvain<span class=\"ui-li-count\" title=\"Groupe\">Groupe M</span></a></li><li data-find=\"Edwin MARTIN\"><a href=\"#projets\">MARTIN Edwin<span class=\"ui-li-count\" title=\"Groupe\">Groupe I</span></a></li><li data-find=\"Tiphaine MARTINEZ\"><a href=\"#projets\">MARTINEZ Tiphaine<span class=\"ui-li-count\" title=\"Groupe\">Groupe F</span></a></li><li data-find=\"Serge MOLINA\"><a href=\"#projets\">MOLINA Serge<span class=\"ui-li-count\" title=\"Groupe\">Groupe C</span></a></li><li data-find=\"Romain NOTO\"><a href=\"#projets\">NOTO Romain<span class=\"ui-li-count\" title=\"Groupe\">Groupe C</span></a></li><li data-find=\"Florian OUDDANE\"><a href=\"#projets\">OUDDANE Florian<span class=\"ui-li-count\" title=\"Groupe\">Groupe G</span></a></li><li data-find=\"Jean-Philippe PERE\"><a href=\"#projets\">PERE Jean-Philippe<span class=\"ui-li-count\" title=\"Groupe\">Groupe L</span></a></li><li data-find=\"Allan PERRIN\"><a href=\"#projets\">PERRIN Allan<span class=\"ui-li-count\" title=\"Groupe\">Groupe E</span></a></li><li data-find=\"Theo PIBOUBES\"><a href=\"#projets\">PIBOUBES Theo<span class=\"ui-li-count\" title=\"Groupe\">Groupe E</span></a></li><li data-find=\"Victor PINQUIER\"><a href=\"#projets\">PINQUIER Victor<span class=\"ui-li-count\" title=\"Groupe\">Groupe A</span></a></li><li data-find=\"Mathieu POUX\"><a href=\"#projets\">POUX Mathieu<span class=\"ui-li-count\" title=\"Groupe\">Groupe K</span></a></li><li data-find=\"Simon PREVIDENTE\"><a href=\"#projets\">PREVIDENTE Simon<span class=\"ui-li-count\" title=\"Groupe\">Groupe B</span></a></li><li data-find=\"Anselme REVUZ\"><a href=\"#projets\">REVUZ Anselme<span class=\"ui-li-count\" title=\"Groupe\">Groupe G</span></a></li><li data-find=\"Hanae RHAYOUR\"><a href=\"#projets\">RHAYOUR Hanae<span class=\"ui-li-count\" title=\"Groupe\">Groupe Q</span></a></li><li data-find=\"Anais RIGAL\"><a href=\"#projets\">RIGAL Anais<span class=\"ui-li-count\" title=\"Groupe\">Groupe H</span></a></li><li data-find=\"Antoine RIVALIER\"><a href=\"#projets\">RIVALIER Antoine<span class=\"ui-li-count\" title=\"Groupe\">Groupe A</span></a></li><li data-find=\"Nathan SANTACANA\"><a href=\"#projets\">SANTACANA Nathan<span class=\"ui-li-count\" title=\"Groupe\">Groupe L</span></a></li><li data-find=\"Aurelien SCUOTTO\"><a href=\"#projets\">SCUOTTO Aurelien<span class=\"ui-li-count\" title=\"Groupe\">Groupe O</span></a></li><li data-find=\"Mehdi SLAMNIA\"><a href=\"#projets\">SLAMNIA Mehdi<span class=\"ui-li-count\" title=\"Groupe\">Groupe K</span></a></li><li data-find=\"Marine SUTARIK\"><a href=\"#projets\">SUTARIK Marine<span class=\"ui-li-count\" title=\"Groupe\">Groupe P</span></a></li><li data-find=\"Killian TESSIER\"><a href=\"#projets\">TESSIER Killian<span class=\"ui-li-count\" title=\"Groupe\">Groupe N</span></a></li><li data-find=\"Jean-Sebastien TRILLE\"><a href=\"#projets\">TRILLE Jean-Sebastien<span class=\"ui-li-count\" title=\"Groupe\">Groupe A</span></a></li><li data-find=\"Vimel VERON\"><a href=\"#projets\">VERON Vimel<span class=\"ui-li-count\" title=\"Groupe\">Groupe L</span></a></li><li data-find=\"Kristen VIGUIER\"><a href=\"#projets\">VIGUIER Kristen<span class=\"ui-li-count\" title=\"Groupe\">Groupe F</span></a></li><li data-find=\"Thomas VINCENOT\"><a href=\"#projets\">VINCENOT Thomas<span class=\"ui-li-count\" title=\"Groupe\">Groupe N</span></a></li><li data-find=\"Damien WOJTOWICZ\"><a href=\"#projets\">WOJTOWICZ Damien<span class=\"ui-li-count\" title=\"Groupe\">Groupe O</span></a></li><li data-find=\"Abdellah ZARIOH\"><a href=\"#projets\">ZARIOH Abdellah<span class=\"ui-li-count\" title=\"Groupe\">Groupe Q</span></a></li></ol>",
							"</div>",
							"<div data-role=\"footer\">",
							"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>",
							"</div>",
							"</div>",
							"<!-- FIN page etudiants -->"
							};
		
		String [] retour = MakeOPTIweb.writeHTMLEtu().split(System.lineSeparator());
		
		for(int i = 0; i < retour.length && i < donnees.length; i++){
			totalAssertions++ ;
			assertEquals(donnees[i], retour[i]);
			bilanAssertions++ ;
		}
	
	}

	/** La méthode test_writeHTMLSujets test la méthode writeHTMLSujets
	* 
	* @since sprint_4
	*/
	public void test_writeHTMLSujets() {
		
		String[] donnees = {"<!-- DEBUT page sujets -->",
							"<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\">",
							"<div data-role=\"header\" data-add-back-btn=\"true\">",
							"<h1>Sujets 2014-2015</h1>",
							"</div>",
							"<div data-role=\"content\">",
							"<form class=\"ui-filterable\"><input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\"></form><ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\"><li data-role=\"list-divider\">Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span></li><li data-find=\"[ApexEComm]\"><a href=\"#projets\">[ApexEComm] <br/><div style=\"white-space:normal;\"><span><b>Application et tutoriel Oracle Apex pour un site d'e-commerce</b></span><span class=\"ui-li-count\">I</span></div></a></li><li data-find=\"[Archeologie]\"><a href=\"#projets\">[Archeologie] <br/><div style=\"white-space:normal;\"><span><b>Groupe de recherche Chasséen Méridional</b></span><span class=\"ui-li-count\">A</span></div></a></li><li data-find=\"[Architekt]\"><a href=\"#projets\">[Architekt] <br/><div style=\"white-space:normal;\"><span><b>Architekt</b></span><span class=\"ui-li-count\">N</span></div></a></li><li data-find=\"[BDM NoSQL]\"><a href=\"#projets\">[BDM NoSQL] <br/><div style=\"white-space:normal;\"><span><b>Développement d'un logiciel de conception d'une base de données multidimensionnelles</b></span><span class=\"ui-li-count\">F</span></div></a></li><li data-find=\"[Carsat]\"><a href=\"#projets\">[Carsat] <br/><div style=\"white-space:normal;\"><span><b>Questionnaire client sur page web et traitement des données</b></span><span class=\"ui-li-count\">P</span></div></a></li><li data-find=\"[E-ICGD]\"><a href=\"#projets\">[E-ICGD] <br/><div style=\"white-space:normal;\"><span><b>Environnement d'intégration continue de génération de documentation</b></span><span class=\"ui-li-count\">G</span></div></a></li><li data-find=\"[GESDEP]\"><a href=\"#projets\">[GESDEP] <br/><div style=\"white-space:normal;\"><span><b>Finalisation et déploiement de l'application de gestion des déplacements des personnels</b></span><span class=\"ui-li-count\">D</span></div></a></li><li data-find=\"[PrestaShop]\"><a href=\"#projets\">[PrestaShop] <br/><div style=\"white-space:normal;\"><span><b>Application et tutoriel sur Prestashop (logiciel e-commerce gratuit )</b></span><span class=\"ui-li-count\">H</span></div></a></li><li data-find=\"[Prodif]\"><a href=\"#projets\">[Prodif] <br/><div style=\"white-space:normal;\"><span><b>Refactoring de l'application Java PRODIF</b></span><span class=\"ui-li-count\">K</span></div></a></li><li data-find=\"[ReconfMI]\"><a href=\"#projets\">[ReconfMI] <br/><div style=\"white-space:normal;\"><span><b>Développement interface graphique</b></span><span class=\"ui-li-count\">C</span></div></a></li><li data-find=\"[Refactor]\"><a href=\"#projets\">[Refactor] <br/><div style=\"white-space:normal;\"><span><b>Refactoring de site web statique en site web dynamique</b></span><span class=\"ui-li-count\">M</span></div></a></li><li data-find=\"[RegExp]\"><a href=\"#projets\">[RegExp] <br/><div style=\"white-space:normal;\"><span><b>Application pédagogique d'apprentissage des expressions régulières par l'expérience</b></span><span class=\"ui-li-count\">L</span></div></a></li><li data-find=\"[SimulMI]\"><a href=\"#projets\">[SimulMI] <br/><div style=\"white-space:normal;\"><span><b>Développement de simulateur</b></span><span class=\"ui-li-count\">E</span></div></a></li><li data-find=\"[SoftVolley]\"><a href=\"#projets\">[SoftVolley] <br/><div style=\"white-space:normal;\"><span><b>SoftVolley : explication de stratégies de jeu au Volley-ball</b></span><span class=\"ui-li-count\">J</span></div></a></li><li data-find=\"[SWAML]\"><a href=\"#projets\">[SWAML] <br/><div style=\"white-space:normal;\"><span><b>Site web association \"Marie Louise\"</b></span><span class=\"ui-li-count\">B</span></div></a></li></ol>",
							"</div>",
							"<div data-role=\"footer\">",
							"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4>",
							"</div>",
							"</div>",
							"<!-- FIN page sujets -->"
							};
		
		String [] retour = MakeOPTIweb.writeHTMLSujets().split(System.lineSeparator());
		
		for(int i = 0; i < retour.length && i < donnees.length; i++){
			totalAssertions++ ;
			assertEquals(donnees[i], retour[i]);
			bilanAssertions++ ;
		}
	
	}
	
	/** La méthode test_writeHTMLInt test la méthode writeHTMLInt
	* 
	* @since sprint_4
	*/
	public void test_writeHTMLInt() {
		
		String[] donnees = {"<!-- DEBUT page intervenants -->",
							"<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1\">",
							"<div data-role=\"header\" data-add-back-btn=\"true\">",
							"<h1>Intervenants 2014-2015</h1>",
							"</div>",
							"<div data-role=\"content\">",
							"<form class=\"ui-filterable\"><input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\"></form><ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\"><li data-role=\"list-divider\">Intervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span></li><li data-find=\"BOULLE Rémi\"><a href=\"#projets\">BOULLE Rémi<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 1</span></a></li><li data-find=\"BRUEL Jean-Michel\"><a href=\"#projets\">BRUEL Jean-Michel<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 3</span><span class=\"ui-li-count\" title=\"Superviseur\"> 0</span></a></li><li data-find=\"CANUT Marie-Françoise\"><a href=\"#projets\">CANUT Marie-Françoise<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 2</span></a></li><li data-find=\"CHANCOGNE Laurent\"><a href=\"#projets\">CHANCOGNE Laurent<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 2</span><span class=\"ui-li-count\" title=\"Superviseur\"> 1</span></a></li><li data-find=\"DARAN Xavier\"><a href=\"#projets\">DARAN Xavier<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 1</span><span class=\"ui-li-count\" title=\"Superviseur\"> 0</span></a></li><li data-find=\"DE MICHIEL Marianne\"><a href=\"#projets\">DE MICHIEL Marianne<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 1</span></a></li><li data-find=\"DEMAY Laurent\"><a href=\"#projets\">DEMAY Laurent<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 2</span></a></li><li data-find=\"HARDY Laurent\"><a href=\"#projets\">HARDY Laurent<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 1</span><span class=\"ui-li-count\" title=\"Superviseur\"> 0</span></a></li><li data-find=\"INGLEBERT Jean-Michel\"><a href=\"#projets\">INGLEBERT Jean-Michel<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 1</span></a></li><li data-find=\"LAFON Isabelle\"><a href=\"#projets\">LAFON Isabelle<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 1</span><span class=\"ui-li-count\" title=\"Superviseur\"> 0</span></a></li><li data-find=\"MOULIN Cyril\"><a href=\"#projets\">MOULIN Cyril<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 1</span><span class=\"ui-li-count\" title=\"Superviseur\"> 0</span></a></li><li data-find=\"NONNE Laurent\"><a href=\"#projets\">NONNE Laurent<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 3</span><span class=\"ui-li-count\" title=\"Superviseur\"> 0</span></a></li><li data-find=\"OBER Iulian\"><a href=\"#projets\">OBER Iulian<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 2</span></a></li><li data-find=\"PÉNINOU André\"><a href=\"#projets\">PÉNINOU André<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 1</span></a></li><li data-find=\"REDON Laurence\"><a href=\"#projets\">REDON Laurence<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 1</span></a></li><li data-find=\"ROQUES Olivier\"><a href=\"#projets\">ROQUES Olivier<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 2</span></a></li><li data-find=\"SOTIN Pascal\"><a href=\"#projets\">SOTIN Pascal<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 1</span><span class=\"ui-li-count\" title=\"Superviseur\"> 1</span></a></li><li data-find=\"STOLF Patricia\"><a href=\"#projets\">STOLF Patricia<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 2</span><span class=\"ui-li-count\" title=\"Superviseur\"> 0</span></a></li><li data-find=\"TESTE Olivier\"><a href=\"#projets\">TESTE Olivier<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 1</span><span class=\"ui-li-count\" title=\"Superviseur\"> 1</span></a></li><li data-find=\"TISSIER Evelyne\"><a href=\"#projets\">TISSIER Evelyne<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 1</span><span class=\"ui-li-count\" title=\"Superviseur\"> 0</span></a></li><li data-find=\"VERDIER Michèle\"><a href=\"#projets\">VERDIER Michèle<span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\"> 0</span><span class=\"ui-li-count\" title=\"Superviseur\"> 1</span></a></li></ul>",
							"</div>",
							"<div data-role=\"footer\">",
							"<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4>",
							"</div>",
							"</div>",
							"<!-- FIN page intervenants -->"
							};
		
		String [] retour = MakeOPTIweb.writeHTMLInt().split(System.lineSeparator());
		
		for(int i = 0; i < retour.length && i < donnees.length; i++){
			totalAssertions++ ;
			assertEquals(donnees[i], retour[i]);
			bilanAssertions++ ;
		}
	
	}
	
	/** La méthode test_writeJSONEtu test la méthode writeJSONEtu
	* 
	* @since sprint_4
	*/
	public void test_writeJSONEtu() {
		
		String[] donnees = {"[",
							"{\"group\": \"A\",",
							"\"id\": \"41\",",
							"\"nom\": \"LARROUY\",",
							"\"prenom\": \"Etienne\"},",
							"{\"group\": \"A\",",
							"\"id\": \"54\",",
							"\"nom\": \"PINQUIER\",",
							"\"prenom\": \"Victor\"},",
							"{\"group\": \"A\",",
							"\"id\": \"60\",",
							"\"nom\": \"RIVALIER\",",
							"\"prenom\": \"Antoine\"},",
							"{\"group\": \"A\",",
							"\"id\": \"66\",",
							"\"nom\": \"TRILLE\",",
							"\"prenom\": \"Jean-Sebastien\"},",
							"{\"group\": \"B\",",
							"\"id\": \"9\",",
							"\"nom\": \"BADETS\",",
							"\"prenom\": \"Julie\"},",
							"{\"group\": \"B\",",
							"\"id\": \"32\",",
							"\"nom\": \"GAUTHIER\",",
							"\"prenom\": \"Nicolas\"},",
							"{\"group\": \"B\",",
							"\"id\": \"33\",",
							"\"nom\": \"GIRARD\",",
							"\"prenom\": \"Theophane\"},",
							"{\"group\": \"B\",",
							"\"id\": \"56\",",
							"\"nom\": \"PREVIDENTE\",",
							"\"prenom\": \"Simon\"},",
							"{\"group\": \"C\",",
							"\"id\": \"20\",",
							"\"nom\": \"CORDEAU-GONORD\",",
							"\"prenom\": \"Julien\"},",
							"{\"group\": \"C\",",
							"\"id\": \"31\",",
							"\"nom\": \"GARIBAL\",",
							"\"prenom\": \"Florian\"},",
							"{\"group\": \"C\",",
							"\"id\": \"48\",",
							"\"nom\": \"MOLINA\",",
							"\"prenom\": \"Serge\"},",
							"{\"group\": \"C\",",
							"\"id\": \"49\",",
							"\"nom\": \"NOTO\",",
							"\"prenom\": \"Romain\"},",
							"{\"group\": \"D\",",
							"\"id\": \"21\",",
							"\"nom\": \"DAZAYOUS\",",
							"\"prenom\": \"Tim\"},",
							"{\"group\": \"D\",",
							"\"id\": \"25\",",
							"\"nom\": \"ERB\",",
							"\"prenom\": \"Alexandre\"},",
							"{\"group\": \"D\",",
							"\"id\": \"34\",",
							"\"nom\": \"GODARD\",",
							"\"prenom\": \"Gabriel\"},",
							"{\"group\": \"D\",",
							"\"id\": \"36\",",
							"\"nom\": \"HERMET\",",
							"\"prenom\": \"Corentin\"},",
							"{\"group\": \"D\",",
							"\"id\": \"43\",",
							"\"nom\": \"LOPEZ\",",
							"\"prenom\": \"Nathan\"},",
							"{\"group\": \"E\",",
							"\"id\": \"8\",",
							"\"nom\": \"AVILES\",",
							"\"prenom\": \"Adrien\"},",
							"{\"group\": \"E\",",
							"\"id\": \"11\",",
							"\"nom\": \"BERNIER LEVALOIS\",",
							"\"prenom\": \"Aurelien\"},",
							"{\"group\": \"E\",",
							"\"id\": \"52\",",
							"\"nom\": \"PERRIN\",",
							"\"prenom\": \"Allan\"},",
							"{\"group\": \"E\",",
							"\"id\": \"53\",",
							"\"nom\": \"PIBOUBES\",",
							"\"prenom\": \"Theo\"},",
							"{\"group\": \"F\",",
							"\"id\": \"1\",",
							"\"nom\": \"ALEX\",",
							"\"prenom\": \"Arnauld\"},",
							"{\"group\": \"F\",",
							"\"id\": \"6\",",
							"\"nom\": \"AUBERT\",",
							"\"prenom\": \"Nicolas\"},",
							"{\"group\": \"F\",",
							"\"id\": \"47\",",
							"\"nom\": \"MARTINEZ\",",
							"\"prenom\": \"Tiphaine\"},",
							"{\"group\": \"F\",",
							"\"id\": \"68\",",
							"\"nom\": \"VIGUIER\",",
							"\"prenom\": \"Kristen\"},",
							"{\"group\": \"G\",",
							"\"id\": \"38\",",
							"\"nom\": \"JEANJEAN\",",
							"\"prenom\": \"Pierre\"},",
							"{\"group\": \"G\",",
							"\"id\": \"40\",",
							"\"nom\": \"LACOSTE\",",
							"\"prenom\": \"Quentin\"},",
							"{\"group\": \"G\",",
							"\"id\": \"50\",",
							"\"nom\": \"OUDDANE\",",
							"\"prenom\": \"Florian\"},",
							"{\"group\": \"G\",",
							"\"id\": \"57\",",
							"\"nom\": \"REVUZ\",",
							"\"prenom\": \"Anselme\"},",
							"{\"group\": \"H\",",
							"\"id\": \"4\",",
							"\"nom\": \"ANDUZE\",",
							"\"prenom\": \"Adrien\"},",
							"{\"group\": \"H\",",
							"\"id\": \"44\",",
							"\"nom\": \"MAGISSON\",",
							"\"prenom\": \"Alexandre\"},",
							"{\"group\": \"H\",",
							"\"id\": \"59\",",
							"\"nom\": \"RIGAL\",",
							"\"prenom\": \"Anais\"},",
							"{\"group\": \"I\",",
							"\"id\": \"2\",",
							"\"nom\": \"ALRAM\",",
							"\"prenom\": \"Camille\"},",
							"{\"group\": \"I\",",
							"\"id\": \"17\",",
							"\"nom\": \"CHAHID KSABI\",",
							"\"prenom\": \"Yassine\"},",
							"{\"group\": \"I\",",
							"\"id\": \"35\",",
							"\"nom\": \"HANNE\",",
							"\"prenom\": \"Remi\"},",
							"{\"group\": \"I\",",
							"\"id\": \"46\",",
							"\"nom\": \"MARTIN\",",
							"\"prenom\": \"Edwin\"},",
							"{\"group\": \"J\",",
							"\"id\": \"10\",",
							"\"nom\": \"BAVEREZ\",",
							"\"prenom\": \"Tristan\"},",
							"{\"group\": \"J\",",
							"\"id\": \"14\",",
							"\"nom\": \"BOUSQUET\",",
							"\"prenom\": \"Clement\"},",
							"{\"group\": \"J\",",
							"\"id\": \"23\",",
							"\"nom\": \"DUROZIER\",",
							"\"prenom\": \"Alexane\"},",
							"{\"group\": \"K\",",
							"\"id\": \"30\",",
							"\"nom\": \"GAMEZ\",",
							"\"prenom\": \"Lucas\"},",
							"{\"group\": \"K\",",
							"\"id\": \"42\",",
							"\"nom\": \"LIEU\",",
							"\"prenom\": \"Alexis\"},",
							"{\"group\": \"K\",",
							"\"id\": \"55\",",
							"\"nom\": \"POUX\",",
							"\"prenom\": \"Mathieu\"},",
							"{\"group\": \"K\",",
							"\"id\": \"63\",",
							"\"nom\": \"SLAMNIA\",",
							"\"prenom\": \"Mehdi\"},",
							"{\"group\": \"L\",",
							"\"id\": \"19\",",
							"\"nom\": \"COLLIGNON\",",
							"\"prenom\": \"Benjamin\"},",
							"{\"group\": \"L\",",
							"\"id\": \"51\",",
							"\"nom\": \"PERE\",",
							"\"prenom\": \"Jean-Philippe\"},",
							"{\"group\": \"L\",",
							"\"id\": \"61\",",
							"\"nom\": \"SANTACANA\",",
							"\"prenom\": \"Nathan\"},",
							"{\"group\": \"L\",",
							"\"id\": \"67\",",
							"\"nom\": \"VERON\",",
							"\"prenom\": \"Vimel\"},",
							"{\"group\": \"M\",",
							"\"id\": \"12\",",
							"\"nom\": \"BOCHU\",",
							"\"prenom\": \"Nicolas\"},",
							"{\"group\": \"M\",",
							"\"id\": \"15\",",
							"\"nom\": \"CANIHAC\",",
							"\"prenom\": \"Jean-Brice\"},",
							"{\"group\": \"M\",",
							"\"id\": \"22\",",
							"\"nom\": \"DIAZ\",",
							"\"prenom\": \"Allan\"},",
							"{\"group\": \"M\",",
							"\"id\": \"26\",",
							"\"nom\": \"EYCHENNE\",",
							"\"prenom\": \"Vincent\"},",
							"{\"group\": \"M\",",
							"\"id\": \"45\",",
							"\"nom\": \"MAILLOT\",",
							"\"prenom\": \"Sylvain\"},",
							"{\"group\": \"N\",",
							"\"id\": \"13\",",
							"\"nom\": \"BONNET\",",
							"\"prenom\": \"Alexandre\"},",
							"{\"group\": \"N\",",
							"\"id\": \"27\",",
							"\"nom\": \"FICAT\",",
							"\"prenom\": \"Timothee\"},",
							"{\"group\": \"N\",",
							"\"id\": \"65\",",
							"\"nom\": \"TESSIER\",",
							"\"prenom\": \"Killian\"},",
							"{\"group\": \"N\",",
							"\"id\": \"69\",",
							"\"nom\": \"VINCENOT\",",
							"\"prenom\": \"Thomas\"},",
							"{\"group\": \"O\",",
							"\"id\": \"7\",",
							"\"nom\": \"AUBERT\",",
							"\"prenom\": \"Mael\"},",
							"{\"group\": \"O\",",
							"\"id\": \"24\",",
							"\"nom\": \"EL YASSEM\",",
							"\"prenom\": \"Bilal\"},",
							"{\"group\": \"O\",",
							"\"id\": \"29\",",
							"\"nom\": \"FOURNET\",",
							"\"prenom\": \"Guillaume\"},",
							"{\"group\": \"O\",",
							"\"id\": \"62\",",
							"\"nom\": \"SCUOTTO\",",
							"\"prenom\": \"Aurelien\"},",
							"{\"group\": \"O\",",
							"\"id\": \"70\",",
							"\"nom\": \"WOJTOWICZ\",",
							"\"prenom\": \"Damien\"},",
							"{\"group\": \"P\",",
							"\"id\": \"3\",",
							"\"nom\": \"ALVES NETO\",",
							"\"prenom\": \"Jeremy\"},",
							"{\"group\": \"P\",",
							"\"id\": \"18\",",
							"\"nom\": \"CHAUMEIL\",",
							"\"prenom\": \"Anais\"},",
							"{\"group\": \"P\",",
							"\"id\": \"28\",",
							"\"nom\": \"FOISSAC\",",
							"\"prenom\": \"Lea\"},",
							"{\"group\": \"P\",",
							"\"id\": \"39\",",
							"\"nom\": \"JOSEPH\",",
							"\"prenom\": \"Leo\"},",
							"{\"group\": \"P\",",
							"\"id\": \"64\",",
							"\"nom\": \"SUTARIK\",",
							"\"prenom\": \"Marine\"},",
							"{\"group\": \"Q\",",
							"\"id\": \"16\",",
							"\"nom\": \"CESPEDES\",",
							"\"prenom\": \"Quentin\"},",
							"{\"group\": \"Q\",",
							"\"id\": \"37\",",
							"\"nom\": \"JACQUOT-FERNANDEZ\",",
							"\"prenom\": \"Alex\"},",
							"{\"group\": \"Q\",",
							"\"id\": \"58\",",
							"\"nom\": \"RHAYOUR\",",
							"\"prenom\": \"Hanae\"},",
							"{\"group\": \"Q\",",
							"\"id\": \"71\",",
							"\"nom\": \"ZARIOH\",",
							"\"prenom\": \"Abdellah\"}",
							"]"
							};
		
		String [] retour = MakeOPTIweb.writeJSONEtu().split(System.lineSeparator());
		
		for(int i = 0; i < retour.length && i < donnees.length; i++){
			totalAssertions++ ;
			assertEquals(donnees[i], retour[i]);
			bilanAssertions++ ;
		}
	
	}
	
	/** La méthode test_writeJSONInt test la méthode writeJSONInt
	* 
	* @since sprint_4
	*/
	public void test_writeJSONInt() {
		
		String[] donnees = {"[",
							"{\"prenom\": \"Rémi\",",
							"\"nom\": \"BOULLE\"}",
							",{\"prenom\": \"Jean-Michel\",",
							"\"nom\": \"BRUEL\"}",
							",{\"prenom\": \"Marie-Françoise\",",
							"\"nom\": \"CANUT\"}",
							",{\"prenom\": \"Laurent\",",
							"\"nom\": \"CHANCOGNE\"}",
							",{\"prenom\": \"Xavier\",",
							"\"nom\": \"DARAN\"}",
							",{\"prenom\": \"Marianne\",",
							"\"nom\": \"DE MICHIEL\"}",
							",{\"prenom\": \"Laurent\",",
							"\"nom\": \"DEMAY\"}",
							",{\"prenom\": \"Laurent\",",
							"\"nom\": \"HARDY\"}",
							",{\"prenom\": \"Jean-Michel\",",
							"\"nom\": \"INGLEBERT\"}",
							",{\"prenom\": \"Isabelle\",",
							"\"nom\": \"LAFON\"}",
							",{\"prenom\": \"Cyril\",",
							"\"nom\": \"MOULIN\"}",
							",{\"prenom\": \"Laurent\",",
							"\"nom\": \"NONNE\"}",
							",{\"prenom\": \"Iulian\",",
							"\"nom\": \"OBER\"}",
							",{\"prenom\": \"André\",",
							"\"nom\": \"PÉNINOU\"}",
							",{\"prenom\": \"Laurence\",",
							"\"nom\": \"REDON\"}",
							",{\"prenom\": \"Olivier\",",
							"\"nom\": \"ROQUES\"}",
							",{\"prenom\": \"Pascal\",",
							"\"nom\": \"SOTIN\"}",
							",{\"prenom\": \"Patricia\",",
							"\"nom\": \"STOLF\"}",
							",{\"prenom\": \"Olivier\",",
							"\"nom\": \"TESTE\"}",
							",{\"prenom\": \"Evelyne\",",
							"\"nom\": \"TISSIER\"}",
							",{\"prenom\": \"Michèle\",",
							"\"nom\": \"VERDIER\"}",
							"]"
							};
		
		String [] retour = MakeOPTIweb.writeJSONInt().split(System.lineSeparator());
		
		for(int i = 0; i < retour.length && i < donnees.length; i++){
			totalAssertions++ ;
			assertEquals(donnees[i], retour[i]);
			bilanAssertions++ ;
		}
	
	}
	
	/**
	* main de la classe Test
	*
	* @param args arguments
	* 
	* @since sprint_4
	* @version sprint_4
	*/
	public static void main(String[] args) {
		  
		  junit.textui.TestRunner.run(new TestSuite(MakeOPTIwebTest.class));
		
		  if (bilanAssertions == totalAssertions) { 
			  System.out.print("Bravo !"); 
		  }
		  else  { 
			  System.out.print("OUPS !"); 
		  }
		
		  System.out.println(" "+bilanAssertions+"/"+totalAssertions+" assertions verifiees");
		  
	  } // fin main
}
