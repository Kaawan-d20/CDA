package exception;
/**
 * Exception levée lorsqu'un joueur entre un nombre de tas invalide (Lors du setup de la partie)
 */
public class NombreTasInvalides extends Exception {
    /**
     * Constructeur de l'exception NombreTasInvalide
     * @param message message de l'exception
     */
    public NombreTasInvalides(String message) {
        super(message);
    }
}
