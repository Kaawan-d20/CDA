package modele.Nim;

import exception.NombreBatonnetsInvalide;
import exception.NumeroTasInvalide;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPlateauNim {
    @Test
    public void testGetPlateau(){
        int nombreTas = 3;
        Plateau plateau = new Plateau(nombreTas);
        plateau.reset();
        int[] resutlatAttendu = {1,3,5};
        assertArrayEquals(resutlatAttendu, plateau.getPlateau());
    }

    @Test
    public void testRetirerBatonnets(){
        int nombreTas = 3;
        int[] resutlatAttendu = {1,3,2};
        Plateau plateau = new Plateau(nombreTas);
        plateau.reset();
        try{
            plateau.retirerBatonnets(4,2);
            fail("Le tas sensé est inexistant");
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {}
        try{
            plateau.retirerBatonnets(3,6);
            fail("Le nombre de bâtonnets est sensé est insuffisant");
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {}
        try{
            plateau.retirerBatonnets(3,3);
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {
            fail("Le programme c'est pas sensé faire d'erreur");
        }
        assertArrayEquals(resutlatAttendu, plateau.getPlateau());
    }

    @Test
    public void testVerifierFin(){
        int nombreTas = 3;
        Plateau plateau = new Plateau(nombreTas);
        plateau.reset();
        try{
            plateau.retirerBatonnets(1,1);
            plateau.retirerBatonnets(2,3);
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {
            fail("Le programme c'est pas sensé faire d'erreur");
        }
        assertFalse(plateau.verifierFin());
        try{
            plateau.retirerBatonnets(3,5);
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {
            fail("Le programme c'est pas sensé faire d'erreur");
        }
        assertTrue(plateau.verifierFin());
    }

    @Test
    public void testToString(){
        int nombreTas = 3;
        Plateau plateau = new Plateau(nombreTas);
        plateau.reset();
        String resultatAttendu = "  |  \n ||| \n|||||\n";
        assertEquals(resultatAttendu, plateau.toString());
    }
}
