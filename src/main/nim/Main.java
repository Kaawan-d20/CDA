package main.nim;

import controleur.ControleurJeuNim;
import vue.Ihm;

/**
 * La classe Main.
 */
public class Main {
    /**
     * La méthode main.
     * @param args Paramètres args
     */
    public static void main (String[] args) {
        Ihm ihm = new Ihm();
        ControleurJeuNim controleurJeuNim = new ControleurJeuNim(ihm);
        controleurJeuNim.jouer();
    }
}
