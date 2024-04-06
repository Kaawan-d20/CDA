package test.modele.nim;

import exception.NombreBatonnetsInvalide;
import exception.NumeroTasInvalide;
import modele.PlateauNim;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe contenant les tests de src/modele/Nim/PlateauNim.java
 * @see PlateauNim
 */
public class TestPlateauNim {
    /**
     * Test la Création d'un PlateauNim et getPlateau()
     * @see PlateauNim#getPlateau()
     */
    @Test
    public void testGetPlateau(){
        int nombreTas = 3;
        PlateauNim plateauNim = new PlateauNim(nombreTas);
        plateauNim.reset();
        int[] resutlatAttendu = {1,3,5};
        assertArrayEquals(resutlatAttendu, plateauNim.getPlateau());
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le cas où le tas est inexistant</li>
     *     <li>Le cas où le nombre de bâtonnets est insuffisant</li>
     *     <li>Le fonctionnement normal</li>
     * </ul>
     * @see PlateauNim#retirerBatonnets(int, int)
     */
    @Test
    public void testRetirerBatonnets(){
        int nombreTas = 3;
        int[] resutlatAttendu = {1,3,2};
        PlateauNim plateauNim = new PlateauNim(nombreTas);
        plateauNim.reset();

        //Test le cas où le tas est inexistant
        try{
            plateauNim.retirerBatonnets(4,2);
            fail("Le tas sensé est inexistant");
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {}

        //Test le cas où le nombre de bâtonnets est insuffisant
        try{
            plateauNim.retirerBatonnets(3,6);
            fail("Le nombre de bâtonnets est sensé est insuffisant");
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {}

        //Test le fonctionnement normal
        try{
            plateauNim.retirerBatonnets(3,3);
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {
            fail("Erreur retirerBâtonnets");
        }
        assertArrayEquals(resutlatAttendu, plateauNim.getPlateau());
    }

    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le cas où le plateau n'est pas vide</li>
     *     <li>Le cas où le plateau est vide</li>
     * </ul>
     * @see PlateauNim#verifierFin()
     */
    @Test
    public void testVerifierFin(){
        int nombreTas = 3;
        PlateauNim plateauNim = new PlateauNim(nombreTas);
        plateauNim.reset();

        try{
            plateauNim.retirerBatonnets(1,1);
            plateauNim.retirerBatonnets(2,3);
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {
            fail("Erreur retirerBâtonnets");
        }
        //Test le cas où le plateauNim n'est pas vide
        assertFalse(plateauNim.verifierFin());

        try{
            plateauNim.retirerBatonnets(3,5);
        } catch (NombreBatonnetsInvalide | NumeroTasInvalide ignored) {
            fail("Erreur retirerBâtonnets");
        }
        //Test le cas où le plateauNim est vide
        assertTrue(plateauNim.verifierFin());
    }

    /**
     * Test de résultat de toString
     * @see PlateauNim#toString()
     */
    @Test
    public void testToString(){
        int nombreTas = 3;
        PlateauNim plateauNim = new PlateauNim(nombreTas);
        plateauNim.reset();
        String resultatAttendu = "  |  \n ||| \n|||||\n";
        assertEquals(resultatAttendu, plateauNim.toString());
    }
}
