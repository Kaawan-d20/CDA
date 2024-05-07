package ztest;

import modele.abstrait.CoupP4;
import modele.joueur.IAP4;
import modele.p4.CoupP4Coup;
import modele.p4.CoupP4Rotation;
import modele.p4.PlateauP4;

import java.util.Arrays;
import java.util.HashMap;

public class TestIAP4 {
    private PlateauP4 plateauVide = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},

            }
    );

    private CoupP4Coup[] coupPlateauVide = {
            new CoupP4Coup((byte) 1),
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Coup((byte) 7)
    };

    private PlateauP4 plateau4IA = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {2,0,0,0,0,0,0},
                    new byte[] {2,0,0,0,0,0,1},
                    new byte[] {2,1,0,1,0,0,1},
                    // Est censé prendre la victoire en 0 et non défendre
            }
    );

    private CoupP4Coup[] coupPlateau4IA = {
            new CoupP4Coup((byte) 1)
    };

    private PlateauP4 plateau4Joueur = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {2,0,0,0,0,0,0},
                    new byte[] {1,1,1,0,0,0,2},
                    // Est censé défendre en colonne 3
            }
    );

    private CoupP4Coup[] coupPlateau4Joueur = {
            new CoupP4Coup((byte) 4)
    };

    private PlateauP4 plateau3IA = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,2,0,0,0},
                    new byte[] {1,0,0,2,0,0,1},
                    // Est censé poser en colonne 3
            }
    );

    private CoupP4Coup[] coupPlateau3IA = {
            new CoupP4Coup((byte) 4)
    };

    private PlateauP4 plateau3Joueur = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,2,0,0,0,0,0},
                    new byte[] {0,1,1,0,0,0,2},
                    // Est censé poser en colonne 0 ou 3.
            }
    );

    private CoupP4Coup[] coupPlateau3Joueur = {
            new CoupP4Coup((byte) 1),
            new CoupP4Coup((byte) 4)
    };

    private PlateauP4 plateau2IA = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,1,0,0,0,2,0},
                    // Est censé poser en colonne 4, 5 ou 6.
            }
    );

    private CoupP4Coup[] coupPlateau2IA = {
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Coup((byte) 7)
    };

    private PlateauP4 plateau2Joueur = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,1,0,0,0,0},
                    // Est censé poser en colonne 1, 2 ou 3.
            }
    );
    private CoupP4Coup[] coupPlateau2Joueur = {
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
    };

    private PlateauP4 plateauHoraireIA = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,1,0,0,0,0},
                    new byte[] {0,0,2,0,0,0,0},
                    new byte[] {0,0,2,0,0,0,0},
                    new byte[] {0,0,2,0,0,0,0},
                    new byte[] {0,0,1,0,0,0,2},
            }
    );

    private CoupP4Rotation[] coupPlateauHoraireIA = {
            new CoupP4Rotation(true)
    };


    private PlateauP4 plateauAntiHoraireIA = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,1,0,0,0,0},
                    new byte[] {0,0,2,0,0,0,0},
                    new byte[] {0,0,2,0,0,0,0},
                    new byte[] {0,0,2,0,0,0,0},
                    new byte[] {2,0,1,0,0,0,0},
            }
    );

    private CoupP4Rotation[] coupPlateauAntiHoraireIA = {
            new CoupP4Rotation(false),
    };


    private PlateauP4 plateauHoraireJoueur = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {1,0,0,0,0,0,0},
                    new byte[] {2,0,0,1,0,0,2},
                    new byte[] {1,0,0,2,1,0,2},
                    new byte[] {2,2,1,2,1,1,1},
            }
    );

    private CoupP4[] coupPlateauHoraireJoueur = {
            new CoupP4Coup((byte) 1),
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Rotation(true, (byte) 2),
            new CoupP4Rotation(false, (byte) 2),
    };


    private PlateauP4 plateauAntiHoraireJoueur = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,1},
                    new byte[] {2,0,0,1,0,0,2},
                    new byte[] {2,0,1,2,0,0,1},
                    new byte[] {1,1,1,2,1,2,2},
            }
    );

    private CoupP4[] coupPlateauAntiHoraireJoueur = {
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Coup((byte) 7),
            new CoupP4Rotation(true, (byte) 2),
            new CoupP4Rotation(false, (byte) 2),
    };


    private HashMap<PlateauP4,CoupP4[]> mapPlateau = new HashMap<>();

    {
        mapPlateau.put(plateauVide,coupPlateauVide);
        mapPlateau.put(plateau4IA,coupPlateau4IA);
        mapPlateau.put(plateau4Joueur,coupPlateau4Joueur);
        mapPlateau.put(plateau3IA,coupPlateau3IA);
        mapPlateau.put(plateau3Joueur,coupPlateau3Joueur);
        mapPlateau.put(plateau2IA,coupPlateau2IA);
        mapPlateau.put(plateau2Joueur,coupPlateau2Joueur);
        mapPlateau.put(plateauHoraireIA,coupPlateauHoraireIA);
        mapPlateau.put(plateauAntiHoraireIA,coupPlateauAntiHoraireIA);
        mapPlateau.put(plateauHoraireJoueur,coupPlateauHoraireJoueur);
        mapPlateau.put(plateauAntiHoraireJoueur,coupPlateauAntiHoraireJoueur);
    }

    public boolean test(){
        IAP4 ia = new IAP4();
        boolean[] etat = new boolean[mapPlateau.size()];
        boolean[] temoin = new boolean[mapPlateau.size()];
        int courant = 0;
        for (PlateauP4 plateau : mapPlateau.keySet()){
            CoupP4 resultat = ia.demanderCoup(plateau);
            boolean etatResultat = false;
            for (CoupP4 coup : mapPlateau.get(plateau)){
                etatResultat |= coup.equals(resultat);
            }
            etat[courant] = etatResultat;
            temoin[courant++] = true;
        }
        return Arrays.equals(etat, temoin);
    }
}
