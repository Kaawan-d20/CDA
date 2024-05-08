package modele.nim;

import exception.NombreBatonnetsInvalide;
import exception.NumeroTasInvalide;
import modele.abstrait.Plateau;

/**
 * Classe représentant un plateau de jeu de Nim hérite de Plateau.
 */
public class PlateauNim extends Plateau {
    /** Le tableau contenant les tas. */
    private Tas[] lesTas;
    /** Le nombre de tas. */
    private int nombreTas;

    /** Le nombre Maximum de bâtonnets retirable par coups. */
    private int maxBatonnets;
    /** L'état d'activation de la limite. */
    private boolean isLimite;

    /**
     * Permet de construire un nouveau PlateauNim, en fonction d'un nombre de tas.
     * @param nombreTas Le nombre de tas à créer dans le plateau.
     */
    public PlateauNim(int nombreTas) {
        this.lesTas = new Tas[nombreTas];
        this.nombreTas = nombreTas;
    }

    /**
     * Permet de construire un nouveau PlateauNim, en fonction du tableau passée en paramètre.
     * @param tas Un tableau représentant les tas souhaité.
     */
    public PlateauNim(int[] tas) {
        this.nombreTas = tas.length;
        this.lesTas = new Tas[nombreTas];
        for (int i = 0; i < tas.length; i++) {
            lesTas[i] = new Tas(tas[i]);
        }
    }

    /** Remplit le plateau en créant les tas ou remplace les tas déjà existant. */
    public void reset() {
        for (int i = 0; i < nombreTas; i++) {
            lesTas[i] = new Tas(2*(i+1)-1);
        }
    }

    /**
     * Permet de verifier si une partie est finie, c'est-à-dire si tous les tas sont vides.
     * @return true si la partie est fini, sinon false.
     */
    public boolean verifierFin() {
        boolean b = true;
        for (Tas t : lesTas) {
            b = b && t.estVide();
        }
        return b;
    }

    /**
     * Permet de retourner l'état du plateau sous la forme d'un tableau d'entier.
     * @return un tableau contenant le nombre de bâtonnets de chaque tas, au format [1, 3, 5].
     */
    public int[] getPlateau() {
        int[] plateau = new int[nombreTas];
        for (int i = 0; i < nombreTas; i++) {
            plateau[i] = lesTas[i].getNombre();
        }
        return plateau;
    }

    /**
     * Retire un nombre spécifié de bâtonnets d'un tas spécifié sur le plateau.
     *
     * @param coup Objet représentant le coup.
     * @throws NumeroTasInvalide Si le numéro du tas est invalide (hors des limites ou égal à zéro).
     * @throws NombreBatonnetsInvalide Si le nombre de bâtonnets à retirer est invalide (négatif ou supérieur au nombre actuel de bâtonnets dans le tas) ou que la limite est atteinte.
     */
    public void retirerBatonnets(CoupNim coup) throws NombreBatonnetsInvalide, NumeroTasInvalide {
        if ( coup.getTas() > nombreTas || coup.getTas() <= 0 ) {
            throw new NumeroTasInvalide("Vous avez sélectionné un tas inconnu.");
        }
        if (isLimite && coup.getNbBatonnets() > maxBatonnets ) {
            throw new NombreBatonnetsInvalide("Vous avez sélectionner trop de bâtonnets (max " + maxBatonnets + " bâtonnets par coup)");
        }
        lesTas[coup.getTas()-1].retirerBatonnet(coup.getNbBatonnets());
    }

    /**
     * <p>Renvoie une représentation sous forme de chaîne de caractères de l'objet.</p>
     * <p>La chaîne est composée de la représentation de chaque "Tas" dans la collection,
     * disposée horizontalement avec un espacement approprié pour les centrer dans une ligne.</p>
     *
     * @return une représentation sous forme de chaîne de caractères de l'objet,
     * avec les représentations des "Tas" centrées dans la ligne.
     */
    @Override
    public String toString() {
        int largeur = 2 * nombreTas - 1;
        String s = "";
        for (int i = 0; i < nombreTas; i++) {
            Tas t = lesTas[i];
            String batonnets = t.toString();
            int tailleMarge = (largeur - batonnets.length()) / 2;
            String marge = " ".repeat(tailleMarge);
            s += (i+1) + ": " + marge + batonnets + marge + "\n";
        }
        return s;
    }

    /**
     * Setter pour le nombre Maximum de bâtonnets retirable en un coup.
     * @param maxBatonnets le nombre maximum de bâtonnets (pour ne pas avoir de limite 0).
     */
    public void setOption(int maxBatonnets) {
        if (maxBatonnets == 0) {
            this.isLimite = false;
            this.maxBatonnets= Integer.MAX_VALUE;
        } else {
            this.isLimite = true;
            this.maxBatonnets = maxBatonnets;
        }
    }

    /**
     * Getter de l'activation de la limite.
     * @return true si la limite est activée, sinon false.
     */
    public boolean isLimite() {
        return isLimite;
    }

    /**
     * Getter du nombre maximum de bâtonnets retirable par coup.
     * @return Le nombre maximum de bâtonnets retirable par coup.
     */
    public int getMaxBatonnets() {
        return maxBatonnets;
    }
}