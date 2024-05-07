package ztest;

import exception.JeuInvalideException;
import modele.joueur.IANim;
import modele.nim.CoupNim;
import modele.nim.PlateauNim;

import java.util.Arrays;
import java.util.HashMap;

public class TestIANim {

    private PlateauNim plateauSituationGagnante = new PlateauNim(new int[]{1,2,5});

    private CoupNim[] coupPlateauSituationGagnante = {
            new CoupNim(3,2),
    };

    private PlateauNim plateauSituationNonGagnante = new PlateauNim(new int[]{1,2,3});

    private CoupNim[] coupPlateauSituationNonGagnante = {
            new CoupNim(1,1),
    };



    private HashMap<PlateauNim,CoupNim[]> mapPlateau = new HashMap<>();

    {
        mapPlateau.put(plateauSituationGagnante, coupPlateauSituationGagnante);
        mapPlateau.put(plateauSituationNonGagnante,coupPlateauSituationNonGagnante);
    }

    public boolean test() throws JeuInvalideException {
        IANim ia = new IANim();
        boolean[] etat = new boolean[mapPlateau.size()];
        boolean[] temoin = new boolean[mapPlateau.size()];
        int courant = 0;
        for (PlateauNim plateau : mapPlateau.keySet()){
            CoupNim resultat = ia.demanderCoup(plateau);
            boolean etatResultat = false;
            for (CoupNim coup : mapPlateau.get(plateau)){
                etatResultat |= coup.equals(resultat);
            }
            etat[courant] = etatResultat;
            temoin[courant++] = true;
        }
        return Arrays.equals(etat, temoin);
    }
}
