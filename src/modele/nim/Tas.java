package modele.nim;

import exception.NombreBatonnetsInvalide;

/**
 * Classe représentant un tas de bâtonnets du jeu de Nim.
 */
public class Tas {
    /** Le nombre de bâtonnets contenu dans le tas.*/
    private int nombreBatonnet;

    /**
     * Permet de construire un objet Tas.
     * @param nombreBatonnet : le nombre de bâtonnets dans le tas, doit être supérieur 0.
     */
    public Tas(int nombreBatonnet) {
        this.nombreBatonnet = nombreBatonnet;
    }

    /**
     * Retire un nombre spécifié de bâtonnets du tas.
     *
     * @param n Le nombre de bâtonnets à retirer du tas. Doit être supérieur ou égal à 0
     *          et ne doit pas dépasser le nombre total de bâtonnets dans le tas.
     * @throws NombreBatonnetsInvalide Si le nombre de bâtonnets à retirer est invalide,
     *          c'est-à-dire s'il est négatif ou s'il dépasse le nombre actuel de bâtonnets dans le tas.
     */
    public void retirerBatonnet(int n) throws NombreBatonnetsInvalide {
        if (n > 0 && n <= nombreBatonnet) {
            nombreBatonnet -= n;
        } else {
            throw new NombreBatonnetsInvalide("Vous avez sélectionné un nombre de bâtonnet(s) invalide.");
        }
    }

    /**
     * Getter du nombre de bâtonnets du tas.
     * @return Le nombre de bâtonnets contenu dans le tas.
     */
    public int getNombre() {
        return nombreBatonnet;
    }

    /**
     * Teste si le tas de bâtonnets est vide, c'est-à-dire si le nombre de bâtonnets est 0
     * @return true si le tas est vide, false sinon
     */
    public boolean estVide() {
        return nombreBatonnet <= 0;
    }

    /**
     * Permet d'afficher le tas de bâtonnets sous forme de string.
     *
     * @return une String au format  n * "|".
     */
    @Override
    public String toString() {
        return "|".repeat(nombreBatonnet);
    }
}
