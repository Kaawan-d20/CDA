package exception;
/**
 * Exception levée lorsqu'un joueur entre un coup invalide, (c'est-à-dire n'étant pas au format 'm n')
 */
public class FormatReponseInvalide extends Exception{
    /**
     * Constructeur de l'exception FormatReponseInvalide
     * @param message message de l'exception
     */
    public FormatReponseInvalide(String message) {
        super(message);
    }
}
