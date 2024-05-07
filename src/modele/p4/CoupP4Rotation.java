package modele.p4;

import modele.abstrait.CoupP4;

import java.util.Objects;

/**
 * Classe représentant un coup de puissance 4 hérite du CoupP4
 */
public class CoupP4Rotation extends CoupP4 {
    /**
     * Le sens de rotation du plateau (sens horaire = true)
     */
    private boolean sens;

    /**
     * Constructeur d'une rotation du jeu de puissance 4
     * @param sens Le sens de rotation (sens horaire = true)
     */
    public CoupP4Rotation(boolean sens) {
        this.sens = sens;
    }

    public CoupP4Rotation(boolean sens, byte joueur) {
        this.sens = sens;
        this.joueur = joueur;
    }

    /**
     * Getter du sens de rotation
     * @return Le sens de rotation (sens horaire = true)
     */
    public boolean getSens() {
        return sens;
    }


    public boolean isRotation() {
        return true;
    }

    @Override
    public String toString() {
        if (sens) {
            return "Le coup est une rotation dans le sens horaire";
        }
        return "Le coup est une rotation dans le sens anti-horaire";
    }

    @Override
    public boolean equals(Object autre) {
        if (this == autre) return true;
        if (autre == null || getClass() != autre.getClass()) return false;
        CoupP4Rotation that = (CoupP4Rotation) autre;
        return sens == that.sens;
    }
}
