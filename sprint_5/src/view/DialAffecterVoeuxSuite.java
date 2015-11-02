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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Projets;
import model.Sujets;
import model.Voeux;

/**
 * Boite de dialogue affichant la liste des projets
 * @author Mathis PEZOU
 * @since sprint_1bis
 * @version sprint_2
 */
public class DialAffecterVoeuxSuite extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1646988624412165979L;
	
	JTable table;
	DefaultTableModel modelTable;
	
	Sujets sujets;
	Projets projets;
	String groupe;
	
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
	* @since sprint1bis
	* @version sprint_2
	*/
	public DialAffecterVoeuxSuite(FenetrePrincipal fenetre, String groupe,Sujets sujets){
		super(fenetre, "Affecter voeux", true);
		this.groupe=groupe;
////////////////////////////////Initialisation variables de la classe////////////////////////////////////////
		this.sujets = sujets;
	
////////////////////////////////Initialisation JTable et model////////////////////////////////////////
		this.table = new JTable();
		
		modelTable = new  DefaultTableModel() {
			private static final long serialVersionUID = 124767772769291431L;
			@Override
		    public boolean isCellEditable(int row, int column) { // row wil be not editable
		       //all cells false
				if(column == 3){
					return true;
				}
		       return false;
		    }
		};
		modelTable.addColumn ("ID");
		modelTable.addColumn ("nom");
		modelTable.addColumn("titre");
		modelTable.addColumn("priorités");
		
		
        
		table.setModel(modelTable);
        
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint(); 
		        int row = table.rowAtPoint(p);//row selected
		        if (me.getClickCount() == 2) {
		            System.out.println(modelTable.getValueAt(row, 3));// first column value of selected row
		        }
		    }
		}); // event when we double click on a row

		
////////////////////////////////Bouton et textes////////////////////////////////////////

		bAply = new JButton("Appliquer");
		bRetour = new JButton("Retour au menu");

	
		
////////////////////////////////GridLayout///////////////////////////////////////
		GridLayout glPrincipal = new GridLayout(1, 2); // GridLayout des l'affchage
		

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
		
		panWest.add(panWSouth, BorderLayout.SOUTH);
		panWest.add(zoneScrollable, BorderLayout.CENTER);
		
		
		panWSouth.add(bRetour);
		
		panWSouth.add(bAply);
		
		
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
		
		ArrayList<String[]> allSujet = sujets.getAllSujets();
		String[] values = new String[allSujet.size()];
		for (int i = 1; i < allSujet.size(); i++)
		{
			values[i]=String.valueOf(i);
		}
		for(int i = 1; i < allSujet.size(); i++)
		{
			Object[] contenue = new Object[4];
			contenue[0]=allSujet.get(i)[0];
			contenue[1]=allSujet.get(i)[1];
			contenue[2]=allSujet.get(i)[2];
			this.modelTable.addRow(contenue);
		}
		TableColumn col = table.getColumnModel().getColumn(3);
		col.setCellEditor(new MyComboBoxEditor(values));
		col.setCellRenderer(new MyComboBoxRenderer(values));
		
		table.setRowHeight(25);
	
		
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
		
		if (event.getSource() == bAply)
		{
			boolean appliquer=true;
			for(int i=1; i<sujets.getAllSujets().size(); i++)
			{
				for(int j=1; j<sujets.getAllSujets().size(); j++)
				{
					if (i!=j)
					{
						if (modelTable.getValueAt(i-1, 3)==modelTable.getValueAt(j-1, 3) &&modelTable.getValueAt(i-1, 3) != null)
						{
							appliquer=false;
						}
					}
				}
			}
			if (appliquer==false)
			{
				JOptionPane.showMessageDialog(this, "Erreur,plusieurs sujets ont la même priorité");
			}
			else
			{
				Voeux voeux= new Voeux();
				JOptionPane.showMessageDialog(this, "Modification(s) validée(s)");
				for(int j=1; j<sujets.getAllSujets().size(); j++)
				{
					if(modelTable.getValueAt(j-1, 3) != null){
						voeux.addVoeuxForGroup(groupe, String.valueOf(j), String.valueOf(modelTable.getValueAt(j-1, 3)));
					}
				}
				
			}
		}
	}

}
