package controleur;

import modele.Joueur;
import modele.puissance4.Plateau;
import vue.Ihm;

/**
 * Classe du contrôleur du jeu de Nim
 */
public class ControleurPuissance4 {
    /** Numéro du joueur courant, x ∈ [0 ; 1]**/
    private int numeroJoueurCourant;

    /** Nombre de parties jouées, incrémentée dans commencerPartie()*/
    private int nbParties = 0;

    /** Array contenant les objets Joueur représentant les joueurs*/
    private Joueur[] lesJoueurs;

    /** Objet Plateau représentant la grille du puissance 4*/
    private Plateau plateau;

    /**
     * Objet Interface Humain Machine chargé de récupérer les commandes du joueur et d'afficher l'état de la partie
     */
    private final Ihm ihm;

    /**
     * Initialise un nouveau contrôleur de jeu de puissance 4 avec une interface utilisateur spécifiée.
     *
     * @param ihm L'interface utilisateur associée au contrôleur.
     */
    public ControleurPuissance4 (Ihm ihm) {
        this.ihm = ihm;
        this.lesJoueurs = new Joueur[2];
        this.plateau = new Plateau();
        plateau.reset();
        System.out.println(plateau);
    }

    /**
     * Initialise une nouvelle partie en demandant les noms des joueurs,
     * et en créant le plateau de jeu.
     */
    public void jouer() {
        for (int i = 0; i < 2; i++) {
            String nomJoueur = "";

            // Permet de s'assurer que l'on ne rentre pas un nom vide.
            boolean nomValide = false;
            while (!nomValide) {
                nomJoueur = ihm.demanderNomJoueur(i+1);
                if (nomJoueur.isBlank()) {
                    ihm.afficherErreur("Veuillez choisir un nom qui ne soit pas composé exclusivement de caractères invisibles (Espaces, Tabulations ...).");
                } else {
                    nomValide = true;
                }
            }
            this.lesJoueurs[i] = new Joueur(nomJoueur);
        }
        commencerPartie();
    }

    /**
     * Incrémente le nombre de parties, reset / crée le plateau,
     * Puis lance la séquence de tours de jeu.
     */
    private void commencerPartie() {
        numeroJoueurCourant = 1;

        // Incrémentation du nombre de parties jouées
        this.nbParties ++;

        // Crée / reset le plateau
        plateau.reset();

    }

}
