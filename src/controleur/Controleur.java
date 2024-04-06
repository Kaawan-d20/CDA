package controleur;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import exception.NombreBatonnetsInvalide;
import exception.NumeroTasInvalide;
import modele.Joueur;
import modele.Plateau;
import vue.Ihm;

public abstract class Controleur {
    protected int numeroJoueurCourant;
    protected  int nombrePartie = 0;
    protected Joueur[] lesJoueurs;
    protected Plateau plateau;
    protected Ihm ihm;

    protected void initJoueur() {
        int nbJoueur = 2;
        lesJoueurs = new Joueur[2];
        for (int i = 0; i < nbJoueur; i++) {
            lesJoueurs[i] = new Joueur(ihm.demanderNomJoueur(i+1));
        }
    }

    protected void toursDeJeu() {
        nombrePartie+=1;
        numeroJoueurCourant=1;
        plateau.reset();
        //plateau.steOption();
        while (!plateau.verifierFin()){
            tourSuivant();
            boolean estCoupCorrect = false;
            while (!estCoupCorrect){
                try{
                    estCoupCorrect = true;
                    ihm.afficherPlateau(plateau.toString());
                    getCoup();
                } catch (NombreBatonnetsInvalide | NumeroTasInvalide | ColonnePleine | FormatReponseInvalide exception){
                    estCoupCorrect = false;
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
    protected abstract void getCoup() throws FormatReponseInvalide, NombreBatonnetsInvalide, NumeroTasInvalide, ColonnePleine;
    protected abstract void victoire();
    public abstract void jouer();
}
