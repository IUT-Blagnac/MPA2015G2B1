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

import model.Sujets;

/**
 * Boite de dialogue permettant de mofifier un sujet
 * @author Mathis PEZOU
 * @since sprint_1bis
 * @version sprint_2
 */
public class DialModifierSujet extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = -3972917529655484426L;
	
	private DialCreerSujet dcs;
	
	JTable table;
	DefaultTableModel modelTable;
	
	private Sujets sujets;
	
	private JButton bRetour;
	JScrollPane zoneScrollable;
	JPanel panWest, panEast, panWSouth;
	
	
	/**
	* Constructeur de la classe DialListerSujet.
	* @param fenetre
	* 			fenetre est la fenetre mère.
	* @param sujets
	* 			sujets est l'objet qui retiens tout les sujets.
	* @since sprint1bis
	* @version sprint_2
	*/
	public DialModifierSujet(final FenetrePrincipal fenetre, final Sujets sujets){
		super(fenetre, "Modifier un sujet", true);
		
////////////////////////////////Initialisation variables de la classe////////////////////////////////////////
		this.sujets = sujets;
		this.dcs = new DialCreerSujet(fenetre, sujets);
		
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
		modelTable.addColumn("nom");
		modelTable.addColumn("titre");
        
		table.setModel(modelTable);
        
		
////////////////////////////////Listener JTablel////////////////////////////////////////
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint(); 
		        int row = table.rowAtPoint(p);//row selected
		        if (me.getClickCount() == 2) {
		        	
		            int index = Integer.parseInt((String) modelTable.getValueAt(row, 0));
		            String nom = (String) modelTable.getValueAt(row, 1);
		            String titre = (String) modelTable.getValueAt(row, 2);
		            System.out.println(index + " : " + nom + " -> " + titre);
		            String[] values = {nom, titre};//on récupere le nom et le titre de le ligne sélectionnée
		            
		            dcs.update(values);// on envoie ces valeurs a la prochaine vue
					dcs.setVisible(true);
					
					if (dcs.isBonneFin()){
						int reponse = JOptionPane.showConfirmDialog(fenetre, "Valider la modification?");
						
						if (reponse == 0){
							
							String[] str = new String[3];
							String[] str2 = dcs.getParam();
							
							str[0] = String.valueOf(index);
							str[1] = str2[0];
							str[2] = str2[1];
							
							sujets.setSubject(str);
							
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
		
		ArrayList<String[]> allSubject = sujets.getAllSujets();
		
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
