package test.vue;

import org.junit.Test;
import vue.Ihm;
import exception.NombreTasInvalides;

import java.io.*;

import static org.junit.Assert.*;

public class TestIhm {
    @Test
    public void testDemanderNbTas() {
        String input = "3\na";
        //Stockage des valeurs initial
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        //Redéfinition des Stream d'entrée et de sortie
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setIn(in);
        System.setOut(printStream);

        String reponseAttendu = "Avec combien de tas voulez-vous jouer ?\nEntrez un entier >= 1 : \r\n";
        int nbTas=0;
        Ihm ihm = new Ihm();
        try {
            nbTas = ihm.demanderNbTas();
        } catch (NombreTasInvalides e) {
            fail("Exception NombreTasInvalides levée");
        }

        String prompt = out.toString();
        assertEquals(3, nbTas);
        assertEquals(reponseAttendu,prompt);

        try {
            nbTas = ihm.demanderNbTas();
            fail("Exception NombreTasInvalides levée");
        } catch (NombreTasInvalides | NullPointerException ignored) {}

        //Rétablissement des valeurs par défault
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testDemanderNomJoueur() {
        String input = "Dany\n\ntest";
        //Stockage des valeurs initial
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        //Redéfinition des Stream d'entrée et de sortie
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setIn(in);
        System.setOut(printStream);

        String reponseAttendu = "Quel est le nom du joueur n°1 ?\r\n";
        Ihm ihm = new Ihm();
        String nomJoueur = ihm.demanderNomJoueur(1);

        String prompt = out.toString();
        assertEquals("Dany", nomJoueur);
        assertEquals(reponseAttendu,prompt);


        nomJoueur = ihm.demanderNomJoueur(1);
        assertEquals("test", nomJoueur);

        //Rétablissement des valeurs par défault
        System.setIn(originalIn);
        System.setOut(originalOut);

    }
}