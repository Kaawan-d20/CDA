package test.controleur;

import controleur.ControleurP4;
import org.junit.Test;
import vue.Ihm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Classe contenant les tests de src/controleur/ControleurP4.java
 */
public class TestControleurP4 {
    /**
     * Scénario de test du jeu de puissance 4
     * <ul>
     *     <li>Test le fonctionnement normal des noms</li>
     *     <li>Test les coups de puissance 4
     *         <ul>
     *             <li>Le cas où l'utilisateur entre un lettre</li>
     *             <li>Le cas où l'utilisateur entre une colonne inexistante (inférieur)</li>
     *             <li>Le cas où l'utilisateur entre une colonne inexistante (supérieur)</li>
     *         </ul>
     *     </li>
     *     <li>Déroule la partie</li>
     *     <li>Test pour rejouer
     *         <ul>
     *             <li>Le cas où l'utilisateur entre un lettre autre que "y" ou "n"</li>
     *             <li>Le fonctionnement normal</li>
     *         </ul>
     *     </li>
     *     <li>Déroule une deuxième partie</li>
     *     <li>Répond "n" à rejouer</li>
     * </ul>
     */
    @Test
    public void testJeuNim(){
        //Création de l'émulateur utilisateur
        String input = "Dany\nNathan\n"; //Test pour le nom du joueur
        input += "a\n0\n8\n"; //Test pour les coups
        input += "1\n2\n1\n2\n1\n2\n1\n";  //Pour dérouler la partie
        input += "a\ny\n";  //Test pour rejouer
        input += "1\n2\n1\n2\n1\n2\n1\n";  //Pour dérouler la partie
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
        System.setOut(printStream); //redéfinition de out

        //Initialisation comme dans le main
        Ihm ihm = new Ihm();
        ControleurP4 controleurP4=new ControleurP4(ihm);
        //Lancement du programme
        controleurP4.jouer();
        //Test des out du programme pour checker le bon fonctionnement

        String scannerPart = "\r\n";
        String reponseAttendu = "";
        String plateauInit = "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n";

        reponseAttendu += "Quel est le nom du joueur n°1 ?" + scannerPart; //Demande de nombre de tas (réponse : "Dany")
        reponseAttendu += "Quel est le nom du joueur n°2 ?" + scannerPart; //Demande de nombre de tas (réponse : "Nathan")

        reponseAttendu += plateauInit + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Dany ?" + scannerPart; //Demande du coup (réponse : "a")
        reponseAttendu +="\n\n⚠ Veuillez entrer un entier compris entre 1 et 7"+scannerPart;
        reponseAttendu += plateauInit + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Dany ?" + scannerPart; //Demande du coup (réponse : "0")
        reponseAttendu +="\n\n⚠ Veuillez entrer un entier compris entre 1 et 7"+scannerPart;
        reponseAttendu += plateauInit + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Dany ?" + scannerPart; //Demande du coup (réponse : "8")
        reponseAttendu +="\n\n⚠ Veuillez entrer un entier compris entre 1 et 7"+scannerPart;

        reponseAttendu += getPartie();

        reponseAttendu += "Fin de la partie, Dany a gagné !\nnombre de Victoires : 1/1." + scannerPart;//Affichage fin de partie
        reponseAttendu += "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" + scannerPart; //Affichage du plateau

        reponseAttendu += "Voulez-vous rejouer une partie ? (y/n)" + scannerPart; //Demande de jouer encore (réponse : "a")
        reponseAttendu +="\n\n⚠ Vous avez répondu avec autre chose que `y` ou `n`."+scannerPart;
        reponseAttendu += "Voulez-vous rejouer une partie ? (y/n)" + scannerPart; //Demande de jouer encore (réponse : "y")

        reponseAttendu += getPartie();

        reponseAttendu += "Fin de la partie, Dany a gagné !\nnombre de Victoires : 2/2." + scannerPart;//Affichage fin de partie
        reponseAttendu += "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" + scannerPart; //Affichage du plateau

        reponseAttendu += "Voulez-vous rejouer une partie ? (y/n)" + scannerPart; //Demande de jouer encore (réponse : "n")
        reponseAttendu += "Fin de la partie, Dany a gagné !\nnombre de Victoires : 2/2." + scannerPart;//Affichage fin de partie

        //Test entre ce qui est attendu et ce qui est affiché
        assertEquals(reponseAttendu,out.toString());

        //Rétablissement des valeurs par défault
        System.setIn(originalIn);
        System.setOut(originalOut);

    }

    /**
     * Permet de ne pas surcharger TestJeuNim
     *
     * @return l'affichage normal d'une partie
     */
    private String getPartie(){
        String scannerPart = "\r\n";
        String reponseAttendu = "";
        reponseAttendu += "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Dany ?" + scannerPart; //Demande du coup (réponse : "1")
        reponseAttendu += "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Nathan ?" + scannerPart; //Demande du coup (réponse : "2")
        reponseAttendu += "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Dany ?" + scannerPart; //Demande du coup (réponse : "1")
        reponseAttendu += "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Nathan ?" + scannerPart; //Demande du coup (réponse : "2")
        reponseAttendu += "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Dany ?" + scannerPart; //Demande du coup (réponse : "1")
        reponseAttendu += "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Nathan ?" + scannerPart; //Demande du coup (réponse : "2")
        reponseAttendu += "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n" + scannerPart; //Affichage du plateau
        reponseAttendu += "Dans quelle colonne voulez-vous jouer Dany ?" + scannerPart; //Demande du coup (réponse : "1")
        return reponseAttendu;
    }
}
