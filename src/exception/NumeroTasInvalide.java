package exception;

/**
 * Exception levée lorsqu'un joueur entre un numéro de tas invalide (Lorsque l'on demande au joueur d'entrer un coup).
 */
public class NumeroTasInvalide extends Exception{
    /**
     * Constructeur de l'exception NumeroTasInvalide.
     * @param message Message de l'exception.
     */
    public NumeroTasInvalide(String message) {
        super(message);
    }
}
