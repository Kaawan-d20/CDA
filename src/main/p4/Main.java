package main.p4;

import controleur.ControleurP4;
import vue.Ihm;

/**
 * La classe Main.
 */
public class Main {
    /**
     * La méthode main.
     * @param args Paramètres args
     */
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurP4 controleurP4=new ControleurP4(ihm);
        controleurP4.jouer();
    }
}
