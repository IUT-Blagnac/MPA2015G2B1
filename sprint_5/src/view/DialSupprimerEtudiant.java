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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import model.Etudiants;

/**
 * Boite de dialogue affichant la liste des sujets
 * @author Mathis PEZOU
 * @since sprint_1bis
 * @version sprint_2
 */
public class DialSupprimerEtudiant extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1646988624412165979L;
	
	JTable table;
	DefaultTableModel modelTable;
	
	private Etudiants etudiants;
	
	private JButton bRetour;
	JScrollPane zoneScrollable;
	JPanel panWest, panEast, panWSouth;
	
	
	/**
	* Constructeur de la classe DialSupprimerEtudiant.
	* @param fenetre
	* 			fenetre est la fenetre mère.
	* @param etudiants
	* 			etudiants est l'objet qui retiens tout les etudiants.
	* @since sprint1bis
	* @version sprint_2
	*/
	public DialSupprimerEtudiant(final FenetrePrincipal fenetre, final Etudiants etudiants){
		super(fenetre, "Supprimer un etudiant", true);
		
////////////////////////////////Initialisation variables de la classe////////////////////////////////////////
		this.etudiants = etudiants;
	
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
		modelTable.addColumn("groupe");
		modelTable.addColumn("id");
		modelTable.addColumn("nom");
		modelTable.addColumn("prenom");
        
		table.setModel(modelTable);
        
		
////////////////////////////////Listener JTablel////////////////////////////////////////
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint(); 
		        int row = table.rowAtPoint(p);//row selected
		        if (me.getClickCount() == 2) {
		        	
		            int index = Integer.parseInt((String) modelTable.getValueAt(row, 1));
		   
		            int supprOrNot = JOptionPane.showConfirmDialog(fenetre, "Voulez vous supprimer l'étudiant n°"+index + " ?");
		            
		            if(supprOrNot == 0){
		            	etudiants.removeEtudiant(index);
		            	dispose();
		            }else {
		            	JOptionPane.showMessageDialog(fenetre, "Suppression annulé.");
		            }
		        }
		    }
		}); // event when we double click on a row

		
////////////////////////////////Bouton et textes////////////////////////////////////////
		bRetour = new JButton("Retour au menu");

		

////////////////////////////////Pane et ScrollPane////////////////////////////////////////
		zoneScrollable = new JScrollPane(this.table );
		
		zoneScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		zoneScrollable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
		zoneScrollable.setPreferredSize(new Dimension(650,400));
		
		panWest = new JPanel(new BorderLayout());
		panEast = new JPanel(new BorderLayout());
		panWSouth = new JPanel();
		
		this.add(panWest, BorderLayout.WEST);
		this.add(panEast, BorderLayout.EAST);

		
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
	 * @version sprint_2
	 */
	public void update(){
		
		this.modelTable.setRowCount(0);
		
		ArrayList<String[]> allSubject = etudiants.getAllEtu();
		
		for(int i = 1; i < allSubject.size(); i++){
			Object[] contenue = allSubject.get(i);
			
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
