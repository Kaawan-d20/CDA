package controleur;

import exception.FormatReponseInvalide;
import exception.NombreBatonnetsInvalide;
import exception.NombreTasInvalides;
import exception.NumeroTasInvalide;
import modele.Joueur;
import modele.PlateauNim;
import vue.Ihm;

/**
 * Classe du contrôleur du jeu de Nim
 */
public class ControleurJeuNim extends Controleur{

    /**
     * Initialise un nouveau contrôleur de jeu Nim avec une interface utilisateur spécifiée.
     *
     * @param ihm L'interface utilisateur associée au contrôleur.
     */
    public ControleurJeuNim (Ihm ihm) {
        this.ihm = ihm;
        this.lesJoueurs = new Joueur[2];
    }

    /**
     * <p>Initialise une nouvelle partie en demandant le nombre de tas.</p>
     * <p>Les noms des joueurs, et en créant le plateauNim de jeu.</p>
     */
    public void jouer() {
        boolean isNbTasValide = false;

        int nombreTas = 0;
        while (!isNbTasValide) {
            try {
                isNbTasValide = true;
                nombreTas = ihm.demanderNbTas();
                if (nombreTas <1){
                    throw new NombreTasInvalides("Le nombre de tas ne peut pas être inférieur à 1");
                }
            } catch (NombreTasInvalides exception) {
                isNbTasValide = false;
                ihm.afficherErreur(exception.getMessage());
            }
        }

        this.plateau = new PlateauNim(nombreTas);

        initJoueur();
        toursDeJeu();
    }

    protected void getCoup() throws FormatReponseInvalide, NombreBatonnetsInvalide, NumeroTasInvalide {
        int[] candidate = ihm.demanderCoupNim(getNomJoueurCourant());
        plateau.retirerBatonnets(candidate[0], candidate[1]);
    }

    protected void victoire(){
        getJoueurCourant().incrementVictoires();
        ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nombrePartie, false);
    }

}
