package modele.joueur;

import modele.abstrait.CoupP4;
import modele.p4.CoupP4Coup;
import modele.p4.CoupP4Rotation;
import modele.p4.PlateauP4;

import java.util.ArrayList;

/**
 * Classe représentant la stratégie du jeu de Puissance 4.
 */
public class IAP4 extends IA{
    // Permets de pré générer une seule fois la totalité des coups possibles.

    /** Les rotations de l'IA. */
    private static CoupP4Rotation[] lesRotations = {
            new CoupP4Rotation(true, (byte) 1),
            new CoupP4Rotation(false, (byte) 1)
    };

    /** Les rotations du joueur. */
    private static CoupP4Rotation[] lesRotationsAdverses = {
            new CoupP4Rotation(true, (byte) 0),
            new CoupP4Rotation(false, (byte) 0)
    };

    /** Les coups de l'IA. */
    private static CoupP4Coup[] lesPlacements = {
            new CoupP4Coup((byte) 1, 1),
            new CoupP4Coup((byte) 2, 1),
            new CoupP4Coup((byte) 3, 1),
            new CoupP4Coup((byte) 4, 1),
            new CoupP4Coup((byte) 5, 1),
            new CoupP4Coup((byte) 6, 1),
            new CoupP4Coup((byte) 7, 1)
    };

    /** Les coups du joueur. */
    private static CoupP4Coup[] lesPlacementsAdverses = {
            new CoupP4Coup((byte) 1, 0),
            new CoupP4Coup((byte) 2, 0),
            new CoupP4Coup((byte) 3, 0),
            new CoupP4Coup((byte) 4, 0),
            new CoupP4Coup((byte) 5, 0),
            new CoupP4Coup((byte) 6, 0),
            new CoupP4Coup((byte) 7, 0)
    };

    /** Score attribué au coup en fonction de la longueur qui correspond aux indices pour l'IA. */
    private static int[] scoreTailleIa = {1,1,3,5,7};

    /** Score attribué au coup en fonction de la longueur qui correspond aux indices pour le joueur. */
    private static int[] scoreTailleJoueur = {1,1,2,4,6};

    /** Permet de créer un nouvel objet IA. */
    public IAP4() {
        super();
    }

    /**
     * Méthode permettant d'obtenir un coup pour le jeu du Puissance 4.
     * @param plateauOrigine Le plateau du jeu.
     * @return Un coup à jouer.
     */
    public CoupP4 demanderCoup(PlateauP4 plateauOrigine){
        // On vérifie que l'IA peut faire des rotations.
        if (plateauOrigine.isRotations(1)) {
            // On vérifie si l'IA peut gagner en faisant une rotation.
            for (CoupP4Rotation coup : lesRotations) {
                PlateauP4 copie = plateauOrigine.copie();
                copie.rotation(coup);
                // L'IA joue les jaunes donc elle est le joueur n°2.
                if (copie.verifierVictoire() == 2) {
                    return coup;
                }
            }
        }

        // Aucune des rotations ne mène à une victoire, on essaie maintenant les différents coups en leur assignant un score.
        // Initialisation du tableau des scores.
        ArrayList<CoupP4Coup>[] scoreCoups = new ArrayList[7];
        for (int i = 0; i < scoreCoups.length; i++) {
            scoreCoups[i] = new ArrayList<>();
        }

        for (int i = 0; i < lesPlacements.length; i++) {
            PlateauP4 copieIA = plateauOrigine.copie();
            PlateauP4 copieJoueur = plateauOrigine.copie();
            try {
                // On simule les coups de l'IA et du Joueur au même endroit.

                copieIA.placerJeton(lesPlacements[i]);
                copieJoueur.placerJeton(lesPlacementsAdverses[i]);

                // On calcule la taille de la plus grande ligne formée par le jeton.
                int tailleLigneIA = copieIA.compterMaxJetonsAlignes(lesPlacements[i]);
                int tailleLigneJoueur = copieJoueur.compterMaxJetonsAlignes(lesPlacementsAdverses[i]);
                // Si la ligne a le potentiel de former une ligne plus longue pour l'IA, ou si les deux lignes sont de même taille.
                if (tailleLigneIA >= tailleLigneJoueur) {
                    // On référence la valeur.
                    scoreCoups[scoreTailleIa[tailleLigneIA]-1].add(lesPlacements[i]);
                } else {
                    scoreCoups[scoreTailleJoueur[tailleLigneJoueur]-1].add(lesPlacements[i]);
                }
            // Coup invalide (ligne pleine).
            } catch (Exception exn) {
                scoreCoups[0].add(lesPlacements[i]);
            }
        }

        for (int i = 6; i >= 0; i-- ) {
            ArrayList<CoupP4Coup> liste = scoreCoups[i];
            for (CoupP4Coup coup : liste) {
                PlateauP4 copie = plateauOrigine.copie();
                try {
                    copie.placerJeton(coup);
                    if (plateauOrigine.isRotations(0)) { // Si l'adversaire peut faire des rotations.
                        PlateauP4 copieHoriaire = copie.copie();
                        PlateauP4 copieAntiHoraire = copie.copie();
                        copieHoriaire.rotation(lesRotationsAdverses[0]);
                        copieAntiHoraire.rotation(lesRotationsAdverses[1]);
                        if (copie.verifierVictoire() != 1 && copieHoriaire.verifierVictoire() != 1 && copieAntiHoraire.verifierVictoire() != 1) {
                            return coup;
                        }
                    }
                    else { // Si l'adversaire ne peut pas faire des rotations.
                        if (copie.verifierVictoire() != 1) {
                            return coup;
                        }
                    }
                } catch (Exception ignored) {}
            }
        }

        return null; // Jamais atteint.
    }
}
