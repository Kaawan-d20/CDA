package modele;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe contenant les tests de src/modele/Joueur.java
 * @see modele.Joueur
 */
public class TestJoueur {
    /**
     * Test la Création d'un Joueur et getNom()
     * @see Joueur#getNom()
     */
    @Test
    public void testGetNom() {
        String nom = "main.Test";
        Joueur joueur = new Joueur(nom);
        assertEquals(nom, joueur.getNom());
    }

    /**
     * Test getNbVictoires()
     * @see Joueur#getNbVictoires()
     */
    @Test
    public void testGetNbVictoires() {
        Joueur joueur = new Joueur("main.Test");
        assertEquals(0, joueur.getNbVictoires());
    }
    /**
     * Test incrementVictoires()
     * @see Joueur#incrementVictoires()
     */
    @Test
    public void testIncrementVictoires() {
        Joueur joueur = new Joueur("main.Test");
        joueur.incrementVictoires();
        assertEquals(1, joueur.getNbVictoires());
    }
    /**
     * Test compareTo()
     * @see Joueur#compareTo (Joueur)
     */
    @Test
    public void testCompareTo() {
        Joueur joueur1 = new Joueur("Test1");
        Joueur joueur2 = new Joueur("Test2");
        joueur1.incrementVictoires();
        assertTrue(joueur1.compareTo(joueur2) > 0);
        joueur2.incrementVictoires();
        assertTrue(joueur1.compareTo(joueur2) == 0);
        joueur2.incrementVictoires();
        assertTrue(joueur1.compareTo(joueur2) < 0);

    }
}