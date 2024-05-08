package ztest;

import modele.joueur.IANim;
import modele.nim.CoupNim;
import modele.nim.PlateauNim;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Classe permettant de tester l'IA du jeu de Nim.
 */
public class TestIANim {

    /**
     * Plateau dans une situation favorable à l'IA.
     * @see #coupPlateauSituationGagnante
     */
    private final PlateauNim plateauSituationGagnante = new PlateauNim(new int[]{1,2,5});

    /** Réponse possible quand le plateau dans une situation favorable à l'IA.
     * Le but est de ramener le plateau à un XOR égale à 0.
     * @see #plateauSituationGagnante
     */
    private final CoupNim[] coupPlateauSituationGagnante = {
            new CoupNim(3,2),
    };

    /**
     * Plateau dans une situation défavorable à l'IA.
     * @see #coupPlateauSituationNonGagnante
     */
    private final PlateauNim plateauSituationNonGagnante = new PlateauNim(new int[]{1,2,3});

    /** Réponse possible quand le plateau dans une situation défavorable à l'IA.
     * Le but est de jouer un coup, car le plateau est déjà dans une situation gagnante, donc on enlève un bâtonnet du premier tas où c'est possible.
     * @see #plateauSituationNonGagnante
     */
    private final CoupNim[] coupPlateauSituationNonGagnante = {
            new CoupNim(1,1),
    };


    /**
     * Map contenant les plateaux à tester ayant pour valeur les réponses attendue.
     */
    private final HashMap<PlateauNim,CoupNim[]> mapPlateau = new HashMap<>();

    // Bloc d'initialisation remplissant le map.
    {
        mapPlateau.put(plateauSituationGagnante, coupPlateauSituationGagnante);
        mapPlateau.put(plateauSituationNonGagnante,coupPlateauSituationNonGagnante);
    }

    /**
     * Méthode permettant de tester l'IA du jeu de Nim, lance tous les tests contenu dans le map.
     * @return true si tous les tests ont été validés sinon false.
     */
    public boolean test() {
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
