package modele.p4;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import modele.abstrait.Plateau;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Classe représentant un plateau de jeu de puissance 4.
 */
public class PlateauP4 extends Plateau {
    /** Le tableau représentant le plateau de puissance 4. */
    private byte[][] plateau;

    /** Stock la position où le dernier jeton a été posé. */
    private CoupP4Coup dernierCoup = new CoupP4Coup((byte) 0, (byte) 0);

    /** Stocker si la dernière action était une rotation. */
    private boolean wasRotation = false;

    /** Toggle des rotations. */
    private boolean rotations;

    /** Compte le nombre de rotations effectuées par les joueurs. */
    private byte[] nbRotations = {4,4};

    /** Permet de construire un nouveau PlateauP4 sans arguments (plateau vide). */
    public PlateauP4() {}

    /** Permet de construire un nouveau PlateauP4 copie d'un autre plateau.
     * @param plateau Un plateau déjà rempli.
     */
    public PlateauP4(byte[][] plateau) {
        this.plateau = plateau;
    }

    /** Permet de construire un nouveau PlateauP4 copie d'un autre plateau.
     * @param plateau Un plateau.
     * @param dernierCoup Le dernierCoup.
     * @param wasRotation Le WasRotation.
     * @param rotations L'activation des rotations.
     * @param nbRotations Le nombre de rotations des joueurs.
     * */
    private PlateauP4(byte[][] plateau, CoupP4Coup dernierCoup, boolean wasRotation, boolean rotations, byte[] nbRotations) {
        this.plateau = plateau;
        this.dernierCoup = dernierCoup;
        this.wasRotation = wasRotation;
        this.rotations = rotations;
        this.nbRotations = nbRotations;
    }

    /** Remplit le plateau en créant le plateau ou remplace le plateau déjà existant. Et reset les rotations. */
    public void reset() {
        plateau = new byte[7][7];
        nbRotations = new byte[]{4, 4};
    }

    /**
     * Permet de verifier si une partie est finie, c'est-à-dire si 4 pions sont alignés ou le plateau est plein.
     * @return true si la partie est fini, sinon false.
     */
    public boolean verifierFin() {
        return (verifierVictoire() !=0) || estPlein();
    }

    /**
     * Permet de vérifier si une partie est gagnée, c'est-à-dire s'il y a une ligne de 4 pions ou plus consécutifs de même couleur
     * à partir du dernier coup joué si la dernière action était de placer un jeton, ou de n'importe où si la dernière
     * action était une rotation.
     *
     * @return Le numéro du gagnant si la partie est gagnée, sinon 0.
     */
    public byte verifierVictoire() {
        if (wasRotation) { // Si c'est une rotation, on vérifie pour chaque case contenant un jeton.
            for (byte i = 0; i < 7; i++) {
                for (byte j = 0; j < 7; j++) {
                    if (plateau[i][j] != 0) {
                        byte candidate = verifierVictoireCase(new CoupP4Coup(j,i));
                        if (candidate != 0) {
                            return candidate;
                        }
                    }
                }
            }
            return 0;
        } else { // Sinon, on vérifie seulement à l'emplacement du coup.
            return verifierVictoireCase(dernierCoup);
        }
    }

    /**
     * Méthode permettant d'obtenir les lignes à vérifier autour d'un coup.
     * Structure du tableau :
     *   0 : Ligne horizontale (-).
     *   1 : Ligne verticale (|).
     *   2 : Ligne diagonale partant de en haut à gauche jusqu'à en bas à droite (\).
     *   3 : Ligne diagonale partant de en bas à gauche jusqu'à en haut à droite (/).
     * @param coup L'endroit d'où il faut générer le tableau.
     * @return Le tableau contenant les coups adjacent.
     */
    private byte[][] getLignePosition(CoupP4Coup coup){
        byte[][] lignes = new byte[4][7];

        // Remplissage du tableau pour la ligne horizontale.
        lignes[0] = plateau[coup.getLigne()];

        // Remplissage du tableau pour la ligne verticale.
        for (int i=0; i < 7; i++) {
            lignes[1][i] = plateau[i][coup.getColonne()];
        }

        // Ligne diagonale partant de en haut à gauche jusqu'à en bas à droite.
        int lig = coup.getLigne();
        int col = coup.getColonne();
        while (lig >= 0 && col >= 0) { // Monte à gauche.
            lignes[2][lig] = plateau[lig--][col--];
        }
        lig = coup.getLigne() + 1;
        col = coup.getColonne() + 1;
        while (lig < 7 && col < 7) { // Descend à droite.
            lignes[2][lig] = plateau[lig++][col++];
        }

        // Ligne diagonale partant de en bas à gauche jusqu'à en haut à droite.
        lig = coup.getLigne();
        col = coup.getColonne();
        while (lig < 7 && col >= 0) { // Descend à gauche.
            lignes[3][lig] = plateau[lig++][col--] ;
        }
        lig = coup.getLigne() -1;
        col = coup.getColonne() +1;
        while (lig >= 0 && col < 7) { // Monte à droite.
            lignes[3][lig] = plateau[lig--][col++];
        }

        return lignes;
    }

    /**
     * Permet de verifier si un jeton placé aux coordonnées x, y est gagnant, c'est-à-dire s'il appartient à
     * une ligne de 4 pions ou plus consécutifs de même couleur.
     *
     * @param coup Objet représentant le coup.
     * @return Le numéro du gagnant si le jeton est gagnant, 0 sinon.
     */
    public byte verifierVictoireCase(CoupP4Coup coup) {
        // Vérification que l'emplacement du coup n'est pas un jeton vide.
        byte color = plateau[coup.getLigne()][coup.getColonne()];
        if (color == 0){
            return 0;
        }

        byte[][] lignes = getLignePosition(coup);
        // Vérification si 4 jetons sont alignés.
        for (byte[] direction : lignes) {
            int compteur = 0;
            for (byte jeton : direction) {
                if (jeton == color) {
                    compteur++;
                } else {
                    compteur = 0;
                }
                if (compteur == 4) {
                    return plateau[coup.getLigne()][coup.getColonne()];
                }
            }
        }
        return 0;
    }


