package vue;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import modele.Nim.Plateau;
import org.junit.Test;
import exception.NombreTasInvalides;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Classe contenant les tests de src/vue/Ihm.java
 * @see Ihm
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
        InputStream in = new ByteArrayInputStream(input.getBytes()); //crée un array contenant le binaire de l'input
        System.setIn(in); // redéfinition de in
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        System.setOut(printStream); //redéfinition de out

        //Test de la String et du fonctionnement normal
        String reponseAttendu = "Avec combien de tas voulez-vous jouer ?\nEntrez un entier >= 1 : \r\n";
        int nbTas=0;
        Ihm ihm = new Ihm();
        try {
            nbTas = ihm.demanderNbTas();
        } catch (NombreTasInvalides e) {
            fail("Exception NombreTasInvalides levée dans demanderTas()");
        }
        
        assertEquals(3, nbTas);
        assertEquals(reponseAttendu,out.toString());

        //Test le cas où le format attendu n'est pas le bon
        try {
            nbTas = ihm.demanderNbTas();
            fail("Exception NombreTasInvalides levée devrait être levée mais ne l'ai pas");
        } catch (NombreTasInvalides ignored) {}

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
     * @see Ihm#demanderNomJoueur(int)
     */
    @Test
    public void testDemanderNomJoueur() {
        String input = "Dany\n\ntest";
        //Stockage des valeurs initial
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        //Redéfinition de l'entrée dans un stream afin de pouvoir émuler l'utilisation du scanner
        InputStream in = new ByteArrayInputStream(input.getBytes()); //crée un array contenant le binaire de l'input
        System.setIn(in); // redéfinition de in
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        System.setOut(printStream); //redéfinition de out

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

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le fonctionnement normal</li>
     *     <li>Le cas où l'utilisateur entre une lettre</li>
     *     <li>Le cas où l'utilisateur entre un espace suivi d'une réponse correct</li>
     *     <li>Le cas où l'utilisateur une réponse correct, mais avec 2 espace entre</li>
     *     <li>Le cas où l'utilisateur une réponse au format correct, mais avec des lettres</li>
     * </ul>
     * @see Ihm#demanderCoupNim(String)
     */
    @Test
    public void testDemanderCoupNim() {
        String input = "1 2\na\n 11 12\n12  13\n1a 2b\n";
        //Stockage des valeurs initial
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        //Redéfinition de l'entrée dans un stream afin de pouvoir émuler l'utilisation du scanner
        InputStream in = new ByteArrayInputStream(input.getBytes()); //crée un array contenant le binaire de l'input
        System.setIn(in); // redéfinition de in
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        System.setOut(printStream); //redéfinition de out

        String reponseAttendu = "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer\r\n";
        Ihm ihm = new Ihm();
        int[] coupNim = new int[2];
        try{
            coupNim = ihm.demanderCoupNim("Dany");
        } catch (FormatReponseInvalide e) {
            fail("Erreur demanderCoupNim(String)");
        }

        //Test le fonctionnement normal
        assertArrayEquals(new int[]{1,2}, coupNim);
        assertEquals(reponseAttendu,out.toString());

        //Test le cas où l'utilisateur entre une lettre
        try{
            coupNim = ihm.demanderCoupNim("Dany");
            fail("Erreur demanderCoupNim(String)");
        } catch (FormatReponseInvalide ignored) {};

        //Test le cas où l'utilisateur entre un espace suivi d'une réponse correct
        try{
            coupNim = ihm.demanderCoupNim("Dany");
            fail("Erreur demanderCoupNim(String)");
        } catch (FormatReponseInvalide ignored) {};

        //Test le cas où l'utilisateur une réponse correct, mais avec 2 espace entre
        try{
            coupNim = ihm.demanderCoupNim("Dany");
            fail("Erreur demanderCoupNim(String)");
        } catch (FormatReponseInvalide ignored) {};

        //Test le cas où l'utilisateur une réponse au format correct, mais avec des lettres
        try{
            coupNim = ihm.demanderCoupNim("Dany");
            fail("Erreur demanderCoupNim(String)");
        } catch (FormatReponseInvalide ignored) {};

        //Rétablissement des valeurs par défault
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le fonctionnement normal true</li>
     *     <li>Le fonctionnement normal false</li>
     *     <li>Le cas où l'utilisateur entre autre chose que "y" ou "n"</li>
     *
     * </ul>
     * @see Ihm#demanderJouerEncore()
     */
    @Test
    public void testDemanderJouerEncore() {
        String input = "y\nn\na\n";
        //Stockage des valeurs initial
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        //Redéfinition de l'entrée dans un stream afin de pouvoir émuler l'utilisation du scanner
        InputStream in = new ByteArrayInputStream(input.getBytes()); //crée un array contenant le binaire de l'input
        System.setIn(in); // redéfinition de in
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        System.setOut(printStream); //redéfinition de out

        String reponseAttendu = "Voulez-vous rejouer une partie ? (y/n)\r\n";
        Ihm ihm = new Ihm();
        boolean jouerEncore = false;
        //Test le fonctionnement normal true

        try{
            jouerEncore = ihm.demanderJouerEncore();
        } catch (FormatReponseInvalide e) {
            fail("Erreur demanderCoupNim(String)");
        }

        assertTrue(jouerEncore);
        assertEquals(reponseAttendu,out.toString());

        //Test le fonctionnement normal false
        try{
            jouerEncore = ihm.demanderJouerEncore();
        } catch (FormatReponseInvalide e) {
            fail("Erreur demanderCoupNim(String)");
        }

        assertFalse(jouerEncore);

        //Test le cas où l'utilisateur entre autre chose que "y" ou "n"
        try{
            jouerEncore = ihm.demanderJouerEncore();
            fail("Erreur demanderJouerEncore()");
        } catch (FormatReponseInvalide ignored) {};

        //Rétablissement des valeurs par défault
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    /**
     * Test le fonctionnement normal d'afficherPlateau avec un plateau du jeu de Nim
     * @see Ihm#afficherPlateau(String)
     */
    @Test
    public void testAfficherPlateauNim() {
        //Stockage de la valeur initial de System.out
        PrintStream originalOut = System.out;
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        System.setOut(printStream); //redéfinition de out

        String reponseAttendu = "  |  \n ||| \n|||||\n\r\n";
        Plateau plateau = new Plateau(3);
        plateau.reset();
        Ihm ihm = new Ihm();
        //Test le fonctionnement normal
        ihm.afficherPlateau(plateau.toString());
        assertEquals(reponseAttendu,out.toString());

        //Rétablissement des valeurs par défault
        System.setOut(originalOut);
    }

    /**
     * Test le fonctionnement normal d'afficherPlateau de puissance 4
     * @see Ihm#afficherPlateau(String)
     */
    @Test
    public void testAfficherPlateauP4() {
        //Stockage de la valeur initial de System.out
        PrintStream originalOut = System.out;
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        System.setOut(printStream); //redéfinition de out

        String reponseAttendu = "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n\r\n";
        modele.p4.Plateau plateau = new modele.p4.Plateau();
        plateau.reset();
        try{
            plateau.placerJeton((byte) 0, (byte) 1);
            plateau.placerJeton((byte) 1, (byte) 2);
        } catch (ColonnePleine e) {
            fail("Erreur placerJeton");
        }

        Ihm ihm = new Ihm();
        //Test le fonctionnement normal
        ihm.afficherPlateau(plateau.toString());
        assertEquals(reponseAttendu,out.toString());

        //Rétablissement des valeurs par défault
        System.setOut(originalOut);
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le cas où un joueur a gagné</li>
     *     <li>Le cas où c'est un Ex-Aequo</li>
     * </ul>
     * @see Ihm#afficherVictoire(String, int, int, boolean)
     */
    @Test
    public void testAfficherVictoire() {
        //Stockage de la valeur initial de System.out
        PrintStream originalOut = System.out;
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        System.setOut(printStream); //redéfinition de out

        String reponseAttendu = "Fin de la partie, Dany a gagné !\nnombre de Victoires : 2/3.\r\n";


        Ihm ihm = new Ihm();
        //Test le cas où un joueur a gagné
        ihm.afficherVictoire("Dany",2,3,false);
        assertEquals(reponseAttendu,out.toString());
        out.reset();

        //Test le cas où c'est un Ex-Aequo
        reponseAttendu = "Fin de la partie : Ex-Aequo!\nChaque Joueur à gagné 2/4.\r\n";
        ihm.afficherVictoire("Dany",2,4,true);
        assertEquals(reponseAttendu,out.toString());

        //Rétablissement des valeurs par défault
        System.setOut(originalOut);
    }

    /**
     * Test le fonctionnement d'afficherErreur()
     * @see Ihm#afficherErreur(String)
     */
    @Test
    public void testAfficherErreur() {
        //Stockage de la valeur initial de System.out
        PrintStream originalOut = System.out;
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        System.setOut(printStream); //redéfinition de out

        String reponseAttendu = "\n\n⚠ test\r\n";

        Ihm ihm = new Ihm();
        //Test le cas où un joueur a gagné
        ihm.afficherErreur("test");
        assertEquals(reponseAttendu,out.toString());
        out.reset();

        //Rétablissement des valeurs par défault
        System.setOut(originalOut);
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le fonctionnement normal</li>
     *     <li>Le cas où le format attendu n'est pas le bon</li>
     * </ul>
     * @see Ihm#demanderCoupP4(String)
     */
    @Test
    public void testDemanderCoupP4() {
        String input = "3\na";
        //Stockage des valeurs initial
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        //Redéfinition de l'entrée dans un stream afin de pouvoir émuler l'utilisation du scanner
        InputStream in = new ByteArrayInputStream(input.getBytes()); //crée un array contenant le binaire de l'input
        System.setIn(in); // redéfinition de in
        //Redéfinition de la sortie dans un stream afin de pouvoir vérifier l'affichage
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //un array qui va stocker du binaire
        PrintStream printStream = new PrintStream(out); //création d'un stream qui peut recevoir le result d'un print
        System.setOut(printStream); //redéfinition de out

        //Test de la String et du fonctionnement normal
        String reponseAttendu = "Dans quelle colonne voulez-vous jouer Dany ?\r\n";
        int nbTas=0;
        Ihm ihm = new Ihm();
        try {
            nbTas = ihm.demanderCoupP4("Dany");
        } catch (FormatReponseInvalide e) {
            fail("Exception FormatReponseInvalide levée dans demanderCoupP4()");
        }

        assertEquals(3, nbTas);
        assertEquals(reponseAttendu,out.toString());

        //Test le cas où le format attendu n'est pas le bon
        try {
            ihm.demanderCoupP4("Dany");
            fail("Exception FormatReponseInvalide levée devrait être levée mais ne l'ai pas");
        } catch (FormatReponseInvalide ignored) {}

        //Rétablissement des valeurs par défault
        System.setIn(originalIn);
        System.setOut(originalOut);
    }


}