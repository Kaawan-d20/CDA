package main;

import controleur.Controleur;
import controleur.ControleurNim;
import controleur.ControleurP4;
import vue.Ihm;

/**
 * La classe Main pour le choix du jeu.
 */
public class Main {
    /**
     * La méthode Main pour le choix du jeu.
     * @param args Paramètres args.
     */
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        Controleur controleur;
        if (ihm.demanderJeu()){
            controleur = new ControleurNim(ihm);
        } else {
            controleur = new ControleurP4(ihm);
        }
        controleur.jouer();
    }
}
