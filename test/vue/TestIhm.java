package vue;

import modele.p4.Plateau;
import org.junit.Test;
import exception.NombreTasInvalides;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Classe contenant les tests de src/vue/Ihm.java
 * @see vue.Ihm
 */
public class TestIhm {
    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le fonctionnement normal</li>
     *     <li>Le cas où le format attendu n'est pas le bon</li>
     * </ul>
     * @see Ihm#demanderNbTas()
     */
    @Test
    public void testDemanderNbTas() {
        String input = "3\na";
        //Stockage des valeurs initial
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        //Redéfinition de l'entrée dans un stream afin de pouvoir émuler l'utilisation du scanner
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        //Test de la String et du fonctionnement normal
        String reponseAttendu = "Avec combien de tas voulez-vous jouer ?\nEntrez un entier >= 1 : \r\n";
        int nbTas=0;
        Ihm ihm = new Ihm();
        try {
            nbTas = ihm.demanderNbTas();
        } catch (NombreTasInvalides e) {
            fail("Exception NombreTasInvalides levée dans demanderTas()");
        }

        String prompt = out.toString();
        assertEquals(3, nbTas);
        assertEquals(reponseAttendu,prompt);

        //Test le cas où le format attendu n'est pas le bon
        try {
            nbTas = ihm.demanderNbTas();
            fail("Exception NombreTasInvalides levée devrait être levée mais ne l'ai pas");
        } catch (NombreTasInvalides | NullPointerException ignored) {}

        //Rétablissement des valeurs par défault
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le fonctionnement normal</li>
     *     <li>Le cas où l'utilisateur entre une chaine vide puis son nom</li>
     * </ul>
     * @see Ihm#demanderNomJoueur(int) ()
     */
    @Test
    public void testDemanderNomJoueur() {
        String input = "Dany\n\ntest";
        //Stockage des valeurs initial
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        //Redéfinition de l'entrée dans un stream afin de pouvoir émuler l'utilisation du scanner
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        String reponseAttendu = "Quel est le nom du joueur n°1 ?\r\n";
        Ihm ihm = new Ihm();
        String nomJoueur = ihm.demanderNomJoueur(1);

        //Test le fonctionnement normal
        assertEquals("Dany", nomJoueur);
        assertEquals(reponseAttendu,out.toString());

        //Test le cas où l'utilisateur entre une chaine vide puis son nom
        nomJoueur = ihm.demanderNomJoueur(1);
        assertEquals("test", nomJoueur);

        //Rétablissement des valeurs par défault
        System.setIn(originalIn);
        System.setOut(originalOut);

    }
}