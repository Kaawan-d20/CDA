package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.modele.Nim.TestPlateauNim;
import test.modele.Nim.TestTas;
import test.modele.TestJoueur;
import test.vue.TestIhm;

public class Test {
    public static void main(String[] args) {
        Class<?>[] classes = new Class<?>[]{
                TestPlateauNim.class,
                TestTas.class,
                TestJoueur.class,
                TestIhm.class
        };

        for (Class<?> classe : classes) {
            Result result = JUnitCore.runClasses(classe);

            if (result.wasSuccessful()) {
                System.out.println("GG tout fonctionne");
            } else {
                System.out.println("RIP CPT");
            }

            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
}