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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Encadrer;
import model.Intervenants;

/**
 * Boîte de dialogue permetant d'affecter des intervenants à un projet.
 * @author Nicolas RIBEREAU
 * @since sprint3
 * @version sprint3
 */
public class DialAffecterIntervenant extends JDialog implements ActionListener{

	private static final long serialVersionUID = -7681388640505396990L;//wat
	private JButton bOk,bAnnul;
	private JLabel lInfo;
	private JComboBox<String> cClient, cSupperviseur, cSupportTech;
	private Intervenants intervenants;
	private Encadrer encadrer;
	private int idProj;
	
	public DialAffecterIntervenant(DialListProjetIntervenant man, Intervenants intervenants, Encadrer encadrer){
		super(man, "Affecter Intervenants", true);
		
		cClient = new JComboBox<String>();
		cSupperviseur = new JComboBox<String>();
		cSupportTech = new JComboBox<String>();
		lInfo = new JLabel("Loutre");
		
		bOk = new JButton("Ok");
		bAnnul = new JButton("Annuler");
		
		this.intervenants = intervenants;
		this.encadrer = encadrer;
		
		JPanel grandKaribou = new JPanel(new BorderLayout());
		JPanel panNorth = new JPanel();
		JPanel panCenter = new JPanel();
		JPanel panSouth = new JPanel();
		
		JLabel lClient = new JLabel("Client :");
		JLabel lSupperviseur = new JLabel("Supperviseur :");
		JLabel lSupportTech = new JLabel("Support Technique :");
		
		cClient.setPreferredSize(new Dimension(200, 25));
		cClient.addItem("------ ------");
		cSupperviseur.addItem("------ ------");
		cSupportTech.addItem("------ ------");
		
		ArrayList<String[]> temp = intervenants.getAllIntervenants();
		
		for (int i = 1; i < temp.size(); i ++){
			cClient.addItem(temp.get(i)[1] + " " + temp.get(i)[2]);
			cSupperviseur.addItem(temp.get(i)[1] + " " + temp.get(i)[2]);
			cSupportTech.addItem(temp.get(i)[1] + " " + temp.get(i)[2]);
		}
		
		GridLayout gl = new GridLayout(2, 3);
		gl.setHgap(5);
		gl.setVgap(5);
		panCenter.setLayout(gl);
		
		this.add(grandKaribou);
		grandKaribou.add(panNorth, BorderLayout.NORTH);
		grandKaribou.add(panSouth, BorderLayout.SOUTH);
		grandKaribou.add(panCenter, BorderLayout.CENTER);
		panCenter.add(lClient);
		panCenter.add(lSupperviseur);
		panCenter.add(lSupportTech);
		panCenter.add(cClient);
		panCenter.add(cSupperviseur);
		panCenter.add(cSupportTech);
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
	public void update(Object s1, Object s2, Object s3, Object i1, Object i2, Object i3, Object idProj){
		
		
		cClient.removeAllItems();
		cSupperviseur.removeAllItems();
		cSupportTech.removeAllItems();
		
		cClient.addItem("------ ------");
		cSupperviseur.addItem("------ ------");
		cSupportTech.addItem("------ ------");
		
		ArrayList<String[]> temp = intervenants.getAllIntervenants();
		
		for (int i = 1; i < temp.size(); i ++){
			cClient.addItem(temp.get(i)[1] + " " + temp.get(i)[2]);
			cSupperviseur.addItem(temp.get(i)[1] + " " + temp.get(i)[2]);
			cSupportTech.addItem(temp.get(i)[1] + " " + temp.get(i)[2]);
		}
		
		
		String idProjet = (String) idProj;
		this.idProj = Integer.parseInt(idProjet.trim());
		
		lInfo.setText("Projet : " + s1 + ", Groupe : " + s2 + ", Sujet : " + s3);
		String s4 = (String)i1;
		String s5 = (String)i2;
		String s6 = (String)i3;//C'EST DEGUEU MAIS JE M'EN FOU
		if(!s4.equals(" ")){
			String[] ss4 = s4.split(" ");
			cClient.setSelectedIndex(Integer.parseInt(intervenants.getIntervenantsByNameAndFirstName(ss4[0], ss4[1])));
		} else {
			cClient.setSelectedIndex(0);
		}
		
		if(!s5.equals(" ")){
			String[] ss5 = s5.split(" ");
			cSupperviseur.setSelectedIndex(Integer.parseInt(intervenants.getIntervenantsByNameAndFirstName(ss5[0], ss5[1])));
		} else {
			cSupperviseur.setSelectedIndex(0);
		}
		
		if(!s6.equals(" ")){
			String[] ss6 = s6.split(" ");
			cSupportTech.setSelectedIndex(Integer.parseInt(intervenants.getIntervenantsByNameAndFirstName(ss6[0], ss6[1])));
		} else {
			cSupportTech.setSelectedIndex(0);
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
		if (event.getSource() == bOk){
			if (cClient.getSelectedIndex() != cSupperviseur.getSelectedIndex() && cClient.getSelectedIndex() != 0){
				encadrer.addEncadrer(""+idProj, ""+cClient.getSelectedIndex(), "1");
			} else if (cClient.getSelectedIndex() == 0){
				
			}else {
				JOptionPane.showMessageDialog(this, "Le client ne peut être également superviseur");
			}
			
			if (cSupperviseur.getSelectedIndex() != 0 && cSupperviseur.getSelectedIndex() != cClient.getSelectedIndex()){
				encadrer.addEncadrer(""+idProj, ""+cSupperviseur.getSelectedIndex(), "2");
			} 
			
			if (cSupportTech.getSelectedIndex() != 0 ){
				encadrer.addEncadrer(""+idProj, ""+cSupportTech.getSelectedIndex(), "3");
			}
			
			dispose();
		}
		if (event.getSource() == bAnnul){
			dispose();
		}
	}

}
