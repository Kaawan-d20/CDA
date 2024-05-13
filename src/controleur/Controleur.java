package controleur;

import exception.*;
import modele.joueur.IA;
import modele.joueur.Joueur;
import modele.abstrait.Plateau;
import vue.Ihm;

/**
 * Classe abstraite du contrôleur contenant les squelettes des méthodes, ainsi que les attributs.
 */
public abstract class Controleur {
    /** Numéro du joueur courant, x ∈ [0 ; 1]. **/
    protected int numeroJoueurCourant;

    /** Nombre de parties jouées. */
    protected  int nombrePartie = 0;

    /** Array contenant les objets Joueur représentant les joueurs. */
    protected Joueur[] lesJoueurs;

    /** Objet Plateau pouvant représenter ses sous-classes PlateauNim ou PlateauP4. */
    protected Plateau plateau;

    /** Objet Interface Humain Machine chargé de récupérer les commandes du joueur et d'afficher l'état de la partie. */
    protected Ihm ihm;

    /** Méthode initialisant les joueurs et lance la partie. */
    public void jouer(){
        initJoueur();
        toursDeJeu();
    }

    /** Méthode permettant d'initialiser un certain nombre de joueurs (humain ou IA).*/
    protected void initJoueur() {
        int nbJoueur;
        if (ihm.demanderSeulOuMulti()){
            nbJoueur = 1;
        } else {
            nbJoueur = 2;
        }
        lesJoueurs = new Joueur[2];
        for (int i = 0; i < nbJoueur; i++) {
            lesJoueurs[i] = new Joueur(ihm.demanderNomJoueur(i+1));
        }
        for (int i = nbJoueur; i < 2; i++) {
            lesJoueurs[i] = getIA();
        }
    }

    /**
     * <p>Squelette du déroulement d'une partie appel des méthodes de ses sous-classes.</p>
     * <ul>
     *     <li>Incrémentation des variables et initialisation du plateau.</li>
     *     <li>Demande du coup.</li>
     *     <li>Vérification de la victoire.</li>
     *     <li>Rappel de la méthode ou fin du programme.</li>
     * </ul>
     */
    protected void toursDeJeu() {
        nombrePartie+=1;
        numeroJoueurCourant=1;
        plateau.reset();
        setOption();
        while (!plateau.verifierFin()){
            tourSuivant();
            boolean estCoupCorrect = false;
            while (!estCoupCorrect){
                try{
                    getCoup();
                    estCoupCorrect = true;
                } catch (NombreBatonnetsInvalide | NumeroTasInvalide | ColonnePleine | FormatReponseInvalide exception){
                    ihm.afficherErreur(exception.getMessage());
                }
            }
        }

        victoire();

        if(ihm.demanderJouerEncore()){
            toursDeJeu();
        } else {
            finPartie();
        }
    }

    /**
     * <p>Passe au joueur suivant dans l'ordre des joueurs.</p>
     * <p>Actualise le numéro du joueur courant en faisant une rotation circulaire entre les joueurs.</p>
     */
    protected void tourSuivant() {
        this.numeroJoueurCourant = (numeroJoueurCourant + 1) % 2;
    }

    /**
     * Permet de verifier a la fin de la partie quel joueur a gagné la partie et le fait afficher par l'IHM.
     */
    protected void finPartie() {
        int comparaison = lesJoueurs[0].compareTo(lesJoueurs[1]);
        if (comparaison == 0) {
            ihm.afficherVictoire("ex Aequo", lesJoueurs[0].getNbVictoires(), nombrePartie, true);
        } else if (comparaison > 0) {
            ihm.afficherVictoire(lesJoueurs[0].getNom(), lesJoueurs[0].getNbVictoires(), nombrePartie, false);
        } else {
            ihm.afficherVictoire(lesJoueurs[1].getNom(), lesJoueurs[1].getNbVictoires(), nombrePartie, false);
        }
    }

    /**
     * Renvoie l'objet Joueur correspondant au joueur actuellement en cours.
     *
     * @return Le joueur courant.
     */
    protected Joueur getJoueurCourant() {
        return lesJoueurs[numeroJoueurCourant];
    }

    /**
     * Renvoie le nom du joueur actuellement en cours.
     *
     * @return Le nom du joueur courant.
     */
    protected String getNomJoueurCourant() {
        return lesJoueurs[numeroJoueurCourant].getNom();
    }

    //Déclaration des méthodes abstraite
    /**
     * <p>Méthode abstraite.</p>
     * <p>Doit demander le coup et demander au plateau de réaliser le coup.</p>
     * @throws FormatReponseInvalide Si la réponse n'est pas compris entre 1 et 7.
     * @throws NombreBatonnetsInvalide Si le nombre de bâtonnets dans le tas à inférieur au retrait demandé ou que la limite de bâtonnets par coup a été atteinte.
     * @throws NumeroTasInvalide Si le tas demandé est inconnue.
     * @throws ColonnePleine Si la colonne est pleine.
     */
    protected abstract void getCoup() throws FormatReponseInvalide, NombreBatonnetsInvalide, NumeroTasInvalide, ColonnePleine;

    /**
     * <p>Méthode abstraite.</p>
     * <p>Doit géré l'affichage du vainqueur,
     * doit aussi prendre en compte la possibilité d'un ex aequo.</p>
     */
    protected abstract void victoire();

    /**
     * <p>Méthode abstraite.</p>
     * <p>Doit demander l'activation de l'option à l'Ihm et demander au plateau d'activer ou non l'option.</p>
     */
    protected abstract void setOption();

    /**
     * <p>Méthode abstraite.</p>
     * <p>Retourne une instance de l'IA correspondant au jeu.</p>
     * @return Une instance de l'IA du jeu.
     */
    protected abstract IA getIA();
}
