package vue;

import exception.NombreTasInvalides;
import exception.FormatReponseInvalide;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe Ihm (Interface Humain Machine) représente l'interface utilisateur du jeu.
 */
public class Ihm {
    /** Le scanner qui permet de récupérer les inputs de l'utilisateur */
    private Scanner scanner;

    /**
     * Constructeur de la classe Ihm.
     * Initialise le scanner pour la lecture des entrées utilisateurs.
     */
    public Ihm() {
        scanner = new Scanner(System.in);
    }

    /**
     * Demande à l'utilisateur le nombre de tas pour jouer.
     *
     * @return Le nombre de tas saisi par l'utilisateur.
     * @throws NombreTasInvalides Si l'utilisateur entre autre chose qu'un int
     */
    public int demanderNbTas() throws NombreTasInvalides {
        System.out.println("Avec combien de tas voulez-vous jouer ?\nEntrez un entier >= 1 : ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        scanner.next();
        throw new NombreTasInvalides("Format de réponse invalide.");
    }

    /**
     * Demande à l'utilisateur le nom du joueur.
     *
     * @param numJoueur Le numéro du joueur.
     * @return Le nom saisi par l'utilisateur.
     */
    public String demanderNomJoueur(int numJoueur) {
        String candidate = "";
        System.out.println("Quel est le nom du joueur n°" + numJoueur + " ?");
        while (candidate.isEmpty()) {
            if (scanner.hasNextLine()) {
                candidate = scanner.nextLine();
            }
        }
        return candidate;
    }

    /**
     * Demande à un joueur de saisir un coup pour le jeu Nim.
     * Affiche un message d'invitation et attend une réponse de l'utilisateur.
     * La réponse doit être au format `m n`, où `m` est le numéro du tas et `n` est le nombre de bâtonnets à retirer.
     *
     * @param nomJoueur Le nom du joueur pour lequel la demande est effectuée.
     * @return Un tableau d'entiers de taille 2, contenant le numéro du tas et le nombre de bâtonnets à retirer [m, n].
     * @throws FormatReponseInvalide Si la réponse de l'utilisateur n'est pas au format attendu.
     */
    public int[] demanderCoupNim(String nomJoueur) throws FormatReponseInvalide {
        int[] candidate = new int[2];
        System.out.println("Quel coup voulez-vous jouer " + nomJoueur + " ?\nVeuillez jouer un coup au format\n" +
                "\t`m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer");
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Le pattern s'assure que la ligne est composée de deux nombres séparés par un espace.
            Pattern pattern = Pattern.compile("^[0-9]+ [0-9]+$"); //Permet de créer le regex.
            Matcher matcher = pattern.matcher(line); // Permet de créer un objet auquel on demande si la String correspond au regex.
            // On utilise un autre scanner pour extraire les deux nombres de la ligne.
            if (matcher.find()) {
                Scanner sc2 = new Scanner(line);
                // On parcourt le tableau candidate pour stocker les deux nombres.
                for (int i = 0; i < 2; i++) {
                    candidate[i] = sc2.nextInt();
                }
                sc2.close();
                return candidate;
            }
        }
        // Si la réponse de l'utilisateur ne correspond pas au format attendu, on lance une exception.
        throw new FormatReponseInvalide("Format de réponse invalide.");
    }

    /**
     * Demande à l'utilisateur s'il souhaite rejouer une partie.
     * Affiche un message et attend une réponse de l'utilisateur.
     * La réponse doit être une chaîne contenant uniquement "y" (pour oui) ou "n" (pour non).
     *
     * @return true si l'utilisateur souhaite rejouer, false sinon.
     * @throws FormatReponseInvalide  Si la réponse de l'utilisateur n'est pas valide.
     */
    public boolean demanderJouerEncore() throws FormatReponseInvalide {
        System.out.println("Voulez-vous rejouer une partie ? (y/n)");
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Le pattern s'assure que la ligne contient uniquement "y" ou "n".
            Pattern pattern = Pattern.compile("^[y|n]$"); //Permet de créer le regex.
            Matcher matcher = pattern.matcher(line); // Permet de créer un objet auquel on demande si la String correspond au regex.
            // On vérifie la réponse et retourne true si c'est "y" et false si c'est "n".
            if (matcher.find()) {
                return line.equals("y");
            }
        }
        // Si la réponse de l'utilisateur n'est pas valide, on lance une exception.
        throw new FormatReponseInvalide("Vous avez répondu avec autre chose que `y` ou `n`.");
    }

    /**
     * Affiche le plateau passé sous la forme d'une String
     *
     * @param plateau le plateau sous forme de string
     */
    public void afficherPlateau(String plateau) {
        System.out.println(plateau);
    }

    /**
     * Affiche un message de fin de partie indiquant le vainqueur et le nombre de victoires.
     *
     * @param nomJoueur   Le nom du joueur gagnant.
     * @param nbVictoires Le nombre de victoires du joueur.
     * @param nbParties   Le nombre total de parties jouées.
     * @param isExAequo   Valeur booléenne représentant une éventuelle égalité
     */
    public void afficherVictoire(String nomJoueur, int nbVictoires, int nbParties, boolean isExAequo) {
        if (isExAequo) {
            System.out.println("Fin de la partie : Ex-Aequo!" +
                    "\nChaque Joueur à gagné " + nbVictoires + "/" + nbParties + ".");
        } else {
            System.out.println("Fin de la partie, " + nomJoueur + " a gagné !" +
                    "\nnombre de Victoires : " + nbVictoires + "/" + nbParties + ".");
        }
    }

    /**
     * Affiche un message d'erreur préfixé d'une icône d'avertissement.
     *
     * @param message Le message d'erreur à afficher.
     */
    public void afficherErreur(String message) {
        System.out.println("\n\n⚠ " + message);
    }


    /**
     * Demande à un joueur de saisir un coup pour le jeu de puissance 4.
     * Affiche un message d'invitation et attend une réponse de l'utilisateur.
     * La réponse doit être un entier.
     *
     * @param nomJoueur Le nom du joueur pour lequel la demande est effectuée.
     * @return Un byte contenant le numéro de colonne.
     * @throws FormatReponseInvalide Si la réponse de l'utilisateur n'est pas au format attendu.
     */
    public byte demanderCoupP4(String nomJoueur) throws FormatReponseInvalide {
        if (scanner.hasNextByte()){
            return scanner.nextByte();
        }
        throw new FormatReponseInvalide("Veuillez entrer un entier compris entre 1 et 7");
    }

}
