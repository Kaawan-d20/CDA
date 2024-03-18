package test.vue;

import modele.Joueur;
import org.junit.Test;
import vue.Ihm;
import exception.NombreTasInvalides;

import java.io.*;

import static org.junit.Assert.*;

public class TestIhm {
    @Test
    public void testDemanderNbTas() {
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setIn(in);
        System.setOut(printStream);
        String reponseAttendu = "Avec combien de tas voulez-vous jouer ?\nEntrez un entier >= 1 : \n";

        Ihm ihm = new Ihm();
        try {
            int nbTas = ihm.demanderNbTas();
            assertEquals(3, nbTas);
//            assertEquals(true, reponseAttendu.equals(out.toString()));
        } catch (NombreTasInvalides e) {
            fail("Exception NombreTasInvalides levée");
        }
    }
}