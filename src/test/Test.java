package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.modele.Nim.TestPlateauNim;
import test.modele.Nim.TestTas;
import test.modele.TestJoueur;
import test.modele.p4.TestPlateauP4;
import test.vue.TestIhm;

public class Test {
    public static void main(String[] args) {
        Class<?>[] classes = new Class<?>[]{
                TestPlateauNim.class,
                TestTas.class,
                TestPlateauP4.class,
                TestJoueur.class,

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
        System.out.println(etat);
    }
}