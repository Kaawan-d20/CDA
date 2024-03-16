package main.puissance4;

import controleur.ControleurPuissance4;
import vue.Ihm;

public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurPuissance4 controleurPuissance4=new ControleurPuissance4(ihm);
        controleurPuissance4.jouer();
    }
}
