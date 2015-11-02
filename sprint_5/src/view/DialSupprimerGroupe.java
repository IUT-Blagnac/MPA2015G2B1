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

import model.Encadrer;
import model.Etudiants;
import model.Groupe;
import model.Projets;

/**
 * Boite de dialogue permettant de supprimer un projet
 * @author Mathis PEZOU
 * @since sprint_1bis
 * @version sprint_2
 */
public class DialSupprimerGroupe extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1646988624412165979L;
	
	JTable table;
	DefaultTableModel modelTable;
	
	private Groupe groupe;
	
	private JButton bRetour;
	JScrollPane zoneScrollable;
	JPanel panWest, panEast, panWSouth;
	
	
	/**
	* Constructeur de la classe DialSupprimerProjet.
	* @param fenetre
	* 			fenetre est la fenetre mère.
	* @param projets
	* 			projets est l'objet qui retiens tout les projets.
	* @since sprint1bis
	* @version sprint_2
	*/
	public DialSupprimerGroupe(final FenetrePrincipal fenetre, final Groupe groupe, final Etudiants etudiants,final Projets projets, final Encadrer encadrer){
		super(fenetre, "Supprimer un groupe/projet", true);
		
////////////////////////////////Initialisation variables de la classe////////////////////////////////////////
		this.groupe = groupe;
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
        
		table.setModel(modelTable);
        
		
////////////////////////////////Listener JTablel////////////////////////////////////////
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint(); 
		        int row = table.rowAtPoint(p);//row selected
		        if (me.getClickCount() == 2) {
		        	
		            String index = (String) modelTable.getValueAt(row, 0);
		   
		            String idProjet =  projets.getProject(index.trim())[0];
		            System.out.println(index);
		            int supprOrNot = JOptionPane.showConfirmDialog(fenetre, "Voulez vous supprimer le groupe n°"+index + " ? \n Cela supprimera également le projet n°"+idProjet+" lié au groupe !" );
		            
		            if(supprOrNot == 0){
		            	groupe.removeGroupe(index);
		            	
		            	etudiants.removeGroupeInEtudiant(index);
		            	
		            	encadrer.removeEncadrer(String.valueOf(idProjet));
		            	projets.removeProject(Integer.parseInt(idProjet));

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
	 * Cette méthode permet de mettre à jour la liste de projets affichés.
	 * @since sprint1bis
	 * @version sprint_2
	 */
	public void update(){
		
		this.modelTable.setRowCount(0);
		
		ArrayList<String[]> allGroupe = groupe.getAllGroupe();
		
		for(int i = 1; i < allGroupe.size(); i++){
			
			Object[] contenue = {allGroupe.get(i)[0],allGroupe.get(i)[1]};
			
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
