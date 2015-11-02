package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.FenetrePrincipal;

/**
 * Boîte de dialogue permetant d'obtenir des informations sur le projet.
 * @author Nicolas RIBEREAU
 * @since sprint0
 * @version sprint2
 */
public class DialAPropos extends JDialog implements ActionListener{

	private static final long serialVersionUID = -8154344769286247865L;

	/**
	* Constructeur de la classe DialAPropos.
	* @param fenetre
	* 		fenetre est la fenêtre mère.
	* @since sprint0
	* @version sprint2
	*/
	public DialAPropos(FenetrePrincipal fenetre){
		super(fenetre, "A propos", true);
				
////////////////////////////////Construction du label et du bouton////////////////////////////////////////		
		JLabel l = new JLabel("<html><br/>"
				+ "Liste des membres de l'équipe de groupe 2B1 :<br/>"
				+ " - JACQUOT-FERNANDEZ Alex<br/>"
				+ " - KOZLOWSKI Nigel<br/>"
				+ " - KUENY Gaetan<br/>"
				+ " - PEZOU Mathis<br/>"
				+ " - RAILLARD Pol-Hervé<br/>"
				+ " - RIBEREAU Nicolas<br/><br/>"
				+ "Projet OPTI Par L'IUT de Blagnac.<br/>"
				+ "Université Toulouse 2.<br/>"
				+ "DUT INFO S3 en Module MPA.<br/>"
				+ "Version : SPRINT_3<br/>"
				+ "</html>");
		
		JButton bOk = new JButton("OK");
		
////////////////////////////////Initalisation des panels////////////////////////////////////////
		JPanel grandKaribou = new JPanel(new BorderLayout()); // panel qui contiendra tout
		JPanel panSouth = new JPanel(); 
		JPanel panWest = new JPanel();
		JPanel panEast = new JPanel();
		
		panWest.setPreferredSize(new Dimension(20, 0));
		panEast.setPreferredSize(new Dimension(20, 0));

	
		this.add(grandKaribou);
		
		grandKaribou.add(panSouth, BorderLayout.SOUTH);
		grandKaribou.add(panWest, BorderLayout.WEST);
		grandKaribou.add(panEast, BorderLayout.EAST);
		grandKaribou.add(l, BorderLayout.CENTER);
		
		panSouth.add(bOk);
		
		bOk.addActionListener(this);

////////////////////////////////fin du constructeur////////////////////////////////////////
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	/**
	* La méthode actionPerformed permet de réagir en fonction des événements, à savoir utiliser la méthode dispose().
	* @param event
	* 		L'événement passé en paramétre.
	* @since sprint0
	*/
	@Override
	public void actionPerformed(ActionEvent event) {
		dispose();
	}
}
