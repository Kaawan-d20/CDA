package main.p4;

import controleur.ControleurP4;
import vue.Ihm;

/**
 * La classe Main pour le jeu de Puissance 4.
 */
public class Main {
    /**
     * La méthode Main pour le jeu de Puissance 4.
     * @param args Paramètres args.
     */
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurP4 controleurP4 = new ControleurP4(ihm);
        controleurP4.jouer();
    }
}
