package main;

import controleur.TestControleurJeuNim;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import modele.nim.TestPlateauNim;
import modele.nim.TestTas;
import modele.TestJoueur;
import modele.p4.TestPlateauP4;
import vue.TestIhm;

/**
 * Classe Test qui permet de lancer tous les tests
 */
public class Test {
    /**
     * Méthode qui permet de lancer tous les tests
     * @param args Rien
     */
    public static void main(String[] args) {
        Class<?>[] classes = new Class<?>[]{
                TestPlateauNim.class,
                TestTas.class,
                TestPlateauP4.class,
                TestJoueur.class,
                TestIhm.class,
                TestControleurJeuNim.class
        };
        boolean etat = true;
        for (Class<?> classe : classes) {
            Result result = JUnitCore.runClasses(classe);
            etat = etat && result.wasSuccessful();
        }
        if (etat){
            System.out.println("C'est bon tout fonctionne");
        }else {
            System.out.println("Mince ya un problème");
        }
    }
}