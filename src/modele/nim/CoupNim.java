package modele.nim;

import modele.abstrait.Coup;

/**
 * Classe représentant un coup du jeu de Nim hérite de Coup
 */
public class CoupNim extends Coup {
    /**
     * Numéro du tas que l'utilisateur à sélectionner
     */
    private int tas;

    /**
     * Nombre de bâtonnets que l'utilisateur à sélectionner
     */
    private int nbBatonnets;

    /**
     * Constructeur d'un coup du jeu de Nim
     * @param tas Numéro du tas
     * @param nbBatonnets Nombre de bâtonnets à retirer
     */
    public CoupNim(int tas, int nbBatonnets) {
        this.tas = tas;
        this.nbBatonnets = nbBatonnets;
    }

    /**
     * Constructeur d'un coup du jeu de Nim
     * @param coup Tableau d'int de taille 2 contenant le numéro du tas et le nombre de bâtonnets à retirer
     */
    public CoupNim(int[] coup) {
        this.tas = coup[0];
        this.nbBatonnets = coup[1];
    }

    /**
     * Getter du coup
     * @return Tableau d'int de taille 2 contenant le numéro du tas et le nombre de bâtonnets à retirer
     */
    public int[] getCoup(){
        return new int[]{tas, nbBatonnets};
    }

    /**
     * Getter du nombre de bâtonnets à retirer
     * @return Le nombre de bâtonnets à retirer
     */
    public int getNbBatonnets() {
        return nbBatonnets;
    }

    /**
     * Getter du numéro du tas
     * @return Le numéro du tas
     */
    public int getTas() {
        return tas;
    }
}
