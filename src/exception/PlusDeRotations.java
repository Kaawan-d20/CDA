package exception;

/**
 * Exception levée lorsqu'un joueur essaie d'effectuer une rotation alors qu'il n'en a plus.
 */
public class PlusDeRotations extends Exception{
    /**
     * Constructeur de l'exception PlusDeRotations.
     * @param message Message de l'exception.
     */
    public PlusDeRotations(String message) {
        super(message);
    }
}
