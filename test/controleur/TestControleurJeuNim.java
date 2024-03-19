package controleur;

import org.junit.Test;
import vue.Ihm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Classe contenant les tests de src/controleur/ControleurJeuNim.java
 */
public class TestControleurJeuNim {
    /**
     * Scénario de Test
     *
     */
    @Test
    public void TestJeuNim(){
        //Création de l'émulateur utilisateur
        String input = "a\n-1\n3\n"; //Test pour le nombre de tas
        input += "Dany\nNathan\n"; //Test pour le nom du joueur
        input += "a\n 11 12\n12 13\n12  13\n1a 2b\n1 1\n"; //Test pour les coups
        input += "2 3\n3 5\n";  //Pour dérouler la partie
        input += "a\ny\n";  //Test pour rejouer
        input += "1 1\n2 3\n3 5\n";  //Pour dérouler la partie
        input += "n\n";  //Pour finir le programme

        //Stockage des valeurs initial
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        //Redéfinition de l'entrée dans un stream afin de pouvoir émuler l'utilisation du scanner
        InputStream in = new ByteArrayInputStream(input.getBytes()); //crée un array contenant le binaire de l'input
        System.setIn(in); // redéfinition de in
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        //System.setOut(printStream); //redéfinition de out

        //Initialisation comme dans le main
        Ihm ihm = new Ihm();
        ControleurJeuNim controleurJeuNim = new ControleurJeuNim(ihm);
        controleurJeuNim.jouer();

        //Test pour le nombre de tas


    }
}
