package test.main;

import test.controleur.TestControleurJeuNim;
import test.controleur.TestControleurP4;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import test.modele.nim.TestPlateauNim;
import test.modele.nim.TestTas;
import test.modele.TestJoueur;
import test.modele.p4.TestPlateauP4;
import test.vue.TestIhm;

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
                TestControleurJeuNim.class,
                TestControleurP4.class
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