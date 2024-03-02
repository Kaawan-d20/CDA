package main;

import controleur.ControleurJeuNim;
import vue.Ihm;

public class Main {
    public static void main (String[] args) {
        Ihm ihm = new Ihm();
        ControleurJeuNim controleurJeuNim = new ControleurJeuNim(ihm);
        controleurJeuNim.jouer();
    }
}
