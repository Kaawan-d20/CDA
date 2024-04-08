package modele;

import exception.*;

/**
 * Classe abstraite du plateau, n'est pas une interface pour que dans
 * le contrôleur puisse avoir un attribut commun de type Plateau
 * et donc peu prend soit un PlateauNim ou un PlateauP4
 * @see controleur.Controleur
 */
public abstract class Plateau {

    /**
     * Remplit et/ou réinitialise le plateau
     */
    public abstract void reset();

    /**
     * Permet de verifier si une partie est finie
     * @return true si la partie est fini, sinon false
     */
    public abstract boolean verifierFin();

    /**
     * Méthode permettant de placer un jeton dans le jeu de puissance 4
     * (si elle n'est pas utile dans la classe mettre : throw new UnsupportedOperationException("Méthode non implémentée");)
     * @param colonne L’indice de la colonne où le jeton doit être placé [0,...,6]
     * @param joueur  Le numéro du joueur [1,2]
     * @throws FormatReponseInvalide Si le numéro de colonne demandé est inexistant
     * @throws ColonnePleine Si la colonne demander est pleine
     */
    public abstract void placerJeton(byte colonne, byte joueur) throws ColonnePleine, FormatReponseInvalide;

    /**
     * Méthode du jeu de puissance 4
     * (si elle n'est pas utile dans la classe mettre : throw new UnsupportedOperationException("Méthode non implémentée");)
     * @return un booléen s'il y a une victoire
     */
    public abstract boolean verifierVictoire();

    /**
     * Méthode du jeu de Nim
     * (si elle n'est pas utile dans la classe mettre : throw new UnsupportedOperationException("Méthode non implémentée");)
     * @throws NumeroTasInvalide Si le numéro du tas est invalide (hors des limites ou égal à zéro).
     * @throws NombreBatonnetsInvalide Si le nombre de bâtonnets à retirer est invalide (négatif ou supérieur au nombre actuel de bâtonnets dans le tas).
     */
    public abstract void retirerBatonnets(int m, int n) throws NombreBatonnetsInvalide, NumeroTasInvalide;
    public abstract void setOption(int opt);
    public abstract void rotation(boolean sens, int joueur)  throws PlusDeRotations;
    public abstract void setRotations(int i);
    public abstract boolean isRotations();
}
