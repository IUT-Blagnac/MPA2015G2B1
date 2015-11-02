package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import model.Projets;
import model.Sujets;

/**
 * Boite de dialogue affichant la liste des projets
 * @author Mathis PEZOU
 * @since sprint_1bis
 * @version sprint_2
 */
public class DialAffecterVoeux extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1646988624412165979L;
	
	JTable table;
	DefaultTableModel modelTable;
	
	Sujets sujets;
	Projets projets;
	
	private JButton bAply, bRetour;
	//private JTextField tNom, tTitre;
	//private JLabel lNom, lTitre;
	JScrollPane zoneScrollable;
	JPanel panWest, panEast, panESouth, panWSouth, panNorth;
	
	
	/**
	* Constructeur de la classe DialListerProjet.
	* @param fenetre
	* 			fenetre est la fenetre mère.
	* @param sujets
	* 			sujets est l'objet qui retiens tout les sujets.
	* @param projets 
	* 			tous les projets
	* @since sprint1bis
	* @version sprint_2
	*/
	public DialAffecterVoeux(final FenetrePrincipal fenetre, Projets projets,final Sujets sujets){
		super(fenetre, "Liste des projets", true);
		
////////////////////////////////Initialisation variables de la classe////////////////////////////////////////
		this.sujets = sujets;
		this.projets = projets;
	
////////////////////////////////Initialisation JTable et model////////////////////////////////////////
		this.table = new JTable();
		
		modelTable = new  DefaultTableModel() {
			private static final long serialVersionUID = 124767772769291431L;
			@Override
		    public boolean isCellEditable(int row, int column) { // row wil be not editable
		       //all cells false
		       return false;
		    }
		};
		modelTable.addColumn("id");
		modelTable.addColumn("groupe");
		modelTable.addColumn("sujet");
        
		table.setModel(modelTable);
        
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint(); 
		        int row = table.rowAtPoint(p);//row selected
		        if (me.getClickCount() == 2) {
		            DialAffecterVoeuxSuite Dial = new DialAffecterVoeuxSuite(fenetre, (String)modelTable.getValueAt(row, 1), sujets);// first column value of selected row
		            Dial.update();
		            Dial.setVisible(true);
		        }
		    }
		}); // event when we double click on a row

		
////////////////////////////////Bouton et textes////////////////////////////////////////
		/*lNom = new JLabel("Le groupe contient :");
		lTitre = new JLabel("Le sujet contient :");*/
		bAply = new JButton("Appliquer le(s) Filtre(s)");
		bRetour = new JButton("Retour au menu");
		/*tNom = new JTextField();
		tTitre = new JTextField();*/
	
		
////////////////////////////////GridLayout///////////////////////////////////////
		GridLayout glPrincipal = new GridLayout(1, 2); // GridLayout des l'affchage
		//GridLayout glFiltre = new GridLayout(3, 2); // GridLayout des filtres 
		

////////////////////////////////Pane et ScrollPane////////////////////////////////////////
		zoneScrollable = new JScrollPane(this.table );
		
		zoneScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		zoneScrollable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
		zoneScrollable.setPreferredSize(new Dimension(650,400));
		
		panWest = new JPanel(new BorderLayout());
		panEast = new JPanel(new BorderLayout());
		panESouth = new JPanel();
		panWSouth = new JPanel();
		panNorth = new JPanel();
		
		this.setLayout(glPrincipal);
		this.add(panWest, BorderLayout.WEST);

		
		/*panNorth.setLayout(glFiltre);
		panNorth.add(lNom);
		panNorth.add(tNom);
		panNorth.add(lTitre);
		panNorth.add(tTitre);*/
		
		panWest.add(panWSouth, BorderLayout.SOUTH);
		panWest.add(zoneScrollable, BorderLayout.CENTER);
		
		panEast.add(panESouth, BorderLayout.SOUTH);
		//panEast.add(panNorth, BorderLayout.NORTH);
		
		panWSouth.add(bRetour);
		
		panESouth.add(bAply);
		
		bRetour.addActionListener(this);
		bAply.addActionListener(this);
		
		
////////////////////////////////Listener////////////////////////////////////////
		bRetour.addActionListener(this);
		bAply.addActionListener(this);
		
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
	}
	
	/**
	 * Cette méthode permet de mettre à jour la liste de sujets affichés.
	 * @since sprint1bis
	 */
	public void update(){
		
		this.modelTable.setRowCount(0);
		
		ArrayList<String[]> allProjetAffichage = projets.getAffichageWithNoEtudiantsIntervenants(sujets);
		
		for(int i = 0; i < allProjetAffichage.size(); i++){
			Object[] contenue = allProjetAffichage.get(i);
			
			this.modelTable.addRow(contenue);
		}

	}
	
	
	/**
	* La méthode actionPerformed permet de réagir en fonction des événements .
	* @param event
	* 		L'événement passé en paramétre.
	* @since sprint1bis
	* @version sprint_2
	*/
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == bRetour){
			dispose(); // closeFenetre
		}
		if (event.getSource() == bAply){
			
			
		}
	}

}
