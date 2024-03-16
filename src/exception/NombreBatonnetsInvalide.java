package exception;

/**
 * Exception levée lorsqu'un joueur entre un nombre invalide de Bâtonnets (Lorsque l'on demande au joueur d'entrer un coup)
 */
public class NombreBatonnetsInvalide extends Exception{
    /**
     * Constructeur de l'exception NombreBatonnetsInvalide
     * @param message message de l'exception
     */
    public NombreBatonnetsInvalide(String message) {
        super(message);
    }
}
