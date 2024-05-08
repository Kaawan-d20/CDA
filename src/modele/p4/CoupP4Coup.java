package modele.p4;

import modele.abstrait.CoupP4;

/**
 * Classe représentant un coup de puissance 4 hérite du CoupP4.
 */
public class CoupP4Coup extends CoupP4 {
    /** La colonne du choisi. */
    private byte colonne;

    /** La ligne où est le jeton. */
    private byte ligne;

    /**
     * Constructeur d'un coup du jeu de puissance 4.
     * @param colonne La colonne choisie par l'utilisateur (c ∈ [1,7]).
     */
    public CoupP4Coup(byte colonne) {
        this.colonne = (byte) (colonne - 1);
    }

    /**
     * Constructeur d'un coup du jeu de puissance 4.
     * @param colonne La colonne choisie par l'utilisateur (c ∈ [1,7]).
     * @param ligne La ligne où a été placé le jeton.
     */
    public CoupP4Coup(byte colonne, byte ligne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }

    /**
     * Constructeur d'un coup du jeu de puissance 4.
     * @param colonne La colonne choisie par l'utilisateur (c ∈ [1,7]).
     * @param joueur Le numéro du joueur.
     */
    public CoupP4Coup(byte colonne, int joueur) {
        this.colonne = (byte) (colonne - 1);
        this.joueur = (byte) (joueur + 1);
    }

    /**
     * Getter de la colonne.
     * @return La colonne choisie par l'utilisateur.
     */
    public byte getColonne() {
        return colonne;
    }

    /**
     * Getter de la ligne.
     * @return La ligne où a été placé le jeton.
     */
    public byte getLigne() {
        return ligne;
    }

    /**
     * Setter de la ligne où a été placé le jeton.
     * @param ligne La ligne où a été placé le jeton.
     */
    public void setLigne(byte ligne) {
        this.ligne = ligne;
    }

    /**
     * Méthode permettant de savoir si le coup est une rotation.
     * @return true pour une rotation et false pour un coup.
     */
    public boolean isRotation() {
        return false;
    }

    /**
     * Permet d'avoir le coup par une représentation sous forme de chaîne de caractère.
     * @return Une représentation du coup sous forme de chaîne de caractère.
     */
    @Override
    public String toString() {
        return "Le coup est colonne " + (colonne+1) + ".";
    }

    /**
     * Permet de tester l'égalité entre deux objets.
     * @param autre L'objet a testé.
     * @return true si égal sinon false.
     */
    @Override
    public boolean equals(Object autre) {
        if (this == autre) return true;
        if (autre == null || getClass() != autre.getClass()) return false;
        CoupP4Coup that = (CoupP4Coup) autre;
        return colonne == that.getColonne();
    }

    /**
     * Méthode permettant de cloner complétement le coup.
     * @return Un clone du coup.
     */
    protected CoupP4Coup copie() {
        CoupP4Coup coupClone = new CoupP4Coup(colonne,ligne);
        coupClone.setJoueur(this.joueur);
        return coupClone;
    }
}
