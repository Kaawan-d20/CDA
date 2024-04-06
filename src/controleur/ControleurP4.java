package controleur;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import modele.Joueur;
import modele.PlateauP4;
import vue.Ihm;

/**
 * Classe du contrôleur du jeu de puissance 4
 */
public class ControleurP4 extends Controleur{

    /**
     * Initialise un nouveau contrôleur de jeu de puissance 4 avec une interface utilisateur spécifiée.
     *
     * @param ihm L'interface utilisateur associée au contrôleur.
     */
    public ControleurP4(Ihm ihm) {
        this.ihm = ihm;
        this.lesJoueurs = new Joueur[2];
        this.plateau = new PlateauP4();
    }

    /**
     * Initialise une nouvelle partie en demandant les noms des joueurs,
     * et en créant le plateauP4 de jeu.
     */
    public void jouer() {
        initJoueur();
        plateau = new PlateauP4();
        toursDeJeu();
    }

    protected void getCoup() throws FormatReponseInvalide, ColonnePleine {
        byte candidate = ihm.demanderCoupP4(getNomJoueurCourant());
        plateau.placerJeton((byte) (candidate-1), (byte) (numeroJoueurCourant+1));
    }

    protected void victoire(){
        if (!plateau.verifierVictoire()){
            ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nombrePartie, true);
        } else {
            getJoueurCourant().incrementVictoires();
            ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nombrePartie, false);
        }
        ihm.afficherPlateau(plateau.toString());
    }
}
