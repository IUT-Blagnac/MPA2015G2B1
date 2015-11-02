package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Etudiants;
import controler.Controleur;

/**
 * Boîte de dialogue permetant de créer un nouveau sujet.
 * 
 * @author Nicolas RIBEREAU et Mathis PEZOU
 * @since sprint_1
 * @version sprint_2
 */
public class DialLogin extends JDialog implements ActionListener {

	private static final long serialVersionUID = 70374439313009090L;

	Etudiants etudiants;

	private JButton buttOk, buttAnnul;

	private JTextField txtFLogin;
	private JPasswordField textFPasswd;

	JLabel labelNom, labelSujet;

	private static Map<String, String> availableLogins = new HashMap<String, String>();
	
	static FenetrePrincipal fenetre;

	
	public DialLogin(Controleur controller) {
		
		super(fenetre, "Login", true);


		// //////////////////////////////Bouton et
		// textes////////////////////////////////////////
		labelNom = new JLabel("Saisir login :");
		labelSujet = new JLabel("Saisir password :");

		this.txtFLogin = new JTextField();
		this.textFPasswd = new JPasswordField();

		this.buttOk = new JButton("OK");
		this.buttAnnul = new JButton("Annuler");

		// //////////////////////////////Pane et
		// ScrollPane////////////////////////////////////////
		JPanel grandPanneau = new JPanel(new BorderLayout()); // panel qui
																// contiendra
																// tout les
																// élements
		JPanel nordPanneau = new JPanel((new GridLayout(3, 1)));
		JPanel sudPanneau = new JPanel();

		nordPanneau.add(labelNom);
		nordPanneau.add(txtFLogin);
		nordPanneau.add(labelSujet);
		nordPanneau.add(textFPasswd);

		sudPanneau.add(buttAnnul);
		sudPanneau.add(buttOk);

		grandPanneau.add(nordPanneau, BorderLayout.NORTH);
		grandPanneau.add(sudPanneau, BorderLayout.SOUTH);

		this.add(grandPanneau);

		// //////////////////////////////Listener////////////////////////////////////////

		buttOk.addActionListener(this);
		buttAnnul.addActionListener(this);
		

		// //////////////////////////////fin////////////////////////////////////////
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initializeLogins();
	}

	public void initializeLogins() {
		availableLogins.put("admin", "admin");
	}
	
	public static void addLogin(String key, String value){
		availableLogins.put(key, value);
	}

	/**
	 * Cette méthode permet de mettre à jour les champs du formulaire affiché.
	 * 
	 * @since sprint1
	 * @version sprint_1
	 */
	public void update() {
		txtFLogin.setText("");
		textFPasswd.setText(""); // on met le text à vide
	}

	/**
	 * Cette méthode permet de mettre à jour les champs du formulaire affiché.
	 * 
	 * @param str
	 *            contenu pour l'update des JText
	 * @since sprint1
	 * @version sprint_2
	 */
	public void update(String[] str) {
		txtFLogin.setText(str[0]);
		textFPasswd.setText(str[1]);// on update le texte
	}

	/**
	 * Cette méthode revoit le nom et le titre d'un sujet.
	 * 
	 * @return str tableau de String contenant le nom et le titre du sujet
	 *         saisi.
	 * @since sprint1
	 * @version sprint_2
	 */
	public String[] getParam() {
		String[] str = new String[2];
		str[0] = txtFLogin.getText();
		str[1] = String.copyValueOf(textFPasswd.getPassword()); // on récupere le text des textes
		return str;
	}

	/**
	 * Cette méthode revoit le nom d'un sujet.
	 * 
	 * @return str String contenant le nom du sujet saisi.
	 * @since sprint1
	 * @version sprint_2
	 */
	public String getLogin() {
		String str = txtFLogin.getText();
		return str;
	}

	/**
	 * Cette méthode revoit le prénom
	 * 
	 * @return str String contenant le prénom saisie
	 * @since sprint1
	 * @version sprint_2
	 */
	public String getPasswd() {
		String str = String.copyValueOf(textFPasswd.getPassword());
		return str;
	}

	/**
	 * La méthode actionPerformed permet de réagir en fonction des événements.
	 * 
	 * @param arg0
	 *            L'événement passé en paramétre.
	 * @since sprint1
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == buttOk) {
			if (!txtFLogin.getText().isEmpty()
					&& textFPasswd.getPassword().length>0) {
				if(availableLogins.containsKey(txtFLogin.getText())&& availableLogins.get(txtFLogin.getText()).equals( String.copyValueOf(textFPasswd.getPassword())))
				{
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(this,"Mauvais login/password.");
				}
			} 
			else {
				JOptionPane.showMessageDialog(this,"Aucun login/password.");
			}
		}
		if (arg0.getSource() == buttAnnul) {
			System.exit(0);
		}
		
	}

}
