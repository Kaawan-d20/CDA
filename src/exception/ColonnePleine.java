package exception;
/**
 * Exception levée lorsqu'un joueur souhaite mettre un jeton dans une colonne pleine.
 */
public class ColonnePleine extends Exception{
    /**
     * Constructeur de l'exception ColonnePleine.
     * @param message Message de l'exception.
     */
    public ColonnePleine(String message) {
        super(message);
    }
}
