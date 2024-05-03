package modele.p4;

import modele.abstrait.CoupP4;

/**
 * Classe représentant un coup de puissance 4 hérite du CoupP4
 */
public class CoupP4Coup extends CoupP4 {
    /**
     * La colonne du choisi
     */
    private byte colonne;

    /**
     * La ligne où est le jeton
     */
    private byte ligne;

    /**
     * Constructeur d'un coup du jeu de puissance 4
     * @param colonne La colonne choisie par l'utilisateur
     */
    public CoupP4Coup(byte colonne) {
        this.colonne = (byte) (colonne - 1);
    }

    /**
     * Constructeur d'un coup du jeu de puissance 4
     * @param colonne La colonne choisie par l'utilisateur
     * @param ligne La ligne où a été placé le jeton
     */
    public CoupP4Coup(byte colonne, byte ligne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }

    /**
     * Getter de la colonne
     * @return La colonne choisie par l'utilisateur
     */
    public byte getColonne() {
        return colonne;
    }

    /**
     * Getter de la ligne
     * @return La ligne où a été placé le jeton
     */
    public byte getLigne() {
        return ligne;
    }

    /**
     * Setter de la ligne où a été placé le jeton
     * @param ligne La ligne où a été placé le jeton
     */
    public void setLigne(byte ligne) {
        this.ligne = ligne;
    }


    public boolean isRotation() {
        return false;
    }

    @Override
    public String toString() {
        return "Le coup est colonne " + (colonne+1);
    }
}
