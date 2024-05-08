package controleur;

import exception.*;
import modele.joueur.IA;
import modele.joueur.IANim;
import modele.nim.CoupNim;
import modele.nim.PlateauNim;
import vue.Ihm;

/**
 * Classe du contrôleur du jeu de Nim hérite de Contrôleur.
 */
public class ControleurNim extends Controleur{
    /**
     * Initialise un nouveau contrôleur de jeu Nim avec une interface utilisateur spécifiée.
     *
     * @param ihm L'interface utilisateur associée au contrôleur.
     */
    public ControleurNim(Ihm ihm) {
        this.ihm = ihm;
    }

    /**
     * Initialise le plateau en demandant le nombre de tas,
     * puis appel son implémentation dans la super classe.
     */
    public void jouer() {
        boolean isNbTasValide = false;

        int nombreTas = 0;
        while (!isNbTasValide) {
            try {
                nombreTas = ihm.demanderNbTas();
                if (nombreTas <1){
                    throw new NombreTasInvalides("Le nombre de tas ne peut pas être inférieur à 1.");
                }
                isNbTasValide = true;
            } catch (NombreTasInvalides exception) {
                ihm.afficherErreur(exception.getMessage());
            }
        }

        this.plateau = new PlateauNim(nombreTas);

        super.jouer();
    }

    /**
     * Demande le coup et demande au plateau de réaliser le coup.
     * @throws NombreBatonnetsInvalide Si le nombre de bâtonnets dans le tas à inférieur au retrait demandé ou que la limite de bâtonnets par coup a été atteinte.
     * @throws NumeroTasInvalide Si le tas demandé est inconnue.
     */
    protected void getCoup() throws NombreBatonnetsInvalide, NumeroTasInvalide {
        CoupNim candidate;
        if (getJoueurCourant().isHuman()){
            ihm.afficherPlateau(plateau.toString());
            candidate = ihm.demanderCoupNim(getNomJoueurCourant());
        } else {
            candidate = ((IANim) getJoueurCourant()).demanderCoup((PlateauNim) plateau);
            ihm.afficherCoup(candidate);
        }
        ((PlateauNim) plateau).retirerBatonnets(candidate);
    }

    /**
     * Incrémente le nombre de victoires du joueur courant
     * et gére l'affiche du vainqueur.
     */
    protected void victoire(){
        getJoueurCourant().incrementVictoires();
        ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nombrePartie, false);
    }

    /**
     * Demande à l'Ihm si l'utilisateur veut activer ou non la limite de bâtonnets par coup,
     * puis demande au plateau de l'activer.
     */
    protected void setOption(){
        //appel de l'Ihm et transfère dans le plateau
        int batonnetMax = ihm.setOptionNim();
        ((PlateauNim)plateau).setOption(batonnetMax);
    }

    /**
     * Retourne une instance de l'IA correspondant au jeu de Nim.
     * @return Une instance de l'IA du jeu de Nim.
     */
    protected IA getIA(){
        return new IANim();
    }
}