    /**
     * Permet de savoir combien de jeton sont aligné autour d'un coup.
     * @param coup Objet représentant le coup.
     * @return Le nombre maximum de jetons qui sont alignés.
     */
    public int compterMaxJetonsAlignes(CoupP4Coup coup) {
        // Vérification que l'emplacement du coup n'est pas un jeton vide.
        byte color = plateau[coup.getLigne()][coup.getColonne()];
        if (color == 0){
            return 0;
        }

        byte[][] lignes = getLignePosition(coup);
        // Calcule du nombre maximum de jetons alignés.
        int maxLigne = 0;
        for (byte[] direction : lignes) {
            int compteur = 0;
            for (byte jeton : direction) {
                if (jeton == color) {
                    compteur++;
                } else {
                    compteur = 0;
                }
                if (compteur > maxLigne) {
                    maxLigne = compteur;
                }
            }
        }
        return maxLigne;
    }

    /**
     * Permet de vérifier si le plateau est plein.
     * @return true si le plateau est plein, sinon false.
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
     * Permet de retourner l'état du plateau sous la forme d'un tableau de byte.
     * @return un tableau représentant le plateau de puissance 4.
     */
    public byte[][] getPlateau(){
        return plateau;
    }

    /**
     * Permet d'obtenir une copie profonde du plateau.
     * @return Une copie profonde du plateau.
     */
    public PlateauP4 copie() {
        byte[][] plateauCopie = new byte[7][7];
        for (byte ligne = 0; ligne < plateau.length; ligne++){
            for (byte pion = 0; pion < plateau[ligne].length; pion++){
                plateauCopie[ligne][pion] = plateau[ligne][pion];
            }
        }
        return new PlateauP4(plateauCopie, dernierCoup.copie(), wasRotation, rotations, nbRotations.clone());
    }

    /**
     * <p>Permet de placer un jeton dans une colonne.</p>
     * <p>Parcours la colonne à partir du bas et place le jeton dès qu'une case est vide (0).</p>
     * @param coup Objet représentant le coup.
     * @throws FormatReponseInvalide Si le numéro de colonne demandé est inexistant.
     * @throws ColonnePleine Si la colonne demander est pleine.
     */
    public void placerJeton(CoupP4Coup coup) throws FormatReponseInvalide, ColonnePleine {
        if (coup.getColonne()<0 || coup.getColonne()>6){
            throw new FormatReponseInvalide("Veuillez entrer un entier compris entre 1 et 7.");
        }
        boolean estPlein = true;
        for (byte ligne = 6; ligne >= 0; ligne--) {
            if (plateau[ligne][coup.getColonne()] == 0) {
                plateau[ligne][coup.getColonne()] = coup.getJoueur();
                coup.setLigne(ligne);
                dernierCoup = coup;
                wasRotation = false;
                estPlein = false;
                break;
            }
        }
        if (estPlein){
            throw new ColonnePleine("Le jeton ne peut pas être placé.");
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
                    string += "⚫ ";
                } else if (contenu == 1) {
                    string += "🔴 ";
                }else {
                    string += "🟡 " ;
                }
            }
            string += "\n";
        }
        return string;
    }

    /**
     * Méthode qui permet d'activer ou désactiver la rotation.
     * @param i true ou false pour activer.
     */
    public void setRotations(boolean i){
        this.rotations= i;
    }

    /**
     * Getter pour savoir si la rotation est activé pour un joueur.
     * @param numJoueur Le numéro du joueur.
     * @return l'état d'activation de la rotation.
     */
    public boolean isRotations(int numJoueur) {
        if (!rotations) {
            return false;
        }
        return nbRotations[numJoueur] > 0;
    }

    /**
     * Fonction qui permet de faire tourner le plateau dans un sens.
     * @param coup Objet représentant le coup.
     */
    public void rotation(CoupP4Rotation coup) {
        nbRotations[coup.getJoueur()-1]--;
        wasRotation = true;
        if (coup.getSens()) {
            rotationHoraire();
        } else {
            rotationAntiHoraire();
        }
    }

    /** Fonction qui permet de faire tourner le plateau dans le sens horaire. */
    private void rotationHoraire() {
        byte[][] nouveau = new byte[7][7];
        for (int i = 0; i < 7; i++) { // la ligne à transformer en colonne
            int k = 6; // la profondeur à laquelle placer le prochain jeton
            for (int j = 6; j >= 0; j--) { // le parcours de la ligne
                if (plateau[i][j] != 0) {
                    nouveau[k--][6-i] = plateau[i][j];
                }
            }
        }
        plateau = nouveau;
    }

    /** Fonction qui permet de faire tourner le plateau dans le sens horaire. */
    private void rotationAntiHoraire() {
        byte[][] nouveau = new byte[7][7];
        for (int i = 0; i < 7; i++) { //la ligne à transformer en colonne
            int k = 6; // la profondeur à laquelle placer le prochain jeton
            for (int j = 0; j < 7; j++) { // le parcours de la ligne
                if (plateau[i][j] != 0) {
                    nouveau[k--][i] = plateau[i][j];
                }
            }
        }
        plateau = nouveau;
    }
}