package modele.p4;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import exception.NombreBatonnetsInvalide;
import exception.NumeroTasInvalide;
import exception.PlusDeRotations;
import modele.abstrait.Plateau;

/**
 * Classe représentant un plateau de jeu de puissance 4
 */
public class PlateauP4 extends Plateau {
    /**
     * Le tableau représentant le plateau de puissance 4
     */
    private byte[][] plateau;
    /**
     * Stock la position où le dernier jeton a été posé
     */
    private byte[] dernierCoup = new byte[2];
    /**
     * Stocker si la derniere action etait une rotation
     */
    private boolean wasRotation = false;
    /**
     * Toggle des rotations
     */
    private boolean rotations;
    /**
     * Compte le nombre de rotations effectuées par les joueurs
     */
    private byte[] nbRotations = {4,4};
    /**
     * Permet de construire un nouveau PlateauNim.
     */
    public PlateauP4() {}

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
        return (verifierVictoire() !=0) || estPlein();
    }

    /**
     * Permet de vérifier si une partie est gagnée, c'est-à-dire s'il y a une ligne de 4 pions ou plus consécutifs de même couleur
     * a partir du dernier coup joué si la derniere action etait de placer un jeton, ou de n'importe ou si la derniere
     * action etait une rotation.
     *
     * @return le numéro du gagnant si la partie est gagnée, sinon 0
     */
    public byte verifierVictoire() {
        if (wasRotation) {
            for (byte i = 0; i < 7; i++) {
                for (byte j = 0; j < 7; j++) {
                    if (plateau[i][j] != 0) {
                        byte candidate = verifierVictoireCase(i,j);
                        if (candidate != 0) {
                            return candidate;
                        }
                    }
                }
            }
            return 0;
        } else {
            return verifierVictoireCase(dernierCoup[0], dernierCoup[1]);
        }
    }

    /**
     * Permet de verifier si un jeton placé aux coordonnées x,y est gagnant c'est-à-dire s'il appartient a
     * une ligne de 4 pions ou plus consécutifs de même couleur
     *
     * @param x coordonnée verticale
     * @param y coordonnée horizontale
     * @return le numéro du gagnant si le jeton est gagnant, 0 sinon
     */
    public byte verifierVictoireCase(byte x, byte y) {
        /*Structure du tableau
         *   0 : Ligne horizontale (-)
         *   1 : Ligne verticale (|)
         *   2 : Ligne diagonale partant de en haut à gauche jusqu'à en bas à droite (\)
         *   3 : Ligne diagonale partant de en bas à gauche jusqu'à en haut à droite (/)
         */
        byte[][] lignes = new byte[4][7];
        byte color = plateau[x][y];

        if (color == 0){
            return 0;
        }

        lignes[0] = plateau[x]; // Remplissage du tableau pour la ligne horizontale

        for (int i=0; i < 7; i++) { // Remplissage du tableau pour la ligne verticale
            lignes[1][i] = plateau[i][y];
        }
        // Ligne diagonale partant de en haut à gauche jusqu'à en bas à droite
        int lig = x;
        int col = y;
        while (lig >= 0 && col >= 0) { // Monte à gauche
            lignes[2][lig] = plateau[lig][col];
            lig--;
            col--;
        }
        lig = x + 1;
        col = y + 1;
        while (lig < 7 && col < 7) { // Descend à droite
            lignes[2][lig] = plateau[lig][col];
            lig++;
            col++;
        }
        // Ligne diagonale partant de en bas à gauche jusqu'à en haut à droite
        lig = x;
        col = y;
        while (lig < 7 && col >= 0) { // Descend à gauche
            lignes[3][lig] = plateau[lig][col] ;
            lig++;
            col--;
        }
        lig = x -1;
        col = y +1;
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
                    return plateau[x][y];
                }
            }
        }
        return 0;
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
     * @throws FormatReponseInvalide Si le numéro de colonne demandé est inexistant
     * @throws ColonnePleine Si la colonne demander est pleine
     */
    public void placerJeton(byte colonne, byte joueur) throws FormatReponseInvalide, ColonnePleine {
        if (colonne<0 || colonne>6){
            throw new FormatReponseInvalide("Veuillez entrer un entier compris entre 1 et 7");
        }
        boolean estPlein = true;
        for (byte ligne = 6; ligne >= 0; ligne--) {
            if (plateau[ligne][colonne] == 0) {
                plateau[ligne][colonne] = joueur;
                dernierCoup[0] = ligne;
                dernierCoup[1] = colonne;
                wasRotation = false;
                estPlein = false;
                break;
            }
        }
        if (estPlein){
            throw new ColonnePleine("Le jeton ne peut pas être placé ");
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

    /**
     * Méthode qui permet d'activer ou désactiver la rotation
     * @param i true ou false pour activer
     */
    public void setRotations(boolean i){
        this.rotations= i;
    }

    /**
     * Getter pour savoir si la rotation est activé
     * @return l'état d'activation de la rotation
     */
    public boolean isRotations(int numJoueur) {
        if (!rotations) {
            return false;
        }
        return nbRotations[numJoueur] > 0;
    }

    /**
     * Fonction qui permet de faire tourner le plateau dans un sens
     * @param sens Le sens de la rotation true pour horaire et false pour antihoraire
     * @param joueur Le numéro du joueur
     * @throws PlusDeRotations Si le joueur n'a plus de rotation disponible
     */
    public void rotation(boolean sens, int joueur)  throws PlusDeRotations {
        if (nbRotations[joueur] > 0) {
            nbRotations[joueur]--;
            wasRotation = true;
            if (sens) {
                rotationHoraire();
            } else {
                rotationAntiHoraire();
            }
        } else {
            throw new PlusDeRotations("Plus de rotations autorisées");
        }
    }

    /**
     * Fonction qui permet de faire tourner le plateau dans le sens horaire
     */
    private void rotationHoraire() {
        byte[][] nouveau = new byte[7][7];
        for (int i = 0; i < 7; i++) { // la ligne à transformer en colonne
            int k = 6; // la profondeur à laquelle placer le prochain jeton
            for (int j = 6; j >= 0; j--) { // le parcours de la ligne
                if (plateau[i][j] != 0) {
                    nouveau[k][6-i] = plateau[i][j];
                    k--;
                }
            }
        }
        plateau = nouveau;
    }

    /**
     * Fonction qui permet de faire tourner le plateau dans le sens horaire
     */
    private void rotationAntiHoraire() {
        byte[][] nouveau = new byte[7][7];
        for (int i = 0; i < 7; i++) { //la ligne à transformer en colonne
            int k = 6; // la profondeur à laquelle placer le prochain jeton
            for (int j = 0; j < 7; j++) { // le parcours de la ligne
                if (plateau[i][j] != 0) {
                    nouveau[k][i] = plateau[i][j];
                    k--;
                }
            }
        }
        plateau = nouveau;
    }
}