package modele.joueur;

import modele.abstrait.Coup;
import modele.abstrait.CoupP4;
import modele.p4.CoupP4Coup;
import modele.p4.CoupP4Rotation;
import modele.p4.PlateauP4;

import java.util.ArrayList;

public class IAP4 extends IA{

    /**
     * Permet de pré-generer une seule fois la totalitées des coups possibles.
     */
    private static CoupP4Rotation[] lesRotations = {
            new CoupP4Rotation(true),
            new CoupP4Rotation(false)
    };

    private static CoupP4Rotation[] lesRotationsAdverses = {
            new CoupP4Rotation(true),
            new CoupP4Rotation(false)
    };
    private static CoupP4Coup[] lesPlacements = {
            new CoupP4Coup((byte) 1),
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Coup((byte) 7)
    };

    private static CoupP4Coup[] lesPlacementsAdverses = {
            new CoupP4Coup((byte) 1),
            new CoupP4Coup((byte) 2),
            new CoupP4Coup((byte) 3),
            new CoupP4Coup((byte) 4),
            new CoupP4Coup((byte) 5),
            new CoupP4Coup((byte) 6),
            new CoupP4Coup((byte) 7)
    };

    private static int[] scoreTailleIa = {1,1,3,5,7};
    private static int[] scoreTailleJoueur = {1,1,2,4,6};


    /**
     * Permet de créer un nouvel objet IA.
     */
    public IAP4() {
        super("IA");
        for (CoupP4 coup : lesRotations) {
            coup.setJoueur(1);
        }
        for (CoupP4 coup : lesRotationsAdverses) {
            coup.setJoueur(0);
        }
        for (CoupP4 coup : lesPlacements) {
            coup.setJoueur(1);
        }
        for (CoupP4 coup : lesPlacementsAdverses) {
            coup.setJoueur(0);
        }
    }


    public CoupP4 demanderCoup(PlateauP4 plateauOrigine){
        // On vérifie si l'IA peut gagner en faisant une rotation
        for (CoupP4Rotation coup : lesRotations) {
            PlateauP4 copie = plateauOrigine.copie();
            copie.rotation(coup);
            // Magic number, l'IA joue les jaunes donc n°2
            if (copie.verifierVictoire() == 2) {
                return coup;
            }
        }
        // Aucune des rotations ne mene a une victoire, on essaie maintenant les différents coups en
        // leur assignant un score.
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
                int tailleLigneIA = copieIA.compterMaxJetonsAlignés(lesPlacements[i]);
                int tailleLigneJoueur = copieJoueur.compterMaxJetonsAlignés(lesPlacementsAdverses[i]);
                /*System.out.println(lesPlacements[i]);
                System.out.println("ia");
                System.out.println(tailleLigneIA);
                System.out.println("j");
                System.out.println(tailleLigneJoueur);*/
                // Si la ligne a le potentiel de former une ligne plus longue pour l'IA, ou si les deux
                // lignes sont de même taille
                if (tailleLigneIA > tailleLigneJoueur) {
                    // On référence la valeur
                    scoreCoups[scoreTailleIa[tailleLigneIA]-1].add(lesPlacements[i]);
                } else {
                    scoreCoups[scoreTailleJoueur[tailleLigneJoueur]-1].add(lesPlacements[i]);
                }
            // Coup invalide (ligne pleine)
            } catch (Exception exn) {
                scoreCoups[0].add(lesPlacements[i]);
            }
            copieJoueur = null;
            copieIA = null;
        }
    for (int i = 6; i > 0; i-- ) {
        ArrayList<CoupP4Coup> liste = scoreCoups[i];
        for (CoupP4Coup coup : liste) {
            PlateauP4 copie = plateauOrigine.copie();
            try {
                copie.placerJeton(coup);
                PlateauP4 copie2 = copie.copie();
                PlateauP4 copie3 = copie.copie();
                copie2.rotation(lesRotationsAdverses[0]);
                copie3.rotation(lesRotationsAdverses[1]);
                if (copie.verifierVictoire() != 1 || copie2.verifierVictoire() != 1 || copie3.verifierVictoire() != 1) {
                    return coup;
                }
            } catch (Exception exn) {
                continue;
            }

        }
    }
    return new CoupP4Coup((byte) 5); // Jamais atteint.
    }
}
