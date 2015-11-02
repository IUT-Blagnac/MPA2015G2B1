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

import model.Etudiants;

/**
 * Boîte de dialogue permetant d'affecter des intervenants à un projet.
 * @author Nicolas RIBEREAU
 * @since sprint3
 * @version sprint3
 */
public class DialSupprimerEtudiantsProjet extends JDialog implements ActionListener{

	private static final long serialVersionUID = -7681388640505396990L;//wat
	private JButton bOk,bAnnul;
	private JLabel lInfo;
	private JComboBox<String> cEtudiant;
	private Etudiants etudiants;
	private String idGroupe;
	
	private DialListSupprEtudiantProjet man;
	
	public DialSupprimerEtudiantsProjet(DialListSupprEtudiantProjet man, Etudiants etudiants){
		super(man, "Affecter etudiants", true);
		
		this.man = man;
		
		cEtudiant = new JComboBox<String>();
		lInfo = new JLabel("Loutre");
		
		bOk = new JButton("Ok");
		bAnnul = new JButton("Annuler");
		
		this.etudiants = etudiants;
		
		JPanel grandKaribou = new JPanel(new BorderLayout());
		JPanel panNorth = new JPanel();
		JPanel panCenter = new JPanel();
		JPanel panSouth = new JPanel();
		
		JLabel lEtudiant = new JLabel("Etudiants :");;
		
		cEtudiant.setPreferredSize(new Dimension(200, 25));
		cEtudiant.addItem("------ ------");
		
		ArrayList<String[]> temp = etudiants.getEtuByGroupe("");
		
		for (int i = 0; i < temp.size(); i ++){
			cEtudiant.addItem(temp.get(i)[2] + " " + temp.get(i)[3]);
		}
		
		GridLayout gl = new GridLayout(2, 3);
		gl.setHgap(5);
		gl.setVgap(5);
		panCenter.setLayout(gl);
		
		this.add(grandKaribou);
		grandKaribou.add(panNorth, BorderLayout.NORTH);
		grandKaribou.add(panSouth, BorderLayout.SOUTH);
		grandKaribou.add(panCenter, BorderLayout.CENTER);
		panCenter.add(lEtudiant);
		panCenter.add(cEtudiant);
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
		
		this.idGroupe = (String) s2;
		
		cEtudiant.removeAllItems();
		
		cEtudiant.addItem("------ ------");
		
		ArrayList<String[]> temp = etudiants.getEtuByGroupe(this.idGroupe.trim());
		
		for (int i = 0; i < temp.size(); i ++){
			cEtudiant.addItem(temp.get(i)[2] + " " + temp.get(i)[3]);
		}
		
		lInfo.setText("Projet : " + s1 + ", Groupe : " + s2 + ", Sujet : " + s3);
	
		
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
			if (cEtudiant.getSelectedIndex() != 0){
				String item = (String) cEtudiant.getSelectedItem();
				String[] split = item.split(" ");
				String[] etudiantCourant = etudiants.getEtuByNameAndFirstName(split[1], split[0]);
				
				System.out.println(item);
				System.out.println(split[0]+":"+split[1]);
				System.out.println(etudiantCourant[0]);
				
				if(etudiantCourant != null){
					etudiants.setGroupe(etudiantCourant[1], " ");		
				}
			}
			man.update();
			dispose();
		}
		if (event.getSource() == bAnnul){
			dispose();
		}
	}

}
