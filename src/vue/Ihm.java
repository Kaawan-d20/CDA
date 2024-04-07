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
    /**
     * Le scanner qui permet de récupérer les inputs de l'utilisateur
     */
    private Scanner scanner;

    /**
     * Constructeur de la classe Ihm.
     * Initialise les scanners pour la lecture des entrées utilisateurs.
     */
    public Ihm() {
        scanner = new Scanner(System.in);
    }

    /**
     * Demande à l'utilisateur le nombre de tas pour jouer, boucle tant que l'utilisateur n'entre pas un entier.
     *
     * @return Le nombre de tas saisi par l'utilisateur.
     */
    public int demanderNbTas() {
        int candidate;
        while (true){
            System.out.println("Avec combien de tas voulez-vous jouer ?\nEntrez un entier >= 1 : ");
            if (scanner.hasNextInt()) {
                candidate = scanner.nextInt();
                scanner.nextLine();
                return candidate;
            }
            scanner.nextLine();

            afficherErreur("Format de réponse invalide.");
        }
    }

    /**
     * Demande à l'utilisateur le nom du joueur.
     *
     * @param numJoueur Le numéro du joueur.
     * @return Le nom saisi par l'utilisateur.
     */
    public String demanderNomJoueur(int numJoueur) {
        String candidate;
        while (true) {
            System.out.println("Quel est le nom du joueur n°" + numJoueur + " ?");
            if (scanner.hasNextLine()) {
                candidate = scanner.nextLine();
                if (!candidate.isBlank()){
                    return candidate;
                }
                afficherErreur("Le nom du joueur ne doit pas est uniquement composée de caractère invisible");
            }
        }
    }

    /**
     * <p>Demande à un joueur de saisir un coup pour le jeu Nim.</p>
     * <p>Affiche un message d'invitation et attend une réponse de l'utilisateur.</p>
     * <p>La réponse doit être au format `m n`, où `m` est le numéro du tas et `n` est le nombre de bâtonnets à retirer.</p>
     * <p>Boucle tant que la réponse n'est pas au format `m n`</p>
     *
     * @param nomJoueur Le nom du joueur pour lequel la demande est effectuée.
     * @return Un tableau d'entiers de taille 2, contenant le numéro du tas et le nombre de bâtonnets à retirer [m, n].
     */
    public int[] demanderCoupNim(String nomJoueur) {
        int[] candidate = new int[2];
        // Le pattern s'assure que la ligne est composée de deux nombres séparés par un espace.
        Pattern pattern = Pattern.compile("^[0-9]+ [0-9]+$"); //Permet de créer le regex.
        while (true){
            System.out.println("Quel coup voulez-vous jouer " + nomJoueur + " ?\nVeuillez jouer un coup au format" +
                    " `m n`\nOù `m` est le numéro du tas, et `n` le nombre de bâtonnet à retirer");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
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
            // Si la réponse de l'utilisateur ne correspond pas au format attendu, on affiche une erreur
            afficherErreur("Format de réponse invalide.");
        }
    }

    /**
     * <p>Demande au joueur le nombre maximum de bâtonnets à retirer en un seul coup</p>
     * @return un entier strictement positif
     */
    public int setOptionNim() {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        while (true) {
            System.out.println("Veuillez entrer le nombre maximum de bâtonnet retirable d'un seul coup (0 pour ne pas mettre de limite).");
            if (scanner.hasNextLine()) {
                String candidate = scanner.nextLine();
                Matcher matcher = pattern.matcher(candidate);
                if (matcher.find()) {
                    return Integer.parseInt(candidate);
                } else {
                    afficherErreur("Merci d'entrer un entier positif");
                }
            }
        }
    }

    /**
     * <p>Demande à l'utilisateur s'il souhaite rejouer une partie.</p>
     * <p>Affiche un message et attend une réponse de l'utilisateur.</p>
     * <p>La réponse doit être une chaîne contenant uniquement "y" (pour oui) ou "n" (pour non).</p>
     *
     * @return true si l'utilisateur souhaite rejouer, false sinon.
     */
    public boolean demanderJouerEncore() {
        // Le pattern s'assure que la ligne contient uniquement "y" ou "n".
        Pattern pattern = Pattern.compile("^[y|n]$"); //Permet de créer le regex.
        while (true) {
            System.out.println("Voulez-vous rejouer une partie ? (y/n)");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line); // Permet de créer un objet auquel on demande si la String correspond au regex.
                // On vérifie la réponse et retourne true si c'est "y" et false si c'est "n".
                if (matcher.find()) {
                    return line.equals("y");
                }
            }

            afficherErreur("Vous avez répondu avec autre chose que `y` ou `n`.");
        }
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
     * <p>Demande à un joueur de saisir le numéro d'une colonne pour le jeu de puissance 4.</p>
     * <p>Affiche un message d'invitation et attend une réponse de l'utilisateur.</p>
     * <p>La réponse doit être un entier sinon boucle</p>
     *
     * @param nomJoueur Le nom du joueur pour lequel la demande est effectuée.
     * @return Un byte contenant le numéro de colonne.
     */
    public byte demanderCoupP4(String nomJoueur) {
        while(true){
            System.out.println("Dans quelle colonne voulez-vous jouer " + nomJoueur + " ?");
            if (scanner.hasNextByte()) {
                byte candidate = scanner.nextByte();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                return candidate;
            }
            scanner.nextLine();
            afficherErreur("Veuillez entrer un entier compris entre 1 et 7");
        }
    }

    /**
     * Demande à l'utilisateur à quel jeu il veut jouer ("nim" ou "p4")
     * @return true pour Nim et false pour Puissance 4
     */
    public boolean demanderJeu() {
        // Le pattern s'assure que la ligne contient uniquement "y" ou "n".
        Pattern pattern = Pattern.compile("^(nim|p4)$"); //Permet de créer le regex.
        while (true) {
            System.out.println("Quel jeu voulez vous jouer ? Nim (nim) ou Puissance 4 (p4)");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line); // Permet de créer un objet auquel on demande si la String correspond au regex.
                // On vérifie la réponse et retourne true si c'est "y" et false si c'est "n".
                if (matcher.find()) {
                    return line.equals("nim");
                }
            }
            afficherErreur("Vous avez répondu avec autre chose que `p4` ou `nim`.");
        }
    }

    /**
     * Demande à l'utilisateur s'il souhaite activer les rotations
     * @return true pour oui et false pour non
     */
    public boolean demanderActivationRotation() {
        Pattern pattern = Pattern.compile("^[y|n]$"); //Permet de créer le regex.
        while (true) {
            System.out.println("Voulez vous activer la rotation ? (y/n)");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line); // Permet de créer un objet auquel on demande si la String correspond au regex.
                // On vérifie la réponse et retourne true si c'est "y" et false si c'est "n".
                if (matcher.find()) {
                    return line.equals("y");
                }
            }
            afficherErreur("Vous avez répondu avec autre chose que `y` ou `n`.");
        }
    }

    /**
     * Demande à l'utilisateur s'il souhaite jouer un coup ou faire une rotation
     * @return true pour coup et false pour rotation
     */
    public boolean demanderCoupOuRotation(String nomJoueur) {
        Pattern pattern = Pattern.compile("^[c|r]$"); //Permet de créer le regex.
        while (true) {
            System.out.println(nomJoueur + ", voulez-vous jouer un coup (c) ou effectuer une rotation (r) ?");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line); // Permet de créer un objet auquel on demande si la String correspond au regex.
                // On vérifie la réponse et retourne true si c'est "c" et false si c'est "r".
                if (matcher.find()) {
                    return line.equals("c");
                }
            }
            afficherErreur("Vous avez répondu avec autre chose que 'c' ou 'r'.");
        }
    }

    /**
     * Demande à l'utilisateur dans quel sens effectuer la rotation
     * @return true pour horaire et false pour anti-horaire
     */
    public boolean demanderRotation(String nomJoueur) {
        Pattern pattern = Pattern.compile("^[h|a]$"); //Permet de créer le regex.
        while (true) {
            System.out.println(nomJoueur + ", voulez vous effectuer la rotation dans le sens horaire (h) ou anti-horaire (a) ?");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line); // Permet de créer un objet auquel on demande si la String correspond au regex.
                // On vérifie la réponse et retourne true si c'est "h" et false si c'est "a".
                if (matcher.find()) {
                    return line.equals("h");
                }
            }
            afficherErreur("Vous avez répondu avec autre chose que 'h' ou 'a'.");
        }
    }
}
