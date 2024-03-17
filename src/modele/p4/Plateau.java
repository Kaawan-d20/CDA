package modele.p4;

import exception.ColonnePleine;

/**
 * Classe représentant un plateau de jeu de puissance 4
 */
public class Plateau {
    /**
     * Le tableau représentant le plateau de puissance 4
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
     * Permet de verifier si une partie est finie, c'est-à-dire si 4 pions sont alignés ou le plateau est plein
     *
     * @return true si la partie est fini, sinon false
     */
    public boolean verifierFin() {
        return verifierVictoire() || estPlein();
    }

    /**
     * Permet de vérifier si une partie est gagnée, c'est à dire si il y a une ligne de 4 pions ou plus consécutifs de même couleur
     *
     * @return true si la partie est gagnée, sinon false
     */
    public boolean verifierVictoire() {
        byte[][] lignes = new byte[4][7];
        byte color = plateau[dernierCoup[0]][dernierCoup[1]];

        if (color == 0){
            return false;
        }

        lignes[0] = plateau[dernierCoup[0]];
        for (int i=0; i < 7; i++) {
            lignes[1][i] = plateau[i][dernierCoup[1]];
        }
        int a = dernierCoup[0];
        int b = dernierCoup[1];
        while (a >= 0 && b >= 0) { // monte à gauche
            lignes[2][a] = plateau[a][b];
            a--;
            b--;
        }
        a = dernierCoup[0] + 1;
        b = dernierCoup[1] + 1;
        while (a < 7 && b < 7) { // descend à droite
            lignes[2][a] = plateau[a][b];
            a++;
            b++;
        }
        a = dernierCoup[0];
        b = dernierCoup[1];
        while (a < 7 && b >= 0) { // descend à gauche
            lignes[3][a] = plateau[a][b] ;
            a++;
            b--;
        }
        a = dernierCoup[0] -1;
        b = dernierCoup[1] +1;
        while (a >= 0 && b < 7) { // monte à droite
            lignes[3][a] = plateau[a][b];
            a--;
            b++;
        }

        for (int i=0; i < 4; i++) {
            int c = 0;
            for (int j=0; j < lignes[i].length; j++) {
                if (lignes[i][j] == color) {
                    c++;
                } else {
                    c = 0;
                }
                if (c == 4) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Permet de vérifier si le plateau est plein
     * @return true si le plateau est plein, sinon false
     */
    public boolean estPlein(){
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

    /**
     * Permet de placer un jeton dans une colonne
     * Parcours la colonne à partir du bas et place le jeton dès qu'une case est vide (0)
     * @param colonne L’indice de la colonne où le jeton doit être placé [0,...,6]
     * @param joueur  Le numéro du joueur [1,2]
     * @throws ColonnePleine Si la colonne dans laquelle le joueur veut placer le jeton est pleine
     */
    public void placerJeton(byte colonne, byte joueur) throws ColonnePleine {
        boolean estPlein = true;
        for (byte i = 6; i >= 0; i--) {
            if (plateau[i][colonne] == 0) {
                plateau[i][colonne] = joueur;
                dernierCoup[0] = i;
                dernierCoup[1] = colonne;
                estPlein = false;
                break;
            }
        }
        if (estPlein){
            throw new ColonnePleine("Le jeton ne peut pas être placer ");
        }
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de l'objet.
     * La chaîne est composée de la représentation de la matrice,
     * disposée horizontalement avec un espacement approprié pour les centrer dans une ligne.
     *
     * @return une représentation sous forme de chaîne de caractères de l'objet.
     */
    @Override
    public String toString() {
        String to = "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n";
        for (byte[] ligne : plateau){
            for (byte contenu : ligne){
                if (contenu == 0){
                    to+= "⚫ ";
                } else if (contenu == 1) {
                    to+= "🔴 ";
                }else {
                    to+= "🟡 " ;
                }
            }
            to+="\n";
        }
        return to;
    }
}