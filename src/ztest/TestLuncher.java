package ztest;

import exception.JeuInvalideException;

public class TestLuncher {
    public static void main(String[] args) {
        TestIAP4 testIAP4 = new TestIAP4();
        if (testIAP4.test()){
            System.out.println("c'est bon pour P4");
        } else {
            System.out.println("c'est pas bon pour P4");
        }
        try {
            TestIANim testIANim = new TestIANim();
            if (testIANim.test()){
                System.out.println("c'est bon pour Nim");
            } else {
                System.out.println("c'est pas bon pour Nim");
            }
        } catch (JeuInvalideException e) {
            System.out.println("Problème l'exception de l'IA est levée");
        }
    }
}
