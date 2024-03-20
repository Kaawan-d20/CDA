package test.controleur;

import controleur.ControleurJeuNim;
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
     * Scénario de test du jeu de nim
     * <ul>
     *     <li>Test nombre de tas
     *         <ul>
     *             <li>Le cas où l'entrée n'est pas un nombre</li>
     *             <li>Le cas où le nombre est inférieur à 1</li>
     *             <li>Le fonctionnement normal</li>
     *         </ul>
     *     </li>
     *     <li>Test le fonctionnement normal des noms</li>
     *     <li>Test les coups de Nim
     *         <ul>
     *             <li>Le cas où l'utilisateur entre un lettre</li>
     *             <li>Le cas où l'utilisateur entre un espace suivi d'une réponse correct</li>
     *             <li>Le cas où l'utilisateur une réponse correct, mais avec 2 espace entre</li>
     *             <li>Le cas où l'utilisateur une réponse au format correct, mais avec des lettres</li>
     *             <li>Le cas où l'utilisateur demande un tas inexistant</li>
     *             <li>Le cas où l'utilisateur veux enlever trop de bâtonnet</li>
     *             <li>Le fonctionnement normal</li>
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
        String input = "a\n-1\n3\n"; //Test pour le nombre de tas
        input += "Dany\nNathan\n"; //Test pour le nom du joueur
        input += "a\n 11 12\n12  13\n1a 2b\n12 13\n1 3\n1 1\n"; //Test pour les coups
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
        System.setOut(printStream); //redéfinition de out

        //Initialisation comme dans le main
        Ihm ihm = new Ihm();
        ControleurJeuNim controleurJeuNim = new ControleurJeuNim(ihm);
        //Lancement du programme
        controleurJeuNim.jouer();
        //Test des out du programme pour checker le bon fonctionnement

        String scannerPart = "\r\n";
        String reponseAttendu = "Avec combien de tas voulez-vous jouer ?\nEntrez un entier >= 1 : " + scannerPart; //Demande de nombre de tas (réponse : "a")
        reponseAttendu +="\n\n⚠ Format de réponse invalide."+scannerPart;
        reponseAttendu += "Avec combien de tas voulez-vous jouer ?\nEntrez un entier >= 1 : " + scannerPart; //Demande de nombre de tas (réponse : "-1")
        reponseAttendu +="\n\n⚠ Le nombre de tas ne peut pas être inférieur à 1"+scannerPart;
        reponseAttendu += "Avec combien de tas voulez-vous jouer ?\nEntrez un entier >= 1 : " + scannerPart; //Demande de nombre de tas (réponse : "3")

        reponseAttendu += "Quel est le nom du joueur n°1 ?" + scannerPart; //Demande de nombre de tas (réponse : "Dany")
        reponseAttendu += "Quel est le nom du joueur n°2 ?" + scannerPart; //Demande de nombre de tas (réponse : "Nathan")

        reponseAttendu += "  |  \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "a")
        reponseAttendu +="\n\n⚠ Format de réponse invalide."+scannerPart;
        reponseAttendu += "  |  \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : " 11 12")
        reponseAttendu +="\n\n⚠ Format de réponse invalide."+scannerPart;
        reponseAttendu += "  |  \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "12  13")
        reponseAttendu +="\n\n⚠ Format de réponse invalide."+scannerPart;
        reponseAttendu += "  |  \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "1a 2b")
        reponseAttendu +="\n\n⚠ Format de réponse invalide."+scannerPart;
        reponseAttendu += "  |  \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "12 13")
        reponseAttendu +="\n\n⚠ Vous avez sélectionné un tas inconnu."+scannerPart;
        reponseAttendu += "  |  \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "1 3")
        reponseAttendu +="\n\n⚠ Vous avez sélectionné un nombre de bâtonnet(s) invalide."+scannerPart;

        reponseAttendu += "  |  \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "1 1")
        reponseAttendu += "    \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Nathan ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "2 3")
        reponseAttendu += "    \n    \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "3 5")

        reponseAttendu += "Fin de la partie, Dany a gagné !\nnombre de Victoires : 1/1." + scannerPart;//Affichage fin de partie

        reponseAttendu += "Voulez-vous rejouer une partie ? (y/n)" + scannerPart; //Demande de jouer encore (réponse : "a")
        reponseAttendu +="\n\n⚠ Vous avez répondu avec autre chose que `y` ou `n`."+scannerPart;
        reponseAttendu += "Voulez-vous rejouer une partie ? (y/n)" + scannerPart; //Demande de jouer encore (réponse : "y")

        reponseAttendu += "  |  \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "1 1")
        reponseAttendu += "    \n ||| \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Nathan ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "2 3")
        reponseAttendu += "    \n    \n|||||\n" + scannerPart; //Affichage des tas
        reponseAttendu += "Quel coup voulez-vous jouer Dany ?\nVeuillez jouer un coup au format\n\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer" + scannerPart; //Demande du coup (réponse : "3 5")

        reponseAttendu += "Fin de la partie, Dany a gagné !\nnombre de Victoires : 2/2." + scannerPart;//Affichage fin de partie

        reponseAttendu += "Voulez-vous rejouer une partie ? (y/n)" + scannerPart; //Demande de jouer encore (réponse : "n")
        reponseAttendu += "Fin de la partie, Dany a gagné !\nnombre de Victoires : 2/2." + scannerPart;//Affichage fin de partie

        //Test entre ce qui est attendu et ce qui est affiché
        assertEquals(reponseAttendu,out.toString());

        //Rétablissement des valeurs par défault
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

}
