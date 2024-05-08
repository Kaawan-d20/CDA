package ztest;

/**
 * Classe permettant de lancer les tests.
 */
public class TestLuncher {
    /** Méthode permettant de lancer les tests. */
    public static void main(String[] args) {
        // Test pour le jeu de Puissance 4.
        TestIAP4 testIAP4 = new TestIAP4();
        if (testIAP4.test()){
            System.out.println("c'est bon pour P4.");
        } else {
            System.out.println("c'est pas bon pour P4.");
        }

        // Test pour le jeu de Nim.
        TestIANim testIANim = new TestIANim();
        if (testIANim.test()){
            System.out.println("c'est bon pour Nim.");
        } else {
            System.out.println("c'est pas bon pour Nim.");
        }
    }
}
