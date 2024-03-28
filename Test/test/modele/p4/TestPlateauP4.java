package test.modele.p4;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import modele.p4.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe contenant les tests de src/modele/p4/Plateau.java
 * @see modele.p4.Plateau
 */
public class TestPlateauP4 {
    /**
     * Test la Création d'un Plateau et getPlateau()
     * @see Plateau#getPlateau()
     */
    @Test
    public void testInit(){
        Plateau plateau = new Plateau();
        plateau.reset();
        byte[][] resultatAttendu =
                {
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                };
        assertEquals(resultatAttendu, plateau.getPlateau());
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le fonctionnement normal</li>
     *     <li>Le cas où la colonne choisie est pleine</li>
     * </ul>
     * @see Plateau#placerJeton(byte, byte)
     */
    @Test
    public void testPlacerJeton() {
        Plateau plateau = new Plateau();
        plateau.reset();
        //Test le fonctionnement normal
        byte[][] resultatAttendu =
                {
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0},
                        {0,0,0,1,0,0,0},
                };
        try {
            plateau.placerJeton((byte) 3, (byte) 1);
        } catch (ColonnePleine | FormatReponseInvalide e){
            fail("Erreur placerJeton");
        }
        assertEquals(resultatAttendu,plateau.getPlateau());

        //Remplissage de la colonne
        resultatAttendu =
                new byte[][]{
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                };
        try {
            for (int i = 0; i < 6; i++) {
                plateau.placerJeton((byte) 3, (byte) 1);
            }
        } catch (ColonnePleine | FormatReponseInvalide e){
            fail("Erreur placerJeton");
        }
        assertEquals(resultatAttendu,plateau.getPlateau());

        //Test le cas où la colonne choisie est pleine
        try {
            plateau.placerJeton((byte) 3, (byte) 1);
            fail("La colonne est sensé être pleine");
        } catch (ColonnePleine | FormatReponseInvalide ignored){}
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le cas où le plateau n'est pas plein</li>
     *     <li>Le cas où le plateau est plein</li>
     * </ul>
     * @see Plateau#estPlein()
     */
    @Test
    public void testEstPlein(){
        Plateau plateau = new Plateau();
        plateau.reset();
        byte[][] resultatAttendu =
                {
                        {2,0,2,1,0,1,2},
                        {1,1,1,2,1,2,1},
                        {2,1,2,1,2,1,2},
                        {2,2,1,1,1,2,1},
                        {1,1,2,1,2,1,2},
                        {2,2,2,2,1,2,1},
                        {1,1,2,1,2,1,2},
                };

        //Remplissage partiel du plateau
        try {
            for (int ligne = 0; ligne < resultatAttendu.length; ligne++) {
                for (int colonne = 0; colonne < resultatAttendu[0].length; colonne++) {
                    plateau.placerJeton((byte) colonne, resultatAttendu[ligne][colonne]);
                }
            }
        }catch (ColonnePleine | FormatReponseInvalide e){
            fail("Erreur placerJeton");
        }

        //Test le cas où le plateau n'est pas plein
        assertFalse(plateau.estPlein());

        //Remplissage total du plateau
        try {
            plateau.placerJeton((byte) 1, (byte) 1);
            plateau.placerJeton((byte) 4, (byte) 2);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test le cas où le plateau est plein
        assertTrue(plateau.estPlein());
    }

    /**
     * Test verifierVictoire en cas de victoire sur la ligne horizontale (-)
     * @see Plateau#verifierVictoire()
     */
    @Test
    public void testVerifierVictoireHorizontal(){
        Plateau plateau = new Plateau();
        plateau.reset();

        try {
            plateau.placerJeton((byte) 0, (byte) 1);
            plateau.placerJeton((byte) 1, (byte) 1);
            plateau.placerJeton((byte) 2, (byte) 1);

        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la non victoire
        assertFalse(plateau.verifierVictoire());

        try {
            plateau.placerJeton((byte) 3, (byte) 1);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la victoire
        assertTrue(plateau.verifierVictoire());
    }

    /**
     * Test verifierVictoire en cas de victoire sur la ligne verticale (|)
     * @see Plateau#verifierVictoire()
     */
    @Test
    public void testVerifierVictoireVertical(){
        Plateau plateau = new Plateau();
        plateau.reset();

        try {
            for (int i = 0; i < 3; i++) {
            plateau.placerJeton((byte) 0, (byte) 1);
            }
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la non victoire
        assertFalse(plateau.verifierVictoire());

        try {
            plateau.placerJeton((byte) 0, (byte) 1);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la victoire
        assertTrue(plateau.verifierVictoire());
    }

    /**
     * Test verifierVictoire en cas de victoire sur la ligne diagonale partant de en haut à gauche jusqu'à en bas à droite (\)
     * @see Plateau#verifierVictoire()
     */
    @Test
    public void testVerifierVictoireDiagonal1(){
        Plateau plateau = new Plateau();
        plateau.reset();

        try {
            plateau.placerJeton((byte) 0, (byte) 1);
            plateau.placerJeton((byte) 1, (byte) 2);
            plateau.placerJeton((byte) 1, (byte) 1);
            plateau.placerJeton((byte) 2, (byte) 2);
            plateau.placerJeton((byte) 2, (byte) 1);
            plateau.placerJeton((byte) 3, (byte) 2);
            plateau.placerJeton((byte) 2, (byte) 1);
            plateau.placerJeton((byte) 3, (byte) 2);
            plateau.placerJeton((byte) 4, (byte) 1);
            plateau.placerJeton((byte) 3, (byte) 2);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la non victoire
        assertFalse(plateau.verifierVictoire());

        try {
            plateau.placerJeton((byte) 3, (byte) 1);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la victoire
        assertTrue(plateau.verifierVictoire());
    }

    /**
     * Test verifierVictoire en cas de victoire sur ligne diagonale partant de en bas à gauche jusqu'à en haut à droite (/)
     * @see Plateau#verifierVictoire()
     */
    @Test
    public void testVerifierVictoireDiagonal2(){
        Plateau plateau = new Plateau();
        plateau.reset();

        try {
            plateau.placerJeton((byte) 6, (byte) 1);
            plateau.placerJeton((byte) 5, (byte) 2);
            plateau.placerJeton((byte) 5, (byte) 1);
            plateau.placerJeton((byte) 4, (byte) 2);
            plateau.placerJeton((byte) 4, (byte) 1);
            plateau.placerJeton((byte) 3, (byte) 2);
            plateau.placerJeton((byte) 4, (byte) 1);
            plateau.placerJeton((byte) 3, (byte) 2);
            plateau.placerJeton((byte) 2, (byte) 1);
            plateau.placerJeton((byte) 3, (byte) 2);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la non victoire
        assertFalse(plateau.verifierVictoire());

        try {
            plateau.placerJeton((byte) 3, (byte) 1);

        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }
        //Test la victoire
        assertTrue(plateau.verifierVictoire());
    }

    /**
     * Test de résultat de toString
     * @see Plateau#toString()
     */
    @Test
    public void testToString(){
        Plateau plateau = new Plateau();
        plateau.reset();

        try {
            plateau.placerJeton((byte) 0, (byte) 1);
            plateau.placerJeton((byte) 1, (byte) 2);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        String reponseAttendu = "1\uFE0F⃣ 2\uFE0F⃣ 3\uFE0F⃣ 4\uFE0F⃣ 5\uFE0F⃣ 6\uFE0F⃣ 7\uFE0F⃣\n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "⚫ ⚫ ⚫ ⚫ ⚫ ⚫ ⚫ \n" +
                "\uD83D\uDD34 \uD83D\uDFE1 ⚫ ⚫ ⚫ ⚫ ⚫ \n";

        assertEquals(reponseAttendu, plateau.toString());
    }
}
