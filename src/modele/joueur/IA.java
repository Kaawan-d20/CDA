package modele.joueur;

/**
 * Classe abstraite représentant les IA.
 */
public abstract class IA extends Joueur{
    /** Appelle le constructeur de la super classe et met isHuman à false. */
    protected IA() {
        super("IA");
        isHuman = false;
    }
}
