package modele.joueur;

import exception.JeuInvalideException;
import modele.nim.CoupNim;
import modele.nim.PlateauNim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IANim extends IA{
    /**
     * Permet de créer un nouvel objet IA.
     */
    public IANim() {
        super("IA");
    }


    public CoupNim demanderCoup(PlateauNim plateau) throws JeuInvalideException {
        if (plateau.isLimite()){
            return demanderCoupAleatoire(plateau);
        } else {
            return demanderCoupGagnant(plateau);
        }
    }

    private CoupNim demanderCoupAleatoire(PlateauNim plateau){
        List<int[]> coupPossible = new ArrayList<>();
        for (int i = 0; i < plateau.getPlateau().length; i++) {
            for (int j = 0; j < plateau.getPlateau()[i]; j++) {
                coupPossible.add(new int[]{i+1,j+1});
            }
        }
        Random random = new Random();
        int indice = random.nextInt(coupPossible.toArray().length);
        return new CoupNim(coupPossible.get(indice));
    }

    private CoupNim demanderCoupGagnant(PlateauNim plateau) throws JeuInvalideException {
        int[] lesTas = plateau.getPlateau();
        int resultatXor = lesTas[0];
        for (int i = 1; i < lesTas.length; i++) {
            resultatXor = resultatXor ^ lesTas[i];
        }
        if (resultatXor == 0) {
            for (int i = 0; i < lesTas.length; i++) {
                if (lesTas[i] != 0) {
                    return new CoupNim (i+1, 1);
                }
            }
        } else {
            for (int i = 0; i < lesTas.length; i++) {
                if ((lesTas[i] ^ resultatXor) < lesTas[i]) {
                    return new CoupNim (i+1, (lesTas[i] - (lesTas[i] ^ resultatXor)));
                }
            }
        }
        throw new JeuInvalideException("Je ne vois vraiment pas comment vous êtes arrivé(e) là. Cette erreur n'existe que pour faire plaisir à IntelliJ et cet état est sensé être mathématiquement inateignable.");
    }
}
