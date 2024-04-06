package controleur;

import exception.*;
import modele.PlateauNim;
import vue.Ihm;

/**
 * Classe du contrôleur du jeu de Nim
 * hérite de Controleur
 * @see Controleur
 */
public class ControleurJeuNim extends Controleur{

    /**
     * Initialise un nouveau contrôleur de jeu Nim avec une interface utilisateur spécifiée.
     *
     * @param ihm L'interface utilisateur associée au contrôleur.
     */
    public ControleurJeuNim (Ihm ihm) {
        this.ihm = ihm;
    }

    /**
     * Initialise une nouvelle partie en demandant le nombre de tas,
     * les noms des joueurs, crée le PlateauNim de jeu et lance la partie.
     */
    public void jouer() {
        boolean isNbTasValide = false;

        int nombreTas = 0;
        while (!isNbTasValide) {
            try {
                nombreTas = ihm.demanderNbTas();
                if (nombreTas <1){
                    throw new NombreTasInvalides("Le nombre de tas ne peut pas être inférieur à 1");
                }
                isNbTasValide = true;
            } catch (NombreTasInvalides exception) {
                ihm.afficherErreur(exception.getMessage());
            }
        }

        this.plateau = new PlateauNim(nombreTas);

        initJoueur();
        toursDeJeu();
    }
    /**
     * Demande le coup à l'ihm et demander au plateau de réaliser le coup.
     * @throws FormatReponseInvalide Si la réponse n'est pas au format `m n`.
     * @throws NombreBatonnetsInvalide Si le nombre de bâtonnets dans le tas à inférieur au retrait demandé ou que la limite de bâtonnets par coup a été atteinte.
     * @throws NumeroTasInvalide Si le tas demandé est inconnue.
     */
    protected void getCoup() throws FormatReponseInvalide, NombreBatonnetsInvalide, NumeroTasInvalide {
        int[] candidate = ihm.demanderCoupNim(getNomJoueurCourant());
        plateau.retirerBatonnets(candidate[0], candidate[1]);
    }

    /**
     * Incrément le nombre de victoires du joueur courant
     * et gére l'affiche du vainqueur.
     */
    protected void victoire(){
        getJoueurCourant().incrementVictoires();
        ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nombrePartie, false);
    }

    /**
     * Demande à l'ihm si l'utilisateur veut activer ou non la limite de bâtonnets par coup,
     * puis demande au plateau de l'activer.
     */
    protected void setOption(){
        //appel de l'ihm et transfère dans le plateau
        int batonnetMax = ihm.setOptionNim();
        if (batonnetMax > 0) {
            plateau.setOption(batonnetMax);
        } else {
            ihm.afficherErreur("Merci d'entrer un entier Positif");
        }
    }
}
