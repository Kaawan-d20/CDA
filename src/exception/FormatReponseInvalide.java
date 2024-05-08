package exception;
/**
 * Exception levée lorsqu'un joueur entre un coup invalide.
 */
public class FormatReponseInvalide extends Exception{
    /**
     * Constructeur de l'exception FormatReponseInvalide.
     * @param message Message de l'exception.
     */
    public FormatReponseInvalide(String message) {
        super(message);
    }
}
