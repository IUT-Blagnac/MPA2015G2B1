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

import model.Intervenants;

/**
 * Boîte de dialogue permetant de créer un nouveau intervenant.
 * @author Nicolas RIBEREAU et Mathis PEZOU
 * @since sprint_1
 * @version sprint_2
 */
public class DialCreerIntervenant extends JDialog implements ActionListener{

	private static final long serialVersionUID = 70374439313009090L;

	Intervenants intervenants;
	
	private boolean bonneFin;
	
	private JButton buttOk, buttAnnul;
	
	private JTextField txtFNom, txtFPrenom;
	
	JLabel labelNom, labelPrenom;
	
	
	/**
	* Constructeur de la classe DialCreerIntervenant.
	* @param fenetre
	* 		fenetre est la fenetre mère.
	* @param intervenants tout les intervenants
	* @since sprint1
	*/
	public DialCreerIntervenant(FenetrePrincipal fenetre, Intervenants intervenants) {
		super(fenetre, "Nouveau intervenant", true);
		
		
////////////////////////////////Initialisation variables de la classe////////////////////////////////////////
		this.intervenants = intervenants;
		
		
////////////////////////////////Bouton et textes////////////////////////////////////////
		labelNom = new JLabel("Saisir nom :");
		labelPrenom = new JLabel("Saisir prénom :");
		
		this.txtFNom = new JTextField();
		this.txtFPrenom = new JTextField();
		
		this.buttOk = new JButton("OK");
		this.buttAnnul = new JButton("Annuler");
		
		
////////////////////////////////Pane et ScrollPane////////////////////////////////////////
		JPanel grandPanneau = new JPanel(new BorderLayout()); // panel qui contiendra tout les élements
		JPanel nordPanneau = new JPanel((new GridLayout(3,1)));
		JPanel sudPanneau = new JPanel();


		nordPanneau.add(labelNom);
		nordPanneau.add(txtFNom);
		nordPanneau.add(labelPrenom);
		nordPanneau.add(txtFPrenom);
		
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
		txtFPrenom.setText(""); // on met le text à vide
	}
	
	
	/**
	 * Cette méthode permet de mettre à jour les champs du formulaire affiché.
	 * 
	 * @param str contenu pour l'update des JText
	 * @since sprint1
	 * @version sprint_2
	 */
	public void update(String[] str){
		bonneFin = false;
		txtFNom.setText(str[0]);
		txtFPrenom.setText(str[1]);// on update le texte
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
	* Cette méthode revoit le nom et le titre d'un sujet.
	* @return str
	* 		tableau de String contenant le nom et le titre du sujet saisi.
	* @since sprint1
	* @version sprint_2
	*/
	public String[] getParam(){
		String[] str = new String[2];
		str[0] = txtFNom.getText();
		str[1] = txtFPrenom.getText(); // on récupere le text des textes
		return str;
	}
	
	/**
	* Cette méthode revoit le nom d'un sujet.
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
	* Cette méthode revoit le prénom
	* @return str
	* 		String contenant le prénom saisie
	* @since sprint1
	* @version sprint_2
	*/
	public String getPrenom(){
		String str = txtFPrenom.getText();
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
			if (!txtFNom.getText().isEmpty() && !txtFPrenom.getText().isEmpty()){
				this.bonneFin = true;
				dispose();
			} else {
				if (txtFNom.getText().isEmpty()){
					JOptionPane.showMessageDialog(this, "Le nom doit être spécifié.");
				}
				if (txtFPrenom.getText().isEmpty()){
					JOptionPane.showMessageDialog(this, "Le prénom doit être spécifié.");
				}
			}
		}
		if (arg0.getSource() == buttAnnul){
			dispose();
		}
		
	}

}
