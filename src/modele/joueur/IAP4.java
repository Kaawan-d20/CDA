package modele.joueur;

import modele.abstrait.CoupP4;
import modele.p4.CoupP4Coup;
import modele.p4.PlateauP4;

public class IAP4 extends IA{
    /**
     * Permet de créer un nouvel objet IA.
     */
    public IAP4() {
        super("IA");
    }

    public CoupP4 demanderCoup(PlateauP4 plateau){
        return new CoupP4Coup((byte) 1); // TODO : A remplacer par le bon code
    }
}
