package main.nim;

import controleur.ControleurNim;
import vue.Ihm;

/**
 * La classe Main pour le jeu de Nim.
 */
public class Main {
    /**
     * La méthode Main pour le jeu de Nim.
     * @param args Paramètres args.
     */
    public static void main (String[] args) {
        Ihm ihm = new Ihm();
        ControleurNim controleurNim = new ControleurNim(ihm);
        controleurNim.jouer();
    }
}
