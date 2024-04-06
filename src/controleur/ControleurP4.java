package controleur;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import modele.PlateauP4;
import vue.Ihm;

/**
 * Classe du contrôleur du jeu de puissance 4,
 * hérite de Controleur
 * @see Controleur
 */
public class ControleurP4 extends Controleur{

    /**
     * Initialise un nouveau contrôleur de jeu de puissance 4 avec une interface utilisateur spécifiée.
     *
     * @param ihm L'interface utilisateur associée au contrôleur.
     */
    public ControleurP4(Ihm ihm) {
        this.ihm = ihm;
    }

    /**
     * Initialise une nouvelle partie en demandant les noms des joueurs,
     * crée le PlateauP4 de jeu et lance la partie.
     */
    public void jouer() {
        initJoueur();
        plateau = new PlateauP4();
        toursDeJeu();
    }

    /**
     * Demande le coup à l'ihm et demander au plateau de réaliser le coup.
     * @throws FormatReponseInvalide Si la réponse n'est pas compris entre 1 et 7.
     * @throws ColonnePleine Si la colonne est pleine.
     */
    protected void getCoup() throws FormatReponseInvalide, ColonnePleine {
        byte candidate = ihm.demanderCoupP4(getNomJoueurCourant());
        plateau.placerJeton((byte) (candidate-1), (byte) (numeroJoueurCourant+1));
    }

    /**
     * Incrément le nombre de victoires du joueur courant
     * et gére l'affiche du vainqueur prendre aussi en compte la possibilité d'un ex aequo
     */
    protected void victoire(){
        if (!plateau.verifierVictoire()){
            ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nombrePartie, true);
        } else {
            getJoueurCourant().incrementVictoires();
            ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nombrePartie, false);
        }
        ihm.afficherPlateau(plateau.toString());
    }

    /**
     * Demande à l'ihm si l'utilisateur veut activer ou non la contrainte de rotation,
     * puis demande au plateau de l'activer.
     */
    protected void setOption(){
        //appel de l'ihm et transfère dans le plateau
    }
}
