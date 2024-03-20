package test.modele.nim;

import exception.NombreBatonnetsInvalide;
import exception.NumeroTasInvalide;
import modele.nim.Plateau;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe contenant les tests de src/modele/Nim/Plateau.java
 * @see modele.nim.Plateau
 */
public class TestPlateauNim {
    /**
     * Test la Création d'un Plateau et getPlateau()
     * @see Plateau#getPlateau()
     */
    @Test
    public void testGetPlateau(){
        int nombreTas = 3;
        Plateau plateau = new Plateau(nombreTas);
        plateau.reset();
        int[] resutlatAttendu = {1,3,5};
        assertArrayEquals(resutlatAttendu, plateau.getPlateau());
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le cas où le tas est inexistant</li>
     *     <li>Le cas où le nombre de bâtonnets est insuffisant</li>
     *     <li>Le fonctionnement normal</li>
     * </ul>
     * @see Plateau#retirerBatonnets(int, int)
     */
    @Test
    public void testRetirerBatonnets(){
        int nombreTas = 3;
        int[] resutlatAttendu = {1,3,2};
        Plateau plateau = new Plateau(nombreTas);
        plateau.reset();

        //Test le cas où le tas est inexistant
        try{
            plateau.retirerBatonnets(4,2);
            fail("Le tas sensé est inexistant");
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {}

        //Test le cas où le nombre de bâtonnets est insuffisant
        try{
            plateau.retirerBatonnets(3,6);
            fail("Le nombre de bâtonnets est sensé est insuffisant");
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {}

        //Test le fonctionnement normal
        try{
            plateau.retirerBatonnets(3,3);
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {
            fail("Erreur retirerBâtonnets");
        }
        assertArrayEquals(resutlatAttendu, plateau.getPlateau());
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le cas où le plateau n'est pas vide</li>
     *     <li>Le cas où le plateau est vide</li>
     * </ul>
     * @see Plateau#verifierFin()
     */
    @Test
    public void testVerifierFin(){
        int nombreTas = 3;
        Plateau plateau = new Plateau(nombreTas);
        plateau.reset();

        try{
            plateau.retirerBatonnets(1,1);
            plateau.retirerBatonnets(2,3);
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {
            fail("Erreur retirerBâtonnets");
        }
        //Test le cas où le plateau n'est pas vide
        assertFalse(plateau.verifierFin());

        try{
            plateau.retirerBatonnets(3,5);
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {
            fail("Erreur retirerBâtonnets");
        }
        //Test le cas où le plateau est vide
        assertTrue(plateau.verifierFin());
    }

    /**
     * Test de résultat de toString
     * @see Plateau#toString()
     */
    @Test
    public void testToString(){
        int nombreTas = 3;
        Plateau plateau = new Plateau(nombreTas);
        plateau.reset();
        String resultatAttendu = "  |  \n ||| \n|||||\n";
        assertEquals(resultatAttendu, plateau.toString());
    }
}
