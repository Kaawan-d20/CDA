package test.modele.p4;

import exception.ColonnePleine;
import exception.FormatReponseInvalide;
import modele.PlateauP4;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe contenant les tests de src/modele/p4/PlateauNim.java
 * @see PlateauP4
 */
public class TestPlateauP4 {
    /**
     * Test la Création d'un PlateauNim et getPlateau()
     * @see PlateauP4#getPlateau()
     */
    @Test
    public void testInit(){
        PlateauP4 plateauP4 = new PlateauP4();
        plateauP4.reset();
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
        assertEquals(resultatAttendu, plateauP4.getPlateau());
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le fonctionnement normal</li>
     *     <li>Le cas où la colonne choisie est pleine</li>
     * </ul>
     * @see PlateauP4#placerJeton(byte, byte)
     */
    @Test
    public void testPlacerJeton() {
        PlateauP4 plateauP4 = new PlateauP4();
        plateauP4.reset();
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
            plateauP4.placerJeton((byte) 3, (byte) 1);
        } catch (ColonnePleine | FormatReponseInvalide e){
            fail("Erreur placerJeton");
        }
        assertEquals(resultatAttendu, plateauP4.getPlateau());

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
                plateauP4.placerJeton((byte) 3, (byte) 1);
            }
        } catch (ColonnePleine | FormatReponseInvalide e){
            fail("Erreur placerJeton");
        }
        assertEquals(resultatAttendu, plateauP4.getPlateau());

        //Test le cas où la colonne choisie est pleine
        try {
            plateauP4.placerJeton((byte) 3, (byte) 1);
            fail("La colonne est sensé être pleine");
        } catch (ColonnePleine | FormatReponseInvalide ignored){}
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le cas où le plateau n'est pas plein</li>
     *     <li>Le cas où le plateau est plein</li>
     * </ul>
     * @see PlateauP4#estPlein()
     */
    @Test
    public void testEstPlein(){
        PlateauP4 plateauP4 = new PlateauP4();
        plateauP4.reset();
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

        //Remplissage partiel du plateauP4
        try {
            for (int ligne = 0; ligne < resultatAttendu.length; ligne++) {
                for (int colonne = 0; colonne < resultatAttendu[0].length; colonne++) {
                    plateauP4.placerJeton((byte) colonne, resultatAttendu[ligne][colonne]);
                }
            }
        }catch (ColonnePleine | FormatReponseInvalide e){
            fail("Erreur placerJeton");
        }

        //Test le cas où le plateauP4 n'est pas plein
        assertFalse(plateauP4.estPlein());

        //Remplissage total du plateauP4
        try {
            plateauP4.placerJeton((byte) 1, (byte) 1);
            plateauP4.placerJeton((byte) 4, (byte) 2);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test le cas où le plateauP4 est plein
        assertTrue(plateauP4.estPlein());
    }

    /**
     * Test verifierVictoire en cas de victoire sur la ligne horizontale (-)
     * @see PlateauP4#verifierVictoire()
     */
    @Test
    public void testVerifierVictoireHorizontal(){
        PlateauP4 plateauP4 = new PlateauP4();
        plateauP4.reset();

        try {
            plateauP4.placerJeton((byte) 0, (byte) 1);
            plateauP4.placerJeton((byte) 1, (byte) 1);
            plateauP4.placerJeton((byte) 2, (byte) 1);

        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la non victoire
        assertFalse(plateauP4.verifierVictoire());

        try {
            plateauP4.placerJeton((byte) 3, (byte) 1);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la victoire
        assertTrue(plateauP4.verifierVictoire());
    }

    /**
     * Test verifierVictoire en cas de victoire sur la ligne verticale (|)
     * @see PlateauP4#verifierVictoire()
     */
    @Test
    public void testVerifierVictoireVertical(){
        PlateauP4 plateauP4 = new PlateauP4();
        plateauP4.reset();

        try {
            for (int i = 0; i < 3; i++) {
            plateauP4.placerJeton((byte) 0, (byte) 1);
            }
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la non victoire
        assertFalse(plateauP4.verifierVictoire());

        try {
            plateauP4.placerJeton((byte) 0, (byte) 1);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la victoire
        assertTrue(plateauP4.verifierVictoire());
    }

    /**
     * Test verifierVictoire en cas de victoire sur la ligne diagonale partant de en haut à gauche jusqu'à en bas à droite (\)
     * @see PlateauP4#verifierVictoire()
     */
    @Test
    public void testVerifierVictoireDiagonal1(){
        PlateauP4 plateauP4 = new PlateauP4();
        plateauP4.reset();

        try {
            plateauP4.placerJeton((byte) 0, (byte) 1);
            plateauP4.placerJeton((byte) 1, (byte) 2);
            plateauP4.placerJeton((byte) 1, (byte) 1);
            plateauP4.placerJeton((byte) 2, (byte) 2);
            plateauP4.placerJeton((byte) 2, (byte) 1);
            plateauP4.placerJeton((byte) 3, (byte) 2);
            plateauP4.placerJeton((byte) 2, (byte) 1);
            plateauP4.placerJeton((byte) 3, (byte) 2);
            plateauP4.placerJeton((byte) 4, (byte) 1);
            plateauP4.placerJeton((byte) 3, (byte) 2);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la non victoire
        assertFalse(plateauP4.verifierVictoire());

        try {
            plateauP4.placerJeton((byte) 3, (byte) 1);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la victoire
        assertTrue(plateauP4.verifierVictoire());
    }

    /**
     * Test verifierVictoire en cas de victoire sur ligne diagonale partant de en bas à gauche jusqu'à en haut à droite (/)
     * @see PlateauP4#verifierVictoire()
     */
    @Test
    public void testVerifierVictoireDiagonal2(){
        PlateauP4 plateauP4 = new PlateauP4();
        plateauP4.reset();

        try {
            plateauP4.placerJeton((byte) 6, (byte) 1);
            plateauP4.placerJeton((byte) 5, (byte) 2);
            plateauP4.placerJeton((byte) 5, (byte) 1);
            plateauP4.placerJeton((byte) 4, (byte) 2);
            plateauP4.placerJeton((byte) 4, (byte) 1);
            plateauP4.placerJeton((byte) 3, (byte) 2);
            plateauP4.placerJeton((byte) 4, (byte) 1);
            plateauP4.placerJeton((byte) 3, (byte) 2);
            plateauP4.placerJeton((byte) 2, (byte) 1);
            plateauP4.placerJeton((byte) 3, (byte) 2);
        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }

        //Test la non victoire
        assertFalse(plateauP4.verifierVictoire());

        try {
            plateauP4.placerJeton((byte) 3, (byte) 1);

        } catch (ColonnePleine | FormatReponseInvalide e) {
            fail("Erreur placerJeton");
        }
        //Test la victoire
        assertTrue(plateauP4.verifierVictoire());
    }

    /**
     * Test de résultat de toString
     * @see PlateauP4#toString()
     */
    @Test
    public void testToString(){
        PlateauP4 plateauP4 = new PlateauP4();
        plateauP4.reset();

        try {
            plateauP4.placerJeton((byte) 0, (byte) 1);
            plateauP4.placerJeton((byte) 1, (byte) 2);
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

        assertEquals(reponseAttendu, plateauP4.toString());
    }
}
