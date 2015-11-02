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

import model.Groupe;

/**
 * Boite de dialogue permettant de mofifier un projet
 * @author Mathis PEZOU
 * @since sprint_1bis
 * @version sprint_2
 */
public class DialModifierGroupe extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = -3972917529655484426L;
	
	private DialCreerGroupe dcg;
	
	JTable table;
	DefaultTableModel modelTable;
	
	private Groupe groupe;
	
	private JButton bRetour;
	JScrollPane zoneScrollable;
	JPanel panWest, panEast, panWSouth;
	
	
	/**
	* Constructeur de la classe DialModifierProjet.
	* @param fenetre
	* 			fenetre est la fenetre mère.
	* @param projets
	* 			projets est l'objet qui retiens tout les projets.
	* @since sprint1bis
	* @version sprint_2
	*/
	public DialModifierGroupe(final FenetrePrincipal fenetre, final Groupe groupe){
		super(fenetre, "Modifier un sujet", true);
		
////////////////////////////////Initialisation variables de la classe////////////////////////////////////////
		this.groupe = groupe;
		this.dcg = new DialCreerGroupe(fenetre);
		
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
		        	
		            String index = String.valueOf(modelTable.getValueAt(row, 0));
		            
		            String myGroupe = (String) modelTable.getValueAt(row, 1);
		            
		            System.out.println(index + " : " + myGroupe);
		            
		            dcg.update(myGroupe);// on envoie ces valeurs a la prochaine vue
					dcg.setVisible(true);
					
					if (dcg.isBonneFin()){
						int reponse = JOptionPane.showConfirmDialog(fenetre, "Valider la modification?");
						
						if (reponse == 0){
							
							String[] str = new String[2];
							String str2 = dcg.getNom();
							
							str[0] = index;
							str[1] = str2;
							
							groupe.setGroupe(str);
							
						}
						
					}
				dispose();
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
