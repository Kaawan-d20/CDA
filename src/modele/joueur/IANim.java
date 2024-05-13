package modele.joueur;

import modele.nim.CoupNim;
import modele.nim.PlateauNim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe représentant les différentes stratégies du jeu de Nim.
 */
public class IANim extends IA{
    /**
     * Permet de créer un nouvel objet IANim.
     */
    public IANim() {
        super();
    }

    /**
     * Méthode permettant d'obtenir un coup pour le jeu de Nim.
     * @param plateau Le plateau du jeu.
     * @return Un coup à jouer.
     */
    public CoupNim demanderCoup(PlateauNim plateau) {
        if (plateau.isLimite()){
            return demanderCoupAleatoire(plateau);
        } else {
            return demanderCoupGagnant(plateau);
        }
    }

    /**
     * Stratégie aléatoire est utilisée quand le jeu à de limite de bâtonnets à retirer en une seule fois.
     * @param plateau Le plateau du jeu.
     * @return Un coup à jouer.
     */
    private CoupNim demanderCoupAleatoire(PlateauNim plateau){
        List<int[]> coupPossible = new ArrayList<>();
        for (int i = 0; i < plateau.getPlateau().length; i++) { // Génération de tout les coups possible.
            for (int j = 0; j < plateau.getPlateau()[i] && j < plateau.getMaxBatonnets(); j++) { // Se limite au nombre de bâtonnets dans le tas et à la limite.
                coupPossible.add(new int[]{i+1,j+1});
            }
        }
        // Choix aléatoire du coup.
        Random random = new Random();
        int indice = random.nextInt(coupPossible.toArray().length);
        return new CoupNim(coupPossible.get(indice));
    }

    /**
     * Stratégie gagnante est utilisée quand le jeu n'a pas de limite de bâtonnets à retirer en une seule fois.
     * @param plateau Le plateau du jeu.
     * @return Un coup à jouer.
     */
    private CoupNim demanderCoupGagnant(PlateauNim plateau) {
        int[] lesTas = plateau.getPlateau();
        int resultatXor = lesTas[0];
        for (int i = 1; i < lesTas.length; i++) { // Calcule du XOR du plateau pour savoir dans quelle situation l'on est.
            resultatXor = resultatXor ^ lesTas[i];
        }
        if (resultatXor == 0) { // Situation non gagnante.
            for (int i = 0; i < lesTas.length; i++) {
                if (lesTas[i] != 0) {
                    return new CoupNim (i+1, 1);
                }
            }
        }
        else { // Situation gagnante.
            for (int i = 0; i < lesTas.length; i++) {
                if ((lesTas[i] ^ resultatXor) < lesTas[i]) {
                    return new CoupNim (i+1, (lesTas[i] - (lesTas[i] ^ resultatXor)));
                }
            }
        }
        return null; // Jamais atteint.
    }
}
