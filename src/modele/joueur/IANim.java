package modele.joueur;

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


    public CoupNim demanderCoup(PlateauNim plateau){
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

    private CoupNim demanderCoupGagnant(PlateauNim plateau){
        return demanderCoupAleatoire(plateau); // TODO : A remplacer par le bon code
    }
}
