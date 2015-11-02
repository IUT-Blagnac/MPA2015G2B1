package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CSVLibrairie;
import model.Etudiants;

/**
 * Boîte de dialogue permetant de créer un nouveau sujet.
 * 
 * @author Nicolas RIBEREAU et Mathis PEZOU
 * @since sprint_1
 * @version sprint_2
 */
public class DialAjouterLogin extends JDialog implements ActionListener {

	private static final long serialVersionUID = 70374439313009090L;

	Etudiants etudiants;


	private JButton buttOk, buttAnnul;

	private JTextField txtFLogin, textFPasswd;

	JLabel labelNom, labelSujet;

	
	private ArrayList<String[]> allLogins;
	
	private DialListLogin parent;

	
	public DialAjouterLogin(DialListLogin dial, ArrayList<String[]> logins) {
		
		super(dial, "Nouveau login", true);


		// //////////////////////////////Bouton et
		// textes////////////////////////////////////////
		labelNom = new JLabel("Saisir login :");
		labelSujet = new JLabel("Saisir password :");

		this.txtFLogin = new JTextField();
		this.textFPasswd = new JTextField();

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
		
		this.allLogins = logins;
		parent = dial;
	}

	public void addLogin(String login, String password) {
		String[] newValues = new String[2];
		newValues[0]=login;
		newValues[1]=password;
		this.allLogins.add(newValues);
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
		str[1] = textFPasswd.getText(); // on récupere le text des textes
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
		String str = textFPasswd.getText();
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
					&& !textFPasswd.getText().isEmpty()) {
				this.addLogin(txtFLogin.getText(),textFPasswd.getText());
				File path = new File("data/mdp.csv");
				CSVLibrairie.saveCSV(path, this.allLogins, ";");
				this.parent.update();
				dispose();
			} 
			else {
				JOptionPane.showMessageDialog(this,"Le login et le password doivent être doit être spécifiés.");
			}
		}
		if (arg0.getSource() == buttAnnul) {
			System.exit(0);
		}

	}

}
