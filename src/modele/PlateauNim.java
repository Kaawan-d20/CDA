package modele;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import exception.NombreBatonnetsInvalide;
import exception.NumeroTasInvalide;

/**
 * Classe représentant un plateau de jeu de Nim
 */
public class PlateauNim extends Plateau {
    /**
     * Le tableau contenant les tas
     */
    private Tas[] lesTas;
    /**
     * Le nombre de tas
     */
    private int nombreTas;

    /**
     * Le nombre Maximum de batonnets a retirable par coups
     */
    private int maxBatonnets;

    /**
     * Permet de construire un nouveau PlateauNim, en fonction d'un nombre de tas.
     *
     * @param nombreTas : nombre de tas à créer dans le plateau
     */
    public PlateauNim(int nombreTas) {
        this.lesTas = new Tas[nombreTas];
        this.nombreTas = nombreTas;
    }

    /**
     * <p>Remplit le plateau en créant les tas</p>
     * <p>Remplace les tas déjà existant</p>
     */
    public void reset() {
        for (int i = 0; i < nombreTas; i++) {
            lesTas[i] = new Tas(2*(i+1)-1);
        }
    }

    /**
     * Permet de verifier si une partie est finie, c'est-à-dire si tous les tas sont vides
     *
     * @return true si la partie est fini, sinon false
     */
    public boolean verifierFin() {
        boolean b = true;
        for (Tas t : lesTas) {
            b = b && t.estVide();
        }
        return b;
    }

    /**
     * <p>Permet de retourner l'état du plateau sous la forme d'un tableau d'entier</p>
     * <p>Inutilisé, pourra être supprimé si jamais utilisé
     * (sera probablement utilisée quand les IA seront implémenté)</p>
     *
     * @return un tableau contenant le nombre de bâtonnets de chaque tas, au format [1, 3, 5]
     */
    public int[] getPlateau() {
        int[] plateau = new int[nombreTas];
        for (int i = 0; i < nombreTas; i++) {
            plateau[i] = lesTas[i].getNombre();
        }
        return plateau;
    }

    /**
     * Retire un nombre spécifié de bâtonnets d'un tas spécifié sur le plateau.
     *
     * @param m Le numéro du tas à partir duquel retirer les bâtonnets.
     * @param n Le nombre de bâtonnets à retirer.
     * @throws NumeroTasInvalide Si le numéro du tas est invalide (hors des limites ou égal à zéro).
     * @throws NombreBatonnetsInvalide Si le nombre de bâtonnets à retirer est invalide (négatif ou supérieur au nombre actuel de bâtonnets dans le tas).
     */
    public void retirerBatonnets(int m, int n) throws NombreBatonnetsInvalide, NumeroTasInvalide {
        if ( m > nombreTas || m <= 0 ) {
            throw new NumeroTasInvalide("Vous avez sélectionné un tas inconnu.");
        }
        if ( n > maxBatonnets ) {
            throw new NombreBatonnetsInvalide("Vous avez selectionner trop de batonnets (max " + maxBatonnets + " batonnets par coup)");
        }
        lesTas[m-1].retirerBatonnet(n);
    }

    /**
     * <p>Renvoie une représentation sous forme de chaîne de caractères de l'objet.</p>
     * <p>La chaîne est composée de la représentation de chaque "Tas" dans la collection,
     * disposée horizontalement avec un espacement approprié pour les centrer dans une ligne.</p>
     *
     * @return une représentation sous forme de chaîne de caractères de l'objet,
     * avec les représentations des "Tas" centrées dans la ligne.
     */
    @Override
    public String toString() {
        int largeur = 2 * nombreTas - 1;
        String s = "";
        for (int i = 0; i < nombreTas; i++) {
            Tas t = lesTas[i];
            String batonnets = t.toString();
            int tailleMarge = (largeur - batonnets.length()) / 2;
            String marge = " ".repeat(tailleMarge);
//            s += Integer.toString(i + 1) + " : " + marge + batonnets + marge + "\n";
            s += (i+1) + ": " + marge + batonnets + marge + "\n";
        }
        return s;
    }

    /**
     * Setter pour le nombre Maximum de batonnets retirable en un coup
     * @param maxBatonnets
     */
    public void setOption(int maxBatonnets) {
        this.maxBatonnets = maxBatonnets;
    }

    public void placerJeton(byte colonne, byte joueur) throws ColonnePleine, FormatReponseInvalide{
        throw new UnsupportedOperationException("Méthode non implémentée");
    }

    public boolean verifierVictoire(){
        throw new UnsupportedOperationException("Méthode non implémentée");
    }
    public void rotation(boolean sens, int joueur){
        throw new UnsupportedOperationException("Méthode non implémentée");
    }

    public void setRotations(int i){
        throw new UnsupportedOperationException("Méthode non implémentée");
    }

    public boolean isRotations(){
        throw new UnsupportedOperationException("Méthode non implémentée");

    }
}