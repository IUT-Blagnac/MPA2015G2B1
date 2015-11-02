package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Boîte de dialogue permetant de créer un nouveau projet.
 * @author Nicolas RIBEREAU et Mathis PEZOU
 * @since sprint_1
 * @version sprint_2
 */
public class DialCreerGroupe extends JDialog implements ActionListener{

	private static final long serialVersionUID = 70374439313009090L;
	
	private boolean bonneFin;
	
	private JButton buttOk, buttAnnul;
	
	private JTextField txtFNom;
	
	JLabel labelNom, labelSujet;
	
	
	/**
	* Constructeur de la classe DialCreerProjet.
	* @param fenetre
	* 		fenetre est la fenetre mère.
	* @param projets tout les projets
	* @since sprint1
	*/
	public DialCreerGroupe(FenetrePrincipal fenetre) {
		super(fenetre, "Nouveau groupe", true);
		
		
		
		
////////////////////////////////Bouton et textes////////////////////////////////////////
		labelNom = new JLabel("Saisir le nom du groupe : ");
		
		this.txtFNom = new JTextField();
		
		this.buttOk = new JButton("OK");
		this.buttAnnul = new JButton("Annuler");
		
		
////////////////////////////////Pane et ScrollPane////////////////////////////////////////
		JPanel grandPanneau = new JPanel(new BorderLayout()); // panel qui contiendra tout les élements
		JPanel nordPanneau = new JPanel((new GridLayout(3,1)));
		JPanel sudPanneau = new JPanel();


		nordPanneau.add(labelNom);
		nordPanneau.add(txtFNom);
		
		sudPanneau.add(buttAnnul);
		sudPanneau.add(buttOk);
		
		grandPanneau.add(nordPanneau,BorderLayout.NORTH);
		grandPanneau.add(sudPanneau,BorderLayout.SOUTH);
		
		this.add(grandPanneau);
		
////////////////////////////////Listener////////////////////////////////////////
		
		buttOk.addActionListener(this);
		buttAnnul.addActionListener(this);
		
////////////////////////////////fin////////////////////////////////////////
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	
	/**
	 * Cette méthode permet de mettre à jour les champs du formulaire affiché.
	 * @since sprint1
	 * @version sprint_1
	 */
	public void update(){
		bonneFin = false; // on spécifie que ce n'est pas finis
		txtFNom.setText("");
	}
	
	
	/**
	 * Cette méthode permet de mettre à jour les champs du formulaire affiché.
	 * 
	 * @param str contenu pour l'update des JText
	 * @since sprint1
	 * @version sprint_2
	 */
	public void update(String str){
		bonneFin = false;
		txtFNom.setText(str);
	}
	
	
	/**
	* Cette méthode permet de savoir si la fenêtre est quittée correctement.
	* @return bonneFin
	* 		booléen indiquant si la fenêtre de dialogue est quittée correctement.
	* @since sprint1
	* @version sprint_1
	*/
	public boolean isBonneFin(){
		return bonneFin;
	}
	
	
	/**
	* Cette méthode revoit le nom d'un projet.
	* @return str
	* 		String contenant le nom du sujet saisi.
	* @since sprint1
	* @version sprint_2
	*/
	public String getNom(){
		String str = txtFNom.getText();
		return str;
	}

	
	/**
	* La méthode actionPerformed permet de réagir en fonction des événements.
	* @param arg0
	* 		L'événement passé en paramétre.
	* @since sprint1
	*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == buttOk){
			if (!txtFNom.getText().isEmpty() ){
				this.bonneFin = true;
				dispose();
			} else {
				if (txtFNom.getText().isEmpty()){
					JOptionPane.showMessageDialog(this, "Le nom doit être spécifié.");
				}
			}
		}
		if (arg0.getSource() == buttAnnul){
			dispose();
		}
		
	}

}
