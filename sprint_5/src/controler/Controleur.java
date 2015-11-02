package controler;
import javax.swing.JOptionPane;

import view.DialLogin;
import view.FenetrePrincipal;

/**
* Controleur permettant de faire fonctionner la vue et le modèle.
* @author Mathis PEZOU et Nicolas RIBEREAU
* @since sprint0
* @version sprint1
*/
public class Controleur {
	
	FenetrePrincipal fenetre;
	DialLogin dialLogin;
	
	
	
	/**
	* Constructeur de la classe Controleur sans paramètre.
	* @since sprint0
	*/
	public Controleur(){
		fenetre = new FenetrePrincipal(this); // Construction de la fenetre principal qui contiendra tout les composants
		dialLogin = new DialLogin(this);
		dialLogin.setVisible(true);
		
		fenetre.setVisible(true); // on ouvre la fenetre principal
	}
	
	/**
	* La méthode demande la confirmation de l'utilisateur avant de terminer le programme.
	* @since sprint0
	*/
	public void ctrlQuit(){
		int confirmation;
		confirmation = JOptionPane.showConfirmDialog(
				fenetre, "Voulez-vous réellement quitter cette application ?", "Quitter ?",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirmation == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	/**
	* La méthode main permet de créer un objet Controleur.
	* @param str
	* @since sprint0
	*/
	public static void main(String[] str){
		new Controleur();
	}
}
