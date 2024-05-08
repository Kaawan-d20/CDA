package modele.abstrait;

/**
 * <p>Classe abstraite représentant un coup de puissance 4.</p>
 * <p>Contient les informations sur le numéro du joueur.</p>
 * <p>Hérite de Coup.</p>
 */
public abstract class CoupP4 extends Coup{
    /**
     * Le numéro du joueur.
     */
    protected byte joueur;

    /**
     * Getter du numéro du joueur.
     * @return Le numéro du joueur.
     */
    public byte getJoueur() {
        return joueur;
    }

    /**
     * Setter du numéro du joueur.
     * @param joueurCourant Le numéro du joueur.
     */
    public void setJoueur(int joueurCourant) {
        this.joueur = (byte) (joueurCourant + 1);
    }

    /**
     * Méthode permettant de savoir si le coup est une rotation.
     * @return true pour une rotation et false pour un coup.
     */
    public abstract boolean isRotation();
}
