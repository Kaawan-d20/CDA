package ztest;

import modele.abstrait.CoupP4;
import modele.joueur.IAP4;
import modele.p4.CoupP4Coup;
import modele.p4.CoupP4Rotation;
import modele.p4.PlateauP4;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Classe permettant de tester l'IA du jeu de Puissance 4.
 */
public class TestIAP4 {
    /**
     * Plateau vide.
     * @see #coupPlateauVide
     */
    private final PlateauP4 plateauVide = new PlateauP4(new byte[][]
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

    /**
     * Réponse possible quand le plateau est vide.
     * Le but est de placer un jeton.
     * @see #plateauVide
     */
    private final CoupP4Coup[] coupPlateauVide = {
            new CoupP4Coup((byte) 1),
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Coup((byte) 7)
    };


    /**
     * Plateau dans le cas où l'IA peut gagner.
     * @see #coupPlateau4IA
     */
    private final PlateauP4 plateau4IA = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {2,0,0,0,0,0,0},
                    new byte[] {2,0,0,0,0,0,1},
                    new byte[] {2,1,0,1,0,0,1},
            }
    );

    /**
     * Réponse possible quand l'IA peut gagner.
     * Le but est de placer un jeton dans la colonne 1 pour gagner.
     * @see #plateau4IA
     */
    private final CoupP4Coup[] coupPlateau4IA = {
            new CoupP4Coup((byte) 1)
    };


    /**
     * Plateau dans le cas où le joueur peut gagner.
     * @see #coupPlateau4Joueur
     */
    private final PlateauP4 plateau4Joueur = new PlateauP4(new byte[][]
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

    /**
     * Réponse possible quand le joueur peut gagner.
     * Le but est de placer un jeton dans la colonne 4 pour défendre et empêcher le joueur de gagner.
     * @see #plateau4Joueur
     */
    private final CoupP4Coup[] coupPlateau4Joueur = {
            new CoupP4Coup((byte) 4)
    };


    /**
     * Plateau dans le cas où l'IA peut aligner 3 jetons.
     * @see #coupPlateau3IA
     */
    private final PlateauP4 plateau3IA = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,2,0,0,0},
                    new byte[] {1,0,0,2,0,0,1},
            }
    );

    /**
     * Réponse possible quand l'IA peut aligner 3 jetons.
     * Le but est de placer un jeton dans la colonne 4 pour aligner 3 jetons.
     * @see #plateau3IA
     */
    private final CoupP4Coup[] coupPlateau3IA = {
            new CoupP4Coup((byte) 4)
    };


    /**
     * Plateau dans le cas où le joueur peut aligner 3 jetons.
     * @see #coupPlateau3Joueur
     */
    private final PlateauP4 plateau3Joueur = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,2,0,0,0,0,0},
                    new byte[] {0,1,1,0,0,0,2},
            }
    );

    /**
     * Réponse possible quand l'IA peut aligner 3 jetons.
     * Le but est de placer un jeton dans la colonne 1 ou 4 pour empêcher le joueur d'aligner 3 jetons.
     * @see #plateau3Joueur
     */
    private final CoupP4Coup[] coupPlateau3Joueur = {
            new CoupP4Coup((byte) 1),
            new CoupP4Coup((byte) 4)
    };


    /**
     * Plateau dans le cas où l'IA peut aligner 2 jetons.
     * @see #coupPlateau2IA
     */
    private final PlateauP4 plateau2IA = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,1,0,0,0,2,0},
            }
    );

    /**
     * Réponse possible quand l'IA peut aligner 2 jetons.
     * Le but est de placer un jeton dans la colonne 5, 6 ou 7 pour aligner 2 jetons.
     * @see #plateau2IA
     */
    private final CoupP4Coup[] coupPlateau2IA = {
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Coup((byte) 7)
    };


    /**
     * Plateau dans le cas où le joueur peut aligner 2 jetons.
     * @see #coupPlateau2Joueur
     */
    private final PlateauP4 plateau2Joueur = new PlateauP4(new byte[][]
            {
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,0,0,0,0,0},
                    new byte[] {0,0,1,0,0,0,0},
            }
    );

    /**
     * Réponse possible quand l'IA peut aligner 2 jetons.
     * Le but est de placer un jeton dans la colonne 2, 3 ou 4 pour empêcher le joueur d'aligner 2 jetons.
     * @see #plateau2Joueur
     */
    private final CoupP4Coup[] coupPlateau2Joueur = {
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
    };


    /**
     * Plateau dans le cas où l'IA peut gagner quand elle fait une rotation dans le sens horaire.
     * @see #coupPlateauHoraireIA
     */
    private final PlateauP4 plateauHoraireIA = new PlateauP4(new byte[][]
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

    /**
     * Réponse possible quand l'IA peut gagner quand elle fait une rotation dans le sens horaire.
     * Le but est de faire une rotation dans le sens horaire.
     * @see #plateauHoraireIA
     */
    private final CoupP4Rotation[] coupPlateauHoraireIA = {
            new CoupP4Rotation(true)
    };


    /**
     * Plateau dans le cas où l'IA peut gagner quand elle fait une rotation dans le sens horaire.
     * @see #coupPlateauAntiHoraireIA
     */
    private final PlateauP4 plateauAntiHoraireIA = new PlateauP4(new byte[][]
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

    /**
     * Réponse possible quand l'IA peut gagner quand elle fait une rotation dans le sens horaire.
     * Le but est de faire une rotation dans le sens horaire.
     * @see #plateauAntiHoraireIA
     */
    private final CoupP4Rotation[] coupPlateauAntiHoraireIA = {
            new CoupP4Rotation(false),
    };


    /**
     * Plateau dans le cas où le meilleur coup de l'IA permet au joueur en faisant une rotation dans le sens horaire de gagner.
     * @see #coupPlateauHoraireJoueur
     */
    private final PlateauP4 plateauHoraireJoueur = new PlateauP4(new byte[][]
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

    /**
     * Réponse dans le cas où le meilleur coup de l'IA permet au joueur en faisant une rotation dans le sens horaire de gagner.
     * Le but est de pas jouer le meilleur coup possible afin d'éviter au joueur de gagner.
     * @see #plateauHoraireJoueur
     */
    private final CoupP4[] coupPlateauHoraireJoueur = {
            new CoupP4Coup((byte) 1),
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Rotation(true, (byte) 1),
            new CoupP4Rotation(false, (byte) 1),
    };


    /**
     * Plateau dans le cas où le meilleur coup de l'IA permet au joueur en faisant une rotation dans le sens antihoraire de gagner.
     * @see #coupPlateauAntiHoraireJoueur
     */
    private final PlateauP4 plateauAntiHoraireJoueur = new PlateauP4(new byte[][]
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

    /**
     * Réponse dans le cas où le meilleur coup de l'IA permet au joueur en faisant une rotation dans le sens antihoraire de gagner.
     * Le but est de pas jouer le meilleur coup possible afin d'éviter au joueur de gagner.
     * @see #plateauAntiHoraireJoueur
     */
    private final CoupP4[] coupPlateauAntiHoraireJoueur = {
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Coup((byte) 7),
            new CoupP4Rotation(true, (byte) 1),
            new CoupP4Rotation(false, (byte) 1),
    };

    /**
     * Map contenant les plateaux à tester ayant pour valeur les réponses attendue.
     */
    private final HashMap<PlateauP4,CoupP4[]> mapPlateau = new HashMap<>();

    // Bloc d'initialisation remplissant le map.
    {
        // Activation des rotations.
        plateauHoraireIA.setRotations(true);
        plateauAntiHoraireIA.setRotations(true);
        plateauHoraireJoueur.setRotations(true);
        plateauAntiHoraireJoueur.setRotations(true);

        // Remplissage du map.
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

    /**
     * Méthode permettant de tester l'IA du jeu de Puissance 4, lance tous les tests contenu dans le map.
     * @return true si tous les tests ont été validés sinon false.
     */
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
