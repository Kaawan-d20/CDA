package modele.abstrait;

public abstract class CoupP4 extends Coup{
    protected byte joueur;

    public byte getJoueur() {
        return joueur;
    }

    public void setJoueur(int joueurCourant) {
        this.joueur = (byte) (joueurCourant + 1);
    }
}
