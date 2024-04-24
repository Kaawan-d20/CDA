package modele.p4;

import modele.abstrait.CoupP4;

public class CoupP4Coup extends CoupP4 {
    private byte colonne;
    private byte ligne;

    public CoupP4Coup(byte colonne) {
        this.colonne = (byte) (colonne - 1);
    }

    public CoupP4Coup(byte colonne, byte ligne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public byte getColonne() {
        return colonne;
    }

    public byte getLigne() {
        return ligne;
    }


    public void setLigne(byte ligne) {
        this.ligne = ligne;
    }
}
