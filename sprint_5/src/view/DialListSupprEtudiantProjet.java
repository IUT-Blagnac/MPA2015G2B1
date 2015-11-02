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

import model.Etudiants;
import model.Intervenants;
import model.Projets;
import model.Sujets;

/**
 * Boite de dialogue affichant la liste des projets
 * @author Mathis PEZOU
 * @since sprint_1bis
 * @version sprint_2
 */
public class DialListSupprEtudiantProjet extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1646988624412165979L;
	
	JTable table;
	DefaultTableModel modelTable;
	
	private Etudiants etudiants;
	private Sujets sujets;
	private Projets projets;
	private Intervenants intervenants;
	private DialSupprimerEtudiantsProjet dsep;
	
	private JButton bRetour;
	JScrollPane zoneScrollable;
	JPanel panWest,  panESouth, panWSouth;
	
	
	/**
	* Constructeur de la classe DialListSupprEtudiantProjet.
	* @param fenetre
	* 			fenetre est la fenetre mère.
	* @param etudiants tout les etudiants
	* @param sujets
	* 			sujets est l'objet qui retiens tout les sujets.
	* @param intervenants tout les intervenants
	* @param projets tout les projets
	* @since sprint1bis
	* @version sprint_2
	*/
	public DialListSupprEtudiantProjet(FenetrePrincipal fenetre, Projets projets, Etudiants etudiants, Sujets sujets, Intervenants intervenants){
		super(fenetre, "Liste des projets", true);
		
////////////////////////////////Initialisation variables de la classe////////////////////////////////////////
		this.etudiants = etudiants;
		this.sujets = sujets;
		this.projets = projets;
		this.intervenants = intervenants;
		dsep = new DialSupprimerEtudiantsProjet(this, etudiants);
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
		modelTable.addColumn("Client");
		modelTable.addColumn("Superviseur");
		modelTable.addColumn("support_technique");
		modelTable.addColumn("étudiants");
        
		table.setModel(modelTable);
        
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint(); 
		        int row = table.rowAtPoint(p);//row selected
		        if (me.getClickCount() == 2) {
		            while(modelTable.getValueAt(row, 0).equals(" ") || modelTable.getValueAt(row, 1).equals("")){
		            	row--;
		            }
		            dsep.update(modelTable.getValueAt(row, 0), modelTable.getValueAt(row, 1), modelTable.getValueAt(row, 2), modelTable.getValueAt(row, 0));
				    dsep.setVisible(true);
		            
		         
		        }
		    }
		}); // event when we double click on a row

		
////////////////////////////////Bouton et textes////////////////////////////////////////

		bRetour = new JButton("Retour au menu");

	
		
////////////////////////////////GridLayout///////////////////////////////////////
		GridLayout glPrincipal = new GridLayout(1, 2); // GridLayout des l'affchage
		
		

////////////////////////////////Pane et ScrollPane////////////////////////////////////////
		zoneScrollable = new JScrollPane(this.table );
		
		zoneScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		zoneScrollable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
		zoneScrollable.setPreferredSize(new Dimension(650,400));
		
		panWest = new JPanel(new BorderLayout());
		panESouth = new JPanel();
		panWSouth = new JPanel();
		
		this.setLayout(glPrincipal);
		this.add(panWest, BorderLayout.WEST);
		
		panWest.add(panWSouth, BorderLayout.SOUTH);
		panWest.add(zoneScrollable, BorderLayout.CENTER);
		
		
		panWSouth.add(bRetour);

		
		
////////////////////////////////Listener////////////////////////////////////////
		bRetour.addActionListener(this);
		
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
		
		ArrayList<String[]> allProjetAffichage = projets.getAffichage(etudiants, intervenants, sujets);
		
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
	}

}
