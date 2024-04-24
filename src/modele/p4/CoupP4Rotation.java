package modele.p4;

import modele.abstrait.CoupP4;

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
     * @param sens
     */
    public CoupP4Rotation(boolean sens) {
        this.sens = sens;
    }

    /**
     * Getter du sens de rotation
     * @return Le sens de rotation (sens horaire = true)
     */
    public boolean getSens() {
        return sens;
    }
}
