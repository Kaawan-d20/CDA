package modele.p4;

import modele.abstrait.CoupP4;

/**
 * Classe représentant un coup de puissance 4 hérite du CoupP4.
 */
public class CoupP4Rotation extends CoupP4 {
    /** Le sens de rotation du plateau (sens horaire = true). */
    private boolean sens;

    /**
     * Constructeur d'une rotation du jeu de puissance 4.
     * @param sens Le sens de rotation (sens horaire = true).
     */
    public CoupP4Rotation(boolean sens) {
        this.sens = sens;
    }

    /**
     * Constructeur d'une rotation du jeu de puissance 4.
     * @param sens Le sens de rotation (sens horaire = true).
     * @param joueur Le numéro du joueur.
     */
    public CoupP4Rotation(boolean sens, byte joueur) {
        this.sens = sens;
        this.joueur = (byte) (joueur + 1);
    }

    /**
     * Getter du sens de rotation.
     * @return Le sens de rotation (sens horaire = true).
     */
    public boolean getSens() {
        return sens;
    }

    /**
     * Méthode permettant de savoir si le coup est une rotation.
     * @return true pour une rotation et false pour un coup.
     */
    public boolean isRotation() {
        return true;
    }

    /**
     * Permet d'avoir le coup par une représentation sous forme de chaîne de caractère.
     * @return Une représentation du coup sous forme de chaîne de caractère.
     */
    @Override
    public String toString() {
        if (sens) {
            return "Le coup est une rotation dans le sens horaire.";
        }
        return "Le coup est une rotation dans le sens anti-horaire.";
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
        CoupP4Rotation that = (CoupP4Rotation) autre;
        return sens == that.sens;
    }
}
