package controleur;

import exception.*;
import modele.joueur.IA;
import modele.joueur.IANim;
import modele.joueur.Joueur;
import modele.abstrait.Plateau;
import modele.nim.PlateauNim;
import modele.p4.PlateauP4;
import vue.Ihm;

/**
 * Classe abstraite du contrôleur contenant les squelettes des méthodes, ainsi que les attributs
 */
public abstract class Controleur {
    /** Numéro du joueur courant, x ∈ [0 ; 1]**/
    protected int numeroJoueurCourant;

    /** Nombre de parties jouées, incrémentée dans toursDeJeu()
     * @see #toursDeJeu() */
    protected  int nombrePartie = 0;

    /** Array contenant les objets Joueur représentant les joueurs
     * @see Joueur*/
    protected Joueur[] lesJoueurs;

    /** Objet Plateau pouvant représenter ses sous-classes PlateauNim ou PlateauP4
     * @see Plateau
     * @see PlateauNim
     * @see PlateauP4 */
    protected Plateau plateau;

    /**
     * Objet Interface Humain Machine chargé de récupérer les commandes du joueur et d'afficher l'état de la partie
     * @see Ihm
     */
    protected Ihm ihm;

    /**
     * Méthode initialisant les joueurs et lance la partie
     */
    public void jouer(){
        initJoueur();
        toursDeJeu();
    }

    /**
     * Méthode permettant d'initialiser un certain nombre de joueurs (pour l'instant, fixé à 2)
     */
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
     * <p>Squelette du déroulement d'une partie appel des méthodes de ses sous-classes</p>
     * <ul>
     *     <li>Incrémentation des variables et initialisation du plateau</li>
     *     <li>Demande du coup</li>
     *     <li>Vérification de la victoire</li>
     *     <li>Rappel de la méthode ou fin du programme</li>
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
                } catch (NombreBatonnetsInvalide | NumeroTasInvalide | ColonnePleine | FormatReponseInvalide |
                         PlusDeRotations exception){
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
     * <p>Renvoie le numéro du joueur actuellement en cours.</p>
     * <p>Pas utilisée pour l'instant, pourra être retirée si besoin</p>
     *
     * @return Le numéro du joueur courant.
     */
    protected int getNumeroJoueurCourant() {
        return numeroJoueurCourant;
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
     * <p>Méthode abstraite</p>
     * <p>Doit demander le coup à l'ihm et demander au plateau de réaliser le coup</p>
     */
    protected abstract void getCoup() throws FormatReponseInvalide, NombreBatonnetsInvalide, NumeroTasInvalide, ColonnePleine, PlusDeRotations;

    /**
     * <p>Méthode abstraite</p>
     * <p>Doit géré l'affichage du vainqueur,
     * doit aussi prendre en compte la possibilité d'un ex aequo</p>
     */
    protected abstract void victoire();


    /**
     * <p>Méthode abstraite</p>
     * <p>Doit demander l'activation de l'option à l'ihm et demander au plateau d'activer ou non l'option</p>
     */
    protected abstract void setOption();

    protected abstract IA getIA();
}
