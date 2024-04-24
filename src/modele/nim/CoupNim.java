package modele.nim;

import modele.abstrait.Coup;

public class CoupNim extends Coup {
    private int tas;
    private int nbBatonnets;

    public CoupNim(int tas, int nbBatonnets) {
        this.tas = tas;
        this.nbBatonnets = nbBatonnets;
    }

    public CoupNim(int[] coup) {
        this.tas = coup[0];
        this.nbBatonnets = coup[1];
    }

    public int[] getCoup(){
        return new int[]{tas, nbBatonnets};
    }

    public int getNbBatonnets() {
        return nbBatonnets;
    }

    public int getTas() {
        return tas;
    }
}
