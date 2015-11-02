package view;


	import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import model.CSVLibrairie;

	/**
	 * Boite de dialogue affichant la liste des sujets
	 * @author Mathis PEZOU
	 * @since sprint_1bis
	 * @version sprint_2
	 */
	public class DialListLogin extends JDialog implements ActionListener{

		private static final long serialVersionUID = 1646988624412165979L;
		
		JTable table;
		DefaultTableModel modelTable;
		
		private JButton bAply, bRetour, bAddLogin, bDeleteLogin;

		JScrollPane zoneScrollable;
		JPanel panWest, panEast, panESouth, panWSouth, panNorth;

		DialAjouterLogin dal;
		
		
		/**
		* Constructeur de la classe DialListLogin.
		* @param fenetre
		* 			Fenêtre mère.
		* @since sprint1bis
		* @version sprint_2
		*/
		public DialListLogin(FenetrePrincipal fenetre){
			super(fenetre, "Liste des sujets", true);
			
		
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
			modelTable.addColumn("login");
			modelTable.addColumn("password");
	        
			table.setModel(modelTable);
	        
			table.addMouseListener(new MouseAdapter() {
			    public void mousePressed(MouseEvent me) {
			        JTable table =(JTable) me.getSource();
			        Point p = me.getPoint(); 
			        int row = table.rowAtPoint(p);//row selected
			        if (me.getClickCount() == 2) {
			            System.out.println(modelTable.getValueAt(row, 0));// first column value of selected row
			        }
			    }
			}); // event when we double click on a row

			
	////////////////////////////////Bouton et textes////////////////////////////////////////

			bAply = new JButton("Appliquer le(s) Filtre(s)");
			bRetour = new JButton("Retour au menu");
			bAddLogin = new JButton("Ajouter nouveau login");
			bDeleteLogin = new JButton("Supprimer login");

		
			
	////////////////////////////////GridLayout///////////////////////////////////////
			GridLayout glPrincipal = new GridLayout(1, 2); // GridLayout des l'affchage
			GridLayout glFiltre = new GridLayout(3, 2); // GridLayout des filtres 
			

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
			this.add(panEast, BorderLayout.EAST);
			
			panNorth.setLayout(glFiltre);
			
			panNorth.add(bAddLogin);
			panNorth.add(bDeleteLogin);
			
			panWest.add(panWSouth, BorderLayout.SOUTH);
			panWest.add(zoneScrollable, BorderLayout.CENTER);
			
			panEast.add(panESouth, BorderLayout.SOUTH);
			panEast.add(panNorth, BorderLayout.NORTH);
			
			panWSouth.add(bRetour);
			
			panESouth.add(bAply);
			
			bAddLogin.addActionListener(this);
			bDeleteLogin.addActionListener(this);
			
			
	////////////////////////////////Listener////////////////////////////////////////
			
			dal = new DialAjouterLogin(this, this.getAllLogins());
			bRetour.addActionListener(this);
			bAply.addActionListener(this);
			
			this.pack();
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			
			this.update();
		}
		
		public ArrayList<String[]> getAllLogins(){
			File path = new File("data/mdp.csv");
			ArrayList<String[]> allLogins = CSVLibrairie.readCSV(path,";");
			return allLogins;
		}
		
		/**
		 * Cette méthode permet de mettre à jour la liste de sujets affichés.
		 * @since sprint1bis
		 */
		public void update(){
			this.modelTable.setRowCount(0);
			
			ArrayList<String[]> allLogins = this.getAllLogins();
			
			for(int i = 1; i < allLogins.size(); i++){
				Object[] contenu = allLogins.get(i);
				
				
				this.modelTable.addRow(contenu);
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
			if (event.getSource() == bAddLogin){
				dal.setVisible(true);
				dal.update();
			}
		}

	

}
