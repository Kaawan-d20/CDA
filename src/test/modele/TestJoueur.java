package test.modele;

import modele.Joueur;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestJoueur {
    @Test
    public void testGetNom() {
        String nom = "Test";
        Joueur joueur = new Joueur(nom);
        assertEquals(nom, joueur.getNom());
    }

    @Test
    public void testGetNbVictoires() {
        Joueur joueur = new Joueur("Test");
        assertEquals(0, joueur.getNbVictoires());
    }

    @Test
    public void testIncrementVictoires() {
        Joueur joueur = new Joueur("Test");
        joueur.incrementVictoires();
        assertEquals(1, joueur.getNbVictoires());
    }

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