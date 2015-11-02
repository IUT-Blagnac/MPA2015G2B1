package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Encadrer;
import model.Etudiants;
import model.Groupe;
import model.Intervenants;
import model.Projets;
import model.Sujets;
import controler.Controleur;

public class FenetrePrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 3359533832391211490L;
	
	////////////////////////////////Variables de la classe////////////////////////////////////////
	Controleur ctrl;
	
	JButton bQuit;
	JButton bsListe, bsCreer, bsModif, bsSuppr;
	JButton bpListe, bpSuppr, bpSujet,bpAep, bpSep, bpGInt, bpVoeux;
	JButton bgListe, bgCreer, bgModif, bgSuppr;
	JButton beListe, beCreer, beModif, beSuppr;
	JButton biListe, biCreer, biModif, biSuppr;
	
	JMenuItem mi;
	JMenu m;
	JMenuBar mb;
	
	JMenu mAdmin;
	JMenuItem miAdmin;
	
	
	Sujets sujets;
	Etudiants etudiants;
	Projets projets;
	Intervenants intervenants;
	Encadrer encadrer;
	Groupe groupe;
	
	DialAPropos dap;
	DialCreerSujet dcs;
	DialCreerGroupe dcg;
	DialCreerEtudiant dce;
	DialCreerIntervenant dci;
	
	DialModifierSujet dms;
	DialModifierGroupe dmg;
	DialModifierEtudiant dme;
	DialModifierIntervenant dmi;
	
	DialSupprimerSujet dss;
	DialSupprimerProjet dsp;
	DialSupprimerGroupe dsg;
	DialSupprimerEtudiant dse;
	DialSupprimerIntervenant dsi;
	
	DialListerSujet dls;
	DialListerProjet dlp;
	DialListerGroupe dlg;
	DialListerEtudiant dle;
	DialListerIntervenant dli;
	
	DialListLogin dll;
	
	DialListProjetIntervenant dlpi;
	DialListProjetSujet dlps;
	
	DialListAjoutEtudiantProjet daep;
	DialListSupprEtudiantProjet dsep;
	
	DialAffecterVoeux dav;
	
	/**
	* Constructeur de la classe MainWindow avec parametre.
	* @param ctrl
	* 		Ceci est un contrôleur.
	* @since sprint0
	* @version sprint1
	*/
	public FenetrePrincipal(final Controleur ctrl){
		super("OPTI");
		
////////////////////////////////Initialisation variables de la classe FenetrePrincipal////////////////////////////////////////
		System.out.println("///////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////////////////////////////////");
		System.out.println("/////////Console d'affichage pour un prof curieux//////////");
		System.out.println("///////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////////////////////////////////////");
		
		sujets = new Sujets();
		encadrer = new Encadrer();
		etudiants = new Etudiants(encadrer);
		projets = new Projets(etudiants, encadrer);
		intervenants = new Intervenants(projets, encadrer);
		groupe = new Groupe(projets);
		
		this.ctrl = ctrl;
		dap = new DialAPropos(this);
		
		dlp = new DialListerProjet(this, projets, etudiants, sujets, intervenants);
		dlg = new DialListerGroupe(this, groupe);
		dls = new DialListerSujet(this, sujets);
		dle = new DialListerEtudiant(this, etudiants, sujets, projets, intervenants);
		dli = new DialListerIntervenant(this, intervenants, sujets, projets);
		
		dcg = new DialCreerGroupe(this);
		dcs = new DialCreerSujet(this, sujets);
		dce = new DialCreerEtudiant(this, etudiants);
		dci = new DialCreerIntervenant(this, intervenants);
		
		dsp = new DialSupprimerProjet(this, projets, groupe, etudiants, encadrer);
		dss = new DialSupprimerSujet(this, sujets);
		dsg = new DialSupprimerGroupe(this, groupe, etudiants, projets, encadrer);
		dse = new DialSupprimerEtudiant(this, etudiants);
		dsi = new DialSupprimerIntervenant(this, intervenants);
		
		dms = new DialModifierSujet(this, sujets);
		dmg = new DialModifierGroupe(this, groupe);
		dme = new DialModifierEtudiant(this, etudiants);
		dmi = new DialModifierIntervenant(this, intervenants);
		
		dlpi = new DialListProjetIntervenant(this, projets, etudiants, sujets, intervenants, encadrer);
		dlps = new DialListProjetSujet(this, projets, sujets, intervenants);
		
		daep = new DialListAjoutEtudiantProjet(this,projets,etudiants,sujets,intervenants);
		dsep = new DialListSupprEtudiantProjet(this,projets,etudiants,sujets,intervenants);
		
		dll = new DialListLogin(this);
		
		dav = new DialAffecterVoeux(this, projets, sujets);
		
////////////////////////////////Initalisation des GridLayout////////////////////////////////////////		
		GridLayout glPrincipal = new GridLayout(1, 4);// Le premier chiffre correspond au nombre de groupe de bouton en collones et le second en ligne
		glPrincipal.setHgap(10);
		glPrincipal.setVgap(10);
		
		GridLayout glSujet = new GridLayout(7, 1); // Le premiers chiffres correspond aux nombres de boutons max en colonnes et le second en lignes
		glSujet.setHgap(10);
		glSujet.setVgap(10);
		
		GridLayout glProjet = new GridLayout(7, 1);
		glProjet.setHgap(10);
		glProjet.setVgap(10);
		
		GridLayout glGroupe = new GridLayout(7, 1);// Le premiers chiffres correspond aux nombres de boutons max en colonnes et le second en lignes
		glGroupe.setHgap(10);
		glGroupe.setVgap(10);
		
		GridLayout glIntervenant = new GridLayout(7, 1);// Le premiers chiffres correspond aux nombres de boutons max en colonnes et le second en lignes
		glIntervenant.setHgap(10);
		glIntervenant.setVgap(10);
		
		GridLayout glEtudiant = new GridLayout(7, 1);// Le premiers chiffres correspond aux nombres de boutons max en colonnes et le second en lignes
		glEtudiant.setHgap(10);
		glEtudiant.setVgap(10);
		
////////////////////////////////Initalisation des Boutons ////////////////////////////////////////	
		bQuit = new JButton("Quitter");
		
		bsListe = new JButton("Lister");
		bsCreer = new JButton("Creer");
		bsModif = new JButton("Modifier");
		bsSuppr = new JButton("Supprimer");
		
		bpListe = new JButton("Lister");
		bpSuppr = new JButton("Supprimer");
		bpSujet = new JButton("Gerer Sujets");
		bpAep = new JButton("Affecter Etudiant");
		bpSep = new JButton("Retirer Etudiant");
		bpGInt = new JButton("Gerer Intervenants");
		bpVoeux = new JButton("Gerer Voeux");
		
		bgListe = new JButton("Lister");
		bgCreer = new JButton("Ajouter"); 
		bgModif = new JButton("Modifier");
		bgSuppr = new JButton("Supprimer");
		
		biListe = new JButton("Lister");
		biCreer = new JButton("Creer");
		biModif = new JButton("Modifier");
		biSuppr = new JButton("Supprimer");
		
		beListe = new JButton("Lister");
		beCreer = new JButton("Creer");
		beModif = new JButton("Modifier");
		beSuppr = new JButton("Supprimer");
		
		bsCreer.setPreferredSize(new Dimension(150, 20));//juste pour la beautééééééééééé<3
		biCreer.setPreferredSize(new Dimension(150, 20));//juste pour la beautééééééééééé<3 (2)
		beCreer.setPreferredSize(new Dimension(150, 20));//juste pour la beautééééééééééé<3 (3)
		
////////////////////////////////Creation de la MenuBar////////////////////////////////////////			
		mb = new JMenuBar();
		m = new JMenu("Option");
		mi = new JMenuItem("A propos");
		mAdmin = new JMenu("Admin");
		miAdmin = new JMenuItem("Gestion logins");
		
		mb.add(m);
		m.add(mi);
		mb.add(mAdmin);
		mAdmin.add(miAdmin);
		

////////////////////////////////Initalisation des panels////////////////////////////////////////
		JPanel grandKaribou = new JPanel(new BorderLayout());//LE grand panel qui recevra tous les elements graphiques => kiff un peu bizarre les gars :p
		JPanel panSouth = new JPanel();
		JPanel panCenter = new JPanel();
		JPanel panSujet = new JPanel();
		JPanel panProjet = new JPanel();
		JPanel panGroupe = new JPanel();
		JPanel panIntervenant = new JPanel();
		JPanel panEtudiant = new JPanel();

		panCenter.setLayout(glPrincipal);
		panSujet.setLayout(glSujet);
		panProjet.setLayout(glProjet);
		panGroupe.setLayout(glGroupe);
		panIntervenant.setLayout(glIntervenant);
		panEtudiant.setLayout(glEtudiant);
		
		panSujet.setBorder(BorderFactory.createTitledBorder("Sujet"));
		panProjet.setBorder(BorderFactory.createTitledBorder("Projet"));
		panGroupe.setBorder(BorderFactory.createTitledBorder("Groupe"));
		panIntervenant.setBorder(BorderFactory.createTitledBorder("Intervenant"));
		panEtudiant.setBorder(BorderFactory.createTitledBorder("Etudiant"));
		
	
////////////////////////////////Ajout des élements dans les panels////////////////////////////////////////
		this.add(grandKaribou);
		
		grandKaribou.add(mb, BorderLayout.NORTH);
		grandKaribou.add(panSouth, BorderLayout.SOUTH);
		grandKaribou.add(panCenter, BorderLayout.CENTER);
		
		panSouth.add(bQuit);
		panCenter.add(panProjet);
		panCenter.add(panGroupe);
		panCenter.add(panSujet);
		panCenter.add(panIntervenant);
		panCenter.add(panEtudiant);
		
		panSujet.add(bsListe);
		panSujet.add(bsCreer);
		panSujet.add(bsModif);
		panSujet.add(bsSuppr);
		
		panGroupe.add(bgListe);
		panGroupe.add(bgCreer);
		panGroupe.add(bgModif);
		panGroupe.add(bgSuppr);
		
		panProjet.add(bpListe);
		panProjet.add(bpSuppr);
		panProjet.add(bpSujet);
		panProjet.add(bpAep);
		panProjet.add(bpSep);
		panProjet.add(bpGInt);
		panProjet.add(bpVoeux);
		
		panIntervenant.add(biListe);
		panIntervenant.add(biCreer);
		panIntervenant.add(biModif);
		panIntervenant.add(biSuppr);

		panEtudiant.add(beListe);
		panEtudiant.add(beCreer);
		panEtudiant.add(beModif);
		panEtudiant.add(beSuppr);
		
		
////////////////////////////////Ajout ActionListener aux boutons////////////////////////////////////////	
		mi.addActionListener(this);
		miAdmin.addActionListener(this);
		bQuit.addActionListener(this);
		
		bsListe.addActionListener(this);
		bsCreer.addActionListener(this);
		bsModif.addActionListener(this);
		bsSuppr.addActionListener(this);
		
		bgListe.addActionListener(this);
		bgCreer.addActionListener(this);
		bgModif.addActionListener(this);
		bgSuppr.addActionListener(this);
		
		bpListe.addActionListener(this);
		bpSuppr.addActionListener(this);
		bpSujet.addActionListener(this);
		bpAep.addActionListener(this);
		bpSep.addActionListener(this);
		bpGInt.addActionListener(this);
		bpVoeux.addActionListener(this);
		
		biListe.addActionListener(this);
		biCreer.addActionListener(this);
		biModif.addActionListener(this);
		biSuppr.addActionListener(this);
		
		beListe.addActionListener(this);
		beCreer.addActionListener(this);
		beModif.addActionListener(this);
		beSuppr.addActionListener(this);
		
		
////////////////////////////////Fin du constructeyr ////////////////////////////////////////			
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e) {
						ctrl.ctrlQuit();
					}
				}
			);
		
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		
	}
	
	
	/**
	* La méthode actionPerformed permet de réagir en fonction des événements, à savoir quitter l'application et ouvrir une fenêtre de dialogue.
	* @param event
	* 		L'événement passé en paramétre.
	* @since sprint0
	*/
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == bQuit){
			ctrl.ctrlQuit();
		}
		
		if (event.getSource() == mi){
			dap.setVisible(true); // ouvre la fenetre "à propos"
		}
		
		if (event.getSource() == miAdmin){
			
			dll.setVisible(true); // ouvre la fenetre "à propos"
		}
				
		
		if (event.getSource() == bsListe){
			dls.update(); // Création de la vue
			dls.setVisible(true); // affichage de la vue
		}
		if (event.getSource() == bsCreer){
			
			dcs.update();
			dcs.setVisible(true);
			
			if (dcs.isBonneFin()){
				sujets.addSubject(dcs.getNom(),dcs.getTitre());
				JOptionPane.showMessageDialog(this, "Ajout validé.");
			}
		}
		if (event.getSource() == bsModif){
			dms.update();
			dms.setVisible(true);
		}
		if (event.getSource() == bsSuppr){
			dss.update();
			dss.setVisible(true);
		
		}
		
		
		if (event.getSource() == bpListe){
			dlp.update();
			dlp.setVisible(true);
		}

		if (event.getSource() == bpSuppr){
			dsp.update();
			dsp.setVisible(true);
			
		}
		if (event.getSource() == bpSujet){
			dlps.update();
			dlps.setVisible(true);
		}
		if (event.getSource() == bpAep){
			daep.update();
			daep.setVisible(true);
		}
		if (event.getSource() == bpSep){
			dsep.update();
			dsep.setVisible(true);
		}
		if (event.getSource() == bpGInt){
			dlpi.update();
			dlpi.setVisible(true);
		}
		if (event.getSource() == bpVoeux)
		{
			dav.update();
			dav.setVisible(true);
		}
		
		if (event.getSource() == bgListe){
			dlg.update();
			dlg.setVisible(true);
		}
		if (event.getSource() == bgCreer){
			dcg.update();
			dcg.setVisible(true);
			
			if (dcg.isBonneFin()){
				groupe.addGroupe(dcg.getNom());
				String idGroupe = groupe.getGroupeByName(dcg.getNom()).get(0)[0];
				String idProjet = projets.getProject(idGroupe)[0];
				JOptionPane.showMessageDialog(this, "Ajout validé, le projet n°"+idProjet+" affilié à ce groupe à également été crée");
			}
		}
		if (event.getSource() == bgSuppr){
			dsg.update();
			dsg.setVisible(true);
			
		}
		if (event.getSource() == bgModif){
			dmg.update();
			dmg.setVisible(true);
		}
		
		
		if (event.getSource() == biListe){
			dli.update();
			dli.setVisible(true);
		}
		if (event.getSource() == biCreer){
			dci.update();
			dci.setVisible(true);
			
			if (dci.isBonneFin()){
				intervenants.addIntervenants(dci.getPrenom(), dci.getNom());
				dlpi.updateItervenants(intervenants);
				JOptionPane.showMessageDialog(this, "Ajout validé.");
			}
		}
		if (event.getSource() == biModif){
			
			dmi.update();
			dmi.setVisible(true);
		
		}
		if (event.getSource() == biSuppr){
			
			dsi.update();
			dsi.setVisible(true);
		
		}
		if (event.getSource() == beListe){
			dle.update();
			dle.setVisible(true);
		}
		if (event.getSource() == beCreer){
			dce.update();
			dce.setVisible(true);
			if (dce.isBonneFin()){
				etudiants.addEtudiant(dce.getNom(), dce.getPrenom());
				JOptionPane.showMessageDialog(this, "Ajout validé.");
			}
		}
		if (event.getSource() == beModif){
			dme.update();
			dme.setVisible(true);
		}
		if (event.getSource() == beSuppr){
			dse.update();
			dse.setVisible(true);
		}

		if (event.getSource() == miAdmin){
			dll.update();
			dll.setVisible(true);
		}
	}

}
