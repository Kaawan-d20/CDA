package modele.abstrait;

/**
 * Classe abstraite du plateau.
 */
public abstract class Plateau {
    /**
     * Méthode permettant de réinitialiser le plateau.
     */
    public abstract void reset();

    /**
     * Méthode permettant de savoir si quelqu'un à gagner.
     */
    public abstract boolean verifierFin();
}
