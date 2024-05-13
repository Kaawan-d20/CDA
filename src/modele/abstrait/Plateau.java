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
     * @return true si la partie est fini, sinon false.
     */
    public abstract boolean verifierFin();
}
