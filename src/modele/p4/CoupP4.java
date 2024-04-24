package modele.p4;

import modele.abstrait.Coup;

public class CoupP4 extends Coup {
    private byte colonne;
    private byte joueur;

    public CoupP4(byte colonne) {
        this.colonne = (byte) (colonne - 1);
    }

    public byte getColonne() {
        return colonne;
    }

    public byte getJoueur() {
        return joueur;
    }

    public void setJoueur(int joueurCourant) {
        this.joueur = (byte) (joueurCourant + 1);
    }
}
