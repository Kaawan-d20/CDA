package controleur;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import modele.Joueur;
import modele.p4.Plateau;
import vue.Ihm;

/**
 * Classe du contrôleur du jeu de puissance 4
 */
public class ControleurP4 {
    /** Numéro du joueur courant, x ∈ [0 ; 1]**/
    private int numeroJoueurCourant;

    /** Nombre de parties jouées, incrémentée dans commencerPartie()*/
    private int nbParties = 0;

    /** Array contenant les objets Joueur représentant les joueurs*/
    private Joueur[] lesJoueurs;

    /** Objet Plateau représentant la grille de puissance 4*/
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
    public ControleurP4(Ihm ihm) {
        this.ihm = ihm;
        this.lesJoueurs = new Joueur[2];
        this.plateau = new Plateau();
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

        // Lance la partie
        toursDeJeu();
    }


    /**
     * Facilite le déroulement complet d'une série de tours de jeu jusqu'à la fin.
     * À chaque tour, demande le coup au joueur courant, met à jour le plateau, et gère les erreurs.
     * Après la fin du jeu, incrémente les victoires du joueur courant, affiche le résultat,
     * puis demande si les joueurs veulent jouer encore.
     * En cas de réponse affirmative, démarre une nouvelle partie ; sinon, termine le programme.
     */
    private void toursDeJeu() {
        while (!plateau.verifierFin()) {
            tourSuivant();
            boolean estCoupCorrect = false;
            while (!estCoupCorrect) {
                try {
                    estCoupCorrect = true;
                    ihm.afficherPlateau(plateau.toString());
                    byte candidate = ihm.demanderCoupP4(getNomJoueurCourant());
                    if (candidate<1 || candidate>7){
                        throw new FormatReponseInvalide("Veuillez entrer un entier compris entre 1 et 7");
                    }
                    plateau.placerJeton((byte) (candidate-1), (byte) (numeroJoueurCourant+1));
                } catch (ColonnePleine | FormatReponseInvalide exception) {
                    estCoupCorrect = false;
                    ihm.afficherErreur(exception.getMessage());
                }
            }
        }
        getJoueurCourant().incrementVictoires();
        ihm.afficherVictoire(getNomJoueurCourant(), getJoueurCourant().getNbVictoires(), nbParties, false);

        boolean reponseAcceptee = false;
        while (!reponseAcceptee) {
            try {
                reponseAcceptee = true;
                if (ihm.demanderJouerEncore()) {
                    commencerPartie();
                } else {
                    finPartie();
                    return;
                }
            } catch (FormatReponseInvalide exception){
                reponseAcceptee = false;
                ihm.afficherErreur(exception.getMessage());
            }
        }
    }

    /**
     * Passe au joueur suivant dans l'ordre des joueurs.
     * Actualise le numéro du joueur courant en faisant une rotation circulaire entre les joueurs.
     */
    private void tourSuivant() {
        this.numeroJoueurCourant = (numeroJoueurCourant + 1) % 2;
    }


    /**
     * Permet de verifier a la fin de la partie quel joueur a gagné la partie et le fait afficher par l'IHM.
     */
    private void finPartie() {
        int comparaison = lesJoueurs[0].compareTo(lesJoueurs[1]);
        if (comparaison == 0) {
            ihm.afficherVictoire("ex Aequo", lesJoueurs[0].getNbVictoires(), nbParties, true);
        } else if (comparaison > 0) {
            ihm.afficherVictoire(lesJoueurs[0].getNom(), lesJoueurs[0].getNbVictoires(), nbParties, false);
        } else {
            ihm.afficherVictoire(lesJoueurs[1].getNom(), lesJoueurs[1].getNbVictoires(), nbParties, false);
        }
    }

    /**
     * Renvoie le numéro du joueur actuellement en cours.
     * Pas utilisée pour l'instant, pourra être retirée si besoin
     *
     * @return Le numéro du joueur courant.
     */
    public int getNumeroJoueurCourant() {
        return numeroJoueurCourant;
    }

    /**
     * Renvoie l'objet Joueur correspondant au joueur actuellement en cours.
     *
     * @return Le joueur courant.
     */
    public Joueur getJoueurCourant() {
        return lesJoueurs[numeroJoueurCourant];
    }

    /**
     * Renvoie le nom du joueur actuellement en cours.
     *
     * @return Le nom du joueur courant.
     */
    public String getNomJoueurCourant() {
        return lesJoueurs[numeroJoueurCourant].getNom();
    }
}
