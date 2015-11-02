package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import model.Intervenants;
import model.Projets;
import model.Sujets;

/**
 * Boite de dialogue affichant la liste des projets
 * @author Mathis PEZOU
 * @since sprint3
 * @version sprint3
 */
public class DialListProjetSujet extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1646988624412165979L;
	
	private JTable table;
	private DefaultTableModel modelTable;
	
	private Sujets sujets;
	private Projets projets;
	private Intervenants intervenants;
	
	private JButton bRetour;
	//private JTextField tNom, tTitre;
	//private JLabel lNom, lTitre;
	private JScrollPane zoneScrollable;
	private JPanel panCenter, panSouth;
	
	private DialAffecterSujet das;
	
	
	/**
	* Constructeur de la classe DialListProjetSujet.
	* @param fenetre
	* 			fenetre est la fenetre mère.
	* @param etudiants tout les etudiants
	* @param sujets
	* 			sujets est l'objet qui retiens tout les sujets.
	* @param intervenants tout les intervenants
	* @param projets tout les projets
	* @since sprint3
	* @version sprint3
	*/
	public DialListProjetSujet(final FenetrePrincipal fenetre, Projets projets,  Sujets sujets, Intervenants intervenants){
		super(fenetre, "Liste des projets", true);
		
////////////////////////////////Initialisation variables de la classe////////////////////////////////////////
		
		this.sujets = sujets;
		this.projets = projets;
		this.intervenants = intervenants;
////////////////////////////////Initialisation JTable et model////////////////////////////////////////

		this.das = new DialAffecterSujet(this, sujets, projets);
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
	        		das.update(modelTable.getValueAt(row, 0), modelTable.getValueAt(row, 1), modelTable.getValueAt(row, 2), modelTable.getValueAt(row, 0));
	        		das.setVisible(true);
		        	update();
		        }
		    }
		}); // event when we double click on a row

		
////////////////////////////////Bouton et textes////////////////////////////////////////
		/*lNom = new JLabel("Le groupe contient :");
		lTitre = new JLabel("Le sujet contient :");*/
		bRetour = new JButton("Retour au menu");
		/*tNom = new JTextField();
		tTitre = new JTextField();*/
		

////////////////////////////////Pane et ScrollPane////////////////////////////////////////
		zoneScrollable = new JScrollPane(this.table );
		
		zoneScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		zoneScrollable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
		zoneScrollable.setPreferredSize(new Dimension(650,400));
		
		panCenter = new JPanel(new BorderLayout());
		panSouth = new JPanel();
		
		this.add(panCenter, BorderLayout.WEST);
		
		/*panNorth.setLayout(glFiltre);
		panNorth.add(lNom);
		panNorth.add(tNom);
		panNorth.add(lTitre);
		panNorth.add(tTitre);*/
		
		panCenter.add(panSouth, BorderLayout.SOUTH);
		panCenter.add(zoneScrollable, BorderLayout.CENTER);
		
		panSouth.add(bRetour);
		
		bRetour.addActionListener(this);
		
		
////////////////////////////////Listener////////////////////////////////////////
		bRetour.addActionListener(this);
		
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
	}
	
	/**
	 * Cette méthode permet de mettre à jour la liste de sujets affichés.
	 * @since sprint3
	 */
	public void update(){
		
		this.modelTable.setRowCount(0);
		
		ArrayList<String[]> allProjetAffichage = projets.getAffichageWithNoEtudiants(intervenants, sujets);//TO DO CHANGER CETTE METHODE
		
		for(int i = 0; i < allProjetAffichage.size(); i++){
			Object[] contenue = allProjetAffichage.get(i);
			
			this.modelTable.addRow(contenue);
		}

	}
	
	
	/**
	* La méthode actionPerformed permet de réagir en fonction des événements .
	* @param event
	* 		L'événement passé en paramétre.
	* @since sprint3
	* @version sprint3
	*/
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == bRetour){
			dispose(); // closeFenetre
		}
	}

}
