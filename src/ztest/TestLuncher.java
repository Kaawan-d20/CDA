package ztest;

public class TestLuncher {
    public static void main(String[] args) {
        TestIAP4 testIAP4 = new TestIAP4();
        if (testIAP4.test()){
            System.out.println("c'est bon");
        } else {
            System.out.println("c'est pas bon");
        }
    }
}
