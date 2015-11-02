package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Projets;
import model.Sujets;

/**
 * Boîte de dialogue permetant d'affecter des intervenants à un projet.
 * @author Nicolas RIBEREAU
 * @since sprint3
 * @version sprint3
 */
public class DialAffecterSujet extends JDialog implements ActionListener{

	private static final long serialVersionUID = -7681388640505396990L;//wat
	private JButton bOk,bAnnul;
	private JLabel lInfo;
	private JComboBox<String> cSujet;
	private Sujets sujets;
	private Projets projets;
	private int idProj;
	
	public DialAffecterSujet(DialListProjetSujet man, Sujets sujets, Projets projets){
		super(man, "Affecter Sujet", true);
		
		cSujet = new JComboBox<String>();
		lInfo = new JLabel("Loutre");
		
		bOk = new JButton("Ok");
		bAnnul = new JButton("Annuler");
		
		this.sujets = sujets;
		this.projets = projets;
		
		JPanel grandKaribou = new JPanel(new BorderLayout());
		JPanel panNorth = new JPanel();
		JPanel panCenter = new JPanel();
		JPanel panSouth = new JPanel();
		
		JLabel lSujet = new JLabel("Sujet :");
		
		cSujet.setPreferredSize(new Dimension(200, 25));
		cSujet.addItem("------ ------");
		
		ArrayList<String[]> temp = sujets.getAllSujets();
		
		for (int i = 1; i < temp.size(); i ++){
			cSujet.addItem(temp.get(i)[1]);
			
		}
		
		GridLayout gl = new GridLayout(2, 1);
		gl.setHgap(5);
		gl.setVgap(5);
		panCenter.setLayout(gl);
		
		this.add(grandKaribou);
		grandKaribou.add(panNorth, BorderLayout.NORTH);
		grandKaribou.add(panSouth, BorderLayout.SOUTH);
		grandKaribou.add(panCenter, BorderLayout.CENTER);
		panCenter.add(lSujet);
		panCenter.add(cSujet);
		panSouth.add(bAnnul);
		panSouth.add(bOk);
		panNorth.add(lInfo);
		
		bOk.addActionListener(this);
		bAnnul.addActionListener(this);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	/**
	 * Cette méthode permet de mettre à jour la fenêtre et son affichage.
	 * @since sprint3
	 */
	public void update(Object s1, Object s2, Object s3, Object idProj){
		
		cSujet.removeAllItems();
		
		cSujet.addItem("------ ------");
		
		ArrayList<String[]> temp = sujets.getAllSujets();
		
		for (int i = 1; i < temp.size(); i ++){
			cSujet.addItem(temp.get(i)[1]);
			
		}
		
		String idProjet = (String) idProj;
		this.idProj = Integer.parseInt(idProjet.trim());
		
		lInfo.setText("Projet : " + s1 + ", Groupe : " + s2 + ", Sujet : " + s3);
		String s4 = (String)s3;
		String ss4 = s4.trim();
		cSujet.setSelectedIndex(sujets.getPosition(ss4));
		System.out.println(ss4 + " : " + sujets.getPosition(ss4));
		
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
		if (event.getSource() == bOk){
			if (cSujet.getSelectedIndex() != 0){
				projets.addSujet(""+idProj, ""+cSujet.getSelectedIndex());
			}

			
			dispose();
		}
		if (event.getSource() == bAnnul){
			dispose();
		}
	}

}
