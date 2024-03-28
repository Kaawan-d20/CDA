package modele.p4;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;

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
     * <p>Remplit le plateau en créant le plateau</p>
     * <p>Remplace le plateau déjà existant</p>
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
     * Permet de vérifier si une partie est gagnée, c'est-à-dire s'il y a une ligne de 4 pions ou plus consécutifs de même couleur
     *
     * @return true si la partie est gagnée, sinon false
     */
    public boolean verifierVictoire() {
        /*Structure du tableau
         *   0 : Ligne horizontale (-)
         *   1 : Ligne verticale (|)
         *   2 : Ligne diagonale partant de en haut à gauche jusqu'à en bas à droite (\)
         *   3 : Ligne diagonale partant de en bas à gauche jusqu'à en haut à droite (/)
         */
        byte[][] lignes = new byte[4][7];
        byte color = plateau[dernierCoup[0]][dernierCoup[1]];

        if (color == 0){
            return false;
        }

        lignes[0] = plateau[dernierCoup[0]]; // Remplissage du tableau pour la ligne horizontale

        for (int i=0; i < 7; i++) { // Remplissage du tableau pour la ligne verticale
            lignes[1][i] = plateau[i][dernierCoup[1]];
        }
        // Ligne diagonale partant de en haut à gauche jusqu'à en bas à droite
        int lig = dernierCoup[0];
        int col = dernierCoup[1];
        while (lig >= 0 && col >= 0) { // Monte à gauche
            lignes[2][lig] = plateau[lig][col];
            lig--;
            col--;
        }
        lig = dernierCoup[0] + 1;
        col = dernierCoup[1] + 1;
        while (lig < 7 && col < 7) { // Descend à droite
            lignes[2][lig] = plateau[lig][col];
            lig++;
            col++;
        }
        // Ligne diagonale partant de en bas à gauche jusqu'à en haut à droite
        lig = dernierCoup[0];
        col = dernierCoup[1];
        while (lig < 7 && col >= 0) { // Descend à gauche
            lignes[3][lig] = plateau[lig][col] ;
            lig++;
            col--;
        }
        lig = dernierCoup[0] -1;
        col = dernierCoup[1] +1;
        while (lig >= 0 && col < 7) { // Monte à droite
            lignes[3][lig] = plateau[lig][col];
            lig--;
            col++;
        }
        //Vérification si 4 jetons sont alignés
        for (byte[] direction : lignes) {
            int compteur = 0;
            for (byte jeton : direction) {
                if (jeton == color) {
                    compteur++;
                } else {
                    compteur = 0;
                }
                if (compteur == 4) {
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
            for (int jeton : ligne){
                if (jeton == 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * <p>Permet de retourner l'état du plateau sous la forme d'un tableau de byte</p>
     * <p>Inutilisé, pourra être supprimé si jamais utilisé
     * (sera probablement utilisée quand les IA seront implémenté)</p>
     *
     * @return un tableau représentant le plateau de puissance 4
     */
    public byte[][] getPlateau(){
        return plateau;
    }

    /**
     * <p>Permet de placer un jeton dans une colonne</p>
     * <p>Parcours la colonne à partir du bas et place le jeton dès qu'une case est vide (0)</p>
     * @param colonne L’indice de la colonne où le jeton doit être placé [0,...,6]
     * @param joueur  Le numéro du joueur [1,2]
     * @throws ColonnePleine Si la colonne dans laquelle le joueur veut placer le jeton est pleine
     * @throws FormatReponseInvalide Si le numéro de colonne demandé est inexistant
     */
    public void placerJeton(byte colonne, byte joueur) throws ColonnePleine, FormatReponseInvalide {
        if (colonne<0 || colonne>6){
            throw new FormatReponseInvalide("Veuillez entrer un entier compris entre 1 et 7");
        }
        boolean estPlein = true;
        for (byte ligne = 6; ligne >= 0; ligne--) {
            if (plateau[ligne][colonne] == 0) {
                plateau[ligne][colonne] = joueur;
                dernierCoup[0] = ligne;
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
     * <p>Renvoie une représentation sous forme de chaîne de caractères de l'objet.</p>
     * <p>La chaîne est composée de la représentation de la matrice,
     * disposée horizontalement avec un espacement approprié pour les centrer dans une ligne.</p>
     *
     * @return une représentation sous forme de chaîne de caractères de l'objet.
     */
    @Override
    public String toString() {
        String string = "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n";
        for (byte[] ligne : plateau){
            for (byte contenu : ligne){
                if (contenu == 0){
                    string+= "⚫ ";
                } else if (contenu == 1) {
                    string+= "🔴 ";
                }else {
                    string+= "🟡 " ;
                }
            }
            string+="\n";
        }
        return string;
    }
}