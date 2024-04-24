package modele.p4;

import modele.abstrait.Coup;

public class CoupP4 extends Coup {
    private byte colonne;
    private byte ligne;
    private byte joueur;

    public CoupP4(byte colonne) {
        this.colonne = (byte) (colonne - 1);
    }

    public CoupP4(byte colonne, byte ligne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public byte getColonne() {
        return colonne;
    }

    public byte getLigne() {
        return ligne;
    }

    public byte getJoueur() {
        return joueur;
    }

    public void setJoueur(int joueurCourant) {
        this.joueur = (byte) (joueurCourant + 1);
    }

    public void setLigne(byte ligne) {
        this.ligne = ligne;
    }
}
