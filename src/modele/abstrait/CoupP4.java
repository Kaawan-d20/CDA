package modele.abstrait;

/**
 * Classe abstraite représentant un coup de puissance 4
 * Contient les informations sur le numéro du joueur
 * Hérite de Coup
 */
public abstract class CoupP4 extends Coup{
    /**
     * Le numéro du joueur
     */
    protected byte joueur;

    /**
     * Getter du numéro du joueur
     * @return le numéro du joueur
     */
    public byte getJoueur() {
        return joueur;
    }

    /**
     * Setter du numéro du joueur
     * @param joueurCourant Le numéro du joueur
     */
    public void setJoueur(int joueurCourant) {
        this.joueur = (byte) (joueurCourant + 1);
    }

    public abstract boolean isRotation();
}
