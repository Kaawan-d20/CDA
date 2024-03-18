package test.modele.p4;

import exception.ColonnePleine;
import modele.p4.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestPlateauP4 {
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
    @Test
    public void testPlacerJeton() {
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
                        {0,0,0,1,0,0,0},
                };
        try {
            plateau.placerJeton((byte) 3, (byte) 1);
        } catch (ColonnePleine e){
            fail("pas normal");
        }
        assertEquals(resultatAttendu,plateau.getPlateau());

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
        } catch (ColonnePleine e){
            fail("pas normal");
        }
        assertEquals(resultatAttendu,plateau.getPlateau());
        try {
            plateau.placerJeton((byte) 3, (byte) 1);
            fail("pas normal");
        } catch (ColonnePleine ignored){}
    }

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
        try {
            for (int ligne = 0; ligne < resultatAttendu.length; ligne++) {
                for (int colonne = 0; colonne < resultatAttendu[0].length; colonne++) {
                    plateau.placerJeton((byte) colonne, resultatAttendu[ligne][colonne]);
                }
            }
        }catch (ColonnePleine e){
            fail("pas normal");
        }
        assertFalse(plateau.estPlein());

        try {
            plateau.placerJeton((byte) 1, (byte) 1);
            plateau.placerJeton((byte) 4, (byte) 2);
        } catch (ColonnePleine e) {
            fail("pas normal");
        }
        assertTrue(plateau.estPlein());
    }

    @Test
    public void testVerifierVictoireHorizontal(){
        Plateau plateau = new Plateau();
        plateau.reset();

        try {
            plateau.placerJeton((byte) 0, (byte) 1);
            plateau.placerJeton((byte) 1, (byte) 1);
            plateau.placerJeton((byte) 2, (byte) 1);

        } catch (ColonnePleine e) {
            fail("pas normal");
        }
        assertFalse(plateau.verifierVictoire());
        try {
            plateau.placerJeton((byte) 3, (byte) 1);
        } catch (ColonnePleine e) {
            fail("pas normal");
        }
        assertTrue(plateau.verifierVictoire());
    }

    @Test
    public void testVerifierVictoireVertical(){
        Plateau plateau = new Plateau();
        plateau.reset();

        try {
            for (int i = 0; i < 3; i++) {
            plateau.placerJeton((byte) 0, (byte) 1);
            }
        } catch (ColonnePleine e) {
            fail("pas normal");
        }
        assertFalse(plateau.verifierVictoire());
        try {
            plateau.placerJeton((byte) 0, (byte) 1);
        } catch (ColonnePleine e) {
            fail("pas normal");
        }
        assertTrue(plateau.verifierVictoire());
    }

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
        } catch (ColonnePleine e) {
            fail("pas normal");
        }
        assertFalse(plateau.verifierVictoire());
        try {
            plateau.placerJeton((byte) 3, (byte) 1);

        } catch (ColonnePleine e) {
            fail("pas normal");
        }
        assertTrue(plateau.verifierVictoire());
    }

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
        } catch (ColonnePleine e) {
            fail("pas normal");
        }
        assertFalse(plateau.verifierVictoire());
        try {
            plateau.placerJeton((byte) 3, (byte) 1);

        } catch (ColonnePleine e) {
            fail("pas normal");
        }
        assertTrue(plateau.verifierVictoire());
    }

    @Test
    public void testToString(){
        Plateau plateau = new Plateau();
        plateau.reset();

        try {
            plateau.placerJeton((byte) 0, (byte) 1);
            plateau.placerJeton((byte) 1, (byte) 2);
        } catch (ColonnePleine e) {
            fail("pas normal");
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
