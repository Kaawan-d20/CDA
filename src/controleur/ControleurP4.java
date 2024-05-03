package controleur;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import modele.abstrait.CoupP4;
import modele.joueur.IA;
import modele.joueur.IANim;
import modele.joueur.IAP4;
import modele.joueur.Joueur;
import modele.nim.PlateauNim;
import modele.p4.CoupP4Coup;
import modele.p4.CoupP4Rotation;
import modele.p4.PlateauP4;
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
        plateau = new PlateauP4();
        super.jouer();
    }

    /**
     * Demande le coup à l'ihm et demander au plateau de réaliser le coup.
     * @throws FormatReponseInvalide Si la réponse n'est pas compris entre 1 et 7.
     * @throws ColonnePleine Si la colonne est pleine.
     */
    protected void getCoup() throws FormatReponseInvalide, ColonnePleine {
        CoupP4 coup;
        if (getJoueurCourant().isHuman()){
            ihm.afficherPlateau(plateau.toString());
            if ( ((PlateauP4)plateau).isRotations(numeroJoueurCourant) && !ihm.demanderCoupOuRotation(getNomJoueurCourant()) ) {
                coup = ihm.demanderRotation(getNomJoueurCourant());
            } else {
                coup = ihm.demanderCoupP4(getNomJoueurCourant());
            }
        } else {
            coup = ((IAP4) getJoueurCourant()).demanderCoup((PlateauP4) plateau);
            ihm.afficherCoup(coup);
        }

        coup.setJoueur(numeroJoueurCourant);

        if (coup.isRotation()) {
            ((PlateauP4)plateau).rotation((CoupP4Rotation) coup);
        } else {
            ((PlateauP4)plateau).placerJeton((CoupP4Coup) coup);
        }
    }

    /**
     * Incrément le nombre de victoires du joueur courant
     * et gére l'affiche du vainqueur prendre aussi en compte la possibilité d'un ex aequo
     */
    protected void victoire(){
        byte idVainqueur = ((PlateauP4)plateau).verifierVictoire();
        if (idVainqueur == 0) {
            ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nombrePartie, true);
        } else {
            Joueur vainqueur = lesJoueurs[idVainqueur -1];
            vainqueur.incrementVictoires();
            ihm.afficherVictoire(vainqueur.getNom(), vainqueur.getNbVictoires(), nombrePartie, false);
        }
        ihm.afficherPlateau(plateau.toString());
    }

    /**
     * Demande à l'ihm si l'utilisateur veut activer ou non la contrainte de rotation,
     * puis demande au plateau de l'activer.
     */
    protected void setOption(){
        //appel de l'ihm et transfère dans le plateau
        boolean option = ihm.demanderActivationRotation();
        ((PlateauP4)plateau).setRotations(option);
    }

    protected IA getIA(){
        return new IAP4();
    }
}
