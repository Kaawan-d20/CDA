package main;

import controleur.Controleur;
import controleur.ControleurJeuNim;
import controleur.ControleurP4;
import vue.Ihm;


public class main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        Controleur controleur;
        if (ihm.demanderJeu()){
            controleur = new ControleurJeuNim(ihm);
        } else {
            controleur = new ControleurP4(ihm);
        }
        controleur.jouer();
    }
}
