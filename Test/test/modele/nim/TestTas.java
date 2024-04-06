package test.modele.nim;

import exception.NombreBatonnetsInvalide;
import modele.Tas;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Classe contenant les tests de src/modele/Nim/Tas.java
 * @see Tas
 */
public class TestTas {
    /**
     * Test la Création d'un Tas et getTas()
     * @see Tas#getNombre()
     */
    @Test
    public void testGetNombre(){
        int nombreBatonnet = 3;
        Tas tas = new Tas(nombreBatonnet);
        assertEquals(nombreBatonnet, tas.getNombre());
    }
    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le fonctionnement normal</li>
     *     <li>Le cas où le nombre de bâtonnets est insuffisant</li>
     * </ul>
     * @see Tas#retirerBatonnet(int)
     */
    @Test
    public void testRetirerBatonnet(){
        int nombreBatonnet = 3;
        Tas tas = new Tas(nombreBatonnet);
        //Test le fonctionnement normal
        try{
            tas.retirerBatonnet(2);
        } catch (NombreBatonnetsInvalide ignored){
            fail("Erreur retirerBâtonnets");
        }
        assertEquals(1, tas.getNombre());
        //Test le cas où le nombre de bâtonnets est insuffisant
        try{
            tas.retirerBatonnet(2);
            fail("Le nombre de bâtonnets est sensé est insuffisant");
        } catch (NombreBatonnetsInvalide ignored){}
        assertEquals(1, tas.getNombre());
    }
    /**
     * <p>Test :</p>
     * <ul>
     *     <li>Le tas n'est pas vide</li>
     *     <li>Le tas est vide</li>
     * </ul>
     * @see Tas#estVide()
     */
    @Test
    public void testEstVide(){
        int nombreBatonnet = 3;
        Tas tas = new Tas(nombreBatonnet);
        //Test le tas n'est pas vide
        assertFalse(tas.estVide());

        try{
            tas.retirerBatonnet(3);
        } catch (NombreBatonnetsInvalide ignored){
            fail("Erreur retirerBâtonnets");
        }
        //Test le tas est vide
        assertTrue(tas.estVide());
    }
    /**
     * Test de résultat de toString
     * @see Tas#toString()
     */
    @Test
    public void testToString(){
        int nombreBatonnet = 3;
        Tas tas = new Tas(nombreBatonnet);
        assertEquals("|||", tas.toString());
    }

}
