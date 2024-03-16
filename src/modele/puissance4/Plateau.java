package modele.puissance4;

import java.util.Arrays;

/**
 * Classe représentant un plateau de jeu de Nim
 */
public class Plateau {
    /**
     * Le tableau représenant le plateau de puissance 4
     */
    private byte[][] plateau;
    /**
     * Stock la position où le dernier jeton a été posé
     */
    private byte[] dernierCoup = new byte[2];
    /**
     * Permet de construire un nouveau Plateau.
     */
    public Plateau() {}

    /**
     * Remplit le plateau en créant le plateau
     * Remplace le plateau déjà existant
     */
    public void reset() {
        plateau = new byte[7][7];
    }

    /**
     * Permet de verifier si une partie est finie, c'est-à-dire si 4 pions sont alignés
     *
     * @return true si la partie est fini, sinon false
     */
    public boolean verifierFin() {
        return true;
    }

    /**
     * Permet de vérifier si le plateau est plein
     * @return true si le plateau est plein, sinon false
     */
    private boolean estPlein(){
        for (byte[] ligne : plateau){
            for (int contenu : ligne){
                if (contenu == 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Permet de retourner l'état du plateau sous la forme d'un tableau de byte
     * Inutilisé, pourra être supprimé si jamais utilisé
     * (sera probablement utilisée quand les IA seront implémenté)
     *
     * @return un tableau représentant le plateau de puissance 4
     */
    public byte[][] getPlateau(){
        return plateau;
    }


    public void placerJeton(int colonne, byte joueur){
        for (int i = 7; i > 0; i++) {
            if (plateau[i][colonne] == 0) {
                plateau[i][colonne] = joueur;
            }
        }
    }

    
    @Override
    public String toString() {
        String to = "[\n";
        for (byte[] ligne : plateau){
            to += Arrays.toString(ligne) + ", \n";
        }
        to += "]";
        return to;
    }
}