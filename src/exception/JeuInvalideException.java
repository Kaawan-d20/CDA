package exception;
/**
 * Exception levée lorsqu'un joueur souhaite jouer à un jeu inexistant
 */
public class JeuInvalideException extends Exception{
    /**
     * Constructeur de l'exception JeuInvalideException
     * @param message message de l'exception
     */
    public JeuInvalideException(String message) {
        super(message);
    }
}
